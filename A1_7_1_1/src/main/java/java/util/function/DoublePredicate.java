package java.util.function;

import java.util.Objects;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.isAllBlocksEmpty(BlockUtils.java:546)
    	at jadx.core.dex.visitors.ExtractFieldInit.getConstructorsList(ExtractFieldInit.java:221)
    	at jadx.core.dex.visitors.ExtractFieldInit.moveCommonFieldsInit(ExtractFieldInit.java:121)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:46)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:42)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ClassModifier.removeFieldUsageFromConstructor(ClassModifier.java:100)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticFields(ClassModifier.java:75)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:48)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:40)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
@FunctionalInterface
public interface DoublePredicate {

    final /* synthetic */ class -java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0 implements DoublePredicate {
        private /* synthetic */ DoublePredicate val$other;
        private /* synthetic */ DoublePredicate val$this;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e8 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0.<init>(java.util.function.DoublePredicate, java.util.function.DoublePredicate):void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        public /* synthetic */ -java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0(java.util.function.DoublePredicate r1, java.util.function.DoublePredicate r2) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e8 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0.<init>(java.util.function.DoublePredicate, java.util.function.DoublePredicate):void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0.<init>(java.util.function.DoublePredicate, java.util.function.DoublePredicate):void");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0.test(double):boolean, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        public boolean test(double r1) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0.test(double):boolean, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0.test(double):boolean");
        }
    }

    final /* synthetic */ class -java_util_function_DoublePredicate_negate__LambdaImpl0 implements DoublePredicate {
        private /* synthetic */ DoublePredicate val$this;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e8 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_negate__LambdaImpl0.<init>(java.util.function.DoublePredicate):void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        public /* synthetic */ -java_util_function_DoublePredicate_negate__LambdaImpl0(java.util.function.DoublePredicate r1) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e8 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_negate__LambdaImpl0.<init>(java.util.function.DoublePredicate):void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_negate__LambdaImpl0.<init>(java.util.function.DoublePredicate):void");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_negate__LambdaImpl0.test(double):boolean, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        public boolean test(double r1) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_negate__LambdaImpl0.test(double):boolean, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_negate__LambdaImpl0.test(double):boolean");
        }
    }

    final /* synthetic */ class -java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0 implements DoublePredicate {
        private /* synthetic */ DoublePredicate val$other;
        private /* synthetic */ DoublePredicate val$this;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e8 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0.<init>(java.util.function.DoublePredicate, java.util.function.DoublePredicate):void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        public /* synthetic */ -java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0(java.util.function.DoublePredicate r1, java.util.function.DoublePredicate r2) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e8 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0.<init>(java.util.function.DoublePredicate, java.util.function.DoublePredicate):void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0.<init>(java.util.function.DoublePredicate, java.util.function.DoublePredicate):void");
        }

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 00e5 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0.test(double):boolean, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        public boolean test(double r1) {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 00e5 in method: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0.test(double):boolean, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.function.DoublePredicate.-java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0.test(double):boolean");
        }
    }

    boolean test(double d);

    DoublePredicate and(DoublePredicate other) {
        Objects.requireNonNull(other);
        return new -java_util_function_DoublePredicate_and_java_util_function_DoublePredicate_other_LambdaImpl0(this, other);
    }

    /* renamed from: -java_util_function_DoublePredicate_lambda$1 */
    /* synthetic */ boolean m64-java_util_function_DoublePredicate_lambda$1(DoublePredicate other, double value) {
        return test(value) ? other.test(value) : false;
    }

    /* renamed from: -java_util_function_DoublePredicate_lambda$2 */
    /* synthetic */ boolean m65-java_util_function_DoublePredicate_lambda$2(double value) {
        return !test(value);
    }

    DoublePredicate negate() {
        return new -java_util_function_DoublePredicate_negate__LambdaImpl0(this);
    }

    DoublePredicate or(DoublePredicate other) {
        Objects.requireNonNull(other);
        return new -java_util_function_DoublePredicate_or_java_util_function_DoublePredicate_other_LambdaImpl0(this, other);
    }

    /* renamed from: -java_util_function_DoublePredicate_lambda$3 */
    /* synthetic */ boolean m66-java_util_function_DoublePredicate_lambda$3(DoublePredicate other, double value) {
        return !test(value) ? other.test(value) : true;
    }
}
