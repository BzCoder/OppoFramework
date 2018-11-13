package com.android.server.wifi.hotspot2;

import com.android.server.wifi.util.InformationElementUtil.SupportedRates;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

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
public abstract class Utils {
    private static final int EUI48Length = 6;
    private static final long EUI48Mask = 281474976710655L;
    private static final int EUI64Length = 8;
    private static final String[] PLMNText = null;
    public static final long UNSET_TIME = -1;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.server.wifi.hotspot2.Utils.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.server.wifi.hotspot2.Utils.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.wifi.hotspot2.Utils.<clinit>():void");
    }

    public static String hs2LogTag(Class c) {
        return "HS20";
    }

    public static List<String> splitDomain(String domain) {
        int i = 0;
        if (domain.endsWith(".")) {
            domain = domain.substring(0, domain.length() - 1);
        }
        int at = domain.indexOf(64);
        if (at >= 0) {
            domain = domain.substring(at + 1);
        }
        String[] labels = domain.toLowerCase().split("\\.");
        LinkedList<String> labelList = new LinkedList();
        int length = labels.length;
        while (i < length) {
            labelList.addFirst(labels[i]);
            i++;
        }
        return labelList;
    }

    public static long parseMac(String s) {
        long mac = 0;
        int count = 0;
        for (int n = 0; n < s.length(); n++) {
            int nibble = fromHex(s.charAt(n), true);
            if (nibble >= 0) {
                mac = (mac << 4) | ((long) nibble);
                count++;
            }
        }
        if (count >= 12 && (count & 1) != 1) {
            return mac;
        }
        throw new IllegalArgumentException("Bad MAC address: '" + s + "'");
    }

    public static String macToString(long mac) {
        int len = (-281474976710656L & mac) != 0 ? 8 : 6;
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int n = (len - 1) * 8; n >= 0; n -= 8) {
            if (first) {
                first = false;
            } else {
                sb.append(':');
            }
            Object[] objArr = new Object[1];
            objArr[0] = Long.valueOf((mac >>> n) & 255);
            sb.append(String.format("%02x", objArr));
        }
        return sb.toString();
    }

    public static String getMccMnc(List<String> domain) {
        if (domain.size() != PLMNText.length) {
            return null;
        }
        int n;
        for (n = 0; n < PLMNText.length; n++) {
            String expect = PLMNText[n];
            if (!((String) domain.get(n)).regionMatches(0, expect, 0, expect.endsWith("*") ? expect.length() - 1 : expect.length())) {
                return null;
            }
        }
        String prefix = ((String) domain.get(2)).substring(3) + ((String) domain.get(3)).substring(3);
        for (n = 0; n < prefix.length(); n++) {
            char ch = prefix.charAt(n);
            if (ch < '0' || ch > '9') {
                return null;
            }
        }
        return prefix;
    }

    public static String roamingConsortiumsToString(long[] ois) {
        if (ois == null) {
            return "null";
        }
        Collection list = new ArrayList(ois.length);
        for (long oi : ois) {
            list.add(Long.valueOf(oi));
        }
        return roamingConsortiumsToString(list);
    }

    public static String roamingConsortiumsToString(Collection<Long> ois) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Long longValue : ois) {
            long oi = longValue.longValue();
            if (first) {
                first = false;
            } else {
                sb.append(", ");
            }
            Object[] objArr;
            if (Long.numberOfLeadingZeros(oi) > 40) {
                objArr = new Object[1];
                objArr[0] = Long.valueOf(oi);
                sb.append(String.format("%06x", objArr));
            } else {
                objArr = new Object[1];
                objArr[0] = Long.valueOf(oi);
                sb.append(String.format("%010x", objArr));
            }
        }
        return sb.toString();
    }

    public static String toUnicodeEscapedString(String s) {
        StringBuilder sb = new StringBuilder(s.length());
        for (int n = 0; n < s.length(); n++) {
            char ch = s.charAt(n);
            if (ch < ' ' || ch >= 127) {
                StringBuilder append = sb.append("\\u");
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(ch);
                append.append(String.format("%04x", objArr));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder(data.length * 3);
        boolean first = true;
        for (byte b : data) {
            if (first) {
                first = false;
            } else {
                sb.append(' ');
            }
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(b & 255);
            sb.append(String.format("%02x", objArr));
        }
        return sb.toString();
    }

    public static String toHex(byte[] octets) {
        StringBuilder sb = new StringBuilder(octets.length * 2);
        for (byte o : octets) {
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(o & 255);
            sb.append(String.format("%02x", objArr));
        }
        return sb.toString();
    }

    public static byte[] hexToBytes(String text) {
        if ((text.length() & 1) == 1) {
            throw new NumberFormatException("Odd length hex string: " + text.length());
        }
        byte[] data = new byte[(text.length() >> 1)];
        int position = 0;
        for (int n = 0; n < text.length(); n += 2) {
            data[position] = (byte) (((fromHex(text.charAt(n), false) & 15) << 4) | (fromHex(text.charAt(n + 1), false) & 15));
            position++;
        }
        return data;
    }

    public static int fromHex(char ch, boolean lenient) throws NumberFormatException {
        if (ch <= '9' && ch >= '0') {
            return ch - 48;
        }
        if (ch >= 'a' && ch <= 'f') {
            return (ch + 10) - 97;
        }
        if (ch <= 'F' && ch >= 'A') {
            return (ch + 10) - 65;
        }
        if (lenient) {
            return -1;
        }
        throw new NumberFormatException("Bad hex-character: " + ch);
    }

    private static char toAscii(int b) {
        return (b < 32 || b >= SupportedRates.MASK) ? '.' : (char) b;
    }

    static boolean isDecimal(String s) {
        for (int n = 0; n < s.length(); n++) {
            char ch = s.charAt(n);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    public static <T extends Comparable> int compare(Comparable<T> c1, T c2) {
        if (c1 == null) {
            return c2 == null ? 0 : -1;
        } else if (c2 == null) {
            return 1;
        } else {
            return c1.compareTo(c2);
        }
    }

    public static String bytesToBingoCard(ByteBuffer data, int len) {
        ByteBuffer dup = data.duplicate();
        dup.limit(dup.position() + len);
        return bytesToBingoCard(dup);
    }

    public static String bytesToBingoCard(ByteBuffer data) {
        Object[] objArr;
        ByteBuffer dup = data.duplicate();
        StringBuilder sbx = new StringBuilder();
        while (dup.hasRemaining()) {
            objArr = new Object[1];
            objArr[0] = Integer.valueOf(dup.get() & 255);
            sbx.append(String.format("%02x ", objArr));
        }
        dup = data.duplicate();
        sbx.append(' ');
        while (dup.hasRemaining()) {
            objArr = new Object[1];
            objArr[0] = Character.valueOf(toAscii(dup.get() & 255));
            sbx.append(String.format("%c", objArr));
        }
        return sbx.toString();
    }

    public static String toHMS(long millis) {
        long time;
        String str;
        if (millis >= 0) {
            time = millis;
        } else {
            time = -millis;
        }
        long tmp = time / 1000;
        long ms = time - (1000 * tmp);
        time = tmp;
        tmp /= 60;
        long s = time - (60 * tmp);
        time = tmp;
        tmp /= 60;
        long m = time - (60 * tmp);
        String str2 = "%s%d:%02d:%02d.%03d";
        Object[] objArr = new Object[5];
        if (millis < 0) {
            str = "-";
        } else {
            str = "";
        }
        objArr[0] = str;
        objArr[1] = Long.valueOf(tmp);
        objArr[2] = Long.valueOf(m);
        objArr[3] = Long.valueOf(s);
        objArr[4] = Long.valueOf(ms);
        return String.format(str2, objArr);
    }

    public static String toUTCString(long ms) {
        if (ms < 0) {
            return "unset";
        }
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        c.setTimeInMillis(ms);
        Object[] objArr = new Object[6];
        objArr[0] = Integer.valueOf(c.get(1));
        objArr[1] = Integer.valueOf(c.get(2) + 1);
        objArr[2] = Integer.valueOf(c.get(5));
        objArr[3] = Integer.valueOf(c.get(11));
        objArr[4] = Integer.valueOf(c.get(12));
        objArr[5] = Integer.valueOf(c.get(13));
        return String.format("%4d/%02d/%02d %2d:%02d:%02dZ", objArr);
    }

    public static String unquote(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() > 1 && s.startsWith("\"") && s.endsWith("\"")) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
