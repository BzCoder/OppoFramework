package com.android.server.display;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Display;
import android.view.Display.HdrCapabilities;
import android.view.Display.Mode;
import android.view.DisplayEventReceiver;
import android.view.SurfaceControl;
import android.view.SurfaceControl.PhysicalDisplayInfo;
import com.android.server.LocalServices;
import com.android.server.display.DisplayAdapter.Listener;
import com.android.server.display.DisplayManagerService.SyncRoot;
import com.android.server.lights.Light;
import com.android.server.lights.LightsManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*  JADX ERROR: NullPointerException in pass: ReSugarCode
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
    	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ExtractFieldInit.checkStaticFieldsInit(ExtractFieldInit.java:58)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:44)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:42)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
final class LocalDisplayAdapter extends DisplayAdapter {
    private static final int[] BUILT_IN_DISPLAY_IDS_TO_SCAN = null;
    private static final boolean DEBUG = true;
    private static final String PROPERTY_EMULATOR_CIRCULAR = "ro.emulator.circular";
    private static final String TAG = "LocalDisplayAdapter";
    private static final String UNIQUE_ID_PREFIX = "local:";
    private final SparseArray<LocalDisplayDevice> mDevices;
    private HotplugDisplayEventReceiver mHotplugReceiver;

    private static final class DisplayModeRecord {
        public final Mode mMode;

        public DisplayModeRecord(PhysicalDisplayInfo phys) {
            this.mMode = DisplayAdapter.createMode(phys.width, phys.height, phys.refreshRate);
        }

        public boolean hasMatchingMode(PhysicalDisplayInfo info) {
            int modeRefreshRate = Float.floatToIntBits(this.mMode.getRefreshRate());
            int displayInfoRefreshRate = Float.floatToIntBits(info.refreshRate);
            if (this.mMode.getPhysicalWidth() == info.width && this.mMode.getPhysicalHeight() == info.height && modeRefreshRate == displayInfoRefreshRate) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "DisplayModeRecord{mMode=" + this.mMode + "}";
        }
    }

    private final class HotplugDisplayEventReceiver extends DisplayEventReceiver {
        public HotplugDisplayEventReceiver(Looper looper) {
            super(looper);
        }

        public void onHotplug(long timestampNanos, int builtInDisplayId, boolean connected) {
            synchronized (LocalDisplayAdapter.this.getSyncRoot()) {
                if (connected) {
                    LocalDisplayAdapter.this.tryConnectDisplayLocked(builtInDisplayId);
                } else {
                    LocalDisplayAdapter.this.tryDisconnectDisplayLocked(builtInDisplayId);
                }
            }
        }
    }

    private final class LocalDisplayDevice extends DisplayDevice {
        /* renamed from: -assertionsDisabled */
        static final /* synthetic */ boolean f12-assertionsDisabled = false;
        final /* synthetic */ boolean $assertionsDisabled;
        private int mActiveColorMode;
        private boolean mActiveColorModeInvalid;
        private int mActiveModeId;
        private boolean mActiveModeInvalid;
        private int mActivePhysIndex;
        private final Light mBacklight;
        private int mBrightness;
        private final int mBuiltInDisplayId;
        private int mDefaultModeId;
        private PhysicalDisplayInfo[] mDisplayInfos;
        private boolean mHavePendingChanges;
        private HdrCapabilities mHdrCapabilities;
        private DisplayDeviceInfo mInfo;
        private int mState;
        private final ArrayList<Integer> mSupportedColorModes;
        private final SparseArray<DisplayModeRecord> mSupportedModes;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: com.android.server.display.LocalDisplayAdapter.LocalDisplayDevice.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e9
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: com.android.server.display.LocalDisplayAdapter.LocalDisplayDevice.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.display.LocalDisplayAdapter.LocalDisplayDevice.<clinit>():void");
        }

        public LocalDisplayDevice(IBinder displayToken, int builtInDisplayId, PhysicalDisplayInfo[] physicalDisplayInfos, int activeDisplayInfo, int[] colorModes, int activeColorMode) {
            super(LocalDisplayAdapter.this, displayToken, LocalDisplayAdapter.UNIQUE_ID_PREFIX + builtInDisplayId);
            this.mSupportedModes = new SparseArray();
            this.mSupportedColorModes = new ArrayList();
            this.mState = 0;
            this.mBrightness = -1;
            this.mBuiltInDisplayId = builtInDisplayId;
            updatePhysicalDisplayInfoLocked(physicalDisplayInfos, activeDisplayInfo, colorModes, activeColorMode);
            updateColorModesLocked(colorModes, activeColorMode);
            if (this.mBuiltInDisplayId == 0) {
                this.mBacklight = ((LightsManager) LocalServices.getService(LightsManager.class)).getLight(0);
            } else {
                this.mBacklight = null;
            }
            this.mHdrCapabilities = SurfaceControl.getHdrCapabilities(displayToken);
        }

