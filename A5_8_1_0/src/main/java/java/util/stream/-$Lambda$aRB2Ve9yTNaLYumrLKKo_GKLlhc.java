package java.util.stream;

import java.util.function.IntFunction;
import java.util.function.LongFunction;

final /* synthetic */ class -$Lambda$aRB2Ve9yTNaLYumrLKKo_GKLlhc implements LongFunction {
    /* renamed from: -$f0 */
    private final /* synthetic */ Object f217-$f0;

    private final /* synthetic */ Object $m$0(long arg0) {
        return Nodes.builder(arg0, (IntFunction) this.f217-$f0);
    }

    public /* synthetic */ -$Lambda$aRB2Ve9yTNaLYumrLKKo_GKLlhc(Object obj) {
        this.f217-$f0 = obj;
    }

    public final Object apply(long j) {
        return $m$0(j);
    }
}
