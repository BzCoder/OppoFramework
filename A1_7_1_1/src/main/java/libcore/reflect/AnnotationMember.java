package libcore.reflect;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

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
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public final class AnnotationMember implements Serializable {
    protected static final char ARRAY = '[';
    protected static final char ERROR = '!';
    protected static final Object NO_VALUE = null;
    protected static final char OTHER = '*';
    protected transient Method definingMethod;
    protected transient Class<?> elementType;
    protected final String name;
    protected final char tag;
    protected final Object value;

    /*  JADX ERROR: NullPointerException in pass: EnumVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$0(DepthTraversal.java:13)
        	at java.util.ArrayList.forEach(ArrayList.java:1251)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:13)
        	at jadx.core.ProcessClass.process(ProcessClass.java:32)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        */
    private enum DefaultValues {
        ;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: libcore.reflect.AnnotationMember.DefaultValues.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: libcore.reflect.AnnotationMember.DefaultValues.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: libcore.reflect.AnnotationMember.DefaultValues.<clinit>():void");
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: libcore.reflect.AnnotationMember.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: libcore.reflect.AnnotationMember.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: libcore.reflect.AnnotationMember.<clinit>():void");
    }

    public AnnotationMember(String name, Object val) {
        this.name = name;
        if (val == null) {
            val = NO_VALUE;
        }
        this.value = val;
        if (this.value instanceof Throwable) {
            this.tag = ERROR;
        } else if (this.value.getClass().isArray()) {
            this.tag = ARRAY;
        } else {
            this.tag = OTHER;
        }
    }

    public AnnotationMember(String name, Object val, Class type, Method m) {
        this(name, val);
        this.definingMethod = m;
        if (type == Integer.TYPE) {
            this.elementType = Integer.class;
        } else if (type == Boolean.TYPE) {
            this.elementType = Boolean.class;
        } else if (type == Character.TYPE) {
            this.elementType = Character.class;
        } else if (type == Float.TYPE) {
            this.elementType = Float.class;
        } else if (type == Double.TYPE) {
            this.elementType = Double.class;
        } else if (type == Long.TYPE) {
            this.elementType = Long.class;
        } else if (type == Short.TYPE) {
            this.elementType = Short.class;
        } else if (type == Byte.TYPE) {
            this.elementType = Byte.class;
        } else {
            this.elementType = type;
        }
    }

    protected AnnotationMember setDefinition(AnnotationMember copy) {
        this.definingMethod = copy.definingMethod;
        this.elementType = copy.elementType;
        return this;
    }

    public String toString() {
        if (this.tag != ARRAY) {
            return this.name + "=" + this.value;
        }
        StringBuilder sb = new StringBuilder(80);
        sb.append(this.name).append("=[");
        int len = Array.getLength(this.value);
        for (int i = 0; i < len; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(Array.get(this.value, i));
        }
        return sb.append("]").toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AnnotationMember) {
            AnnotationMember that = (AnnotationMember) obj;
            if (this.name.equals(that.name) && this.tag == that.tag) {
                if (this.tag == ARRAY) {
                    return equalArrayValue(that.value);
                }
                if (this.tag == ERROR) {
                    return false;
                }
                return this.value.equals(that.value);
            }
        }
        return false;
    }

    public boolean equalArrayValue(Object otherValue) {
        if ((this.value instanceof Object[]) && (otherValue instanceof Object[])) {
            return Arrays.equals((Object[]) this.value, (Object[]) otherValue);
        }
        Class type = this.value.getClass();
        if (type != otherValue.getClass()) {
            return false;
        }
        if (type == int[].class) {
            return Arrays.equals((int[]) this.value, (int[]) otherValue);
        }
        if (type == byte[].class) {
            return Arrays.equals((byte[]) this.value, (byte[]) otherValue);
        }
        if (type == short[].class) {
            return Arrays.equals((short[]) this.value, (short[]) otherValue);
        }
        if (type == long[].class) {
            return Arrays.equals((long[]) this.value, (long[]) otherValue);
        }
        if (type == char[].class) {
            return Arrays.equals((char[]) this.value, (char[]) otherValue);
        }
        if (type == boolean[].class) {
            return Arrays.equals((boolean[]) this.value, (boolean[]) otherValue);
        }
        if (type == float[].class) {
            return Arrays.equals((float[]) this.value, (float[]) otherValue);
        }
        if (type == double[].class) {
            return Arrays.equals((double[]) this.value, (double[]) otherValue);
        }
        return false;
    }

    public int hashCode() {
        int hash = this.name.hashCode() * 127;
        if (this.tag != ARRAY) {
            return this.value.hashCode() ^ hash;
        }
        Class type = this.value.getClass();
        if (type == int[].class) {
            return Arrays.hashCode((int[]) this.value) ^ hash;
        }
        if (type == byte[].class) {
            return Arrays.hashCode((byte[]) this.value) ^ hash;
        }
        if (type == short[].class) {
            return Arrays.hashCode((short[]) this.value) ^ hash;
        }
        if (type == long[].class) {
            return Arrays.hashCode((long[]) this.value) ^ hash;
        }
        if (type == char[].class) {
            return Arrays.hashCode((char[]) this.value) ^ hash;
        }
        if (type == boolean[].class) {
            return Arrays.hashCode((boolean[]) this.value) ^ hash;
        }
        if (type == float[].class) {
            return Arrays.hashCode((float[]) this.value) ^ hash;
        }
        if (type == double[].class) {
            return Arrays.hashCode((double[]) this.value) ^ hash;
        }
        return Arrays.hashCode((Object[]) this.value) ^ hash;
    }

    public void rethrowError() throws Throwable {
        if (this.tag != ERROR) {
            return;
        }
        if (this.value instanceof TypeNotPresentException) {
            TypeNotPresentException tnpe = this.value;
            throw new TypeNotPresentException(tnpe.typeName(), tnpe.getCause());
        } else if (this.value instanceof EnumConstantNotPresentException) {
            EnumConstantNotPresentException ecnpe = this.value;
            throw new EnumConstantNotPresentException(ecnpe.enumType(), ecnpe.constantName());
        } else if (this.value instanceof ArrayStoreException) {
            throw new ArrayStoreException(this.value.getMessage());
        } else {
            int i;
            Throwable error = this.value;
            StackTraceElement[] ste = error.getStackTrace();
            if (ste == null) {
                i = 512;
            } else {
                i = (ste.length + 1) * 80;
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream(i);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(error);
            oos.flush();
            oos.close();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            error = (Throwable) ois.readObject();
            ois.close();
            throw error;
        }
    }

    public Object validateValue() throws Throwable {
        if (this.tag == ERROR) {
            rethrowError();
        }
        if (this.value == NO_VALUE) {
            return null;
        }
        if (this.elementType == this.value.getClass() || this.elementType.isInstance(this.value)) {
            return copyValue();
        }
        throw new AnnotationTypeMismatchException(this.definingMethod, this.value.getClass().getName());
    }

    public Object copyValue() throws Throwable {
        if (this.tag != ARRAY || Array.getLength(this.value) == 0) {
            return this.value;
        }
        Class type = this.value.getClass();
        if (type == int[].class) {
            return ((int[]) this.value).clone();
        }
        if (type == byte[].class) {
            return ((byte[]) this.value).clone();
        }
        if (type == short[].class) {
            return ((short[]) this.value).clone();
        }
        if (type == long[].class) {
            return ((long[]) this.value).clone();
        }
        if (type == char[].class) {
            return ((char[]) this.value).clone();
        }
        if (type == boolean[].class) {
            return ((boolean[]) this.value).clone();
        }
        if (type == float[].class) {
            return ((float[]) this.value).clone();
        }
        if (type == double[].class) {
            return ((double[]) this.value).clone();
        }
        return ((Object[]) this.value).clone();
    }
}