        public boolean hasStableUniqueId() {
            return true;
        }

        public boolean updatePhysicalDisplayInfoLocked(PhysicalDisplayInfo[] physicalDisplayInfos, int activeDisplayInfo, int[] colorModes, int activeColorMode) {
            int i;
            DisplayModeRecord record;
            this.mDisplayInfos = (PhysicalDisplayInfo[]) Arrays.copyOf(physicalDisplayInfos, physicalDisplayInfos.length);
            this.mActivePhysIndex = activeDisplayInfo;
            ArrayList<DisplayModeRecord> records = new ArrayList();
            boolean modesAdded = false;
            for (PhysicalDisplayInfo info : physicalDisplayInfos) {
                boolean existingMode = false;
                for (int j = 0; j < records.size(); j++) {
                    if (((DisplayModeRecord) records.get(j)).hasMatchingMode(info)) {
                        existingMode = true;
                        break;
                    }
                }
                if (!existingMode) {
                    record = findDisplayModeRecord(info);
                    if (record == null) {
                        record = new DisplayModeRecord(info);
                        modesAdded = true;
                    }
                    records.add(record);
                }
            }
            DisplayModeRecord activeRecord = null;
            for (i = 0; i < records.size(); i++) {
                record = (DisplayModeRecord) records.get(i);
                if (record.hasMatchingMode(physicalDisplayInfos[activeDisplayInfo])) {
                    activeRecord = record;
                    break;
                }
            }
            if (!(this.mActiveModeId == 0 || this.mActiveModeId == activeRecord.mMode.getModeId())) {
                this.mActiveModeInvalid = true;
                LocalDisplayAdapter.this.sendTraversalRequestLocked();
            }
            if (!(records.size() == this.mSupportedModes.size() ? modesAdded : true)) {
                return false;
            }
            this.mHavePendingChanges = true;
            this.mSupportedModes.clear();
            for (DisplayModeRecord record2 : records) {
                this.mSupportedModes.put(record2.mMode.getModeId(), record2);
            }
            if (findDisplayInfoIndexLocked(this.mDefaultModeId) < 0) {
                if (this.mDefaultModeId != 0) {
                    Slog.w(LocalDisplayAdapter.TAG, "Default display mode no longer available, using currently active mode as default.");
                }
                this.mDefaultModeId = activeRecord.mMode.getModeId();
            }
            if (this.mSupportedModes.indexOfKey(this.mActiveModeId) < 0) {
                if (this.mActiveModeId != 0) {
                    Slog.w(LocalDisplayAdapter.TAG, "Active display mode no longer available, reverting to default mode.");
                }
                this.mActiveModeId = this.mDefaultModeId;
                this.mActiveModeInvalid = true;
            }
            LocalDisplayAdapter.this.sendTraversalRequestLocked();
            return true;
        }

        private boolean updateColorModesLocked(int[] colorModes, int activeColorMode) {
            boolean colorModesChanged;
            List<Integer> pendingColorModes = new ArrayList();
            boolean colorModesAdded = false;
            for (int colorMode : colorModes) {
                if (!this.mSupportedColorModes.contains(Integer.valueOf(colorMode))) {
                    colorModesAdded = true;
                }
                pendingColorModes.add(Integer.valueOf(colorMode));
            }
            if (pendingColorModes.size() == this.mSupportedColorModes.size()) {
                colorModesChanged = colorModesAdded;
            } else {
                colorModesChanged = true;
            }
            if (!colorModesChanged) {
                return false;
            }
            this.mHavePendingChanges = true;
            this.mSupportedColorModes.clear();
            this.mSupportedColorModes.addAll(pendingColorModes);
            Collections.sort(this.mSupportedColorModes);
            if (!this.mSupportedColorModes.contains(Integer.valueOf(this.mActiveColorMode))) {
                if (this.mActiveColorMode != 0) {
                    Slog.w(LocalDisplayAdapter.TAG, "Active color mode no longer available, reverting to default mode.");
                    this.mActiveColorMode = 0;
                    this.mActiveColorModeInvalid = true;
                } else if (this.mSupportedColorModes.isEmpty()) {
                    Slog.e(LocalDisplayAdapter.TAG, "No color modes available!");
                } else {
                    Slog.e(LocalDisplayAdapter.TAG, "Default and active color mode is no longer available! Reverting to first available mode.");
                    this.mActiveColorMode = ((Integer) this.mSupportedColorModes.get(0)).intValue();
                    this.mActiveColorModeInvalid = true;
                }
            }
            return true;
        }

