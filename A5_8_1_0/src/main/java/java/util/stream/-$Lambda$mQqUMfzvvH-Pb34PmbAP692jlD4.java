package java.util.stream;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.DistinctOps.AnonymousClass1;

final /* synthetic */ class -$Lambda$mQqUMfzvvH-Pb34PmbAP692jlD4 implements Consumer {
    private final /* synthetic */ byte $id;
    /* renamed from: -$f0 */
    private final /* synthetic */ Object f218-$f0;
    /* renamed from: -$f1 */
    private final /* synthetic */ Object f219-$f1;

    private final /* synthetic */ void $m$0(Object arg0) {
        AnonymousClass1.m137lambda$-java_util_stream_DistinctOps$1_3835((AtomicBoolean) this.f218-$f0, (ConcurrentHashMap) this.f219-$f1, arg0);
    }

    private final /* synthetic */ void $m$1(Object arg0) {
        ((BiConsumer) this.f218-$f0).lambda$-java_util_stream_ReferencePipeline_19478(this.f219-$f1, arg0);
    }

    private final /* synthetic */ void $m$2(Object arg0) {
        ((DistinctSpliterator) this.f218-$f0).m138x559c8077((Consumer) this.f219-$f1, arg0);
    }

    public /* synthetic */ -$Lambda$mQqUMfzvvH-Pb34PmbAP692jlD4(byte b, Object obj, Object obj2) {
        this.$id = b;
        this.f218-$f0 = obj;
        this.f219-$f1 = obj2;
    }

    public final void accept(Object obj) {
        switch (this.$id) {
            case (byte) 0:
                $m$0(obj);
                return;
            case (byte) 1:
                $m$1(obj);
                return;
            case (byte) 2:
                $m$2(obj);
                return;
            default:
                throw new AssertionError();
        }
    }
}
