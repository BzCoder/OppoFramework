package sun.security.provider.certpath;

import java.security.cert.PKIXCertPathBuilderResult;
import sun.security.util.Debug;

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
public class SunCertPathBuilderResult extends PKIXCertPathBuilderResult {
    private static final Debug debug = null;
    private AdjacencyList adjList;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: sun.security.provider.certpath.SunCertPathBuilderResult.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: sun.security.provider.certpath.SunCertPathBuilderResult.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.SunCertPathBuilderResult.<clinit>():void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: null in method: sun.security.provider.certpath.SunCertPathBuilderResult.<init>(java.security.cert.CertPath, java.security.cert.TrustAnchor, java.security.cert.PolicyNode, java.security.PublicKey, sun.security.provider.certpath.AdjacencyList):void, dex:  in method: sun.security.provider.certpath.SunCertPathBuilderResult.<init>(java.security.cert.CertPath, java.security.cert.TrustAnchor, java.security.cert.PolicyNode, java.security.PublicKey, sun.security.provider.certpath.AdjacencyList):void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: jadx.core.utils.exceptions.DecodeException: null in method: sun.security.provider.certpath.SunCertPathBuilderResult.<init>(java.security.cert.CertPath, java.security.cert.TrustAnchor, java.security.cert.PolicyNode, java.security.PublicKey, sun.security.provider.certpath.AdjacencyList):void, dex: 
        	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:51)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:103)
        	... 5 more
        Caused by: java.io.EOFException
        	at com.android.dx.io.instructions.ShortArrayCodeInput.read(ShortArrayCodeInput.java:54)
        	at com.android.dx.io.instructions.ShortArrayCodeInput.readLong(ShortArrayCodeInput.java:71)
        	at com.android.dx.io.instructions.InstructionCodec$31.decode(InstructionCodec.java:652)
        	at jadx.core.dex.instructions.InsnDecoder.decodeRawInsn(InsnDecoder.java:66)
        	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:48)
        	... 6 more
        */
    SunCertPathBuilderResult(java.security.cert.CertPath r1, java.security.cert.TrustAnchor r2, java.security.cert.PolicyNode r3, java.security.PublicKey r4, sun.security.provider.certpath.AdjacencyList r5) {
        /*
        // Can't load method instructions: Load method exception: null in method: sun.security.provider.certpath.SunCertPathBuilderResult.<init>(java.security.cert.CertPath, java.security.cert.TrustAnchor, java.security.cert.PolicyNode, java.security.PublicKey, sun.security.provider.certpath.AdjacencyList):void, dex:  in method: sun.security.provider.certpath.SunCertPathBuilderResult.<init>(java.security.cert.CertPath, java.security.cert.TrustAnchor, java.security.cert.PolicyNode, java.security.PublicKey, sun.security.provider.certpath.AdjacencyList):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.SunCertPathBuilderResult.<init>(java.security.cert.CertPath, java.security.cert.TrustAnchor, java.security.cert.PolicyNode, java.security.PublicKey, sun.security.provider.certpath.AdjacencyList):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: null in method: sun.security.provider.certpath.SunCertPathBuilderResult.getAdjacencyList():sun.security.provider.certpath.AdjacencyList, dex:  in method: sun.security.provider.certpath.SunCertPathBuilderResult.getAdjacencyList():sun.security.provider.certpath.AdjacencyList, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: jadx.core.utils.exceptions.DecodeException: null in method: sun.security.provider.certpath.SunCertPathBuilderResult.getAdjacencyList():sun.security.provider.certpath.AdjacencyList, dex: 
        	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:51)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:103)
        	... 5 more
        Caused by: java.io.EOFException
        	at com.android.dx.io.instructions.ShortArrayCodeInput.read(ShortArrayCodeInput.java:54)
        	at com.android.dx.io.instructions.ShortArrayCodeInput.readLong(ShortArrayCodeInput.java:71)
        	at com.android.dx.io.instructions.InstructionCodec$31.decode(InstructionCodec.java:652)
        	at jadx.core.dex.instructions.InsnDecoder.decodeRawInsn(InsnDecoder.java:66)
        	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:48)
        	... 6 more
        */
    public sun.security.provider.certpath.AdjacencyList getAdjacencyList() {
        /*
        // Can't load method instructions: Load method exception: null in method: sun.security.provider.certpath.SunCertPathBuilderResult.getAdjacencyList():sun.security.provider.certpath.AdjacencyList, dex:  in method: sun.security.provider.certpath.SunCertPathBuilderResult.getAdjacencyList():sun.security.provider.certpath.AdjacencyList, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.SunCertPathBuilderResult.getAdjacencyList():sun.security.provider.certpath.AdjacencyList");
    }
}
