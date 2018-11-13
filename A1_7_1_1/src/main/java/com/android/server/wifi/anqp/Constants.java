package com.android.server.wifi.anqp;

import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
public class Constants {
    public static final int ANQP_3GPP_NETWORK = 264;
    public static final int ANQP_CAPABILITY_LIST = 257;
    public static final int ANQP_CIVIC_LOC = 266;
    public static final int ANQP_DOM_NAME = 268;
    public static final int ANQP_EMERGENCY_ALERT = 269;
    public static final int ANQP_EMERGENCY_NAI = 271;
    public static final int ANQP_EMERGENCY_NUMBER = 259;
    public static final int ANQP_GEO_LOC = 265;
    public static final int ANQP_IP_ADDR_AVAILABILITY = 262;
    public static final int ANQP_LOC_URI = 267;
    public static final int ANQP_NAI_REALM = 263;
    public static final int ANQP_NEIGHBOR_REPORT = 272;
    public static final int ANQP_NWK_AUTH_TYPE = 260;
    public static final int ANQP_QUERY_LIST = 256;
    public static final int ANQP_ROAMING_CONSORTIUM = 261;
    public static final int ANQP_TDLS_CAP = 270;
    public static final int ANQP_VENDOR_SPEC = 56797;
    public static final int ANQP_VENUE_NAME = 258;
    public static final int BYTES_IN_EUI48 = 6;
    public static final int BYTES_IN_INT = 4;
    public static final int BYTES_IN_SHORT = 2;
    public static final int BYTE_MASK = 255;
    public static final int HS20_FRAME_PREFIX = 278556496;
    public static final int HS20_PREFIX = 295333712;
    public static final int HS_CAPABILITY_LIST = 2;
    public static final int HS_CONN_CAPABILITY = 5;
    public static final int HS_FRIENDLY_NAME = 3;
    public static final int HS_ICON_FILE = 11;
    public static final int HS_ICON_REQUEST = 10;
    public static final int HS_NAI_HOME_REALM_QUERY = 6;
    public static final int HS_OPERATING_CLASS = 7;
    public static final int HS_OSU_PROVIDERS = 8;
    public static final int HS_QUERY_LIST = 1;
    public static final int HS_WAN_METRICS = 4;
    public static final long INT_MASK = 4294967295L;
    public static final int LANG_CODE_LENGTH = 3;
    public static final long MILLIS_IN_A_SEC = 1000;
    public static final int NIBBLE_MASK = 15;
    public static final int SHORT_MASK = 65535;
    public static final int UTF8_INDICATOR = 1;
    private static final Map<Integer, ANQPElementType> sAnqpMap = null;
    private static final Map<Integer, ANQPElementType> sHs20Map = null;
    private static final Map<ANQPElementType, Integer> sRevAnqpmap = null;
    private static final Map<ANQPElementType, Integer> sRevHs20map = null;

    /*  JADX ERROR: NullPointerException in pass: EnumVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    public enum ANQPElementType {
        ;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.server.wifi.anqp.Constants.ANQPElementType.<clinit>():void, dex: 
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
            Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
            	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
            	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
            	... 10 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.server.wifi.anqp.Constants.ANQPElementType.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.wifi.anqp.Constants.ANQPElementType.<clinit>():void");
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.server.wifi.anqp.Constants.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.server.wifi.anqp.Constants.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.wifi.anqp.Constants.<clinit>():void");
    }

    public static ANQPElementType mapANQPElement(int id) {
        return (ANQPElementType) sAnqpMap.get(Integer.valueOf(id));
    }

    public static ANQPElementType mapHS20Element(int id) {
        return (ANQPElementType) sHs20Map.get(Integer.valueOf(id));
    }

    public static Integer getANQPElementID(ANQPElementType elementType) {
        return (Integer) sRevAnqpmap.get(elementType);
    }

    public static Integer getHS20ElementID(ANQPElementType elementType) {
        return (Integer) sRevHs20map.get(elementType);
    }

    public static boolean hasBaseANQPElements(Collection<ANQPElementType> elements) {
        if (elements == null) {
            return false;
        }
        for (ANQPElementType element : elements) {
            if (sRevAnqpmap.containsKey(element)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasR2Elements(List<ANQPElementType> elements) {
        return elements.contains(ANQPElementType.HSOSUProviders);
    }

    public static long getInteger(ByteBuffer payload, ByteOrder bo, int size) {
        byte[] octets = new byte[size];
        payload.get(octets);
        long value = 0;
        if (bo == ByteOrder.LITTLE_ENDIAN) {
            for (int n = octets.length - 1; n >= 0; n--) {
                value = (value << 8) | ((long) (octets[n] & 255));
            }
        } else {
            for (byte octet : octets) {
                value = (value << 8) | ((long) (octet & 255));
            }
        }
        return value;
    }

    public static String getPrefixedString(ByteBuffer payload, int lengthLength, Charset charset) throws ProtocolException {
        return getPrefixedString(payload, lengthLength, charset, false);
    }

    public static String getPrefixedString(ByteBuffer payload, int lengthLength, Charset charset, boolean useNull) throws ProtocolException {
        if (payload.remaining() >= lengthLength) {
            return getString(payload, (int) getInteger(payload, ByteOrder.LITTLE_ENDIAN, lengthLength), charset, useNull);
        }
        throw new ProtocolException("Runt string: " + payload.remaining());
    }

    public static String getTrimmedString(ByteBuffer payload, int length, Charset charset) throws ProtocolException {
        String s = getString(payload, length, charset, false);
        int zero = length - 1;
        while (zero >= 0 && s.charAt(zero) == 0) {
            zero--;
        }
        return zero < length + -1 ? s.substring(0, zero + 1) : s;
    }

    public static String getString(ByteBuffer payload, int length, Charset charset) throws ProtocolException {
        return getString(payload, length, charset, false);
    }

    public static String getString(ByteBuffer payload, int length, Charset charset, boolean useNull) throws ProtocolException {
        if (length > payload.remaining()) {
            throw new ProtocolException("Bad string length: " + length);
        } else if (useNull && length == 0) {
            return null;
        } else {
            byte[] octets = new byte[length];
            payload.get(octets);
            return new String(octets, charset);
        }
    }
}