        private DisplayModeRecord findDisplayModeRecord(PhysicalDisplayInfo info) {
            for (int i = 0; i < this.mSupportedModes.size(); i++) {
                DisplayModeRecord record = (DisplayModeRecord) this.mSupportedModes.valueAt(i);
                if (record.hasMatchingMode(info)) {
                    return record;
                }
            }
            return null;
        }

        public void applyPendingDisplayDeviceInfoChangesLocked() {
            if (this.mHavePendingChanges) {
                this.mInfo = null;
                this.mHavePendingChanges = false;
            }
        }

        public DisplayDeviceInfo getDisplayDeviceInfoLocked() {
            if (this.mInfo == null) {
                int i;
                PhysicalDisplayInfo phys = this.mDisplayInfos[this.mActivePhysIndex];
                this.mInfo = new DisplayDeviceInfo();
                this.mInfo.width = phys.width;
                this.mInfo.height = phys.height;
                this.mInfo.modeId = this.mActiveModeId;
                this.mInfo.defaultModeId = this.mDefaultModeId;
                this.mInfo.supportedModes = new Mode[this.mSupportedModes.size()];
                for (i = 0; i < this.mSupportedModes.size(); i++) {
                    this.mInfo.supportedModes[i] = ((DisplayModeRecord) this.mSupportedModes.valueAt(i)).mMode;
                }
                this.mInfo.colorMode = this.mActiveColorMode;
                this.mInfo.supportedColorModes = new int[this.mSupportedColorModes.size()];
                for (i = 0; i < this.mSupportedColorModes.size(); i++) {
                    this.mInfo.supportedColorModes[i] = ((Integer) this.mSupportedColorModes.get(i)).intValue();
                }
                this.mInfo.hdrCapabilities = this.mHdrCapabilities;
                this.mInfo.appVsyncOffsetNanos = phys.appVsyncOffsetNanos;
                this.mInfo.presentationDeadlineNanos = phys.presentationDeadlineNanos;
                this.mInfo.state = this.mState;
                this.mInfo.uniqueId = getUniqueId();
                if (phys.secure) {
                    this.mInfo.flags = 12;
                }
                Resources res = LocalDisplayAdapter.this.getContext().getResources();
                DisplayDeviceInfo displayDeviceInfo;
                if (this.mBuiltInDisplayId == 0) {
                    this.mInfo.name = res.getString(17040658);
                    displayDeviceInfo = this.mInfo;
                    displayDeviceInfo.flags |= 3;
                    if (res.getBoolean(17957029) || (Build.IS_EMULATOR && SystemProperties.getBoolean(LocalDisplayAdapter.PROPERTY_EMULATOR_CIRCULAR, false))) {
                        displayDeviceInfo = this.mInfo;
                        displayDeviceInfo.flags |= 256;
                    }
                    this.mInfo.type = 1;
                    this.mInfo.densityDpi = (int) ((phys.density * 160.0f) + 0.5f);
                    this.mInfo.xDpi = phys.xDpi;
                    this.mInfo.yDpi = phys.yDpi;
                    this.mInfo.touch = 1;
                } else {
                    this.mInfo.type = 2;
                    displayDeviceInfo = this.mInfo;
                    displayDeviceInfo.flags |= 64;
                    this.mInfo.name = LocalDisplayAdapter.this.getContext().getResources().getString(17040659);
                    this.mInfo.touch = 2;
                    this.mInfo.setAssumedDensityForExternalDisplay(phys.width, phys.height);
                    if ("portrait".equals(SystemProperties.get("persist.demo.hdmirotation"))) {
                        this.mInfo.rotation = 3;
                    }
                    if (SystemProperties.getBoolean("persist.demo.hdmirotates", false)) {
                        displayDeviceInfo = this.mInfo;
                        displayDeviceInfo.flags |= 2;
                    }
                    if (!res.getBoolean(17956985)) {
                        displayDeviceInfo = this.mInfo;
                        displayDeviceInfo.flags |= 128;
                    }
                }
            }
            return this.mInfo;
        }

