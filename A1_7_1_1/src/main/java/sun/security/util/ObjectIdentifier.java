package sun.security.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;

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
public final class ObjectIdentifier implements Serializable {
    /* renamed from: -assertionsDisabled */
    static final /* synthetic */ boolean f57-assertionsDisabled = false;
    private static final long serialVersionUID = 8697030238860181294L;
    private int componentLen;
    private Object components;
    private transient boolean componentsCalculated;
    private byte[] encoding;
    private volatile transient String stringForm;

    static class HugeOidNotSupportedByOldJDK implements Serializable {
        private static final long serialVersionUID = 1;
        static HugeOidNotSupportedByOldJDK theOne;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: sun.security.util.ObjectIdentifier.HugeOidNotSupportedByOldJDK.<clinit>():void, dex: 
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
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: sun.security.util.ObjectIdentifier.HugeOidNotSupportedByOldJDK.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.security.util.ObjectIdentifier.HugeOidNotSupportedByOldJDK.<clinit>():void");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: sun.security.util.ObjectIdentifier.HugeOidNotSupportedByOldJDK.<init>():void, dex: 
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
        HugeOidNotSupportedByOldJDK() {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: sun.security.util.ObjectIdentifier.HugeOidNotSupportedByOldJDK.<init>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.security.util.ObjectIdentifier.HugeOidNotSupportedByOldJDK.<init>():void");
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: sun.security.util.ObjectIdentifier.<clinit>():void, dex: 
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
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e9
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 9 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: sun.security.util.ObjectIdentifier.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.util.ObjectIdentifier.<clinit>():void");
    }

    private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
        is.defaultReadObject();
        if (this.encoding == null) {
            init((int[]) this.components, this.componentLen);
        }
    }

    private void writeObject(ObjectOutputStream os) throws IOException {
        if (!this.componentsCalculated) {
            int[] comps = toIntArray();
            if (comps != null) {
                this.components = comps;
                this.componentLen = comps.length;
            } else {
                this.components = HugeOidNotSupportedByOldJDK.theOne;
            }
            this.componentsCalculated = true;
        }
        os.defaultWriteObject();
    }

    public ObjectIdentifier(String oid) throws IOException {
        this.encoding = null;
        this.components = null;
        this.componentLen = -1;
        this.componentsCalculated = f57-assertionsDisabled;
        int start = 0;
        int pos = 0;
        byte[] tmp = new byte[oid.length()];
        int first = 0;
        int count = 0;
        int end;
        do {
            try {
                String comp;
                int length;
                end = oid.indexOf(46, start);
                if (end == -1) {
                    comp = oid.substring(start);
                    length = oid.length() - start;
                } else {
                    comp = oid.substring(start, end);
                    length = end - start;
                }
                if (length > 9) {
                    BigInteger bignum = new BigInteger(comp);
                    if (count == 0) {
                        checkFirstComponent(bignum);
                        first = bignum.intValue();
                    } else {
                        if (count == 1) {
                            checkSecondComponent(first, bignum);
                            bignum = bignum.add(BigInteger.valueOf((long) (first * 40)));
                        } else {
                            checkOtherComponent(count, bignum);
                        }
                        pos += pack7Oid(bignum, tmp, pos);
                    }
                } else {
                    int num = Integer.parseInt(comp);
                    if (count == 0) {
                        checkFirstComponent(num);
                        first = num;
                    } else {
                        if (count == 1) {
                            checkSecondComponent(first, num);
                            num += first * 40;
                        } else {
                            checkOtherComponent(count, num);
                        }
                        pos += pack7Oid(num, tmp, pos);
                    }
                }
                start = end + 1;
                count++;
            } catch (IOException ioe) {
                throw ioe;
            } catch (Exception e) {
                throw new IOException("ObjectIdentifier() -- Invalid format: " + e.toString(), e);
            }
        } while (end != -1);
        checkCount(count);
        this.encoding = new byte[pos];
        System.arraycopy(tmp, 0, this.encoding, 0, pos);
        this.stringForm = oid;
    }

    public ObjectIdentifier(int[] values) throws IOException {
        this.encoding = null;
        this.components = null;
        this.componentLen = -1;
        this.componentsCalculated = f57-assertionsDisabled;
        checkCount(values.length);
        checkFirstComponent(values[0]);
        checkSecondComponent(values[0], values[1]);
        for (int i = 2; i < values.length; i++) {
            checkOtherComponent(i, values[i]);
        }
        init(values, values.length);
    }

    public ObjectIdentifier(DerInputStream in) throws IOException {
        this.encoding = null;
        this.components = null;
        this.componentLen = -1;
        this.componentsCalculated = f57-assertionsDisabled;
        int type_id = (byte) in.getByte();
        if (type_id != (byte) 6) {
            throw new IOException("ObjectIdentifier() -- data isn't an object ID (tag = " + type_id + ")");
        }
        this.encoding = new byte[in.getLength()];
        in.getBytes(this.encoding);
        check(this.encoding);
    }

