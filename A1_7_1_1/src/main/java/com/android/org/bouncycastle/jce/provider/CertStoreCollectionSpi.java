package com.android.org.bouncycastle.jce.provider;

import java.security.cert.CertStoreSpi;
import java.security.cert.CollectionCertStoreParameters;

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
public class CertStoreCollectionSpi extends CertStoreSpi {
    private CollectionCertStoreParameters params;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e9 in method: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.<init>(java.security.cert.CertStoreParameters):void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
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
        	... 5 more
        */
    public CertStoreCollectionSpi(java.security.cert.CertStoreParameters r1) throws java.security.InvalidAlgorithmParameterException {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e9 in method: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.<init>(java.security.cert.CertStoreParameters):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.<init>(java.security.cert.CertStoreParameters):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.engineGetCRLs(java.security.cert.CRLSelector):java.util.Collection, dex: 
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
    public java.util.Collection engineGetCRLs(java.security.cert.CRLSelector r1) throws java.security.cert.CertStoreException {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.engineGetCRLs(java.security.cert.CRLSelector):java.util.Collection, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.engineGetCRLs(java.security.cert.CRLSelector):java.util.Collection");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.engineGetCertificates(java.security.cert.CertSelector):java.util.Collection, dex: 
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
    public java.util.Collection engineGetCertificates(java.security.cert.CertSelector r1) throws java.security.cert.CertStoreException {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.engineGetCertificates(java.security.cert.CertSelector):java.util.Collection, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jce.provider.CertStoreCollectionSpi.engineGetCertificates(java.security.cert.CertSelector):java.util.Collection");
    }
}