        public Runnable requestDisplayStateLocked(int state, int brightness) {
            Object obj = 1;
            if (!f12-assertionsDisabled) {
                if (state == 1 && brightness != 0) {
                    obj = null;
                }
                if (obj == null) {
                    throw new AssertionError();
                }
            }
            boolean stateChanged = this.mState != state;
            final boolean brightnessChanged = (this.mBrightness == brightness || this.mBacklight == null) ? false : true;
            if (!stateChanged && !brightnessChanged) {
                return null;
            }
            final int displayId = this.mBuiltInDisplayId;
            final IBinder token = getDisplayTokenLocked();
            final int oldState = this.mState;
            if (stateChanged) {
                this.mState = state;
                updateDeviceInfoLocked();
            }
            if (brightnessChanged) {
                this.mBrightness = brightness;
            }
            final int i = state;
            final int i2 = brightness;
            return new Runnable() {
                public void run() {
                    int currentState = oldState;
                    if (Display.isSuspendedState(oldState) || oldState == 0) {
                        if (!Display.isSuspendedState(i)) {
                            setDisplayState(i);
                            currentState = i;
                        } else if (i == 4 || oldState == 4) {
                            setDisplayState(3);
                            currentState = 3;
                        } else {
                            return;
                        }
                    }
                    if (brightnessChanged) {
                        setDisplayBrightness(i2);
                    }
                    if (i != currentState) {
                        setDisplayState(i);
                    }
                }

                private void setDisplayState(int state) {
                    Slog.d(LocalDisplayAdapter.TAG, "setDisplayState(id=" + displayId + ", state=" + Display.stateToString(state) + ")");
                    Trace.traceBegin(524288, "setDisplayState(id=" + displayId + ", state=" + Display.stateToString(state) + ")");
                    try {
                        int mode = LocalDisplayAdapter.getPowerModeForState(state);
                        OppoBrightUtils oppoBrightUtils = OppoBrightUtils.getInstance();
                        if (mode == 2) {
                            OppoBrightUtils.mBrightnessNoAnimation = true;
                        }
                        SurfaceControl.setDisplayPowerMode(token, mode);
                    } finally {
                        Trace.traceEnd(524288);
                    }
                }

                private void setDisplayBrightness(int brightness) {
                    Slog.d(LocalDisplayAdapter.TAG, "setDisplayBrightness(id=" + displayId + ", brightness=" + brightness + ")");
                    Trace.traceBegin(524288, "setDisplayBrightness(id=" + displayId + ", brightness=" + brightness + ")");
                    try {
                        LocalDisplayDevice.this.mBacklight.setBrightness(brightness);
                    } finally {
                        Trace.traceEnd(524288);
                    }
                }
            };
        }

        public void requestDisplayModesInTransactionLocked(int colorMode, int modeId) {
            if (requestModeInTransactionLocked(modeId) || requestColorModeInTransactionLocked(colorMode)) {
                updateDeviceInfoLocked();
            }
        }

        public boolean requestModeInTransactionLocked(int modeId) {
            if (modeId == 0) {
                modeId = this.mDefaultModeId;
            } else if (this.mSupportedModes.indexOfKey(modeId) < 0) {
                Slog.w(LocalDisplayAdapter.TAG, "Requested mode " + modeId + " is not supported by this display," + " reverting to default display mode.");
                modeId = this.mDefaultModeId;
            }
            int physIndex = findDisplayInfoIndexLocked(modeId);
            if (physIndex < 0) {
                Slog.w(LocalDisplayAdapter.TAG, "Requested mode ID " + modeId + " not available," + " trying with default mode ID");
                modeId = this.mDefaultModeId;
                physIndex = findDisplayInfoIndexLocked(modeId);
            }
            if (this.mActivePhysIndex == physIndex) {
                return false;
            }
            SurfaceControl.setActiveConfig(getDisplayTokenLocked(), physIndex);
            this.mActivePhysIndex = physIndex;
            this.mActiveModeId = modeId;
            this.mActiveModeInvalid = false;
            return true;
        }

        public boolean requestColorModeInTransactionLocked(int colorMode) {
            if (this.mActiveColorMode == colorMode) {
                return false;
            }
            if (this.mSupportedColorModes.contains(Integer.valueOf(colorMode))) {
                SurfaceControl.setActiveColorMode(getDisplayTokenLocked(), colorMode);
                this.mActiveColorMode = colorMode;
                this.mActiveColorModeInvalid = false;
                return true;
            }
            Slog.w(LocalDisplayAdapter.TAG, "Unable to find color mode " + colorMode + ", ignoring request.");
            return false;
        }

