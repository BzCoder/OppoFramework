package com.android.internal.inputmethod;

import android.app.AppOpsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.LocaleList;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.text.TextUtils.SimpleStringSplitter;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Pair;
import android.util.Printer;
import android.util.Slog;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodSubtype;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.TextServicesManager;
import com.android.internal.R;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.inputmethod.LocaleUtils.LocaleExtractor;
import com.android.internal.telephony.PhoneConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

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
    	at jadx.core.utils.BlockUtils.isAllBlocksEmpty(BlockUtils.java:546)
    	at jadx.core.dex.visitors.ExtractFieldInit.getConstructorsList(ExtractFieldInit.java:221)
    	at jadx.core.dex.visitors.ExtractFieldInit.moveCommonFieldsInit(ExtractFieldInit.java:121)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:46)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:42)
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
public class InputMethodUtils {
    public static final boolean DEBUG = false;
    private static final Locale ENGLISH_LOCALE = null;
    private static final char INPUT_METHOD_SEPARATOR = ':';
    private static final char INPUT_METHOD_SUBTYPE_SEPARATOR = ';';
    private static final Locale LOCALE_EN_GB = null;
    private static final Locale LOCALE_EN_US = null;
    public static final int NOT_A_SUBTYPE_ID = -1;
    private static final String NOT_A_SUBTYPE_ID_STR = null;
    private static final Locale[] SEARCH_ORDER_OF_FALLBACK_LOCALES = null;
    public static final String SUBTYPE_MODE_ANY = null;
    public static final String SUBTYPE_MODE_KEYBOARD = "keyboard";
    public static final String SUBTYPE_MODE_VOICE = "voice";
    private static final String TAG = "InputMethodUtils";
    private static final String TAG_ASCII_CAPABLE = "AsciiCapable";
    private static final String TAG_ENABLED_WHEN_DEFAULT_IS_NOT_ASCII_CAPABLE = "EnabledWhenDefaultIsNotAsciiCapable";
    private static final Object sCacheLock = null;
    @GuardedBy("sCacheLock")
    private static InputMethodInfo sCachedInputMethodInfo;
    @GuardedBy("sCacheLock")
    private static ArrayList<InputMethodSubtype> sCachedResult;
    @GuardedBy("sCacheLock")
    private static LocaleList sCachedSystemLocales;
    private static final LocaleExtractor<InputMethodSubtype> sSubtypeToLocale = null;

    private static final class InputMethodListBuilder {
        private final LinkedHashSet<InputMethodInfo> mInputMethodSet;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e8 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.<init>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
            	at java.lang.Iterable.forEach(Iterable.java:75)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e8
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 10 more
            */
        private InputMethodListBuilder() {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e8 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.<init>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.<init>():void");
        }

