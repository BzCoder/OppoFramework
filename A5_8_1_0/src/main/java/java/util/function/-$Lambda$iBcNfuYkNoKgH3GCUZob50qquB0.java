package java.util.function;

final /* synthetic */ class -$Lambda$iBcNfuYkNoKgH3GCUZob50qquB0 implements LongPredicate {
    /* renamed from: -$f0 */
    private final /* synthetic */ Object f183-$f0;

    /* renamed from: java.util.function.-$Lambda$iBcNfuYkNoKgH3GCUZob50qquB0$1 */
    final /* synthetic */ class AnonymousClass1 implements LongPredicate {
        private final /* synthetic */ byte $id;
        /* renamed from: -$f0 */
        private final /* synthetic */ Object f184-$f0;
        /* renamed from: -$f1 */
        private final /* synthetic */ Object f185-$f1;

        private final /* synthetic */ boolean $m$0(long arg0) {
            return ((LongPredicate) this.f184-$f0).m97lambda$-java_util_function_LongPredicate_2838((LongPredicate) this.f185-$f1, arg0);
        }

        private final /* synthetic */ boolean $m$1(long arg0) {
            return ((LongPredicate) this.f184-$f0).m99lambda$-java_util_function_LongPredicate_4082((LongPredicate) this.f185-$f1, arg0);
        }

        public /* synthetic */ AnonymousClass1(byte b, Object obj, Object obj2) {
            this.$id = b;
            this.f184-$f0 = obj;
            this.f185-$f1 = obj2;
        }

        public final boolean test(long j) {
            switch (this.$id) {
                case (byte) 0:
                    return $m$0(j);
                case (byte) 1:
                    return $m$1(j);
                default:
                    throw new AssertionError();
            }
        }
    }

    private final /* synthetic */ boolean $m$0(long arg0) {
        return ((LongPredicate) this.f183-$f0).m98lambda$-java_util_function_LongPredicate_3144(arg0);
    }

    public /* synthetic */ -$Lambda$iBcNfuYkNoKgH3GCUZob50qquB0(Object obj) {
        this.f183-$f0 = obj;
    }

    public final boolean test(long j) {
        return $m$0(j);
    }
}