    ObjectIdentifier(DerInputBuffer buf) throws IOException {
        this.encoding = null;
        this.components = null;
        this.componentLen = -1;
        this.componentsCalculated = f57-assertionsDisabled;
        DerInputStream in = new DerInputStream(buf);
        this.encoding = new byte[in.available()];
        in.getBytes(this.encoding);
        check(this.encoding);
    }

    private void init(int[] components, int length) {
        int pos;
        byte[] tmp = new byte[((length * 5) + 1)];
        if (components[1] < Integer.MAX_VALUE - (components[0] * 40)) {
            pos = pack7Oid((components[0] * 40) + components[1], tmp, 0) + 0;
        } else {
            pos = pack7Oid(BigInteger.valueOf((long) components[1]).add(BigInteger.valueOf((long) (components[0] * 40))), tmp, 0) + 0;
        }
        for (int i = 2; i < length; i++) {
            pos += pack7Oid(components[i], tmp, pos);
        }
        this.encoding = new byte[pos];
        System.arraycopy(tmp, 0, this.encoding, 0, pos);
    }

    public static ObjectIdentifier newInternal(int[] values) {
        try {
            return new ObjectIdentifier(values);
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        }
    }

    void encode(DerOutputStream out) throws IOException {
        out.write((byte) 6, this.encoding);
    }