        /* synthetic */ InputMethodListBuilder(InputMethodListBuilder inputMethodListBuilder) {
            this();
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.build():java.util.ArrayList<android.view.inputmethod.InputMethodInfo>, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
            	at java.lang.Iterable.forEach(Iterable.java:75)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 10 more
            */
        public java.util.ArrayList<android.view.inputmethod.InputMethodInfo> build() {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.build():java.util.ArrayList<android.view.inputmethod.InputMethodInfo>, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.build():java.util.ArrayList<android.view.inputmethod.InputMethodInfo>");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.fillAuxiliaryImes(java.util.ArrayList, android.content.Context):com.android.internal.inputmethod.InputMethodUtils$InputMethodListBuilder, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
            	at java.lang.Iterable.forEach(Iterable.java:75)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 10 more
            */
        public com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder fillAuxiliaryImes(java.util.ArrayList<android.view.inputmethod.InputMethodInfo> r1, android.content.Context r2) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.fillAuxiliaryImes(java.util.ArrayList, android.content.Context):com.android.internal.inputmethod.InputMethodUtils$InputMethodListBuilder, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.fillAuxiliaryImes(java.util.ArrayList, android.content.Context):com.android.internal.inputmethod.InputMethodUtils$InputMethodListBuilder");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.fillImes(java.util.ArrayList, android.content.Context, boolean, java.util.Locale, boolean, java.lang.String):com.android.internal.inputmethod.InputMethodUtils$InputMethodListBuilder, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
            	at java.lang.Iterable.forEach(Iterable.java:75)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e9
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 10 more
            */
        public com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder fillImes(java.util.ArrayList<android.view.inputmethod.InputMethodInfo> r1, android.content.Context r2, boolean r3, java.util.Locale r4, boolean r5, java.lang.String r6) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.fillImes(java.util.ArrayList, android.content.Context, boolean, java.util.Locale, boolean, java.lang.String):com.android.internal.inputmethod.InputMethodUtils$InputMethodListBuilder, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.fillImes(java.util.ArrayList, android.content.Context, boolean, java.util.Locale, boolean, java.lang.String):com.android.internal.inputmethod.InputMethodUtils$InputMethodListBuilder");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.isEmpty():boolean, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
            	at jadx.core.ProcessClass.process(ProcessClass.java:29)
            	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
            	at java.lang.Iterable.forEach(Iterable.java:75)
            	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
            	at jadx.core.ProcessClass.process(ProcessClass.java:37)
            	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
            	at jadx.api.JavaClass.decompile(JavaClass.java:62)
            	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 10 more
            */
        public boolean isEmpty() {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.isEmpty():boolean, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.internal.inputmethod.InputMethodUtils.InputMethodListBuilder.isEmpty():boolean");
        }
    }

    public static class InputMethodSettings {
        private boolean mCopyOnWrite;
        private final HashMap<String, String> mCopyOnWriteDataStore;
        private int[] mCurrentProfileIds;
        private int mCurrentUserId;
        private String mEnabledInputMethodsStrCache;
        private final SimpleStringSplitter mInputMethodSplitter;
        private final ArrayList<InputMethodInfo> mMethodList;
        private final HashMap<String, InputMethodInfo> mMethodMap;
        private final Resources mRes;
        private final ContentResolver mResolver;
        private final SimpleStringSplitter mSubtypeSplitter;

        private static void buildEnabledInputMethodsSettingString(StringBuilder builder, Pair<String, ArrayList<String>> ime) {
            builder.append((String) ime.first);
            for (String subtypeId : (ArrayList) ime.second) {
                builder.append(';').append(subtypeId);
            }
        }

        public static String buildInputMethodsSettingString(List<Pair<String, ArrayList<String>>> allImeSettingsMap) {
            StringBuilder b = new StringBuilder();
            boolean needsSeparator = false;
            for (Pair<String, ArrayList<String>> ime : allImeSettingsMap) {
                if (needsSeparator) {
                    b.append(InputMethodUtils.INPUT_METHOD_SEPARATOR);
                }
                buildEnabledInputMethodsSettingString(b, ime);
                needsSeparator = true;
            }
            return b.toString();
        }

        public static List<Pair<String, ArrayList<String>>> buildInputMethodsAndSubtypeList(String enabledInputMethodsStr, SimpleStringSplitter inputMethodSplitter, SimpleStringSplitter subtypeSplitter) {
            ArrayList<Pair<String, ArrayList<String>>> imsList = new ArrayList();
            if (TextUtils.isEmpty(enabledInputMethodsStr)) {
                return imsList;
            }
            inputMethodSplitter.setString(enabledInputMethodsStr);
            while (inputMethodSplitter.hasNext()) {
                subtypeSplitter.setString(inputMethodSplitter.next());
                if (subtypeSplitter.hasNext()) {
                    ArrayList<String> subtypeHashes = new ArrayList();
                    String imeId = subtypeSplitter.next();
                    while (subtypeSplitter.hasNext()) {
                        subtypeHashes.add(subtypeSplitter.next());
                    }
                    imsList.add(new Pair(imeId, subtypeHashes));
                }
            }
            return imsList;
        }

        public InputMethodSettings(Resources res, ContentResolver resolver, HashMap<String, InputMethodInfo> methodMap, ArrayList<InputMethodInfo> methodList, int userId, boolean copyOnWrite) {
            this.mInputMethodSplitter = new SimpleStringSplitter(InputMethodUtils.INPUT_METHOD_SEPARATOR);
            this.mSubtypeSplitter = new SimpleStringSplitter(';');
            this.mCopyOnWriteDataStore = new HashMap();
            this.mCopyOnWrite = false;
            this.mEnabledInputMethodsStrCache = PhoneConstants.MVNO_TYPE_NONE;
            this.mCurrentProfileIds = new int[0];
            this.mRes = res;
            this.mResolver = resolver;
            this.mMethodMap = methodMap;
            this.mMethodList = methodList;
            switchCurrentUser(userId, copyOnWrite);
        }

        public void switchCurrentUser(int userId, boolean copyOnWrite) {
            if (!(this.mCurrentUserId == userId && this.mCopyOnWrite == copyOnWrite)) {
                this.mCopyOnWriteDataStore.clear();
                this.mEnabledInputMethodsStrCache = PhoneConstants.MVNO_TYPE_NONE;
            }
            this.mCurrentUserId = userId;
            this.mCopyOnWrite = copyOnWrite;
        }

        private void putString(String key, String str) {
            if (this.mCopyOnWrite) {
                this.mCopyOnWriteDataStore.put(key, str);
            } else {
                Secure.putStringForUser(this.mResolver, key, str, this.mCurrentUserId);
            }
        }

        private String getString(String key, String defaultValue) {
            String result;
            if (this.mCopyOnWrite && this.mCopyOnWriteDataStore.containsKey(key)) {
                result = (String) this.mCopyOnWriteDataStore.get(key);
            } else {
                result = Secure.getStringForUser(this.mResolver, key, this.mCurrentUserId);
            }
            if (result != null) {
                return result;
            }
            return defaultValue;
        }

        private void putInt(String key, int value) {
            if (this.mCopyOnWrite) {
                this.mCopyOnWriteDataStore.put(key, String.valueOf(value));
            } else {
                Secure.putIntForUser(this.mResolver, key, value, this.mCurrentUserId);
            }
        }

        private int getInt(String key, int defaultValue) {
            if (!this.mCopyOnWrite || !this.mCopyOnWriteDataStore.containsKey(key)) {
                return Secure.getIntForUser(this.mResolver, key, defaultValue, this.mCurrentUserId);
            }
            String result = (String) this.mCopyOnWriteDataStore.get(key);
            return result != null ? Integer.parseInt(result) : 0;
        }

        private void putBoolean(String key, boolean value) {
            putInt(key, value ? 1 : 0);
        }

        private boolean getBoolean(String key, boolean defaultValue) {
            return getInt(key, defaultValue ? 1 : 0) == 1;
        }

        public void setCurrentProfileIds(int[] currentProfileIds) {
            synchronized (this) {
                this.mCurrentProfileIds = currentProfileIds;
            }
        }

        public boolean isCurrentProfile(int userId) {
            synchronized (this) {
                if (userId == this.mCurrentUserId) {
                    return true;
                }
                for (int i : this.mCurrentProfileIds) {
                    if (userId == i) {
                        return true;
                    }
                }
                return false;
            }
        }

        public ArrayList<InputMethodInfo> getEnabledInputMethodListLocked() {
            return createEnabledInputMethodListLocked(getEnabledInputMethodsAndSubtypeListLocked());
        }

        public List<InputMethodSubtype> getEnabledInputMethodSubtypeListLocked(Context context, InputMethodInfo imi, boolean allowsImplicitlySelectedSubtypes) {
            List<InputMethodSubtype> enabledSubtypes = getEnabledInputMethodSubtypeListLocked(imi);
            if (allowsImplicitlySelectedSubtypes && enabledSubtypes.isEmpty()) {
                enabledSubtypes = InputMethodUtils.getImplicitlyApplicableSubtypesLocked(context.getResources(), imi);
            }
            return InputMethodSubtype.sort(context, 0, imi, enabledSubtypes);
        }

        public List<InputMethodSubtype> getEnabledInputMethodSubtypeListLocked(InputMethodInfo imi) {
            List<Pair<String, ArrayList<String>>> imsList = getEnabledInputMethodsAndSubtypeListLocked();
            ArrayList<InputMethodSubtype> enabledSubtypes = new ArrayList();
            if (imi != null) {
                for (Pair<String, ArrayList<String>> imsPair : imsList) {
                    InputMethodInfo info = (InputMethodInfo) this.mMethodMap.get(imsPair.first);
                    if (info != null && info.getId().equals(imi.getId())) {
                        int subtypeCount = info.getSubtypeCount();
                        for (int i = 0; i < subtypeCount; i++) {
                            InputMethodSubtype ims = info.getSubtypeAt(i);
                            for (String s : (ArrayList) imsPair.second) {
                                if (String.valueOf(ims.hashCode()).equals(s)) {
                                    enabledSubtypes.add(ims);
                                }
                            }
                        }
                    }
                }
            }
            return enabledSubtypes;
        }

        public void enableAllIMEsIfThereIsNoEnabledIME() {
            if (TextUtils.isEmpty(getEnabledInputMethodsStr())) {
                StringBuilder sb = new StringBuilder();
                int N = this.mMethodList.size();
                for (int i = 0; i < N; i++) {
                    InputMethodInfo imi = (InputMethodInfo) this.mMethodList.get(i);
                    Slog.i(InputMethodUtils.TAG, "Adding: " + imi.getId());
                    if (i > 0) {
                        sb.append(InputMethodUtils.INPUT_METHOD_SEPARATOR);
                    }
                    sb.append(imi.getId());
                }
                putEnabledInputMethodsStr(sb.toString());
            }
        }

        public List<Pair<String, ArrayList<String>>> getEnabledInputMethodsAndSubtypeListLocked() {
            return buildInputMethodsAndSubtypeList(getEnabledInputMethodsStr(), this.mInputMethodSplitter, this.mSubtypeSplitter);
        }

        public void appendAndPutEnabledInputMethodLocked(String id, boolean reloadInputMethodStr) {
            if (reloadInputMethodStr) {
                getEnabledInputMethodsStr();
            }
            if (TextUtils.isEmpty(this.mEnabledInputMethodsStrCache)) {
                putEnabledInputMethodsStr(id);
            } else {
                putEnabledInputMethodsStr(this.mEnabledInputMethodsStrCache + InputMethodUtils.INPUT_METHOD_SEPARATOR + id);
            }
        }

        public boolean buildAndPutEnabledInputMethodsStrRemovingIdLocked(StringBuilder builder, List<Pair<String, ArrayList<String>>> imsList, String id) {
            boolean isRemoved = false;
            boolean needsAppendSeparator = false;
            for (Pair<String, ArrayList<String>> ims : imsList) {
                if (ims.first.equals(id)) {
                    isRemoved = true;
                } else {
                    if (needsAppendSeparator) {
                        builder.append(InputMethodUtils.INPUT_METHOD_SEPARATOR);
                    } else {
                        needsAppendSeparator = true;
                    }
                    buildEnabledInputMethodsSettingString(builder, ims);
                }
            }
            if (isRemoved) {
                putEnabledInputMethodsStr(builder.toString());
            }
            return isRemoved;
        }

        private ArrayList<InputMethodInfo> createEnabledInputMethodListLocked(List<Pair<String, ArrayList<String>>> imsList) {
            ArrayList<InputMethodInfo> res = new ArrayList();
            for (Pair<String, ArrayList<String>> ims : imsList) {
                InputMethodInfo info = (InputMethodInfo) this.mMethodMap.get(ims.first);
                if (info != null) {
                    res.add(info);
                }
            }
            return res;
        }

        private void putEnabledInputMethodsStr(String str) {
            if (TextUtils.isEmpty(str)) {
                putString("enabled_input_methods", null);
            } else {
                putString("enabled_input_methods", str);
            }
            if (str == null) {
                str = PhoneConstants.MVNO_TYPE_NONE;
            }
            this.mEnabledInputMethodsStrCache = str;
        }

        public String getEnabledInputMethodsStr() {
            this.mEnabledInputMethodsStrCache = getString("enabled_input_methods", PhoneConstants.MVNO_TYPE_NONE);
            return this.mEnabledInputMethodsStrCache;
        }

        private void saveSubtypeHistory(List<Pair<String, String>> savedImes, String newImeId, String newSubtypeId) {
            StringBuilder builder = new StringBuilder();
            boolean isImeAdded = false;
            if (!(TextUtils.isEmpty(newImeId) || TextUtils.isEmpty(newSubtypeId))) {
                builder.append(newImeId).append(';').append(newSubtypeId);
                isImeAdded = true;
            }
            for (Pair<String, String> ime : savedImes) {
                String imeId = ime.first;
                String subtypeId = ime.second;
                if (TextUtils.isEmpty(subtypeId)) {
                    subtypeId = InputMethodUtils.NOT_A_SUBTYPE_ID_STR;
                }
                if (isImeAdded) {
                    builder.append(InputMethodUtils.INPUT_METHOD_SEPARATOR);
                } else {
                    isImeAdded = true;
                }
                builder.append(imeId).append(';').append(subtypeId);
            }
            putSubtypeHistoryStr(builder.toString());
        }

        private void addSubtypeToHistory(String imeId, String subtypeId) {
            List<Pair<String, String>> subtypeHistory = loadInputMethodAndSubtypeHistoryLocked();
            for (Pair<String, String> ime : subtypeHistory) {
                if (((String) ime.first).equals(imeId)) {
                    subtypeHistory.remove(ime);
                    break;
                }
            }
            saveSubtypeHistory(subtypeHistory, imeId, subtypeId);
        }

        private void putSubtypeHistoryStr(String str) {
            if (TextUtils.isEmpty(str)) {
                putString("input_methods_subtype_history", null);
            } else {
                putString("input_methods_subtype_history", str);
            }
        }

        public Pair<String, String> getLastInputMethodAndSubtypeLocked() {
            return getLastSubtypeForInputMethodLockedInternal(null);
        }

        public String getLastSubtypeForInputMethodLocked(String imeId) {
            Pair<String, String> ime = getLastSubtypeForInputMethodLockedInternal(imeId);
            if (ime != null) {
                return (String) ime.second;
            }
            return null;
        }

        private Pair<String, String> getLastSubtypeForInputMethodLockedInternal(String imeId) {
            List<Pair<String, ArrayList<String>>> enabledImes = getEnabledInputMethodsAndSubtypeListLocked();
            for (Pair<String, String> imeAndSubtype : loadInputMethodAndSubtypeHistoryLocked()) {
                String imeInTheHistory = imeAndSubtype.first;
                if (TextUtils.isEmpty(imeId) || imeInTheHistory.equals(imeId)) {
                    String subtypeHashCode = getEnabledSubtypeHashCodeForInputMethodAndSubtypeLocked(enabledImes, imeInTheHistory, imeAndSubtype.second);
                    if (!TextUtils.isEmpty(subtypeHashCode)) {
                        return new Pair(imeInTheHistory, subtypeHashCode);
                    }
                }
            }
            return null;
        }

        private String getEnabledSubtypeHashCodeForInputMethodAndSubtypeLocked(List<Pair<String, ArrayList<String>>> enabledImes, String imeId, String subtypeHashCode) {
            for (Pair<String, ArrayList<String>> enabledIme : enabledImes) {
                if (((String) enabledIme.first).equals(imeId)) {
                    ArrayList<String> explicitlyEnabledSubtypes = enabledIme.second;
                    InputMethodInfo imi = (InputMethodInfo) this.mMethodMap.get(imeId);
                    if (explicitlyEnabledSubtypes.size() != 0) {
                        for (String s : explicitlyEnabledSubtypes) {
                            if (s.equals(subtypeHashCode)) {
                                try {
                                    if (InputMethodUtils.isValidSubtypeId(imi, Integer.parseInt(subtypeHashCode))) {
                                        return s;
                                    }
                                    return InputMethodUtils.NOT_A_SUBTYPE_ID_STR;
                                } catch (NumberFormatException e) {
                                    return InputMethodUtils.NOT_A_SUBTYPE_ID_STR;
                                }
                            }
                        }
                    } else if (imi != null && imi.getSubtypeCount() > 0) {
                        List<InputMethodSubtype> implicitlySelectedSubtypes = InputMethodUtils.getImplicitlyApplicableSubtypesLocked(this.mRes, imi);
                        if (implicitlySelectedSubtypes != null) {
                            int N = implicitlySelectedSubtypes.size();
                            for (int i = 0; i < N; i++) {
                                if (String.valueOf(((InputMethodSubtype) implicitlySelectedSubtypes.get(i)).hashCode()).equals(subtypeHashCode)) {
                                    return subtypeHashCode;
                                }
                            }
                        }
                    }
                    return InputMethodUtils.NOT_A_SUBTYPE_ID_STR;
                }
            }
            return null;
        }

        private List<Pair<String, String>> loadInputMethodAndSubtypeHistoryLocked() {
            ArrayList<Pair<String, String>> imsList = new ArrayList();
            String subtypeHistoryStr = getSubtypeHistoryStr();
            if (TextUtils.isEmpty(subtypeHistoryStr)) {
                return imsList;
            }
            this.mInputMethodSplitter.setString(subtypeHistoryStr);
            while (this.mInputMethodSplitter.hasNext()) {
                this.mSubtypeSplitter.setString(this.mInputMethodSplitter.next());
                if (this.mSubtypeSplitter.hasNext()) {
                    String subtypeId = InputMethodUtils.NOT_A_SUBTYPE_ID_STR;
                    String imeId = this.mSubtypeSplitter.next();
                    if (this.mSubtypeSplitter.hasNext()) {
                        subtypeId = this.mSubtypeSplitter.next();
                    }
                    imsList.add(new Pair(imeId, subtypeId));
                }
            }
            return imsList;
        }

        private String getSubtypeHistoryStr() {
            return getString("input_methods_subtype_history", PhoneConstants.MVNO_TYPE_NONE);
        }

        public void putSelectedInputMethod(String imeId) {
            putString("default_input_method", imeId);
        }

        public void putSelectedSubtype(int subtypeId) {
            putInt("selected_input_method_subtype", subtypeId);
        }

        public String getSelectedInputMethod() {
            return getString("default_input_method", null);
        }

        public boolean isSubtypeSelected() {
            return getSelectedInputMethodSubtypeHashCode() != -1;
        }

        private int getSelectedInputMethodSubtypeHashCode() {
            return getInt("selected_input_method_subtype", -1);
        }

        public boolean isShowImeWithHardKeyboardEnabled() {
            return getBoolean("show_ime_with_hard_keyboard", false);
        }

        public void setShowImeWithHardKeyboard(boolean show) {
            putBoolean("show_ime_with_hard_keyboard", show);
        }

        public int getCurrentUserId() {
            return this.mCurrentUserId;
        }

        public int getSelectedInputMethodSubtypeId(String selectedImiId) {
            InputMethodInfo imi = (InputMethodInfo) this.mMethodMap.get(selectedImiId);
            if (imi == null) {
                return -1;
            }
            return InputMethodUtils.getSubtypeIdFromHashCode(imi, getSelectedInputMethodSubtypeHashCode());
        }

        public void saveCurrentInputMethodAndSubtypeToHistory(String curMethodId, InputMethodSubtype currentSubtype) {
            String subtypeId = InputMethodUtils.NOT_A_SUBTYPE_ID_STR;
            if (currentSubtype != null) {
                subtypeId = String.valueOf(currentSubtype.hashCode());
            }
            if (InputMethodUtils.canAddToLastInputMethod(currentSubtype)) {
                addSubtypeToHistory(curMethodId, subtypeId);
            }
        }

        public HashMap<InputMethodInfo, List<InputMethodSubtype>> getExplicitlyOrImplicitlyEnabledInputMethodsAndSubtypeListLocked(Context context) {
            HashMap<InputMethodInfo, List<InputMethodSubtype>> enabledInputMethodAndSubtypes = new HashMap();
            for (InputMethodInfo imi : getEnabledInputMethodListLocked()) {
                enabledInputMethodAndSubtypes.put(imi, getEnabledInputMethodSubtypeListLocked(context, imi, true));
            }
            return enabledInputMethodAndSubtypes;
        }

        public void dumpLocked(Printer pw, String prefix) {
            pw.println(prefix + "mCurrentUserId=" + this.mCurrentUserId);
            pw.println(prefix + "mCurrentProfileIds=" + Arrays.toString(this.mCurrentProfileIds));
            pw.println(prefix + "mCopyOnWrite=" + this.mCopyOnWrite);
            pw.println(prefix + "mEnabledInputMethodsStrCache=" + this.mEnabledInputMethodsStrCache);
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.internal.inputmethod.InputMethodUtils.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.internal.inputmethod.InputMethodUtils.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.inputmethod.InputMethodUtils.<clinit>():void");
    }

    private InputMethodUtils() {
    }

    public static String getApiCallStack() {
        String apiCallStack = PhoneConstants.MVNO_TYPE_NONE;
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            StackTraceElement[] frames = e.getStackTrace();
            for (int j = 1; j < frames.length; j++) {
                String tempCallStack = frames[j].toString();
                if (!TextUtils.isEmpty(apiCallStack) && tempCallStack.indexOf("Transact(") >= 0) {
                    break;
                }
                apiCallStack = tempCallStack;
            }
            return apiCallStack;
        }
    }

    public static boolean isSystemIme(InputMethodInfo inputMethod) {
        return (inputMethod.getServiceInfo().applicationInfo.flags & 1) != 0;
    }

    public static boolean isSystemImeThatHasSubtypeOf(InputMethodInfo imi, Context context, boolean checkDefaultAttribute, Locale requiredLocale, boolean checkCountry, String requiredSubtypeMode) {
        if (!isSystemIme(imi)) {
            return false;
        }
        if ((!checkDefaultAttribute || imi.isDefault(context)) && containsSubtypeOf(imi, requiredLocale, checkCountry, requiredSubtypeMode)) {
            return true;
        }
        return false;
    }

    public static Locale getFallbackLocaleForDefaultIme(ArrayList<InputMethodInfo> imis, Context context) {
        int i;
        for (Locale fallbackLocale : SEARCH_ORDER_OF_FALLBACK_LOCALES) {
            for (i = 0; i < imis.size(); i++) {
                if (isSystemImeThatHasSubtypeOf((InputMethodInfo) imis.get(i), context, true, fallbackLocale, true, SUBTYPE_MODE_KEYBOARD)) {
                    return fallbackLocale;
                }
            }
        }
        for (Locale fallbackLocale2 : SEARCH_ORDER_OF_FALLBACK_LOCALES) {
            for (i = 0; i < imis.size(); i++) {
                if (isSystemImeThatHasSubtypeOf((InputMethodInfo) imis.get(i), context, false, fallbackLocale2, true, SUBTYPE_MODE_KEYBOARD)) {
                    return fallbackLocale2;
                }
            }
        }
        Slog.w(TAG, "Found no fallback locale. imis=" + Arrays.toString(imis.toArray()));
        return null;
    }

    private static boolean isSystemAuxilialyImeThatHasAutomaticSubtype(InputMethodInfo imi, Context context, boolean checkDefaultAttribute) {
        if (!isSystemIme(imi)) {
            return false;
        }
        if ((checkDefaultAttribute && !imi.isDefault(context)) || !imi.isAuxiliaryIme()) {
            return false;
        }
        int subtypeCount = imi.getSubtypeCount();
        for (int i = 0; i < subtypeCount; i++) {
            if (imi.getSubtypeAt(i).overridesImplicitlyEnabledSubtype()) {
                return true;
            }
        }
        return false;
    }

    public static Locale getSystemLocaleFromContext(Context context) {
        try {
            return context.getResources().getConfiguration().locale;
        } catch (NotFoundException e) {
            return null;
        }
    }

    private static InputMethodListBuilder getMinimumKeyboardSetWithoutSystemLocale(ArrayList<InputMethodInfo> imis, Context context, Locale fallbackLocale) {
        InputMethodListBuilder builder = new InputMethodListBuilder();
        builder.fillImes(imis, context, true, fallbackLocale, true, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, false, fallbackLocale, true, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, true, fallbackLocale, false, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, false, fallbackLocale, false, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        Slog.w(TAG, "No software keyboard is found. imis=" + Arrays.toString(imis.toArray()) + " fallbackLocale=" + fallbackLocale);
        return builder;
    }

    private static InputMethodListBuilder getMinimumKeyboardSetWithSystemLocale(ArrayList<InputMethodInfo> imis, Context context, Locale systemLocale, Locale fallbackLocale) {
        InputMethodListBuilder builder = new InputMethodListBuilder();
        builder.fillImes(imis, context, true, systemLocale, true, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, true, systemLocale, false, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, true, fallbackLocale, true, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, true, fallbackLocale, false, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, false, fallbackLocale, true, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        builder.fillImes(imis, context, false, fallbackLocale, false, SUBTYPE_MODE_KEYBOARD);
        if (!builder.isEmpty()) {
            return builder;
        }
        Slog.w(TAG, "No software keyboard is found. imis=" + Arrays.toString(imis.toArray()) + " systemLocale=" + systemLocale + " fallbackLocale=" + fallbackLocale);
        return builder;
    }

    public static ArrayList<InputMethodInfo> getDefaultEnabledImes(Context context, boolean isSystemReady, ArrayList<InputMethodInfo> imis) {
        Locale fallbackLocale = getFallbackLocaleForDefaultIme(imis, context);
        if (isSystemReady) {
            Locale systemLocale = getSystemLocaleFromContext(context);
            return getMinimumKeyboardSetWithSystemLocale(imis, context, systemLocale, fallbackLocale).fillImes(imis, context, true, systemLocale, true, SUBTYPE_MODE_ANY).fillAuxiliaryImes(imis, context).build();
        }
        return getMinimumKeyboardSetWithoutSystemLocale(imis, context, fallbackLocale).fillImes(imis, context, true, fallbackLocale, true, SUBTYPE_MODE_ANY).build();
    }

    public static Locale constructLocaleFromString(String localeStr) {
        if (TextUtils.isEmpty(localeStr)) {
            return null;
        }
        String[] localeParams = localeStr.split("_", 3);
        if (localeParams.length >= 1 && "tl".equals(localeParams[0])) {
            localeParams[0] = "fil";
        }
        if (localeParams.length == 1) {
            return new Locale(localeParams[0]);
        }
        if (localeParams.length == 2) {
            return new Locale(localeParams[0], localeParams[1]);
        }
        if (localeParams.length == 3) {
            return new Locale(localeParams[0], localeParams[1], localeParams[2]);
        }
        return null;
    }

    public static boolean containsSubtypeOf(InputMethodInfo imi, Locale locale, boolean checkCountry, String mode) {
        if (locale == null) {
            return false;
        }
        int N = imi.getSubtypeCount();
        for (int i = 0; i < N; i++) {
            InputMethodSubtype subtype = imi.getSubtypeAt(i);
            if (checkCountry) {
                Locale subtypeLocale = subtype.getLocaleObject();
                if (subtypeLocale == null) {
                    continue;
                } else if (!TextUtils.equals(subtypeLocale.getLanguage(), locale.getLanguage())) {
                    continue;
                } else if (!TextUtils.equals(subtypeLocale.getCountry(), locale.getCountry())) {
                    continue;
                }
            } else if (!TextUtils.equals(new Locale(getLanguageFromLocaleString(subtype.getLocale())).getLanguage(), locale.getLanguage())) {
                continue;
            }
            if (mode == SUBTYPE_MODE_ANY || TextUtils.isEmpty(mode) || mode.equalsIgnoreCase(subtype.getMode())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<InputMethodSubtype> getSubtypes(InputMethodInfo imi) {
        ArrayList<InputMethodSubtype> subtypes = new ArrayList();
        int subtypeCount = imi.getSubtypeCount();
        for (int i = 0; i < subtypeCount; i++) {
            subtypes.add(imi.getSubtypeAt(i));
        }
        return subtypes;
    }

    public static ArrayList<InputMethodSubtype> getOverridingImplicitlyEnabledSubtypes(InputMethodInfo imi, String mode) {
        ArrayList<InputMethodSubtype> subtypes = new ArrayList();
        int subtypeCount = imi.getSubtypeCount();
        for (int i = 0; i < subtypeCount; i++) {
            InputMethodSubtype subtype = imi.getSubtypeAt(i);
            if (subtype.overridesImplicitlyEnabledSubtype() && subtype.getMode().equals(mode)) {
                subtypes.add(subtype);
            }
        }
        return subtypes;
    }

    public static InputMethodInfo getMostApplicableDefaultIME(List<InputMethodInfo> enabledImes) {
        if (enabledImes == null || enabledImes.isEmpty()) {
            return null;
        }
        int i = enabledImes.size();
        int firstFoundSystemIme = -1;
        while (i > 0) {
            i--;
            InputMethodInfo imi = (InputMethodInfo) enabledImes.get(i);
            if (!imi.isAuxiliaryIme()) {
                if (isSystemIme(imi) && containsSubtypeOf(imi, ENGLISH_LOCALE, false, SUBTYPE_MODE_KEYBOARD)) {
                    return imi;
                }
                if (firstFoundSystemIme < 0 && isSystemIme(imi)) {
                    firstFoundSystemIme = i;
                }
            }
        }
        return (InputMethodInfo) enabledImes.get(Math.max(firstFoundSystemIme, 0));
    }

    public static boolean isValidSubtypeId(InputMethodInfo imi, int subtypeHashCode) {
        return getSubtypeIdFromHashCode(imi, subtypeHashCode) != -1;
    }

    public static int getSubtypeIdFromHashCode(InputMethodInfo imi, int subtypeHashCode) {
        if (imi != null) {
            int subtypeCount = imi.getSubtypeCount();
            for (int i = 0; i < subtypeCount; i++) {
                if (subtypeHashCode == imi.getSubtypeAt(i).hashCode()) {
                    return i;
                }
            }
        }
        return -1;
    }

    /* JADX WARNING: Missing block: B:11:0x0021, code:
            r0 = getImplicitlyApplicableSubtypesLockedImpl(r5, r6);
            r3 = sCacheLock;
     */
    /* JADX WARNING: Missing block: B:12:0x0027, code:
            monitor-enter(r3);
     */
    /* JADX WARNING: Missing block: B:14:?, code:
            sCachedSystemLocales = r1;
            sCachedInputMethodInfo = r6;
            sCachedResult = new java.util.ArrayList(r0);
     */
    /* JADX WARNING: Missing block: B:15:0x0033, code:
            monitor-exit(r3);
     */
    /* JADX WARNING: Missing block: B:16:0x0034, code:
            return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static ArrayList<InputMethodSubtype> getImplicitlyApplicableSubtypesLocked(Resources res, InputMethodInfo imi) {
        LocaleList systemLocales = res.getConfiguration().getLocales();
        synchronized (sCacheLock) {
            if (systemLocales.equals(sCachedSystemLocales) && sCachedInputMethodInfo == imi) {
                ArrayList<InputMethodSubtype> arrayList = new ArrayList(sCachedResult);
                return arrayList;
            }
        }
    }

    private static ArrayList<InputMethodSubtype> getImplicitlyApplicableSubtypesLockedImpl(Resources res, InputMethodInfo imi) {
        List<InputMethodSubtype> subtypes = getSubtypes(imi);
        LocaleList systemLocales = res.getConfiguration().getLocales();
        String systemLocale = systemLocales.get(0).toString();
        if (TextUtils.isEmpty(systemLocale)) {
            return new ArrayList();
        }
        int i;
        InputMethodSubtype subtype;
        String mode;
        int numSubtypes = subtypes.size();
        HashMap<String, InputMethodSubtype> applicableModeAndSubtypesMap = new HashMap();
        for (i = 0; i < numSubtypes; i++) {
            subtype = (InputMethodSubtype) subtypes.get(i);
            if (subtype.overridesImplicitlyEnabledSubtype()) {
                mode = subtype.getMode();
                if (!applicableModeAndSubtypesMap.containsKey(mode)) {
                    applicableModeAndSubtypesMap.put(mode, subtype);
                }
            }
        }
        if (applicableModeAndSubtypesMap.size() > 0) {
            return new ArrayList(applicableModeAndSubtypesMap.values());
        }
        HashMap<String, ArrayList<InputMethodSubtype>> nonKeyboardSubtypesMap = new HashMap();
        ArrayList<InputMethodSubtype> keyboardSubtypes = new ArrayList();
        for (i = 0; i < numSubtypes; i++) {
            subtype = (InputMethodSubtype) subtypes.get(i);
            mode = subtype.getMode();
            if (SUBTYPE_MODE_KEYBOARD.equals(mode)) {
                keyboardSubtypes.add(subtype);
            } else {
                if (!nonKeyboardSubtypesMap.containsKey(mode)) {
                    nonKeyboardSubtypesMap.put(mode, new ArrayList());
                }
                ((ArrayList) nonKeyboardSubtypesMap.get(mode)).add(subtype);
            }
        }
        ArrayList<InputMethodSubtype> applicableSubtypes = new ArrayList();
        LocaleUtils.filterByLanguage(keyboardSubtypes, sSubtypeToLocale, systemLocales, applicableSubtypes);
        if (!applicableSubtypes.isEmpty()) {
            boolean hasAsciiCapableKeyboard = false;
            int numApplicationSubtypes = applicableSubtypes.size();
            for (i = 0; i < numApplicationSubtypes; i++) {
                if (((InputMethodSubtype) applicableSubtypes.get(i)).containsExtraValueKey(TAG_ASCII_CAPABLE)) {
                    hasAsciiCapableKeyboard = true;
                    break;
                }
            }
            if (!hasAsciiCapableKeyboard) {
                int numKeyboardSubtypes = keyboardSubtypes.size();
                for (i = 0; i < numKeyboardSubtypes; i++) {
                    subtype = (InputMethodSubtype) keyboardSubtypes.get(i);
                    if (SUBTYPE_MODE_KEYBOARD.equals(subtype.getMode()) && subtype.containsExtraValueKey(TAG_ENABLED_WHEN_DEFAULT_IS_NOT_ASCII_CAPABLE)) {
                        applicableSubtypes.add(subtype);
                    }
                }
            }
        }
        if (applicableSubtypes.isEmpty()) {
            InputMethodSubtype lastResortKeyboardSubtype = findLastResortApplicableSubtypeLocked(res, subtypes, SUBTYPE_MODE_KEYBOARD, systemLocale, true);
            if (lastResortKeyboardSubtype != null) {
                applicableSubtypes.add(lastResortKeyboardSubtype);
            }
        }
        for (ArrayList<InputMethodSubtype> subtypeList : nonKeyboardSubtypesMap.values()) {
            LocaleUtils.filterByLanguage(subtypeList, sSubtypeToLocale, systemLocales, applicableSubtypes);
        }
        return applicableSubtypes;
    }

    public static String getLanguageFromLocaleString(String locale) {
        int idx = locale.indexOf(95);
        if (idx < 0) {
            return locale;
        }
        return locale.substring(0, idx);
    }

    public static InputMethodSubtype findLastResortApplicableSubtypeLocked(Resources res, List<InputMethodSubtype> subtypes, String mode, String locale, boolean canIgnoreLocaleAsLastResort) {
        if (subtypes == null || subtypes.size() == 0) {
            return null;
        }
        if (TextUtils.isEmpty(locale)) {
            locale = res.getConfiguration().locale.toString();
        }
        String language = getLanguageFromLocaleString(locale);
        boolean partialMatchFound = false;
        InputMethodSubtype applicableSubtype = null;
        InputMethodSubtype firstMatchedModeSubtype = null;
        int N = subtypes.size();
        int i = 0;
        while (i < N) {
            InputMethodSubtype subtype = (InputMethodSubtype) subtypes.get(i);
            String subtypeLocale = subtype.getLocale();
            String subtypeLanguage = getLanguageFromLocaleString(subtypeLocale);
            if (mode == null || ((InputMethodSubtype) subtypes.get(i)).getMode().equalsIgnoreCase(mode)) {
                if (firstMatchedModeSubtype == null) {
                    firstMatchedModeSubtype = subtype;
                }
                if (locale.equals(subtypeLocale)) {
                    applicableSubtype = subtype;
                    break;
                } else if (!partialMatchFound && language.equals(subtypeLanguage)) {
                    applicableSubtype = subtype;
                    partialMatchFound = true;
                }
            }
            i++;
        }
        if (applicableSubtype == null && canIgnoreLocaleAsLastResort) {
            return firstMatchedModeSubtype;
        }
        return applicableSubtype;
    }

    public static boolean canAddToLastInputMethod(InputMethodSubtype subtype) {
        boolean z = true;
        if (subtype == null) {
            return true;
        }
        if (subtype.isAuxiliary()) {
            z = false;
        }
        return z;
    }

    public static void setNonSelectedSystemImesDisabledUntilUsed(IPackageManager packageManager, List<InputMethodInfo> enabledImis, int userId, String callingPackage) {
        String[] systemImesDisabledUntilUsed = Resources.getSystem().getStringArray(R.array.config_disabledUntilUsedPreinstalledImes);
        if (systemImesDisabledUntilUsed != null && systemImesDisabledUntilUsed.length != 0) {
            SpellCheckerInfo currentSpellChecker = TextServicesManager.getInstance().getCurrentSpellChecker();
            for (String packageName : systemImesDisabledUntilUsed) {
                boolean enabledIme = false;
                for (int j = 0; j < enabledImis.size(); j++) {
                    if (packageName.equals(((InputMethodInfo) enabledImis.get(j)).getPackageName())) {
                        enabledIme = true;
                        break;
                    }
                }
                if (!enabledIme && (currentSpellChecker == null || !packageName.equals(currentSpellChecker.getPackageName()))) {
                    try {
                        ApplicationInfo ai = packageManager.getApplicationInfo(packageName, 32768, userId);
                        if (ai != null) {
                            if ((ai.flags & 1) != 0) {
                                setDisabledUntilUsed(packageManager, packageName, userId, callingPackage);
                            }
                        }
                    } catch (RemoteException e) {
                        Slog.w(TAG, "getApplicationInfo failed. packageName=" + packageName + " userId=" + userId, e);
                    }
                }
            }
        }
    }

    private static void setDisabledUntilUsed(IPackageManager packageManager, String packageName, int userId, String callingPackage) {
        try {
            int state = packageManager.getApplicationEnabledSetting(packageName, userId);
            if (state == 0 || state == 1) {
                try {
                    packageManager.setApplicationEnabledSetting(packageName, 4, 0, userId, callingPackage);
                } catch (RemoteException e) {
                    Slog.w(TAG, "setApplicationEnabledSetting failed. packageName=" + packageName + " userId=" + userId + " callingPackage=" + callingPackage, e);
                }
            }
        } catch (RemoteException e2) {
            Slog.w(TAG, "getApplicationEnabledSetting failed. packageName=" + packageName + " userId=" + userId, e2);
        }
    }

    public static CharSequence getImeAndSubtypeDisplayName(Context context, InputMethodInfo imi, InputMethodSubtype subtype) {
        CharSequence imiLabel = imi.loadLabel(context.getPackageManager());
        if (subtype == null) {
            return imiLabel;
        }
        CharSequence[] charSequenceArr = new CharSequence[2];
        charSequenceArr[0] = subtype.getDisplayName(context, imi.getPackageName(), imi.getServiceInfo().applicationInfo);
        charSequenceArr[1] = TextUtils.isEmpty(imiLabel) ? PhoneConstants.MVNO_TYPE_NONE : " - " + imiLabel;
        return TextUtils.concat(charSequenceArr);
    }

    public static boolean checkIfPackageBelongsToUid(AppOpsManager appOpsManager, int uid, String packageName) {
        try {
            appOpsManager.checkPackage(uid, packageName);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }

    public static ArrayMap<String, ArraySet<String>> parseInputMethodsAndSubtypesString(String inputMethodsAndSubtypesString) {
        ArrayMap<String, ArraySet<String>> imeMap = new ArrayMap();
        if (TextUtils.isEmpty(inputMethodsAndSubtypesString)) {
            return imeMap;
        }
        for (Pair<String, ArrayList<String>> ime : InputMethodSettings.buildInputMethodsAndSubtypeList(inputMethodsAndSubtypesString, new SimpleStringSplitter(INPUT_METHOD_SEPARATOR), new SimpleStringSplitter(';'))) {
            ArraySet<String> subtypes = new ArraySet();
            if (ime.second != null) {
                subtypes.addAll((Collection) ime.second);
            }
            imeMap.put((String) ime.first, subtypes);
        }
        return imeMap;
    }

    public static String buildInputMethodsAndSubtypesString(ArrayMap<String, ArraySet<String>> map) {
        List<Pair<String, ArrayList<String>>> imeMap = new ArrayList(4);
        for (Entry<String, ArraySet<String>> entry : map.entrySet()) {
            String imeName = (String) entry.getKey();
            ArraySet<String> subtypeSet = (ArraySet) entry.getValue();
            ArrayList<String> subtypes = new ArrayList(2);
            if (subtypeSet != null) {
                subtypes.addAll(subtypeSet);
            }
            imeMap.add(new Pair(imeName, subtypes));
        }
        return InputMethodSettings.buildInputMethodsSettingString(imeMap);
    }

    public static ArrayList<Locale> getSuitableLocalesForSpellChecker(Locale systemLocale) {
        Object systemLocaleLanguageCountryVariant;
        Object systemLocaleLanguageCountry;
        Object systemLocaleLanguage;
        if (systemLocale != null) {
            String language = systemLocale.getLanguage();
            boolean hasLanguage = !TextUtils.isEmpty(language);
            String country = systemLocale.getCountry();
            boolean hasCountry = !TextUtils.isEmpty(country);
            String variant = systemLocale.getVariant();
            boolean hasVariant = !TextUtils.isEmpty(variant);
            if (hasLanguage && hasCountry && hasVariant) {
                systemLocaleLanguageCountryVariant = new Locale(language, country, variant);
            } else {
                systemLocaleLanguageCountryVariant = null;
            }
            if (hasLanguage && hasCountry) {
                systemLocaleLanguageCountry = new Locale(language, country);
            } else {
                systemLocaleLanguageCountry = null;
            }
            if (hasLanguage) {
                systemLocaleLanguage = new Locale(language);
            } else {
                systemLocaleLanguage = null;
            }
        } else {
            systemLocaleLanguageCountryVariant = null;
            systemLocaleLanguageCountry = null;
            systemLocaleLanguage = null;
        }
        ArrayList<Locale> locales = new ArrayList();
        if (systemLocaleLanguageCountryVariant != null) {
            locales.add(systemLocaleLanguageCountryVariant);
        }
        if (!Locale.ENGLISH.equals(systemLocaleLanguage)) {
            if (systemLocaleLanguageCountry != null) {
                locales.add(systemLocaleLanguageCountry);
            }
            if (systemLocaleLanguage != null) {
                locales.add(systemLocaleLanguage);
            }
            locales.add(LOCALE_EN_US);
            locales.add(LOCALE_EN_GB);
            locales.add(Locale.ENGLISH);
        } else if (systemLocaleLanguageCountry != null) {
            if (systemLocaleLanguageCountry != null) {
                locales.add(systemLocaleLanguageCountry);
            }
            if (!LOCALE_EN_US.equals(systemLocaleLanguageCountry)) {
                locales.add(LOCALE_EN_US);
            }
            if (!LOCALE_EN_GB.equals(systemLocaleLanguageCountry)) {
                locales.add(LOCALE_EN_GB);
            }
            locales.add(Locale.ENGLISH);
        } else {
            locales.add(Locale.ENGLISH);
            locales.add(LOCALE_EN_US);
            locales.add(LOCALE_EN_GB);
        }
        return locales;
    }
}
