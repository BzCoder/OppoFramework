package java.nio.file;

import java.io.Closeable;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

final /* synthetic */ class -$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs implements Function {
    public static final /* synthetic */ -$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs $INST$0 = new -$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs((byte) 0);
    public static final /* synthetic */ -$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs $INST$1 = new -$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs((byte) 1);
    private final /* synthetic */ byte $id;

    /* renamed from: java.nio.file.-$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs$1 */
    final /* synthetic */ class AnonymousClass1 implements Runnable {
        private final /* synthetic */ byte $id;
        /* renamed from: -$f0 */
        private final /* synthetic */ Object f66-$f0;

        private final /* synthetic */ void $m$0() {
            Files.m30lambda$-java_nio_file_Files_3831((Closeable) this.f66-$f0);
        }

        private final /* synthetic */ void $m$1() {
            ((FileTreeIterator) this.f66-$f0).-java_nio_file_Files-mthref-1();
        }

        private final /* synthetic */ void $m$2() {
            ((FileTreeIterator) this.f66-$f0).-java_nio_file_Files-mthref-1();
        }

        public /* synthetic */ AnonymousClass1(byte b, Object obj) {
            this.$id = b;
            this.f66-$f0 = obj;
        }

        public final void run() {
            switch (this.$id) {
                case (byte) 0:
                    $m$0();
                    return;
                case (byte) 1:
                    $m$1();
                    return;
                case (byte) 2:
                    $m$2();
                    return;
                default:
                    throw new AssertionError();
            }
        }
    }

    /* renamed from: java.nio.file.-$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs$2 */
    final /* synthetic */ class AnonymousClass2 implements Predicate {
        /* renamed from: -$f0 */
        private final /* synthetic */ Object f67-$f0;

        private final /* synthetic */ boolean $m$0(Object arg0) {
            return ((BiPredicate) this.f67-$f0).test(((Event) arg0).lambda$-java_nio_file_Files_166757(), ((Event) arg0).attributes());
        }

        public /* synthetic */ AnonymousClass2(Object obj) {
            this.f67-$f0 = obj;
        }

        public final boolean test(Object obj) {
            return $m$0(obj);
        }
    }

    private /* synthetic */ -$Lambda$iV0HzPWaaR68t7NV87hCwF49CFs(byte b) {
        this.$id = b;
    }

    public final Object apply(Object obj) {
        switch (this.$id) {
            case (byte) 0:
                return $m$0(obj);
            case (byte) 1:
                return $m$1(obj);
            default:
                throw new AssertionError();
        }
    }
}
