package com.android.org.bouncycastle.asn1.x509;

import com.android.org.bouncycastle.asn1.ASN1Object;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.isAllBlocksEmpty(BlockUtils.java:546)
    	at jadx.core.dex.visitors.ExtractFieldInit.getConstructorsList(ExtractFieldInit.java:221)
    	at jadx.core.dex.visitors.ExtractFieldInit.moveCommonFieldsInit(ExtractFieldInit.java:121)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:46)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class V2Form extends ASN1Object {
    IssuerSerial baseCertificateID;
    GeneralNames issuerName;
    ObjectDigestInfo objectDigestInfo;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: null in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.ASN1Sequence):void, dex:  in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.ASN1Sequence):void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: jadx.core.utils.exceptions.DecodeException: null in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.ASN1Sequence):void, dex: 
        	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:51)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:103)
        	... 5 more
        Caused by: java.io.EOFException
        	at com.android.dx.io.instructions.ShortArrayCodeInput.read(ShortArrayCodeInput.java:54)
        	at com.android.dx.io.instructions.ShortArrayCodeInput.readInt(ShortArrayCodeInput.java:62)
        	at com.android.dx.io.instructions.InstructionCodec$34.decode(InstructionCodec.java:756)
        	at jadx.core.dex.instructions.InsnDecoder.decodeRawInsn(InsnDecoder.java:66)
        	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:48)
        	... 6 more
        */
    public V2Form(com.android.org.bouncycastle.asn1.ASN1Sequence r1) {
        /*
        // Can't load method instructions: Load method exception: null in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.ASN1Sequence):void, dex:  in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.ASN1Sequence):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.ASN1Sequence):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames):void, dex: 
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
    public V2Form(com.android.org.bouncycastle.asn1.x509.GeneralNames r1) {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.IssuerSerial):void, dex: 
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
    public V2Form(com.android.org.bouncycastle.asn1.x509.GeneralNames r1, com.android.org.bouncycastle.asn1.x509.IssuerSerial r2) {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.IssuerSerial):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.IssuerSerial):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e8 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.IssuerSerial, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo):void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e8
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    public V2Form(com.android.org.bouncycastle.asn1.x509.GeneralNames r1, com.android.org.bouncycastle.asn1.x509.IssuerSerial r2, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo r3) {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e8 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.IssuerSerial, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.IssuerSerial, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo):void, dex: 
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
    public V2Form(com.android.org.bouncycastle.asn1.x509.GeneralNames r1, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo r2) {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.<init>(com.android.org.bouncycastle.asn1.x509.GeneralNames, com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.getBaseCertificateID():com.android.org.bouncycastle.asn1.x509.IssuerSerial, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    public com.android.org.bouncycastle.asn1.x509.IssuerSerial getBaseCertificateID() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.getBaseCertificateID():com.android.org.bouncycastle.asn1.x509.IssuerSerial, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.getBaseCertificateID():com.android.org.bouncycastle.asn1.x509.IssuerSerial");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.getIssuerName():com.android.org.bouncycastle.asn1.x509.GeneralNames, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    public com.android.org.bouncycastle.asn1.x509.GeneralNames getIssuerName() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.getIssuerName():com.android.org.bouncycastle.asn1.x509.GeneralNames, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.getIssuerName():com.android.org.bouncycastle.asn1.x509.GeneralNames");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.getObjectDigestInfo():com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    public com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo getObjectDigestInfo() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.getObjectDigestInfo():com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.getObjectDigestInfo():com.android.org.bouncycastle.asn1.x509.ObjectDigestInfo");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.toASN1Primitive():com.android.org.bouncycastle.asn1.ASN1Primitive, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 00e5
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 5 more
        */
    public com.android.org.bouncycastle.asn1.ASN1Primitive toASN1Primitive() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.asn1.x509.V2Form.toASN1Primitive():com.android.org.bouncycastle.asn1.ASN1Primitive, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.asn1.x509.V2Form.toASN1Primitive():com.android.org.bouncycastle.asn1.ASN1Primitive");
    }

    public static V2Form getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static V2Form getInstance(Object obj) {
        if (obj instanceof V2Form) {
            return obj;
        }
        if (obj != null) {
            return new V2Form(ASN1Sequence.getInstance(obj));
        }
        return null;
    }
}