        public void dumpLocked(PrintWriter pw) {
            int i;
            super.dumpLocked(pw);
            pw.println("mBuiltInDisplayId=" + this.mBuiltInDisplayId);
            pw.println("mActivePhysIndex=" + this.mActivePhysIndex);
            pw.println("mActiveModeId=" + this.mActiveModeId);
            pw.println("mActiveColorMode=" + this.mActiveColorMode);
            pw.println("mState=" + Display.stateToString(this.mState));
            pw.println("mBrightness=" + this.mBrightness);
            pw.println("mBacklight=" + this.mBacklight);
            pw.println("mDisplayInfos=");
            for (Object obj : this.mDisplayInfos) {
                pw.println("  " + obj);
            }
            pw.println("mSupportedModes=");
            for (i = 0; i < this.mSupportedModes.size(); i++) {
                pw.println("  " + this.mSupportedModes.valueAt(i));
            }
            pw.print("mSupportedColorModes=[");
            for (i = 0; i < this.mSupportedColorModes.size(); i++) {
                if (i != 0) {
                    pw.print(", ");
                }
                pw.print(this.mSupportedColorModes.get(i));
            }
            pw.println("]");
        }

        private int findDisplayInfoIndexLocked(int modeId) {
            DisplayModeRecord record = (DisplayModeRecord) this.mSupportedModes.get(modeId);
            if (record != null) {
                for (int i = 0; i < this.mDisplayInfos.length; i++) {
                    if (record.hasMatchingMode(this.mDisplayInfos[i])) {
                        return i;
                    }
                }
            }
            return -1;
        }

        private void updateDeviceInfoLocked() {
            this.mInfo = null;
            LocalDisplayAdapter.this.sendDisplayDeviceEventLocked(this, 2);
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.server.display.LocalDisplayAdapter.<clinit>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.server.display.LocalDisplayAdapter.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.display.LocalDisplayAdapter.<clinit>():void");
    }

    public LocalDisplayAdapter(SyncRoot syncRoot, Context context, Handler handler, Listener listener) {
        super(syncRoot, context, handler, listener, TAG);
        this.mDevices = new SparseArray();
    }

    public void registerLocked() {
        super.registerLocked();
        this.mHotplugReceiver = new HotplugDisplayEventReceiver(getHandler().getLooper());
        for (int builtInDisplayId : BUILT_IN_DISPLAY_IDS_TO_SCAN) {
            tryConnectDisplayLocked(builtInDisplayId);
        }
    }

    private void tryConnectDisplayLocked(int builtInDisplayId) {
        IBinder displayToken = SurfaceControl.getBuiltInDisplay(builtInDisplayId);
        if (displayToken != null) {
            PhysicalDisplayInfo[] configs = SurfaceControl.getDisplayConfigs(displayToken);
            if (configs == null) {
                Slog.w(TAG, "No valid configs found for display device " + builtInDisplayId);
                return;
            }
            int activeConfig = SurfaceControl.getActiveConfig(displayToken);
            if (activeConfig < 0) {
                Slog.w(TAG, "No active config found for display device " + builtInDisplayId);
                return;
            }
            int activeColorMode = SurfaceControl.getActiveColorMode(displayToken);
            if (activeColorMode < 0) {
                Slog.w(TAG, "Unable to get active color mode for display device " + builtInDisplayId);
                activeColorMode = -1;
            }
            int[] colorModes = SurfaceControl.getDisplayColorModes(displayToken);
            LocalDisplayDevice device = (LocalDisplayDevice) this.mDevices.get(builtInDisplayId);
            if (device == null) {
                device = new LocalDisplayDevice(displayToken, builtInDisplayId, configs, activeConfig, colorModes, activeColorMode);
                this.mDevices.put(builtInDisplayId, device);
                sendDisplayDeviceEventLocked(device, 1);
            } else if (device.updatePhysicalDisplayInfoLocked(configs, activeConfig, colorModes, activeColorMode)) {
                sendDisplayDeviceEventLocked(device, 2);
            }
        }
    }

    private void tryDisconnectDisplayLocked(int builtInDisplayId) {
        LocalDisplayDevice device = (LocalDisplayDevice) this.mDevices.get(builtInDisplayId);
        if (device != null) {
            this.mDevices.remove(builtInDisplayId);
            sendDisplayDeviceEventLocked(device, 3);
        }
    }

    static int getPowerModeForState(int state) {
        switch (state) {
            case 1:
                return 0;
            case 3:
                return 1;
            case 4:
                return 3;
            default:
                return 2;
        }
    }
}