    @Deprecated
    public boolean equals(ObjectIdentifier other) {
        return equals((Object) other);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ObjectIdentifier)) {
            return f57-assertionsDisabled;
        }
        return Arrays.equals(this.encoding, ((ObjectIdentifier) obj).encoding);
    }

    public int hashCode() {
        return Arrays.hashCode(this.encoding);
    }

    public int[] toIntArray() {
        int length = this.encoding.length;
        int[] result = new int[20];
        int fromPos = 0;
        int i = 0;
        int which = 0;
        while (i < length) {
            int i2;
            if ((this.encoding[i] & 128) == 0) {
                if ((i - fromPos) + 1 > 4) {
                    BigInteger big = new BigInteger(pack(this.encoding, fromPos, (i - fromPos) + 1, 7, 8));
                    if (fromPos == 0) {
                        i2 = which + 1;
                        result[which] = 2;
                        BigInteger second = big.subtract(BigInteger.valueOf(80));
                        if (second.compareTo(BigInteger.valueOf(2147483647L)) == 1) {
                            return null;
                        }
                        which = i2 + 1;
                        result[i2] = second.intValue();
                        i2 = which;
                    } else if (big.compareTo(BigInteger.valueOf(2147483647L)) == 1) {
                        return null;
                    } else {
                        i2 = which + 1;
                        result[which] = big.intValue();
                    }
                } else {
                    int retval = 0;
                    for (int j = fromPos; j <= i; j++) {
                        retval = (retval << 7) | (this.encoding[j] & 127);
                    }
                    if (fromPos != 0) {
                        i2 = which + 1;
                        result[which] = retval;
                    } else if (retval < 80) {
                        i2 = which + 1;
                        result[which] = retval / 40;
                        which = i2 + 1;
                        result[i2] = retval % 40;
                        i2 = which;
                    } else {
                        i2 = which + 1;
                        result[which] = 2;
                        which = i2 + 1;
                        result[i2] = retval - 80;
                        i2 = which;
                    }
                }
                fromPos = i + 1;
            } else {
                i2 = which;
            }
            if (i2 >= result.length) {
                result = Arrays.copyOf(result, i2 + 10);
            }
            i++;
            which = i2;
        }
        return Arrays.copyOf(result, which);
    }

    public String toString() {
        String s = this.stringForm;
        if (s != null) {
            return s;
        }
        int length = this.encoding.length;
        StringBuffer sb = new StringBuffer(length * 4);
        int fromPos = 0;
        for (int i = 0; i < length; i++) {
            if ((this.encoding[i] & 128) == 0) {
                if (fromPos != 0) {
                    sb.append('.');
                }
                if ((i - fromPos) + 1 > 4) {
                    Object big = new BigInteger(pack(this.encoding, fromPos, (i - fromPos) + 1, 7, 8));
                    if (fromPos == 0) {
                        sb.append("2.");
                        sb.append(big.subtract(BigInteger.valueOf(80)));
                    } else {
                        sb.append(big);
                    }
                } else {
                    int retval = 0;
                    for (int j = fromPos; j <= i; j++) {
                        retval = (retval << 7) | (this.encoding[j] & 127);
                    }
                    if (fromPos != 0) {
                        sb.append(retval);
                    } else if (retval < 80) {
                        sb.append(retval / 40);
                        sb.append('.');
                        sb.append(retval % 40);
                    } else {
                        sb.append("2.");
                        sb.append(retval - 80);
                    }
                }
                fromPos = i + 1;
            }
        }
        s = sb.toString();
        this.stringForm = s;
        return s;
    }

    private static byte[] pack(byte[] in, int ioffset, int ilength, int iw, int ow) {
        int i = 0;
        if (!f57-assertionsDisabled) {
            int i2 = (iw <= 0 || iw > 8) ? 0 : 1;
            if (i2 == 0) {
                throw new AssertionError((Object) "input NUB must be between 1 and 8");
            }
        }
        if (!f57-assertionsDisabled) {
            if (ow > 0 && ow <= 8) {
                i = 1;
            }
            if (i == 0) {
                throw new AssertionError((Object) "output NUB must be between 1 and 8");
            }
        }
        if (iw == ow) {
            return (byte[]) in.clone();
        }
        int bits = ilength * iw;
        byte[] out = new byte[(((bits + ow) - 1) / ow)];
        int ipos = 0;
        int opos = ((((bits + ow) - 1) / ow) * ow) - bits;
        while (ipos < bits) {
            int count = iw - (ipos % iw);
            if (count > ow - (opos % ow)) {
                count = ow - (opos % ow);
            }
            i = opos / ow;
            out[i] = (byte) (out[i] | ((((in[(ipos / iw) + ioffset] + 256) >> ((iw - (ipos % iw)) - count)) & ((1 << count) - 1)) << ((ow - (opos % ow)) - count)));
            ipos += count;
            opos += count;
        }
        return out;
    }

    private static int pack7Oid(byte[] in, int ioffset, int ilength, byte[] out, int ooffset) {
        byte[] pack = pack(in, ioffset, ilength, 8, 7);
        int firstNonZero = pack.length - 1;
        for (int i = pack.length - 2; i >= 0; i--) {
            if (pack[i] != (byte) 0) {
                firstNonZero = i;
            }
            pack[i] = (byte) (pack[i] | 128);
        }
        System.arraycopy(pack, firstNonZero, out, ooffset, pack.length - firstNonZero);
        return pack.length - firstNonZero;
    }

    private static int pack8(byte[] in, int ioffset, int ilength, byte[] out, int ooffset) {
        byte[] pack = pack(in, ioffset, ilength, 7, 8);
        int firstNonZero = pack.length - 1;
        for (int i = pack.length - 2; i >= 0; i--) {
            if (pack[i] != (byte) 0) {
                firstNonZero = i;
            }
        }
        System.arraycopy(pack, firstNonZero, out, ooffset, pack.length - firstNonZero);
        return pack.length - firstNonZero;
    }

    private static int pack7Oid(int input, byte[] out, int ooffset) {
        byte[] b = new byte[4];
        b[0] = (byte) (input >> 24);
        b[1] = (byte) (input >> 16);
        b[2] = (byte) (input >> 8);
        b[3] = (byte) input;
        return pack7Oid(b, 0, 4, out, ooffset);
    }

    private static int pack7Oid(BigInteger input, byte[] out, int ooffset) {
        byte[] b = input.toByteArray();
        return pack7Oid(b, 0, b.length, out, ooffset);
    }

    private static void check(byte[] encoding) throws IOException {
        int length = encoding.length;
        if (length < 1 || (encoding[length - 1] & 128) != 0) {
            throw new IOException("ObjectIdentifier() -- Invalid DER encoding, not ended");
        }
        int i = 0;
        while (i < length) {
            if (encoding[i] == Byte.MIN_VALUE && (i == 0 || (encoding[i - 1] & 128) == 0)) {
                throw new IOException("ObjectIdentifier() -- Invalid DER encoding, useless extra octet detected");
            }
            i++;
        }
    }

    private static void checkCount(int count) throws IOException {
        if (count < 2) {
            throw new IOException("ObjectIdentifier() -- Must be at least two oid components ");
        }
    }

    private static void checkFirstComponent(int first) throws IOException {
        if (first < 0 || first > 2) {
            throw new IOException("ObjectIdentifier() -- First oid component is invalid ");
        }
    }

    private static void checkFirstComponent(BigInteger first) throws IOException {
        if (first.signum() == -1 || first.compareTo(BigInteger.valueOf(2)) == 1) {
            throw new IOException("ObjectIdentifier() -- First oid component is invalid ");
        }
    }

    private static void checkSecondComponent(int first, int second) throws IOException {
        if (second < 0 || (first != 2 && second > 39)) {
            throw new IOException("ObjectIdentifier() -- Second oid component is invalid ");
        }
    }

    private static void checkSecondComponent(int first, BigInteger second) throws IOException {
        if (second.signum() == -1 || (first != 2 && second.compareTo(BigInteger.valueOf(39)) == 1)) {
            throw new IOException("ObjectIdentifier() -- Second oid component is invalid ");
        }
    }

    private static void checkOtherComponent(int i, int num) throws IOException {
        if (num < 0) {
            throw new IOException("ObjectIdentifier() -- oid component #" + (i + 1) + " must be non-negative ");
        }
    }

    private static void checkOtherComponent(int i, BigInteger num) throws IOException {
        if (num.signum() == -1) {
            throw new IOException("ObjectIdentifier() -- oid component #" + (i + 1) + " must be non-negative ");
        }
    }
}
