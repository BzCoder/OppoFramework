package com.android.server.face;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManagerNative;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.PendingIntent;
import android.app.SynchronousUserSwitchObserver;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.graphics.Rect;
import android.hardware.display.DisplayManagerInternal;
import android.hardware.face.ClientMode;
import android.hardware.face.CommandResult;
import android.hardware.face.FaceFeature;
import android.hardware.face.FaceInfoWrapper;
import android.hardware.face.FaceInternal;
import android.hardware.face.IFaceDaemon;
import android.hardware.face.IFaceService.Stub;
import android.hardware.face.IFaceServiceLockoutResetCallback;
import android.hardware.face.IFaceServiceReceiver;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Binder;
import android.os.DeadObjectException;
import android.os.Environment;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.os.SELinux;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.secrecy.SecrecyManager;
import android.secrecy.SecrecyManager.SecrecyStateListener;
import android.view.Surface;
import com.android.internal.widget.LockPatternUtils;
import com.android.server.LocationManagerService;
import com.android.server.ServiceThread;
import com.android.server.SystemService;
import com.android.server.Watchdog;
import com.android.server.Watchdog.Monitor;
import com.android.server.face.FaceSwitchHelper.ISwitchUpdateListener;
import com.android.server.face.dcs.DcsFaceUtil;
import com.android.server.face.health.HealthMonitor;
import com.android.server.face.health.HealthState;
import com.android.server.face.keyguard.KeyguardPolicy;
import com.android.server.face.power.FacePowerManager;
import com.android.server.face.power.FacePowerManager.IPowerCallback;
import com.android.server.face.sensetime.SenseTimeSdkManager;
import com.android.server.face.sensetime.faceapi.model.FaceOrientation;
import com.android.server.face.setting.FaceSettings;
import com.android.server.face.setting.Ilistener;
import com.android.server.face.setting.UnlockSettingMonitor;
import com.android.server.face.test.oppo.util.ImageUtil;
import com.android.server.face.tool.ExHandler;
import com.android.server.face.utils.LogUtil;
import com.android.server.face.utils.TimeUtils;
import com.android.server.oppo.IElsaManager;
import com.android.server.secrecy.policy.DecryptTool;
import com.sensetime.faceapi.model.FaceInfo;
import java.io.File;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ReSugarCode
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
    	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ExtractFieldInit.checkStaticFieldsInit(ExtractFieldInit.java:58)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class FaceService extends SystemService implements IRecognitionCallback, IPowerCallback, ISwitchUpdateListener, DeathRecipient, Monitor {
    private static final String ACTION_LOCKOUT_RESET = "com.android.server.face.ACTION_LOCKOUT_RESET";
    private static final String CAMERA_SERVICE_BINDER_NAME = "media.camera";
    public static boolean DEBUG = false;
    public static boolean DEBUG_PERF = false;
    private static final int ENROLLMENT_TIMEOUT_MS = 60000;
    private static final String FACED = "android.hardware.face.IFaceDaemon";
    private static final String FACE_DATA_DIR = "facedata";
    private static final long FAIL_LOCKOUT_TIMEOUT_MS = 30000;
    public static final String FEATURE_FACE = "oppo.hardware.face.support";
    public static final boolean IS_REALEASE_VERSION = false;
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final int MSG_APP_SECRECY_STATE_CHANGED = 12;
    private static final int MSG_DAEMON_CALLBACK = 11;
    private static final int MSG_UNBLOCK_SCREEN_ON = 13;
    private static final int MSG_USER_SWITCHING = 10;
    private static final long MS_PER_SEC = 1000;
    private static final String PROP_FACE_POWER_BLOCK = "persist.face.power.block";
    public static final String TAG = "FaceService.Main";
    public static final String TAG_NAME = "FaceService";
    public static final long UNBLOCK_SCREEN_ON_DELAY = 48;
    public static boolean mImageOn;
    private final AlarmManager mAlarmManager;
    private final AppOpsManager mAppOps;
    private ClientMonitor mAuthClient;
    private final Object mClientLock;
    private ClientMonitor mCommandClient;
    private final Context mContext;
    private long mCurrentAuthenticatorId;
    private int mCurrentUserId;
    private IFaceDaemon mDaemon;
    private final ConcurrentLinkedDeque<Runnable> mDaemonCallbackQueue;
    private IFaceDaemon mDaemonStub;
    private IFaceDaemon mDaemonWrapper;
    private final DcsFaceUtil mDcsFaceUtil;
    private final Runnable mDecreaseFailedAttemptsRunnable;
    private DisplayManagerInternal mDisplayManagerInternal;
    private ClientMonitor mEnrollClient;
    private int mEnrollmentTotalTimes;
    private final boolean mFaceEnabled;
    private final boolean mFaceSupport;
    private final FaceSwitchHelper mFaceSwitchHelper;
    private boolean mFaceUnlockAutoWhenScreenOn;
    private boolean mFaceUnlockEnabled;
    private final FaceUtils mFaceUtils;
    private int mFailedAttempts;
    private final FingerprintManager mFingerprintManager;
    private long mHalDeviceId;
    private ExHandler mHandler;
    private final HealthMonitor mHealthMonitor;
    private boolean mIsEncryptApp;
    private boolean mIsFirstUnlockedInPasswordOnlyMode;
    private boolean mIsImageSaveEnable;
    private boolean mIsLogOpened;
    private int mKeyguardAuthType;
    private final String mKeyguardPackage;
    private final KeyguardPolicy mKeyguardPolicy;
    private final Ilistener mListener;
    private final LockPatternUtils mLockPatternUtils;
    private long mLockoutDeadline;
    private final ArrayList<FaceServiceLockoutResetMonitor> mLockoutMonitors;
    private final BroadcastReceiver mLockoutReceiver;
    private final PowerManager mPowerManager;
    private ClientMonitor mRemoveClient;
    private final Runnable mResetFailedAttemptsRunnable;
    private SecrecyManager mSecrecyManager;
    private SecrecyStateListener mSecrecyStateListener;
    private final ServiceThread mServiceThread;
    private boolean mSupportPowerBlock;
    private boolean mSystemReady;
    private final UnlockSettingMonitor mUnlockSettingMonitor;
    private final UserManager mUserManager;

    private class ClientMonitor implements DeathRecipient {
        ClientMode mClientMode = ClientMode.NONE;
        private final int mGroupId;
        boolean mIsKeyguard;
        String mPackageName;
        IFaceServiceReceiver mReceiver;
        boolean mRestricted;
        IBinder mToken;
        int mUserId;

        public ClientMonitor(IBinder token, IFaceServiceReceiver receiver, int userId, int groupId, boolean restricted, String pkgName, ClientMode clientMode) {
            this.mToken = token;
            this.mReceiver = receiver;
            this.mUserId = userId;
            this.mGroupId = groupId;
            this.mRestricted = restricted;
            this.mPackageName = pkgName;
            this.mClientMode = clientMode;
            this.mIsKeyguard = FaceService.this.mKeyguardPackage.equals(this.mPackageName);
            try {
                this.mToken.linkToDeath(this, 0);
            } catch (RemoteException e) {
                LogUtil.w(FaceService.TAG, "caught remote exception in linkToDeath: ", e);
            }
        }

        public void destroy() {
            synchronized (this) {
                if (this.mToken != null) {
                    try {
                        this.mToken.unlinkToDeath(this, 0);
                    } catch (NoSuchElementException e) {
                        LogUtil.e(FaceService.TAG, "destroy(): " + this + ":", new Exception("here"));
                    }
                    this.mToken = null;
                }
            }
            this.mReceiver = null;
            return;
        }

        public void binderDied() {
            FaceService.this.mDaemonCallbackQueue.addFirst(new Runnable() {
                public void run() {
                    LogUtil.d(FaceService.TAG, "binderDied, mPackageName = " + ClientMonitor.this.mPackageName);
                    ClientMonitor.this.mToken = null;
                    FaceService.this.updateScreenOnBlockedState(false, true, 48);
                    FaceService.this.removeClient(ClientMonitor.this, false);
                    ClientMonitor.this.mReceiver = null;
                }
            });
            if (!FaceService.this.mHandler.hasMessages(11)) {
                FaceService.this.mHandler.sendMessageAtFrontOfQueue(FaceService.this.mHandler.obtainMessage(11));
            }
        }

        protected void finalize() throws Throwable {
            try {
                if (this.mToken != null) {
                    LogUtil.w(FaceService.TAG, "removing leaked reference: " + this.mToken);
                    FaceService.this.removeClient(this, false);
                }
                super.finalize();
            } catch (Throwable th) {
                super.finalize();
            }
        }

        private boolean sendRemoved(int faceId, int groupId) {
            boolean z = true;
            if (this.mReceiver == null) {
                return true;
            }
            try {
                this.mReceiver.onRemoved(FaceService.this.mHalDeviceId, faceId, groupId);
                if (faceId != 0) {
                    z = false;
                }
                return z;
            } catch (RemoteException e) {
                LogUtil.w(FaceService.TAG, "Failed to notify Removed:", e);
                return false;
            }
        }

        private boolean sendEnrollResult(int faceFeatureId, int groupId, int remaining) {
            if (this.mReceiver == null) {
                return true;
            }
            try {
                this.mReceiver.onEnrollResult(FaceService.this.mHalDeviceId, faceFeatureId, groupId, remaining);
                return remaining == 0;
            } catch (RemoteException e) {
                LogUtil.w(FaceService.TAG, "Failed to notify EnrollResult:", e);
                return true;
            }
        }

        private boolean sendAuthenticated(int faceFeatureId, int groupId) {
            boolean result = false;
            boolean authenticated = faceFeatureId != 0;
            LogUtil.d(FaceService.TAG, "sendAuthenticated (faceFeatureId = " + faceFeatureId + ", groupId  = " + groupId + ")");
            if (this.mReceiver == null) {
                result = true;
            } else if (authenticated) {
                LogUtil.v(FaceService.TAG, "onAuthenticated(mPackageName=" + this.mPackageName + ", id=" + faceFeatureId + ", gp=" + groupId + ")");
                this.mReceiver.onAuthenticationSucceeded(FaceService.this.mHalDeviceId, !this.mRestricted ? new FaceFeature(IElsaManager.EMPTY_PACKAGE, groupId, faceFeatureId, FaceService.this.mHalDeviceId) : null, groupId);
            } else {
                try {
                    this.mReceiver.onAuthenticationFailed(FaceService.this.mHalDeviceId);
                } catch (RemoteException e) {
                    LogUtil.w(FaceService.TAG, "Failed to notify Authenticated:", e);
                    result = true;
                }
            }
            if (faceFeatureId == 0) {
                return result | FaceService.this.handleFailedAttempt(this);
            }
            result |= true;
            FaceService.this.resetFailedAttempts();
            return result;
        }

        private boolean sendAcquired(int acquiredInfo) {
            if (this.mReceiver == null) {
                return true;
            }
            try {
                this.mReceiver.onAcquired(FaceService.this.mHalDeviceId, acquiredInfo);
                if (acquiredInfo == 0) {
                    FaceService.this.userActivity();
                }
                return false;
            } catch (RemoteException e) {
                LogUtil.w(FaceService.TAG, "Failed to invoke sendAcquired:", e);
                if (acquiredInfo == 0) {
                    FaceService.this.userActivity();
                }
                return true;
            } catch (Throwable th) {
                if (acquiredInfo == 0) {
                    FaceService.this.userActivity();
                }
                throw th;
            }
        }

        private boolean sendProcess(int progressInfo) {
            if (this.mReceiver == null) {
                return true;
            }
            try {
                this.mReceiver.onProgressChanged(FaceService.this.mHalDeviceId, progressInfo);
                return false;
            } catch (RemoteException e) {
                LogUtil.w(FaceService.TAG, "Failed to invoke sendProcess:", e);
                return true;
            }
        }

        private boolean sendError(int error) {
            LogUtil.d(FaceService.TAG, "sendError = " + error);
            if (this.mReceiver != null) {
                try {
                    this.mReceiver.onError(FaceService.this.mHalDeviceId, error);
                } catch (RemoteException e) {
                    LogUtil.w(FaceService.TAG, "Failed to invoke sendError:", e);
                }
            }
            return true;
        }

        private void sendCommandResult(CommandResult info) {
            LogUtil.w(FaceService.TAG, "sendCommandResult");
            if (this.mReceiver != null) {
                try {
                    this.mReceiver.onCommandResult(info);
                } catch (RemoteException e) {
                    LogUtil.w(FaceService.TAG, "Failed to invoke sendCommandResult.", e);
                }
            }
        }

        public boolean isKeyguard() {
            return this.mIsKeyguard;
        }

        public String getClientPackageName() {
            return this.mPackageName;
        }

        public ClientMode getClientMode() {
            return this.mClientMode;
        }

        public String toString() {
            return "mClientMode = " + this.mClientMode + ", mPackageName = " + this.mPackageName + ", mUserId = " + this.mUserId + ", mRestricted = " + this.mRestricted + ", mIsKeyguard = " + this.mIsKeyguard + ", mToken = " + this.mToken + " {" + Integer.toHexString(System.identityHashCode(this)) + "}";
        }
    }

    private class FaceServiceLockoutResetMonitor {
        private final IFaceServiceLockoutResetCallback mCallback;
        private final Runnable mRemoveCallbackRunnable = new Runnable() {
            public void run() {
                FaceService.this.removeLockoutResetCallback(FaceServiceLockoutResetMonitor.this);
            }
        };

        public FaceServiceLockoutResetMonitor(IFaceServiceLockoutResetCallback callback) {
            this.mCallback = callback;
        }

        public void sendLockoutReset() {
            if (this.mCallback != null) {
                try {
                    this.mCallback.onLockoutReset(FaceService.this.mHalDeviceId);
                } catch (DeadObjectException e) {
                    LogUtil.w(FaceService.TAG, "Death object while invoking onLockoutReset: ", e);
                    FaceService.this.mHandler.post(this.mRemoveCallbackRunnable);
                } catch (RemoteException e2) {
                    LogUtil.w(FaceService.TAG, "Failed to invoke onLockoutReset: ", e2);
                }
            }
        }
    }

    private final class FaceServiceWrapper extends Stub {
        private static final String KEYGUARD_PACKAGE = "com.android.systemui";

        /* synthetic */ FaceServiceWrapper(FaceService this$0, FaceServiceWrapper faceServiceWrapper) {
            this();
        }

        private FaceServiceWrapper() {
        }

        public long preEnroll(final IBinder token) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            final PendingResult<Long> r = new PendingResult(Long.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    if (FaceService.this.tryPreOperation(token, HealthState.PREENROLL)) {
                        r.setResult(Long.valueOf(FaceService.this.startPreEnroll(token)));
                    } else {
                        r.cancel();
                    }
                }
            });
            return ((Long) r.await()).longValue();
        }

        public int postEnroll(final IBinder token) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            final PendingResult<Integer> r = new PendingResult(Integer.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    if (FaceService.this.tryPreOperation(token, HealthState.POSTENROLL)) {
                        r.setResult(Integer.valueOf(FaceService.this.startPostEnroll(token)));
                    } else {
                        r.cancel();
                    }
                }
            });
            return ((Integer) r.await()).intValue();
        }

        public void enroll(IBinder token, byte[] cryptoToken, int userId, IFaceServiceReceiver receiver, int flags, String opPackageName) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            LogUtil.d(FaceService.TAG, "enroll, opPackageName = " + opPackageName);
            if (FaceService.this.getEnrolledFaces(userId).size() >= FaceService.this.mContext.getResources().getInteger(17694880)) {
                LogUtil.w(FaceService.TAG, "Too many faces registered");
            } else if (FaceService.this.isCurrentUserOrProfile(userId)) {
                if (cryptoToken == null) {
                    LogUtil.e(FaceService.TAG, "token is null");
                }
                final boolean restricted = isRestricted();
                final IBinder iBinder = token;
                final byte[] bArr = cryptoToken;
                final int i = userId;
                final IFaceServiceReceiver iFaceServiceReceiver = receiver;
                final int i2 = flags;
                final String str = opPackageName;
                FaceService.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (FaceService.this.tryPreOperation(iBinder, HealthState.ENROLL)) {
                            FaceService.this.startEnrollment(iBinder, bArr, i, iFaceServiceReceiver, i2, restricted, str);
                        } else {
                            FaceService.this.notifyOperationCanceled(iFaceServiceReceiver);
                        }
                    }
                });
            }
        }

        private boolean isRestricted() {
            return !FaceService.this.hasPermission("oppo.permission.MANAGE_FACE");
        }

        public void cancelEnrollment(final IBinder token, String opPackageName) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            LogUtil.d(FaceService.TAG, "cancelEnrollment, opPackageName = " + opPackageName);
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    if (FaceService.this.tryPreOperation(token, HealthState.CANCELENROLLMENT)) {
                        FaceService.this.stopEnrollment(token, true);
                    }
                }
            });
        }

        public void authenticate(IBinder token, long opId, int groupId, IFaceServiceReceiver receiver, int flags, String opPackageName, int type) {
            int callingUid = Binder.getCallingUid();
            final int callingUserId = UserHandle.getCallingUserId();
            if (FaceService.this.canUseFace(opPackageName, true, callingUid, Binder.getCallingPid())) {
                final boolean restricted = isRestricted();
                LogUtil.d(FaceService.TAG, "authenticate, opPackageName = " + opPackageName);
                final IBinder iBinder = token;
                final long j = opId;
                final int i = groupId;
                final IFaceServiceReceiver iFaceServiceReceiver = receiver;
                final int i2 = flags;
                final String str = opPackageName;
                final int i3 = type;
                FaceService.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (FaceService.this.tryPreOperation(iBinder, HealthState.AUTHENTICATE)) {
                            FaceService.this.startAuthentication(iBinder, j, callingUserId, i, iFaceServiceReceiver, i2, restricted, str, i3);
                        } else {
                            FaceService.this.notifyOperationCanceled(iFaceServiceReceiver);
                        }
                    }
                });
                return;
            }
            LogUtil.v(FaceService.TAG, "authenticate(): reject " + opPackageName);
        }

        public void cancelAuthentication(final IBinder token, String opPackageName) {
            if (FaceService.this.canUseFace(opPackageName, false, Binder.getCallingUid(), Binder.getCallingPid())) {
                LogUtil.d(FaceService.TAG, "cancelAuthentication, opPackageName = " + opPackageName);
                FaceService.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (FaceService.this.tryPreOperation(token, HealthState.CANCELAUTHENTICATE)) {
                            FaceService.this.stopAuthentication(token, true, false, false);
                        }
                    }
                });
            }
        }

        public void setActiveUser(final int userId) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    LogUtil.d(FaceService.TAG, "setActiveUser");
                    FaceService.this.updateActiveGroup(userId, null);
                }
            });
        }

        public void remove(IBinder token, int faceId, int groupId, int userId, IFaceServiceReceiver receiver, String opPackageName) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            final boolean restricted = isRestricted();
            LogUtil.d(FaceService.TAG, "remove faceId = " + faceId + ", groupId=  " + groupId);
            final IBinder iBinder = token;
            final int i = faceId;
            final int i2 = groupId;
            final int i3 = userId;
            final IFaceServiceReceiver iFaceServiceReceiver = receiver;
            final String str = opPackageName;
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    if (FaceService.this.tryPreOperation(iBinder, HealthState.REMOVE)) {
                        FaceService.this.startRemove(iBinder, i, i2, i3, iFaceServiceReceiver, restricted, str);
                    } else {
                        FaceService.this.notifyOperationCanceled(iFaceServiceReceiver);
                    }
                }
            });
        }

        public boolean isHardwareDetected(long deviceId, String opPackageName) {
            boolean z = false;
            if (!FaceService.this.canUseFace(opPackageName, false, Binder.getCallingUid(), Binder.getCallingPid())) {
                return false;
            }
            if (FaceService.this.mHalDeviceId != 0) {
                z = FaceService.this.isCameraServiceReady();
            }
            return z;
        }

        public void rename(final int faceId, final int groupId, final String name) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            if (FaceService.this.isCurrentUserOrProfile(groupId)) {
                LogUtil.d(FaceService.TAG, " rename faceId = " + faceId + ", groupId = " + groupId + ", name =  " + name);
                FaceService.this.mHandler.post(new Runnable() {
                    public void run() {
                        FaceService.this.mFaceUtils.renameFaceForUser(FaceService.this.mContext, faceId, groupId, name);
                    }
                });
            }
        }

        public List<FaceFeature> getEnrolledFaces(int userId, String opPackageName) {
            if (!FaceService.this.canUseFace(opPackageName, false, Binder.getCallingUid(), Binder.getCallingPid())) {
                return Collections.emptyList();
            }
            if (!FaceService.this.isCurrentUserOrProfile(userId)) {
                return Collections.emptyList();
            }
            LogUtil.d(FaceService.TAG, "getEnrolledFaces opPackageName = " + opPackageName);
            return FaceService.this.getEnrolledFaces(userId);
        }

        public boolean hasEnrolledFaces(int userId, String opPackageName) {
            if (!FaceService.this.canUseFace(opPackageName, false, Binder.getCallingUid(), Binder.getCallingPid()) || !FaceService.this.isCurrentUserOrProfile(userId)) {
                return false;
            }
            boolean hasFace = FaceService.this.hasEnrolledFaces(userId, opPackageName);
            LogUtil.d(FaceService.TAG, " hasEnrolledFaces opPackageName = " + opPackageName + ", hasFace = " + hasFace);
            return hasFace;
        }

        public long getAuthenticatorId(String opPackageName) {
            return FaceService.this.getAuthenticatorId(opPackageName);
        }

        protected void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
            if (FaceService.this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
                pw.println("Permission Denial: can't dump Face from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
                return;
            }
            long ident = Binder.clearCallingIdentity();
            try {
                FaceService.this.dumpInternal(pw, args);
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        public void resetTimeout(byte[] token) {
            FaceService.this.checkPermission("oppo.permission.RESET_FACE_LOCKOUT");
            LogUtil.v(FaceService.TAG, "resetTimeout");
            if (token == null || token.length <= 0 || token[0] != (byte) 1) {
                FaceService.this.mHandler.post(FaceService.this.mResetFailedAttemptsRunnable);
            } else {
                FaceService.this.mHandler.post(FaceService.this.mDecreaseFailedAttemptsRunnable);
            }
        }

        public void addLockoutResetCallback(final IFaceServiceLockoutResetCallback callback) throws RemoteException {
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    FaceService.this.addLockoutResetMonitor(new FaceServiceLockoutResetMonitor(callback));
                }
            });
        }

        public int executeCommand(IBinder token, String opPackageName, int userId, IFaceServiceReceiver receiver, int type) {
            if (FaceService.this.canUseFace(opPackageName, true, Binder.getCallingUid(), Binder.getCallingPid())) {
                LogUtil.d(FaceService.TAG, "executeCommand type = " + type);
                final boolean restricted = isRestricted();
                final PendingResult<Integer> r = new PendingResult(Integer.valueOf(-1));
                final IBinder iBinder = token;
                final int i = userId;
                final IFaceServiceReceiver iFaceServiceReceiver = receiver;
                final String str = opPackageName;
                final int i2 = type;
                FaceService.this.mHandler.post(new Runnable() {
                    public void run() {
                        r.setResult(Integer.valueOf(FaceService.this.startExecuteCommand(iBinder, restricted, i, iFaceServiceReceiver, str, i2)));
                    }
                });
                return ((Integer) r.await()).intValue();
            }
            LogUtil.d(FaceService.TAG, "executeCommand failed , " + opPackageName);
            return -1;
        }

        public void cancelCommand(final IBinder token, String opPackageName, final int type) {
            LogUtil.d(FaceService.TAG, "cancelCommand type = " + type);
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    FaceService.this.stopExecuteCommand(token, true, type);
                }
            });
        }

        public int get(IBinder token, int type, String opPackageName) {
            if (FaceService.this.canUseFace(opPackageName, true, Binder.getCallingUid(), Binder.getCallingPid())) {
                LogUtil.d(FaceService.TAG, "get type = " + type);
                final PendingResult<Integer> r = new PendingResult(Integer.valueOf(-1));
                final IBinder iBinder = token;
                final int i = type;
                final String str = opPackageName;
                FaceService.this.mHandler.post(new Runnable() {
                    public void run() {
                        r.setResult(Integer.valueOf(FaceService.this.startGet(iBinder, i, str)));
                    }
                });
                return ((Integer) r.await()).intValue();
            }
            LogUtil.d(FaceService.TAG, "get failed , " + opPackageName);
            return -1;
        }

        public void setPreviewFrame(final IBinder token, final Rect rect) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    FaceService.this.startSetPreviewFrame(token, rect);
                }
            });
        }

        public int setPreviewSurface(IBinder token, final Surface surface) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            final PendingResult<Integer> r = new PendingResult(Integer.valueOf(-1));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    r.setResult(Integer.valueOf(FaceService.this.startSetPreviewSurface(surface)));
                }
            });
            return ((Integer) r.await()).intValue();
        }

        public void notifyFirstUnlockWhenBoot(boolean isFirstUnlockInPasswordOnlyMode) {
            LogUtil.d(FaceService.TAG, "notifyFirstUnlockWhenBoot, mIsFirstUnlockedInPasswordOnlyMode = " + isFirstUnlockInPasswordOnlyMode);
            FaceService.this.mIsFirstUnlockedInPasswordOnlyMode = isFirstUnlockInPasswordOnlyMode;
        }

        public int getEnrollmentTotalTimes(final IBinder token) {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            if (FaceService.this.mEnrollmentTotalTimes != 0) {
                LogUtil.d(FaceService.TAG, "has got total times, just return it");
                return FaceService.this.mEnrollmentTotalTimes;
            }
            final PendingResult<Integer> r = new PendingResult(Integer.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    if (FaceService.this.tryPreOperation(token, HealthState.GET_ENROLLMENT_TOTAL_TIMES)) {
                        r.setResult(Integer.valueOf(FaceService.this.startGetEnrollmentTotalTimes(token)));
                    } else {
                        r.cancel();
                    }
                }
            });
            return ((Integer) r.await()).intValue();
        }

        public long getLockoutAttemptDeadline(String opPackageName) {
            FaceService.this.checkPermission("oppo.permission.USE_FACE");
            LogUtil.d(FaceService.TAG, "getLockoutAttemptDeadline opPackageName = " + opPackageName);
            final PendingResult<Long> r = new PendingResult(Long.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    r.setResult(Long.valueOf(FaceService.this.getLockoutAttemptDeadline()));
                }
            });
            return ((Long) r.await()).longValue();
        }

        public int getFailedAttempts(String opPackageName) {
            FaceService.this.checkPermission("oppo.permission.USE_FACE");
            LogUtil.d(FaceService.TAG, "getFailedAttempts opPackageName = " + opPackageName);
            final PendingResult<Integer> r = new PendingResult(Integer.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    r.setResult(Integer.valueOf(FaceService.this.getFailedAttempts()));
                }
            });
            return ((Integer) r.await()).intValue();
        }

        public int getPreviewWidth() {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            LogUtil.d(FaceService.TAG, "getPreviewWidth");
            final PendingResult<Integer> r = new PendingResult(Integer.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    r.setResult(Integer.valueOf(FaceService.this.getPreViewWidth()));
                }
            });
            return ((Integer) r.await()).intValue();
        }

        public int getPreviewHeight() {
            FaceService.this.checkPermission("oppo.permission.MANAGE_FACE");
            LogUtil.d(FaceService.TAG, "getPreviewWidth");
            final PendingResult<Integer> r = new PendingResult(Integer.valueOf(0));
            FaceService.this.mHandler.post(new Runnable() {
                public void run() {
                    r.setResult(Integer.valueOf(FaceService.this.getPreViewHeight()));
                }
            });
            return ((Integer) r.await()).intValue();
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.server.face.FaceService.<clinit>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 9 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.server.face.FaceService.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.face.FaceService.<clinit>():void");
    }

    private void initHandler() {
        this.mHandler = new ExHandler(this.mServiceThread.getLooper()) {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 10:
                        FaceService.this.handleUserSwitching(msg.arg1);
                        return;
                    case 11:
                        break;
                    case 12:
                        FaceService.this.updateAppSecrecyState();
                        return;
                    case 13:
                        FaceService.this.handleUnBlockScreenOn();
                        return;
                    default:
                        LogUtil.w(FaceService.TAG, "Unknown message:" + msg.what);
                        return;
                }
                while (true) {
                    Runnable r = (Runnable) FaceService.this.mDaemonCallbackQueue.poll();
                    if (r != null) {
                        r.run();
                    } else {
                        return;
                    }
                }
            }
        };
    }

    public FaceService(Context context) {
        super(context);
        this.mSupportPowerBlock = "on".equals(SystemProperties.get(PROP_FACE_POWER_BLOCK, "off"));
        this.mLockoutMonitors = new ArrayList();
        this.mCurrentUserId = -2;
        this.mFaceUtils = FaceUtils.getInstance();
        this.mEnrollmentTotalTimes = 0;
        this.mAuthClient = null;
        this.mEnrollClient = null;
        this.mRemoveClient = null;
        this.mCommandClient = null;
        this.mDaemonCallbackQueue = new ConcurrentLinkedDeque();
        this.mClientLock = new Object();
        this.mFaceEnabled = true;
        this.mFaceUnlockEnabled = false;
        this.mFaceUnlockAutoWhenScreenOn = false;
        this.mKeyguardAuthType = -1;
        this.mIsEncryptApp = false;
        this.mIsImageSaveEnable = false;
        this.mIsLogOpened = false;
        this.mIsFirstUnlockedInPasswordOnlyMode = true;
        this.mLockoutReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                if (FaceService.ACTION_LOCKOUT_RESET.equals(intent.getAction())) {
                    LogUtil.v(FaceService.TAG, "onReceive");
                    FaceService.this.mHandler.post(FaceService.this.mResetFailedAttemptsRunnable);
                }
            }
        };
        this.mResetFailedAttemptsRunnable = new Runnable() {
            public void run() {
                FaceService.this.resetFailedAttempts();
            }
        };
        this.mDecreaseFailedAttemptsRunnable = new Runnable() {
            public void run() {
                FaceService.this.decreaseFailedAttempts();
            }
        };
        this.mSecrecyStateListener = new SecrecyStateListener() {
            public void onSecrecyStateChanged(int type, boolean value) {
                switch (type) {
                    case 2:
                        FaceService.this.mHandler.sendMessage(FaceService.this.mHandler.obtainMessage(12));
                        return;
                    default:
                        return;
                }
            }
        };
        this.mSystemReady = false;
        this.mListener = new Ilistener() {
            public void onSettingChanged(String settingName, boolean isOn) {
                LogUtil.d(FaceService.TAG, "onSettingChanged, settingName = " + settingName + ", isOn = " + isOn);
                if (FaceSettings.FACE_UNLOCK_SWITCH.equals(settingName)) {
                    FaceService.this.mFaceUnlockEnabled = isOn;
                } else if (FaceSettings.FACE_UNLOCK_AUTO_CHECK_SWITCH.equals(settingName)) {
                    FaceService.this.mFaceUnlockAutoWhenScreenOn = isOn;
                }
            }
        };
        DEBUG = SystemProperties.getBoolean("persist.sys.assert.panic", false);
        LogUtil.IS_DEBUGING = DEBUG;
        LogUtil.d(TAG, TAG_NAME);
        this.mContext = context;
        this.mKeyguardPackage = ComponentName.unflattenFromString(context.getResources().getString(17039465)).getPackageName();
        this.mAppOps = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mPowerManager = (PowerManager) this.mContext.getSystemService(PowerManager.class);
        this.mAlarmManager = (AlarmManager) this.mContext.getSystemService(AlarmManager.class);
        this.mSecrecyManager = (SecrecyManager) this.mContext.getSystemService(SecrecyManager.class);
        this.mSecrecyManager.setSecrecyStateListener(this.mSecrecyStateListener);
        this.mIsEncryptApp = this.mSecrecyManager.getSecrecyState(2);
        this.mFingerprintManager = (FingerprintManager) this.mContext.getSystemService(FingerprintManager.class);
        this.mLockPatternUtils = new LockPatternUtils(this.mContext);
        this.mContext.registerReceiver(this.mLockoutReceiver, new IntentFilter(ACTION_LOCKOUT_RESET), "oppo.permission.RESET_FACE_LOCKOUT", null);
        this.mUserManager = UserManager.get(this.mContext);
        Watchdog.getInstance().addMonitor(this);
        this.mLockoutDeadline = 0;
        this.mServiceThread = new ServiceThread(TAG, -2, true);
        this.mServiceThread.start();
        initHandler();
        this.mHealthMonitor = new HealthMonitor(this.mContext, TAG);
        this.mDcsFaceUtil = DcsFaceUtil.getDcsFaceUtil(context);
        this.mKeyguardPolicy = KeyguardPolicy.getKeyguardPolicy(context);
        FacePowerManager.initFacePms(this.mContext, this);
        this.mUnlockSettingMonitor = new UnlockSettingMonitor(this.mContext, this.mListener, this.mServiceThread.getLooper());
        this.mFaceSupport = context.getPackageManager().hasSystemFeature(FEATURE_FACE);
        this.mFaceSwitchHelper = new FaceSwitchHelper(this.mContext, this);
    }

    public void binderDied() {
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                LogUtil.d(FaceService.TAG, "faced died");
                FaceService.this.mHealthMonitor.notifyDemoProcessDied();
                ((FaceDaemonWrapper) FaceService.this.mDaemonWrapper).binderDied();
                FaceService.this.mDaemonStub = FaceService.this.mDaemonWrapper = FaceService.this.mDaemon = null;
                FaceService.this.mCurrentUserId = -2;
                FaceService.this.handleError(FaceService.this.mHalDeviceId, 1);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public IFaceDaemon getFaceDaemon() {
        boolean z = false;
        if (!this.mFaceSupport) {
            return null;
        }
        if (this.mDaemon == null) {
            if (this.mDaemonStub == null) {
                this.mDaemonStub = IFaceDaemon.Stub.asInterface(ServiceManager.getService(FACED));
            }
            if (this.mDaemonStub != null) {
                try {
                    if (this.mDaemonWrapper == null) {
                        FaceHypnus.getInstance().hypnusSpeedUp(2500);
                        int faceNum = getEnrolledFaceNum(ActivityManager.getCurrentUser());
                        this.mDaemonWrapper = new FaceDaemonWrapper(this.mDaemonStub, this.mHealthMonitor, this.mContext, this);
                        this.mDaemonWrapper.asBinder().linkToDeath(this, 0);
                        FaceDaemonWrapper faceDaemonWrapper = (FaceDaemonWrapper) this.mDaemonWrapper;
                        if (faceNum > 0) {
                            z = true;
                        }
                        faceDaemonWrapper.initDaemon(z);
                    }
                    this.mHalDeviceId = this.mDaemonWrapper.openHal();
                    if (this.mHalDeviceId != 0) {
                        this.mDaemon = this.mDaemonWrapper;
                        updateActiveGroup(ActivityManager.getCurrentUser(), null);
                        this.mEnrollmentTotalTimes = this.mDaemon.getEnrollmentTotalTimes();
                        this.mDaemon.updateRusNativeData(this.mFaceSwitchHelper.getRusNativeData());
                    } else {
                        LogUtil.w(TAG, "Failed to open Face HAL!");
                        this.mDaemon = null;
                    }
                } catch (RemoteException e) {
                    LogUtil.e(TAG, "Failed to open faced HAL", e);
                    this.mDaemon = null;
                }
            } else {
                LogUtil.w(TAG, "face service not available");
            }
            if (this.mDaemon == null) {
                LogUtil.e(TAG, "mDaemon = null, Failed to open faced HAL");
            }
        }
        return this.mDaemon;
    }

    protected void handleEnumerate(long deviceId, int[] faceIds, int groupId) {
        LogUtil.w(TAG, "handleEnumerate");
        syncTemplates(faceIds, groupId);
    }

    protected void handleSlightError(long deviceId, int error) {
        synchronized (this.mClientLock) {
            LogUtil.d(TAG, "handleSlightError error = " + error);
            if (this.mEnrollClient != null) {
                this.mEnrollClient.sendError(error);
            } else if (this.mAuthClient != null) {
                this.mAuthClient.sendError(error);
            } else if (this.mRemoveClient != null) {
                this.mRemoveClient.sendError(error);
            } else if (this.mCommandClient != null) {
                this.mCommandClient.sendError(error);
            }
        }
    }

    protected void handleError(long deviceId, int error) {
        synchronized (this.mClientLock) {
            LogUtil.d(TAG, "handleError error = " + error);
            IBinder token;
            if (this.mEnrollClient != null) {
                token = this.mEnrollClient.mToken;
                if (this.mEnrollClient.sendError(error)) {
                    stopEnrollment(token, false);
                }
            } else if (this.mAuthClient != null) {
                token = this.mAuthClient.mToken;
                if (this.mAuthClient.sendError(error)) {
                    updateScreenOnBlockedState(false, true, 48);
                    stopAuthentication(token, false, false, false);
                }
            } else if (this.mRemoveClient != null) {
                if (this.mRemoveClient.sendError(error)) {
                    removeClient(this.mRemoveClient, false);
                }
            } else if (this.mCommandClient != null) {
                token = this.mCommandClient.mToken;
                if (this.mCommandClient.sendError(error)) {
                    stopExecuteCommand(token, false, -1);
                }
            }
        }
    }

    protected void handleRemoved(long deviceId, int faceId, int groupId) {
        LogUtil.d(TAG, "handleRemoved faceId = " + faceId);
        removeTemplateForUser(groupId, faceId);
        synchronized (this.mClientLock) {
            ClientMonitor client = this.mRemoveClient;
            if (client != null && client.sendRemoved(faceId, groupId)) {
                LogUtil.d(TAG, "handleRemoved remove mRemoveClient");
                removeClient(this.mRemoveClient, false);
            }
        }
    }

    protected void handleAuthenticated(long deviceId, int faceId, int groupId) {
        LogUtil.d(TAG, "handleAuthenticated faceId = " + faceId);
        synchronized (this.mClientLock) {
            if (this.mAuthClient != null) {
                if (faceId != 0 && this.mAuthClient.isKeyguard() && this.mKeyguardAuthType == 1 && this.mLockPatternUtils.getVerifyPwdAttemptDeadline(this.mCurrentUserId) - SystemClock.elapsedRealtime() > 5000) {
                    this.mKeyguardPolicy.setKeyguardVisibility(false);
                }
                IBinder token = this.mAuthClient.mToken;
                boolean isSended = this.mAuthClient.sendAuthenticated(faceId, groupId);
                if (isSended && !inLockoutMode()) {
                    stopAuthentication(token, false, false, false);
                    removeClient(this.mAuthClient, false);
                } else if (isSended && inLockoutMode()) {
                    stopAuthentication(token, false, true, false);
                    removeClient(this.mAuthClient, false);
                }
            }
        }
    }

    protected void handleAcquired(long deviceId, int acquiredInfo) {
        LogUtil.d(TAG, "handleAcquired acquiredInfo = " + acquiredInfo);
        synchronized (this.mClientLock) {
            if (this.mEnrollClient != null) {
                if (this.mEnrollClient.sendAcquired(acquiredInfo)) {
                    removeClient(this.mEnrollClient, false);
                }
            } else if (this.mAuthClient != null && this.mAuthClient.sendAcquired(acquiredInfo)) {
                removeClient(this.mAuthClient, false);
            }
        }
    }

    protected void handleProgressChanged(long deviceId, int progressInfo) {
        LogUtil.d(TAG, "handleProgressChanged progressInfo = " + progressInfo);
        synchronized (this.mClientLock) {
            if (this.mEnrollClient != null) {
                if (this.mEnrollClient.sendProcess(progressInfo)) {
                    removeClient(this.mEnrollClient, false);
                }
            } else if (this.mAuthClient != null && this.mAuthClient.sendProcess(progressInfo)) {
                removeClient(this.mAuthClient, false);
            }
        }
    }

    protected void handleEnrollResult(long deviceId, int faceId, int groupId, int remaining) {
        if (remaining == 0) {
            addTemplateForUser(groupId, faceId);
        }
        synchronized (this.mClientLock) {
            if (this.mEnrollClient != null) {
                LogUtil.d(TAG, "handleEnrollResult , faceId = " + faceId + ", groupId = " + groupId + ", remaining = " + remaining);
                if (this.mEnrollClient.sendEnrollResult(faceId, groupId, remaining) && remaining == 0) {
                    removeClient(this.mEnrollClient, false);
                }
            }
        }
    }

    private void userActivity() {
        this.mPowerManager.userActivity(SystemClock.uptimeMillis(), 2, 0);
    }

    void handleUserSwitching(int userId) {
        LogUtil.d(TAG, "handleUserSwitching");
        updateActiveGroup(userId, null);
    }

    /* JADX WARNING: Missing block: B:10:0x0015, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void removeEnrollClient(ClientMonitor client) {
        synchronized (this.mClientLock) {
            if (client == null) {
                return;
            }
            client.destroy();
            if (client == this.mEnrollClient) {
                this.mEnrollClient = null;
                stopPreview();
            }
        }
    }

    /* JADX WARNING: Missing block: B:12:0x001a, code:
            return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void removeClient(ClientMonitor client, boolean isPendingForKeyguard) {
        synchronized (this.mClientLock) {
            if (client == null) {
                return;
            }
            client.destroy();
            if (client == this.mAuthClient) {
                this.mKeyguardAuthType = -1;
                this.mAuthClient = null;
                if (!isPendingForKeyguard) {
                    cancelRecognition();
                }
            } else if (client == this.mEnrollClient) {
                this.mEnrollClient = null;
                cancelRecognition();
            } else if (client == this.mRemoveClient) {
                this.mRemoveClient = null;
            } else if (client == this.mCommandClient) {
                this.mCommandClient = null;
            }
        }
    }

    private boolean inLockoutMode() {
        LogUtil.d(TAG, "inLockoutMode mFailedAttempts = " + this.mFailedAttempts);
        return this.mFailedAttempts >= 5;
    }

    private void scheduleLockoutReset() {
        this.mAlarmManager.setExact(2, SystemClock.elapsedRealtime() + FAIL_LOCKOUT_TIMEOUT_MS, getLockoutResetIntent());
    }

    private void cancelLockoutReset() {
        this.mAlarmManager.cancel(getLockoutResetIntent());
    }

    private PendingIntent getLockoutResetIntent() {
        return PendingIntent.getBroadcast(this.mContext, 0, new Intent(ACTION_LOCKOUT_RESET), 134217728);
    }

    protected void resetFailedAttempts() {
        LogUtil.d(TAG, "resetFailedAttempts");
        if (DEBUG && inLockoutMode()) {
            LogUtil.v(TAG, "Reset face lockout");
        }
        this.mFailedAttempts = 0;
        cancelLockoutReset();
        notifyLockoutResetMonitors();
        setLockoutAttemptDeadline(0);
        LogUtil.v(TAG, "resetFailedAttempts finish");
    }

    protected void decreaseFailedAttempts() {
        LogUtil.d(TAG, "decreaseFailedAttempts");
        this.mFailedAttempts--;
        LogUtil.v(TAG, "decreaseFailedAttempts finish");
    }

    private boolean handleFailedAttempt(ClientMonitor clientMonitor) {
        this.mFailedAttempts++;
        LogUtil.v(TAG, "mFailedAttempts = " + this.mFailedAttempts);
        if (!inLockoutMode()) {
            return false;
        }
        scheduleLockoutReset();
        setLockoutAttemptDeadline(SystemClock.elapsedRealtime() + FAIL_LOCKOUT_TIMEOUT_MS);
        if (!(clientMonitor == null || clientMonitor.sendError(7))) {
            LogUtil.w(TAG, "Cannot send lockout message to client");
        }
        return true;
    }

    private void removeTemplateForUser(int groupId, int faceId) {
        this.mFaceUtils.removeFaceIdForUser(this.mContext, faceId, groupId);
    }

    private void addTemplateForUser(int groupId, int faceId) {
        this.mFaceUtils.addFaceForUser(this.mContext, faceId, groupId);
    }

    private void startEnrollment(IBinder token, byte[] cryptoToken, int userId, IFaceServiceReceiver receiver, int flags, boolean restricted, String pkgName) {
        LogUtil.d(TAG, "startEnrollment pkgName = " + pkgName);
        updateActiveGroup(userId, pkgName);
        int groupId = userId;
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            if (receiver != null) {
                try {
                    LogUtil.d(TAG, "startEnrollment: no faced! send error FACE_ERROR_HW_UNAVAILABLE");
                    receiver.onError(-1, 1);
                } catch (RemoteException e) {
                    LogUtil.w(TAG, "Failed to invoke sendError:", e);
                }
            }
            LogUtil.w(TAG, "enroll: no faced!");
            return;
        }
        stopPendingOperations(true, false);
        synchronized (this.mClientLock) {
            this.mEnrollClient = new ClientMonitor(token, receiver, userId, userId, restricted, pkgName, ClientMode.ENROLL);
        }
        try {
            int result = daemon.enroll(cryptoToken, userId, 60);
            if (result != 0) {
                LogUtil.w(TAG, "startEnroll failed, result=" + result);
                handleError(this.mHalDeviceId, 1);
            }
        } catch (RemoteException e2) {
            LogUtil.e(TAG, "startEnroll failed", e2);
        }
    }

    public long startPreEnroll(IBinder token) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startPreEnroll: no faced!");
            return 0;
        }
        try {
            LogUtil.d(TAG, "startPreEnroll");
            return daemon.preEnroll();
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startPreEnroll failed", e);
            return 0;
        }
    }

    public int startPostEnroll(IBinder token) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startPostEnroll: no faced!");
            return 0;
        }
        try {
            LogUtil.d(TAG, "startPostEnroll");
            return daemon.postEnroll();
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startPostEnroll failed", e);
            return 0;
        }
    }

    private void stopPendingOperations(boolean initiatedByClient, boolean isPendingForKeyguard) {
        LogUtil.d(TAG, "stopPendingOperations initiatedByClient = " + initiatedByClient + ", mEnrollClient = " + this.mEnrollClient + ", mAuthClient = " + this.mAuthClient);
        synchronized (this.mClientLock) {
            if (this.mEnrollClient != null) {
                stopEnrollment(this.mEnrollClient.mToken, initiatedByClient);
            }
            if (this.mAuthClient != null) {
                stopAuthentication(this.mAuthClient.mToken, initiatedByClient, false, isPendingForKeyguard);
            }
            if (this.mCommandClient != null) {
                stopExecuteCommand(this.mCommandClient.mToken, initiatedByClient, -1);
            }
        }
    }

    void stopEnrollment(IBinder token, boolean initiatedByClient) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "stopEnrollment: no faced!");
            return;
        }
        ClientMonitor client = this.mEnrollClient;
        if (client == null || client.mToken != token) {
            LogUtil.e(TAG, " client = " + client + ", token = " + token + ", return");
            cancelRecognition();
        } else if (token == null || token.isBinderAlive()) {
            LogUtil.w(TAG, "stopEnrollment initiatedByClient = " + initiatedByClient);
            if (initiatedByClient) {
                try {
                    int result = daemon.cancelEnrollment();
                    if (result != 0) {
                        LogUtil.w(TAG, "startEnrollCancel failed, result = " + result);
                    }
                } catch (RemoteException e) {
                    LogUtil.e(TAG, "stopEnrollment failed", e);
                }
                client.sendError(5);
            }
            removeClient(this.mEnrollClient, false);
        } else {
            LogUtil.e(TAG, token + " has died, just skip this stopEnrollment");
            cancelRecognition();
        }
    }

    private void startAuthentication(IBinder token, long opId, int callingUserId, int groupId, IFaceServiceReceiver receiver, int flags, boolean restricted, String pkgName, int type) {
        updateActiveGroup(groupId, pkgName);
        LogUtil.d(TAG, "startAuthentication pkgName = " + pkgName + ", type = " + type);
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            if (receiver != null) {
                try {
                    LogUtil.w(TAG, "startAuthentication: no faced! send error FACE_ERROR_HW_UNAVAILABLE");
                    receiver.onError(-1, 1);
                } catch (RemoteException e) {
                    LogUtil.w(TAG, "Failed to invoke sendError:", e);
                }
            }
        } else if (token == null || token.isBinderAlive()) {
            boolean isPendingForKeyguard = isKeyguard(pkgName) && this.mAuthClient != null;
            stopPendingOperations(true, isPendingForKeyguard);
            synchronized (this.mClientLock) {
                this.mAuthClient = new ClientMonitor(token, receiver, this.mCurrentUserId, groupId, restricted, pkgName, ClientMode.AUTHEN);
            }
            if (inLockoutMode()) {
                LogUtil.d(TAG, "In lockout mode; disallowing authentication");
                if (!this.mAuthClient.sendError(7)) {
                    LogUtil.w(TAG, "Cannot send timeout message to client");
                }
                this.mAuthClient = null;
                return;
            }
            if (this.mDcsFaceUtil != null) {
                this.mDcsFaceUtil.clearMap();
            }
            if (this.mAuthClient != null && this.mAuthClient.isKeyguard()) {
                this.mKeyguardAuthType = type;
                if (this.mKeyguardAuthType == 0) {
                    updateScreenOnBlockedState(true, false, 0);
                }
            }
            try {
                int result = daemon.authenticate(opId, groupId, type);
                if (result != 0) {
                    LogUtil.w(TAG, "startAuthentication failed, result=" + result);
                    handleError(this.mHalDeviceId, 1);
                }
            } catch (RemoteException e2) {
                LogUtil.e(TAG, "startAuthentication failed", e2);
            }
        } else {
            LogUtil.e(TAG, token + " has died, just skip this startAuthentication");
            if (isKeyguard(pkgName)) {
                cancelRecognition();
            }
        }
    }

    void stopAuthentication(IBinder token, boolean initiatedByClient, boolean initiatedInLockOutMode, boolean isPendingForKeyguard) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "stopAuthentication: no faced!");
            return;
        }
        ClientMonitor client = this.mAuthClient;
        if (client == null || client.mToken != token) {
            LogUtil.w(TAG, "stopAuthentication, client == null");
            cancelRecognition();
        } else if (token == null || token.isBinderAlive()) {
            LogUtil.d(TAG, "stopAuthentication initiatedByClient = " + initiatedByClient + ", initiatedInLockOutMode = " + initiatedInLockOutMode + ", packageName = " + client.getClientPackageName());
            if (initiatedByClient || initiatedInLockOutMode) {
                try {
                    int result = daemon.cancelAuthentication();
                    if (result != 0) {
                        LogUtil.w(TAG, "stopAuthentication failed, result=" + result);
                    }
                } catch (RemoteException e) {
                    LogUtil.e(TAG, "stopAuthentication failed", e);
                }
                if (!initiatedInLockOutMode) {
                    client.sendError(5);
                }
            }
            removeClient(this.mAuthClient, isPendingForKeyguard);
        } else {
            LogUtil.e(TAG, token + " has died, just skip this stopAuthentication");
            cancelRecognition();
        }
    }

    void startRemove(IBinder token, int faceId, int groupId, int userId, IFaceServiceReceiver receiver, boolean restricted, String pkgName) {
        LogUtil.d(TAG, "startRemove pkgName = " + pkgName);
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startRemove: no faced!");
            return;
        }
        stopPendingOperations(true, false);
        synchronized (this.mClientLock) {
            this.mRemoveClient = new ClientMonitor(token, receiver, userId, groupId, restricted, pkgName, ClientMode.REMOVE);
        }
        try {
            LogUtil.w(TAG, "startRemove faceId = " + faceId + ", groupId = " + groupId);
            int result = daemon.remove(faceId, groupId);
            if (result != 0) {
                LogUtil.w(TAG, "startRemove with id = " + faceId + " failed, result=" + result);
                handleError(this.mHalDeviceId, 1);
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startRemove failed", e);
        }
    }

    protected int handleVerifyFace(byte[] nv21ImageData, int nv21ImageDataWidth, int nv21ImageDataHeight, FaceInfo faceInfo, ClientMode mode) {
        LogUtil.d(TAG, "handleVerifyFace mode = " + mode);
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "handleVerifyFace: no faced!");
            return -1;
        }
        try {
            LogUtil.w(TAG, "handleVerifyFace");
            int result = daemon.verifyFace(nv21ImageData, nv21ImageDataWidth, nv21ImageDataHeight, new FaceInfoWrapper(faceInfo.faceRect, faceInfo.facePoints, faceInfo.id, faceInfo.score, faceInfo.yaw, faceInfo.pitch, faceInfo.roll, faceInfo.eyeDist), FaceOrientation.RIGHT.getValue(), mode.getValue());
            if (result != 0) {
                LogUtil.w(TAG, "handleVerifyFace failed, result=" + result);
                handleError(this.mHalDeviceId, 1);
            }
            return result;
        } catch (RemoteException e) {
            LogUtil.e(TAG, "handleVerifyFace failed", e);
            return -1;
        }
    }

    public List<FaceFeature> getEnrolledFaces(int userId) {
        LogUtil.d(TAG, "FaceFeatures number = " + this.mFaceUtils.getFacesForUser(this.mContext, userId).size());
        return this.mFaceUtils.getFacesForUser(this.mContext, userId);
    }

    public boolean hasEnrolledFaces(int userId, String opPackageName) {
        if (userId != UserHandle.getCallingUserId()) {
            checkPermission("android.permission.INTERACT_ACROSS_USERS");
        }
        if (this.mFaceUtils.getFacesForUser(this.mContext, userId).size() > 0) {
            return true;
        }
        return false;
    }

    boolean hasPermission(String permission) {
        return getContext().checkCallingOrSelfPermission(permission) == 0;
    }

    void checkPermission(String permission) {
        getContext().enforceCallingOrSelfPermission(permission, "Must have " + permission + " permission.");
    }

    int getEffectiveUserId(int userId) {
        UserManager um = UserManager.get(this.mContext);
        if (um != null) {
            long callingIdentity = Binder.clearCallingIdentity();
            userId = um.getCredentialOwnerProfile(userId);
            Binder.restoreCallingIdentity(callingIdentity);
            return userId;
        }
        LogUtil.e(TAG, "Unable to acquire UserManager");
        return userId;
    }

    boolean isCurrentUserOrProfile(int userId) {
        List<UserInfo> profiles = UserManager.get(this.mContext).getEnabledProfiles(userId);
        int n = profiles.size();
        for (int i = 0; i < n; i++) {
            if (((UserInfo) profiles.get(i)).id == userId) {
                return true;
            }
        }
        return false;
    }

    private boolean isForegroundActivity(int uid, int pid) {
        try {
            List<RunningAppProcessInfo> procs = ActivityManagerNative.getDefault().getRunningAppProcesses();
            int N = procs.size();
            for (int i = 0; i < N; i++) {
                RunningAppProcessInfo proc = (RunningAppProcessInfo) procs.get(i);
                if (proc.pid == pid && proc.uid == uid && proc.importance == 100) {
                    return true;
                }
            }
        } catch (RemoteException e) {
            LogUtil.w(TAG, "am.getRunningAppProcesses() failed");
        }
        return false;
    }

    private boolean canUseFace(String opPackageName, boolean foregroundOnly, int uid, int pid) {
        checkPermission("oppo.permission.USE_FACE");
        if (isKeyguard(opPackageName)) {
            return true;
        }
        if (!isCurrentUserOrProfile(UserHandle.getCallingUserId())) {
            LogUtil.w(TAG, "Rejecting " + opPackageName + " ; not a current user or profile");
            return false;
        } else if (this.mAppOps.noteOp(71, uid, opPackageName) != 0) {
            LogUtil.w(TAG, "Rejecting " + opPackageName + " ; permission denied");
            return false;
        } else if (!foregroundOnly || isForegroundActivity(uid, pid)) {
            return true;
        } else {
            LogUtil.w(TAG, "Rejecting " + opPackageName + " ; not in foreground");
            return false;
        }
    }

    private boolean isKeyguard(String clientPackage) {
        return this.mKeyguardPackage.equals(clientPackage);
    }

    private void addLockoutResetMonitor(FaceServiceLockoutResetMonitor monitor) {
        if (!this.mLockoutMonitors.contains(monitor)) {
            this.mLockoutMonitors.add(monitor);
        }
    }

    private void removeLockoutResetCallback(FaceServiceLockoutResetMonitor monitor) {
        this.mLockoutMonitors.remove(monitor);
    }

    private void notifyLockoutResetMonitors() {
        for (int i = 0; i < this.mLockoutMonitors.size(); i++) {
            ((FaceServiceLockoutResetMonitor) this.mLockoutMonitors.get(i)).sendLockoutReset();
        }
    }

    public void onEnrollResult(long deviceId, int faceId, int groupId, int remaining) {
        final long j = deviceId;
        final int i = faceId;
        final int i2 = groupId;
        final int i3 = remaining;
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleEnrollResult(j, i, i2, i3);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onAcquired(final long deviceId, final int acquiredInfo) {
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleAcquired(deviceId, acquiredInfo);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onAuthenticated(long deviceId, int faceId, int groupId) {
        LogUtil.d(TAG, "onAuthenticated, faceId = " + faceId);
        final long j = deviceId;
        final int i = faceId;
        final int i2 = groupId;
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleAuthenticated(j, i, i2);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onError(final long deviceId, final int error) {
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleError(deviceId, error);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onSlightError(final long deviceId, final int error) {
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleSlightError(deviceId, error);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onRemoved(long deviceId, int faceId, int groupId) {
        final long j = deviceId;
        final int i = faceId;
        final int i2 = groupId;
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleRemoved(j, i, i2);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onEnumerate(long deviceId, int[] faceIds, int groupId) {
        final long j = deviceId;
        final int[] iArr = faceIds;
        final int i = groupId;
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleEnumerate(j, iArr, i);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onCommandResult(final CommandResult info) {
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.dispatchCommandResult(info);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public void onPreviewStarted() {
        this.mDaemonCallbackQueue.add(new Runnable() {
            public void run() {
                FaceService.this.handleProgressChanged(FaceService.this.mHalDeviceId, 1001);
            }
        });
        if (!this.mHandler.hasMessages(11)) {
            this.mHandler.sendMessageAtFrontOfQueue(this.mHandler.obtainMessage(11));
        }
    }

    public int onFaceFilterSucceeded(byte[] nv21ImageData, int nv21ImageDataWidth, int nv21ImageDataHeight, FaceInfo face, ClientMode mode) {
        final PendingResult<Integer> r = new PendingResult(Integer.valueOf(-1));
        final byte[] bArr = nv21ImageData;
        final int i = nv21ImageDataWidth;
        final int i2 = nv21ImageDataHeight;
        final FaceInfo faceInfo = face;
        final ClientMode clientMode = mode;
        this.mHandler.post(new Runnable() {
            public void run() {
                r.setResult(Integer.valueOf(FaceService.this.handleVerifyFace(bArr, i, i2, faceInfo, clientMode)));
            }
        });
        return ((Integer) r.await()).intValue();
    }

    public void updateScreenOnBlockedState(boolean isBlockedScreenOn, boolean needKeyguardOpaque, long delayTime) {
        boolean isKeyguard;
        synchronized (this.mClientLock) {
            isKeyguard = this.mAuthClient != null ? this.mAuthClient.isKeyguard() : false;
        }
        if (isKeyguard && this.mKeyguardAuthType == 0) {
            if (needKeyguardOpaque && !this.mSupportPowerBlock) {
                LogUtil.d(TAG, "updateScreenOnBlockedState isBlockedScreenOn = " + isBlockedScreenOn);
                this.mKeyguardPolicy.onWakeUp("setOpaqueWhileFailToUnlock");
            }
            if (this.mSupportPowerBlock) {
                LogUtil.d(TAG, "updateScreenOnBlockedState, isBlockedScreenOn = " + isBlockedScreenOn + ", needKeyguardOpaque = " + needKeyguardOpaque);
                if (isBlockedScreenOn) {
                    this.mDisplayManagerInternal.updateScreenOnBlockedState(isBlockedScreenOn);
                } else if (!isBlockedScreenOn && !needKeyguardOpaque) {
                    this.mDisplayManagerInternal.updateScreenOnBlockedState(isBlockedScreenOn);
                } else if (!isBlockedScreenOn && needKeyguardOpaque) {
                    this.mKeyguardPolicy.onWakeUp("setOpaqueWhileFailToUnlock");
                    this.mKeyguardPolicy.setKeyguardVisibility(true);
                    this.mHandler.sendMessageDelayed(13, delayTime);
                }
            }
        }
    }

    public void handleUnBlockScreenOn() {
        LogUtil.d(TAG, "handleUnBlockScreenOn");
        this.mDisplayManagerInternal.updateScreenOnBlockedState(false);
    }

    private boolean isCameraServiceReady() {
        if (ServiceManager.getService(CAMERA_SERVICE_BINDER_NAME) != null) {
            return true;
        }
        LogUtil.d(TAG, "cameraService is not ready");
        return false;
    }

    private long getLockoutAttemptDeadline() {
        long now = SystemClock.elapsedRealtime();
        if (this.mLockoutDeadline == 0) {
            return 0;
        }
        if (this.mLockoutDeadline != 0 && this.mLockoutDeadline < now) {
            setLockoutAttemptDeadline(0);
            return 0;
        } else if (this.mLockoutDeadline <= FAIL_LOCKOUT_TIMEOUT_MS + now) {
            return this.mLockoutDeadline;
        } else {
            setLockoutAttemptDeadline(0);
            return 0;
        }
    }

    private void setLockoutAttemptDeadline(long deadline) {
        this.mLockoutDeadline = deadline;
        LogUtil.w(TAG, "setLockoutAttemptDeadline, mLockoutDeadline = " + this.mLockoutDeadline);
    }

    private int getFailedAttempts() {
        return this.mFailedAttempts;
    }

    private int startGetEnrollmentTotalTimes(IBinder token) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startGetEnrollmentTotalTimes: no faced!");
            return 0;
        }
        try {
            LogUtil.d(TAG, "startGetEnrollmentTotalTimes");
            this.mEnrollmentTotalTimes = daemon.getEnrollmentTotalTimes();
            return this.mEnrollmentTotalTimes;
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startGetEnrollmentTotalTimes failed", e);
            return 0;
        }
    }

    private int getPreViewWidth() {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "getPreViewWidth: no faced!");
            return -1;
        }
        try {
            LogUtil.d(TAG, "getPreViewWidth");
            return daemon.getPreViewWidth();
        } catch (RemoteException e) {
            LogUtil.e(TAG, "getPreViewWidth failed", e);
            return -1;
        }
    }

    private int getPreViewHeight() {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "getPreViewHeight: no faced!");
            return -1;
        }
        try {
            LogUtil.d(TAG, "getPreViewHeight");
            return daemon.getPreViewHeight();
        } catch (RemoteException e) {
            LogUtil.e(TAG, "getPreViewHeight failed", e);
            return -1;
        }
    }

    private void dumpInternal(PrintWriter pw, String[] args) {
        if (args.length == 2 && "<face daemon started>".equals(args[0])) {
            LogUtil.d(TAG, "received faced started msg, pid = " + args[1]);
            this.mHandler.post(new Runnable() {
                public void run() {
                    if (FaceService.this.getFaceDaemon() == null) {
                        LogUtil.e(FaceService.TAG, "dumpInner: no faced!");
                    }
                }
            });
            this.mHealthMonitor.demoProcessSystemReady(Integer.parseInt(args[1]));
            return;
        }
        int opti = 0;
        while (opti < args.length) {
            String opt = args[opti];
            if (opt == null || opt.length() <= 0 || opt.charAt(0) != '-') {
                break;
            }
            opti++;
            String on;
            if ("-h".equals(opt)) {
                if (!IS_REALEASE_VERSION) {
                    pw.println("face service dump options:");
                    pw.println("  [-h] [cmd] ...");
                    pw.println("  cmd may be one of:");
                    pw.println("    l[log]: dynamically adjust face log ");
                    pw.println("    -camera iso=auto,exposure=4  ,  adjust camera parameters ");
                    pw.println("    -enrollTimeout 100  ,  adjust enroll timeout ");
                    pw.println("    -autoVerify 1/0  ,  adjust hacker funtion ");
                    return;
                }
                return;
            } else if ("-camera".equals(opt)) {
                if (!DEBUG) {
                    return;
                }
                if (opti < args.length) {
                    String parameter_args = args[opti];
                    opti++;
                    if (parameter_args != null) {
                        ((FaceDaemonWrapper) this.mDaemonWrapper).updateCameraParameters(pw, parameter_args);
                    }
                    return;
                }
                pw.println("ERROR: Camera parameters is missing.");
                return;
            } else if ("-enrollTimeout".equals(opt)) {
                if (!DEBUG) {
                    return;
                }
                if (opti < args.length) {
                    String enrollTimeout = args[opti];
                    opti++;
                    if (enrollTimeout != null) {
                        ((FaceDaemonWrapper) this.mDaemonWrapper).updateEnrollTimeout(pw, enrollTimeout);
                    }
                    return;
                }
                pw.println("ERROR: enroll timeout parameter is missing.");
                return;
            } else if ("-autoVerify".equals(opt)) {
                if (!DEBUG) {
                    return;
                }
                if (opti < args.length) {
                    on = args[opti];
                    opti++;
                    ((FaceDaemonWrapper) this.mDaemonWrapper).autoVerify(pw, on);
                    return;
                }
                pw.println("ERROR: autoVeify parameters is missing.");
                return;
            } else if ("-dropFrames".equals(opt)) {
                if (!DEBUG) {
                    return;
                }
                if (opti < args.length) {
                    String frames = args[opti];
                    opti++;
                    ((FaceDaemonWrapper) this.mDaemonWrapper).adjustDropFrames(pw, frames);
                    return;
                }
                pw.println("ERROR: dropFrames parameters is missing.");
                return;
            } else if ("-powerblock".equals(opt)) {
                if (opti < args.length) {
                    on = args[opti];
                    opti++;
                    SystemProperties.set(PROP_FACE_POWER_BLOCK, on);
                    this.mSupportPowerBlock = "on".equals(on);
                    return;
                }
                pw.println("ERROR: powerblock parameters is missing.");
                return;
            } else if (!"-perf".equals(opt)) {
                pw.println("Unknown argument: " + opt + "; use -h for help");
            } else if (opti < args.length) {
                on = args[opti];
                opti++;
                DEBUG_PERF = "on".equals(on);
                updateDynamicallyLog(3, DEBUG_PERF);
                return;
            } else {
                pw.println("ERROR: parameters is missing.");
                return;
            }
        }
        if (opti < args.length) {
            String cmd = args[opti];
            opti++;
            if ("log".equals(cmd) || "l".equals(cmd)) {
                dynamicallyConfigLogTag(pw, args);
                return;
            } else if ("debug_switch".equals(cmd)) {
                pw.println("  all=" + DEBUG);
                return;
            } else if ("reset".equals(cmd)) {
                int pid = this.mHealthMonitor.getDemoProcessPid();
                if (pid != -1) {
                    Process.sendSignal(pid, 3);
                }
                return;
            }
        }
        pw.println("DEBUG = " + DEBUG);
        pw.println("DEBUG_PERF = " + DEBUG_PERF);
        pw.println("LogLevel : " + LogUtil.getLevelString());
        pw.println("mFaceSupport = " + this.mFaceSupport);
        pw.println("mSupportPowerBlock = " + this.mSupportPowerBlock);
        pw.println("mFailedAttempts = " + this.mFailedAttempts);
        pw.println("mHalDeviceId = " + this.mHalDeviceId);
        pw.println("mFaceUnlockEnabled = " + this.mFaceUnlockEnabled);
        pw.println("mFaceUnlockAutoWhenScreenOn = " + this.mFaceUnlockAutoWhenScreenOn);
        synchronized (this.mClientLock) {
            pw.println("mEnrollClient = " + this.mEnrollClient);
            pw.println("mAuthClient = " + this.mAuthClient);
            pw.println("mRemoveClient = " + this.mRemoveClient);
        }
        ((FaceDaemonWrapper) this.mDaemonWrapper).dump(pw, args, IElsaManager.EMPTY_PACKAGE);
        pw.println("mFaceUtils dump");
        if (DEBUG) {
            this.mFaceSwitchHelper.dump(pw, args, IElsaManager.EMPTY_PACKAGE);
        }
        JSONObject jsonObj = new JSONObject();
        try {
            JSONArray sets = new JSONArray();
            for (UserInfo user : UserManager.get(getContext()).getUsers()) {
                int userId = user.getUserHandle().getIdentifier();
                int N = this.mFaceUtils.getFacesForUser(this.mContext, userId).size();
                JSONObject set = new JSONObject();
                set.put(DecryptTool.UNLOCK_TYPE_ID, userId);
                set.put("count", N);
                sets.put(set);
            }
            jsonObj.put("prints", sets);
        } catch (JSONException e) {
            LogUtil.e(TAG, "jsonObj formatting failure", e);
        }
        pw.println(jsonObj);
    }

    public void onStart() {
        LogUtil.d(TAG, "onStart");
        publishBinderService("face", new FaceServiceWrapper(this, null));
        int facedPid = this.mHealthMonitor.getDemoProcessPid();
        if (facedPid != -1) {
            this.mHealthMonitor.demoProcessSystemReady(facedPid);
        }
        this.mHandler.post(new Runnable() {
            public void run() {
                IFaceDaemon daemon = FaceService.this.getFaceDaemon();
                LogUtil.v(FaceService.TAG, "Face HAL id: " + FaceService.this.mHalDeviceId);
            }
        });
        publishLocalService(FaceInternal.class, getFacePms().getFaceLocalService());
        listenForUserSwitches();
    }

    private void updateActiveGroup(int userId, String clientPackage) {
        boolean z = false;
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon != null) {
            try {
                userId = getUserOrWorkProfileId(clientPackage, userId);
                if (userId != this.mCurrentUserId) {
                    File faceDir = new File(Environment.getUserSystemDirectory(userId), FACE_DATA_DIR);
                    if (!faceDir.exists()) {
                        if (!faceDir.mkdir()) {
                            LogUtil.v(TAG, "Cannot make directory: " + faceDir.getAbsolutePath());
                            return;
                        } else if (!SELinux.restorecon(faceDir)) {
                            LogUtil.w(TAG, "Restorecons failed. Directory will have wrong label.");
                            return;
                        }
                    }
                    int faceNum = getEnrolledFaceNum(userId);
                    LogUtil.w(TAG, "updateActiveGroup setActiveGroup userId = " + userId);
                    byte[] bytes = faceDir.getAbsolutePath().getBytes();
                    if (faceNum > 0) {
                        z = true;
                    }
                    daemon.setActiveGroup(userId, bytes, z);
                    this.mCurrentUserId = userId;
                }
                this.mCurrentAuthenticatorId = daemon.getAuthenticatorId();
                LogUtil.d(TAG, "updateActiveGroup mCurrentAuthenticatorId = " + this.mCurrentAuthenticatorId);
            } catch (RemoteException e) {
                LogUtil.e(TAG, "Failed to setActiveGroup():", e);
            }
        }
    }

    private int getUserOrWorkProfileId(String clientPackage, int userId) {
        if (isKeyguard(clientPackage) || !isWorkProfile(userId)) {
            return getEffectiveUserId(userId);
        }
        return userId;
    }

    private boolean isWorkProfile(int userId) {
        UserInfo info = this.mUserManager.getUserInfo(userId);
        return info != null ? info.isManagedProfile() : false;
    }

    private void listenForUserSwitches() {
        try {
            ActivityManagerNative.getDefault().registerUserSwitchObserver(new SynchronousUserSwitchObserver() {
                public void onUserSwitching(int newUserId) throws RemoteException {
                    FaceService.this.mHandler.obtainMessage(10, newUserId, 0).sendToTarget();
                }

                public void onUserSwitchComplete(int newUserId) throws RemoteException {
                }

                public void onForegroundProfileSwitch(int newProfileId) {
                }
            }, TAG);
        } catch (RemoteException e) {
            LogUtil.w(TAG, "Failed to listen for user switching event", e);
        }
    }

    public long getAuthenticatorId(String opPackageName) {
        LogUtil.w(TAG, "getAuthenticatorId, opPackageName = " + opPackageName + ", mCurrentAuthenticatorId = " + this.mCurrentAuthenticatorId);
        return this.mCurrentAuthenticatorId;
    }

    public void monitor() {
    }

    protected void dispatchCommandResult(CommandResult info) {
        LogUtil.d(TAG, "dispatchCommandResult");
        synchronized (this.mClientLock) {
            if (this.mCommandClient != null) {
                this.mCommandClient.sendCommandResult(info);
            }
        }
    }

    private void syncTemplates(int[] faceIds, int groupId) {
        if (faceIds == null) {
            LogUtil.w(TAG, "faceIds and groupIds are null");
            return;
        }
        int remainingTemplates = faceIds.length;
        if (groupId != getCurrentUserId()) {
            LogUtil.w(TAG, "template is not beylong to the current user");
            return;
        }
        int faceNum = getEnrolledFaceNum(groupId);
        LogUtil.d(TAG, "syncTemplates started, remainingTemplates = " + remainingTemplates + ", faceNum = " + faceNum + ", groupId = " + groupId);
        for (int i = 0; i < remainingTemplates; i++) {
            LogUtil.d(TAG, "faceIds[" + i + "] = " + faceIds[i]);
        }
        if (remainingTemplates != faceNum) {
            this.mFaceUtils.syncFaceIdForUser(this.mContext, faceIds, groupId);
        } else {
            LogUtil.d(TAG, "templates are synchronized, do nothing");
        }
    }

    private int getCurrentUserId() {
        try {
            return getEffectiveUserId(ActivityManagerNative.getDefault().getCurrentUser().id);
        } catch (RemoteException e) {
            LogUtil.w(TAG, "Failed to get current user id\n");
            return -10000;
        }
    }

    private int getEnrolledFaceNum(int userId) {
        return this.mFaceUtils.getFacesForUser(this.mContext, userId).size();
    }

    private FacePowerManager getFacePms() {
        return FacePowerManager.getFacePowerManager();
    }

    /* JADX WARNING: Missing block: B:13:0x003a, code:
            return r1;
     */
    /* JADX WARNING: Missing block: B:24:0x0053, code:
            return false;
     */
    /* JADX WARNING: Missing block: B:40:0x0080, code:
            return false;
     */
    /* JADX WARNING: Missing block: B:45:0x0087, code:
            return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean tryPreOperation(IBinder token, String operation) {
        boolean z = true;
        LogUtil.d(TAG, "try operation " + operation);
        if (token != null && !token.isBinderAlive()) {
            return false;
        }
        synchronized (this.mClientLock) {
            if (this.mAuthClient != null) {
                IBinder authtoken = this.mAuthClient.mToken;
                if (this.mAuthClient.isKeyguard()) {
                    if (token != authtoken) {
                        z = false;
                    }
                } else if (!(authtoken == token || token == null)) {
                    if (operation.equals(HealthState.CANCELAUTHENTICATE) || operation.equals(HealthState.CANCELENROLLMENT)) {
                    } else {
                        stopPendingOperations(true, false);
                    }
                }
            }
            if (!(this.mEnrollClient == null || this.mEnrollClient.getClientMode() != ClientMode.ENROLL || this.mEnrollClient.mToken == token || token == null)) {
                if (operation.equals(HealthState.CANCELAUTHENTICATE) || operation.equals(HealthState.CANCELENROLLMENT)) {
                } else {
                    stopPendingOperations(true, false);
                }
            }
        }
    }

    void notifyOperationCanceled(IFaceServiceReceiver receiver) {
        if (receiver != null) {
            try {
                receiver.onError(this.mHalDeviceId, 5);
            } catch (RemoteException e) {
                LogUtil.w(TAG, "Failed to send error to receiver: ", e);
            }
        }
    }

    private int startExecuteCommand(IBinder token, boolean restricted, int groupId, IFaceServiceReceiver receiver, String opPackageName, int type) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startExecuteCommand: no faced!");
            return -1;
        }
        if (type == 0) {
            stopPendingOperations(true, false);
            synchronized (this.mClientLock) {
                this.mCommandClient = new ClientMonitor(token, receiver, this.mCurrentUserId, groupId, restricted, opPackageName, ClientMode.ENGINEERING_INFO);
            }
        }
        try {
            LogUtil.w(TAG, "startExecuteCommand opPackageName = " + opPackageName + ", type = " + type);
            int result = daemon.executeCommand(type);
            if (result != 0) {
                LogUtil.w(TAG, "startExecuteCommand failed, result=" + result);
            }
            return result;
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startExecuteCommand failed", e);
            return -1;
        }
    }

    private void stopExecuteCommand(IBinder token, boolean initiatedByClient, int type) {
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "stopExecuteCommand: no faced!");
            return;
        }
        if (type == -1 || type == 0) {
            ClientMonitor client = this.mCommandClient;
            if (client == null || client.mToken != token) {
                LogUtil.w(TAG, "stopExecuteCommand, mCommandClient == null");
                return;
            } else if (!(token == null || token.isBinderAlive())) {
                LogUtil.e(TAG, token + " has died, just skip this stopExecuteCommand");
                return;
            }
        }
        LogUtil.w(TAG, "stopExecuteCommand initiatedByClient = " + initiatedByClient + ", type = " + type);
        if (initiatedByClient) {
            try {
                int result = daemon.cancelCommand(type);
                if (result != 0) {
                    LogUtil.w(TAG, "stopExecuteCommand failed, result = " + result);
                }
            } catch (RemoteException e) {
                LogUtil.e(TAG, "stopExecuteCommand failed", e);
            }
        }
        if (type == -1 || type == 0) {
            removeClient(this.mCommandClient, false);
        }
    }

    private int startGet(IBinder token, int type, String opPackageName) {
        return -1;
    }

    public void systemReady() {
        LogUtil.d(TAG, "systemReady, mFaceSupport = " + this.mFaceSupport);
        this.mSystemReady = true;
        if (this.mFaceSupport) {
            this.mFaceSwitchHelper.initUpdateBroadcastReceiver();
        }
        this.mDisplayManagerInternal = (DisplayManagerInternal) -wrap1(DisplayManagerInternal.class);
        if (!this.mFaceSupport) {
            SystemProperties.set("ctl.stop", "faced");
        }
    }

    private int startSetPreviewSurface(Surface surface) {
        LogUtil.d(TAG, "startSetPreviewSurface Surface = " + surface);
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startSetPreviewSurface: no faced!");
            return 0;
        }
        try {
            LogUtil.w(TAG, "startSetPreviewSurface");
            int result = daemon.setPreviewSurface(surface);
            if (result != 0) {
                LogUtil.w(TAG, "startSetPreviewSurface with surface = " + surface + " failed, result=" + result);
                handleError(this.mHalDeviceId, 1);
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startSetPreviewSurface failed", e);
        }
        return 1;
    }

    public void startSetPreviewFrame(IBinder token, Rect rect) {
        LogUtil.d(TAG, "startSetPreviewFrame rect = " + rect);
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "startSetPreviewFrame: no faced!");
            return;
        }
        try {
            LogUtil.w(TAG, "startSetPreviewFrame");
            int result = daemon.setPreviewFrame(rect);
            if (result != 0) {
                LogUtil.w(TAG, "startSetPreviewFrame with rect = " + rect + " failed, result=" + result);
                handleError(this.mHalDeviceId, 1);
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "startSetPreviewFrame failed", e);
        }
    }

    private void handlePreRecognition(boolean needStartPreview) {
        LogUtil.d(TAG, "handlePreRecognition needStartPreview = " + needStartPreview);
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon == null) {
            LogUtil.w(TAG, "handlePreRecognition: no faced!");
            return;
        }
        try {
            LogUtil.w(TAG, "handlePreRecognition");
            int result = daemon.preRecognition(needStartPreview);
            if (result != 0) {
                LogUtil.w(TAG, "handlePreRecognition with failed, result=" + result);
                handleError(this.mHalDeviceId, 1);
            }
        } catch (RemoteException e) {
            LogUtil.e(TAG, "handlePreRecognition failed", e);
        }
    }

    private void startPreRecognition(String wakeupReason) {
        LogUtil.d(TAG, "startPreRecognition");
        if (this.mHalDeviceId != 0 ? isCameraServiceReady() : false) {
            if (this.mFaceUnlockEnabled) {
                long startTime = SystemClock.uptimeMillis();
                if (this.mFaceUnlockAutoWhenScreenOn) {
                    this.mKeyguardPolicy.onWakeUp(wakeupReason);
                }
                TimeUtils.calculateTime(TAG, "onWakeUp", SystemClock.uptimeMillis() - startTime);
            }
            return;
        }
        LogUtil.d(TAG, "hardware not detected; disallowing openCamera");
    }

    private void stopPreview() {
        if (this.mDaemonWrapper != null) {
            ((FaceDaemonWrapper) this.mDaemonWrapper).stopPreview();
        }
    }

    private void cancelRecognition() {
        if (this.mDaemonWrapper != null) {
            ((FaceDaemonWrapper) this.mDaemonWrapper).cancelRecognition();
        }
    }

    private void stopPreRecognition() {
        LogUtil.d(TAG, "stopPreRecognition");
        this.mKeyguardPolicy.onWakeUp("cancelRecognitionByScreenOff");
    }

    protected void dynamicallyConfigLogTag(PrintWriter pw, String[] args) {
        boolean logOn = false;
        boolean needUpdateLog = false;
        boolean needUpdateImage = false;
        pw.println("dynamicallyConfigLogTag, args.length:" + args.length);
        for (int index = 0; index < args.length; index++) {
            pw.println("dynamicallyConfigLogTag, args[" + index + "]: " + args[index]);
        }
        if (args.length != 3) {
            pw.println("********** Invalid argument! Get detail help as bellow: **********");
            logoutTagConfigHelp(pw);
            return;
        }
        if ("all".equals(args[1])) {
            boolean isImageSaveOn;
            if ("0".equals(args[2])) {
                logOn = false;
                needUpdateLog = true;
                needUpdateImage = true;
            } else if (LocationManagerService.OPPO_FAKE_LOCATOIN_SWITCH_ON.equals(args[2])) {
                logOn = true;
                needUpdateLog = true;
                needUpdateImage = true;
            } else if ("2".equals(args[2])) {
                mImageOn = true;
                needUpdateImage = true;
            } else if ("3".equals(args[2])) {
                mImageOn = false;
                needUpdateImage = true;
            }
            if (needUpdateLog) {
                DEBUG = logOn;
                SenseTimeSdkManager.DEBUG = logOn;
                if (this.mIsLogOpened != logOn) {
                    this.mIsLogOpened = logOn;
                    updateDynamicallyLog(1, logOn);
                }
            }
            if (needUpdateImage && IS_REALEASE_VERSION) {
                isImageSaveOn = (this.mIsEncryptApp || !mImageOn) ? false : DEBUG;
                if (isImageSaveOn != this.mIsImageSaveEnable) {
                    this.mIsImageSaveEnable = isImageSaveOn;
                    updateDynamicallyLog(2, this.mIsImageSaveEnable);
                }
            }
            if ((needUpdateLog || needUpdateImage) && !IS_REALEASE_VERSION) {
                isImageSaveOn = DEBUG;
                if (isImageSaveOn != this.mIsImageSaveEnable) {
                    this.mIsImageSaveEnable = isImageSaveOn;
                    updateDynamicallyLog(2, this.mIsImageSaveEnable);
                }
            }
        }
    }

    private void updateDynamicallyLog(int type, boolean isOn) {
        int i = 1;
        if (type == 1) {
            LogUtil.dynamicallyConfigLog(isOn);
        }
        if (type == 2) {
            ImageUtil.getImageUtil().updateDebugSwitch(2, isOn);
        }
        IFaceDaemon daemon = getFaceDaemon();
        if (daemon != null) {
            if (!isOn) {
                i = 0;
            }
            try {
                daemon.dynamicallyConfigLog(type, i);
            } catch (RemoteException e) {
                LogUtil.e(TAG, "dynamicallyConfigLog failed", e);
            }
        }
    }

    protected void logoutTagConfigHelp(PrintWriter pw) {
        pw.println("********************** Help begin:**********************");
        pw.println("1. open all log in FaceService");
        pw.println("cmd: dumpsys face log all 0/1/");
        pw.println("0: close the face detect log");
        pw.println("1: open the face detect log");
        pw.println("----------------------------------");
        pw.println("********************** Help end.  **********************");
    }

    private void updateAppSecrecyState() {
        this.mIsEncryptApp = this.mSecrecyManager.getSecrecyState(2);
    }

    private boolean isKeyguardLockoutByOtherUnlockWay() {
        long now = SystemClock.elapsedRealtime();
        if (this.mFingerprintManager.getFailedAttempts() >= 5 || this.mFingerprintManager.getLockoutAttemptDeadline() - now > 0 || this.mLockPatternUtils.getLockoutAttemptDeadline(this.mCurrentUserId) - now > 0) {
            return true;
        }
        return false;
    }

    public void onWakeUp(final String wakeupReason) {
        LogUtil.d(TAG, "onWakeUp wakeupReason = " + wakeupReason + ", mFaceUnlockEnabled = " + this.mFaceUnlockEnabled + ", mFaceUnlockAutoWhenScreenOn = " + this.mFaceUnlockAutoWhenScreenOn + ", mIsFirstUnlockedInPasswordOnlyMode = " + this.mIsFirstUnlockedInPasswordOnlyMode);
        if (this.mFaceSupport) {
            if (this.mHandler.hasMessages(13)) {
                this.mHandler.removeMessage(13);
            }
            if (inLockoutMode()) {
                LogUtil.d(TAG, "onWakeUp in lockout mode; disallowing openCamera");
            } else {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        FaceService.this.startPreRecognition(wakeupReason);
                    }
                });
            }
        }
    }

    public void onGoToSleep() {
        LogUtil.d(TAG, "onGoToSleep");
        if (this.mFaceSupport) {
            if (this.mHandler.hasMessages(13)) {
                this.mHandler.removeMessage(13);
            }
            if (inLockoutMode()) {
                LogUtil.d(TAG, "onGoToSleep in lockout mode; disallowing closeCamera");
            } else {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        FaceService.this.stopPreRecognition();
                    }
                });
            }
        }
    }

    public void onWakeUpFinish() {
        LogUtil.d(TAG, "onWakeUpFinish");
    }

    public void onGoToSleepFinish() {
        LogUtil.d(TAG, "onGoToSleepFinish");
    }

    public void onScreenOnUnBlockedByOther(final String unBlockedReason) {
        LogUtil.d(TAG, "onScreenOnUnBlockedByOther, unBlockedReason = " + unBlockedReason);
        if (this.mFaceSupport) {
            if (this.mHandler.hasMessages(13)) {
                this.mHandler.removeMessage(13);
            }
            if (inLockoutMode()) {
                LogUtil.d(TAG, "onScreenOnUnBlockedByOther in lockout mode; disallowing openCamera");
            } else {
                this.mHandler.post(new Runnable() {
                    public void run() {
                        FaceService.this.startPreRecognition(unBlockedReason);
                    }
                });
            }
        }
    }

    public void onFaceSwitchUpdate() {
        LogUtil.d(TAG, "onFaceSwitchUpdate");
    }

    public void onFaceUpdateFromProvider() {
        LogUtil.d(TAG, "onFaceUpdateFromProvider");
        if (getFaceDaemon() == null) {
            LogUtil.e(TAG, "daemon is null ,reject updateRusNativeData");
            return;
        }
        try {
            this.mDaemon.updateRusNativeData(this.mFaceSwitchHelper.getRusNativeData());
        } catch (RemoteException e) {
            LogUtil.e(TAG, "updateRusNativeData failed", e);
        }
    }
}
