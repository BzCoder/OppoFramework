package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CRLReason;
import java.util.Enumeration;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

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
public class CRLReasonCodeExtension extends Extension implements CertAttrSet<String> {
    public static final int AA_COMPROMISE = 10;
    public static final int AFFLIATION_CHANGED = 3;
    public static final int CA_COMPROMISE = 2;
    public static final int CERTIFICATE_HOLD = 6;
    public static final int CESSATION_OF_OPERATION = 5;
    public static final int KEY_COMPROMISE = 1;
    public static final String NAME = "CRLReasonCode";
    public static final int PRIVILEGE_WITHDRAWN = 9;
    public static final String REASON = "reason";
    public static final int REMOVE_FROM_CRL = 8;
    public static final int SUPERSEDED = 4;
    public static final int UNSPECIFIED = 0;
    private static CRLReason[] values;
    private int reasonCode;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: sun.security.x509.CRLReasonCodeExtension.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: sun.security.x509.CRLReasonCodeExtension.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.x509.CRLReasonCodeExtension.<clinit>():void");
    }

    private void encodeThis() throws IOException {
        if (this.reasonCode == 0) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream dos = new DerOutputStream();
        dos.putEnumerated(this.reasonCode);
        this.extensionValue = dos.toByteArray();
    }

    public CRLReasonCodeExtension(int reason) throws IOException {
        this(false, reason);
    }

    public CRLReasonCodeExtension(boolean critical, int reason) throws IOException {
        this.reasonCode = 0;
        this.extensionId = PKIXExtensions.ReasonCode_Id;
        this.critical = critical;
        this.reasonCode = reason;
        encodeThis();
    }

    public CRLReasonCodeExtension(Boolean critical, Object value) throws IOException {
        this.reasonCode = 0;
        this.extensionId = PKIXExtensions.ReasonCode_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        this.reasonCode = new DerValue(this.extensionValue).getEnumerated();
    }

    public void set(String name, Object obj) throws IOException {
        if (!(obj instanceof Integer)) {
            throw new IOException("Attribute must be of type Integer.");
        } else if (name.equalsIgnoreCase(REASON)) {
            this.reasonCode = ((Integer) obj).intValue();
            encodeThis();
        } else {
            throw new IOException("Name not supported by CRLReasonCodeExtension");
        }
    }

    public Object get(String name) throws IOException {
        if (name.equalsIgnoreCase(REASON)) {
            return new Integer(this.reasonCode);
        }
        throw new IOException("Name not supported by CRLReasonCodeExtension");
    }

    public void delete(String name) throws IOException {
        if (name.equalsIgnoreCase(REASON)) {
            this.reasonCode = 0;
            encodeThis();
            return;
        }
        throw new IOException("Name not supported by CRLReasonCodeExtension");
    }

    public String toString() {
        return super.toString() + "    Reason Code: " + values[this.reasonCode];
    }

    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.ReasonCode_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    public Enumeration<String> getElements() {
        AttributeNameEnumeration elements = new AttributeNameEnumeration();
        elements.addElement(REASON);
        return elements.elements();
    }

    public String getName() {
        return NAME;
    }

    public CRLReason getReasonCode() {
        if (this.reasonCode <= 0 || this.reasonCode >= values.length) {
            return CRLReason.UNSPECIFIED;
        }
        return values[this.reasonCode];
    }
}
