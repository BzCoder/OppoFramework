package com.android.okhttp.okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class ByteString implements Serializable, Comparable<ByteString> {
    public static final ByteString EMPTY = null;
    static final char[] HEX_DIGITS = null;
    private static final long serialVersionUID = 1;
    final byte[] data;
    transient int hashCode;
    transient String utf8;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.okhttp.okio.ByteString.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.okhttp.okio.ByteString.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.okhttp.okio.ByteString.<clinit>():void");
    }

    ByteString(byte[] data) {
        this.data = data;
    }

    public static ByteString of(byte... data) {
        if (data != null) {
            return new ByteString((byte[]) data.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString of(byte[] data, int offset, int byteCount) {
        if (data == null) {
            throw new IllegalArgumentException("data == null");
        }
        Util.checkOffsetAndCount((long) data.length, (long) offset, (long) byteCount);
        byte[] copy = new byte[byteCount];
        System.arraycopy(data, offset, copy, 0, byteCount);
        return new ByteString(copy);
    }

    public static ByteString encodeUtf8(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(s.getBytes(Util.UTF_8));
        byteString.utf8 = s;
        return byteString;
    }

    public String utf8() {
        String result = this.utf8;
        if (result != null) {
            return result;
        }
        result = new String(this.data, Util.UTF_8);
        this.utf8 = result;
        return result;
    }

    public String base64() {
        return Base64.encode(this.data);
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    private ByteString digest(String digest) {
        try {
            return of(MessageDigest.getInstance(digest).digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public String base64Url() {
        return Base64.encodeUrl(this.data);
    }

    public static ByteString decodeBase64(String base64) {
        if (base64 == null) {
            throw new IllegalArgumentException("base64 == null");
        }
        byte[] decoded = Base64.decode(base64);
        if (decoded != null) {
            return new ByteString(decoded);
        }
        return null;
    }

    public String hex() {
        char[] result = new char[(this.data.length * 2)];
        int c = 0;
        for (byte b : this.data) {
            int i = c + 1;
            result[c] = HEX_DIGITS[(b >> 4) & 15];
            c = i + 1;
            result[i] = HEX_DIGITS[b & 15];
        }
        return new String(result);
    }

    public static ByteString decodeHex(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + hex);
        } else {
            byte[] result = new byte[(hex.length() / 2)];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) ((decodeHexDigit(hex.charAt(i * 2)) << 4) + decodeHexDigit(hex.charAt((i * 2) + 1)));
            }
            return of(result);
        }
    }

    private static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - 48;
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 97) + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 65) + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static ByteString read(InputStream in, int byteCount) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("in == null");
        } else if (byteCount < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        } else {
            byte[] result = new byte[byteCount];
            int offset = 0;
            while (offset < byteCount) {
                int read = in.read(result, offset, byteCount - offset);
                if (read == -1) {
                    throw new EOFException();
                }
                offset += read;
            }
            return new ByteString(result);
        }
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (i < this.data.length) {
            byte c = this.data[i];
            if (c < (byte) 65 || c > (byte) 90) {
                i++;
            } else {
                byte[] lowercase = (byte[]) this.data.clone();
                int i2 = i + 1;
                lowercase[i] = (byte) (c + 32);
                for (i = i2; i < lowercase.length; i++) {
                    c = lowercase[i];
                    if (c >= (byte) 65 && c <= (byte) 90) {
                        lowercase[i] = (byte) (c + 32);
                    }
                }
                return new ByteString(lowercase);
            }
        }
        return this;
    }

    public ByteString toAsciiUppercase() {
        int i = 0;
        while (i < this.data.length) {
            byte c = this.data[i];
            if (c < (byte) 97 || c > (byte) 122) {
                i++;
            } else {
                byte[] lowercase = (byte[]) this.data.clone();
                int i2 = i + 1;
                lowercase[i] = (byte) (c - 32);
                for (i = i2; i < lowercase.length; i++) {
                    c = lowercase[i];
                    if (c >= (byte) 97 && c <= (byte) 122) {
                        lowercase[i] = (byte) (c - 32);
                    }
                }
                return new ByteString(lowercase);
            }
        }
        return this;
    }

    public ByteString substring(int beginIndex) {
        return substring(beginIndex, this.data.length);
    }

    public ByteString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (endIndex > this.data.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        } else {
            int subLen = endIndex - beginIndex;
            if (subLen < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (beginIndex == 0 && endIndex == this.data.length) {
                return this;
            } else {
                byte[] copy = new byte[subLen];
                System.arraycopy(this.data, beginIndex, copy, 0, subLen);
                return new ByteString(copy);
            }
        }
    }

    public byte getByte(int pos) {
        return this.data[pos];
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    public void write(OutputStream out) throws IOException {
        if (out == null) {
            throw new IllegalArgumentException("out == null");
        }
        out.write(this.data);
    }

    void write(Buffer buffer) {
        buffer.write(this.data, 0, this.data.length);
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        return other.rangeEquals(otherOffset, this.data, offset, byteCount);
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        if (offset > this.data.length - byteCount || otherOffset > other.length - byteCount) {
            return false;
        }
        return Util.arrayRangeEquals(this.data, offset, other, otherOffset, byteCount);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        boolean rangeEquals;
        if ((o instanceof ByteString) && ((ByteString) o).size() == this.data.length) {
            rangeEquals = ((ByteString) o).rangeEquals(0, this.data, 0, this.data.length);
        } else {
            rangeEquals = false;
        }
        return rangeEquals;
    }

    public int hashCode() {
        int result = this.hashCode;
        if (result != 0) {
            return result;
        }
        result = Arrays.hashCode(this.data);
        this.hashCode = result;
        return result;
    }

    public int compareTo(ByteString byteString) {
        int i = -1;
        int sizeA = size();
        int sizeB = byteString.size();
        int i2 = 0;
        int size = Math.min(sizeA, sizeB);
        while (i2 < size) {
            int byteA = getByte(i2) & 255;
            int byteB = byteString.getByte(i2) & 255;
            if (byteA == byteB) {
                i2++;
            } else {
                if (byteA >= byteB) {
                    i = 1;
                }
                return i;
            }
        }
        if (sizeA == sizeB) {
            return 0;
        }
        if (sizeA >= sizeB) {
            i = 1;
        }
        return i;
    }

    public String toString() {
        if (this.data.length == 0) {
            return "ByteString[size=0]";
        }
        Object[] objArr;
        if (this.data.length <= 16) {
            objArr = new Object[2];
            objArr[0] = Integer.valueOf(this.data.length);
            objArr[1] = hex();
            return String.format("ByteString[size=%s data=%s]", objArr);
        }
        objArr = new Object[2];
        objArr[0] = Integer.valueOf(this.data.length);
        objArr[1] = md5().hex();
        return String.format("ByteString[size=%s md5=%s]", objArr);
    }

    private void readObject(ObjectInputStream in) throws IOException {
        ByteString byteString = read(in, in.readInt());
        try {
            Field field = ByteString.class.getDeclaredField("data");
            field.setAccessible(true);
            field.set(this, byteString.data);
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.data.length);
        out.write(this.data);
    }
}
