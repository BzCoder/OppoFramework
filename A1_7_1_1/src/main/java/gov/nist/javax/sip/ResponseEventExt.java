package gov.nist.javax.sip;

import javax.sip.ResponseEvent;

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
public class ResponseEventExt extends ResponseEvent {
    private ClientTransactionExt m_originalTransaction;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: null in method: gov.nist.javax.sip.ResponseEventExt.<init>(java.lang.Object, gov.nist.javax.sip.ClientTransactionExt, javax.sip.Dialog, javax.sip.message.Response):void, dex:  in method: gov.nist.javax.sip.ResponseEventExt.<init>(java.lang.Object, gov.nist.javax.sip.ClientTransactionExt, javax.sip.Dialog, javax.sip.message.Response):void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: jadx.core.utils.exceptions.DecodeException: null in method: gov.nist.javax.sip.ResponseEventExt.<init>(java.lang.Object, gov.nist.javax.sip.ClientTransactionExt, javax.sip.Dialog, javax.sip.message.Response):void, dex: 
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
    public ResponseEventExt(java.lang.Object r1, gov.nist.javax.sip.ClientTransactionExt r2, javax.sip.Dialog r3, javax.sip.message.Response r4) {
        /*
        // Can't load method instructions: Load method exception: null in method: gov.nist.javax.sip.ResponseEventExt.<init>(java.lang.Object, gov.nist.javax.sip.ClientTransactionExt, javax.sip.Dialog, javax.sip.message.Response):void, dex:  in method: gov.nist.javax.sip.ResponseEventExt.<init>(java.lang.Object, gov.nist.javax.sip.ClientTransactionExt, javax.sip.Dialog, javax.sip.message.Response):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: gov.nist.javax.sip.ResponseEventExt.<init>(java.lang.Object, gov.nist.javax.sip.ClientTransactionExt, javax.sip.Dialog, javax.sip.message.Response):void");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: null in method: gov.nist.javax.sip.ResponseEventExt.getOriginalTransaction():gov.nist.javax.sip.ClientTransactionExt, dex:  in method: gov.nist.javax.sip.ResponseEventExt.getOriginalTransaction():gov.nist.javax.sip.ClientTransactionExt, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: jadx.core.utils.exceptions.DecodeException: null in method: gov.nist.javax.sip.ResponseEventExt.getOriginalTransaction():gov.nist.javax.sip.ClientTransactionExt, dex: 
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
    public gov.nist.javax.sip.ClientTransactionExt getOriginalTransaction() {
        /*
        // Can't load method instructions: Load method exception: null in method: gov.nist.javax.sip.ResponseEventExt.getOriginalTransaction():gov.nist.javax.sip.ClientTransactionExt, dex:  in method: gov.nist.javax.sip.ResponseEventExt.getOriginalTransaction():gov.nist.javax.sip.ClientTransactionExt, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: gov.nist.javax.sip.ResponseEventExt.getOriginalTransaction():gov.nist.javax.sip.ClientTransactionExt");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: gov.nist.javax.sip.ResponseEventExt.isForkedResponse():boolean, dex: 
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
    public boolean isForkedResponse() {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: gov.nist.javax.sip.ResponseEventExt.isForkedResponse():boolean, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: gov.nist.javax.sip.ResponseEventExt.isForkedResponse():boolean");
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: null in method: gov.nist.javax.sip.ResponseEventExt.setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt):void, dex:  in method: gov.nist.javax.sip.ResponseEventExt.setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt):void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: jadx.core.utils.exceptions.DecodeException: null in method: gov.nist.javax.sip.ResponseEventExt.setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt):void, dex: 
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
    public void setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt r1) {
        /*
        // Can't load method instructions: Load method exception: null in method: gov.nist.javax.sip.ResponseEventExt.setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt):void, dex:  in method: gov.nist.javax.sip.ResponseEventExt.setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt):void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: gov.nist.javax.sip.ResponseEventExt.setOriginalTransaction(gov.nist.javax.sip.ClientTransactionExt):void");
    }
}
