package com.android.internal.widget;

import com.android.internal.widget.LockPatternChecker.OnCheckCallback;
import com.android.internal.widget.LockPatternUtils.CheckCredentialProgressCallback;

final /* synthetic */ class -$Lambda$E2sSlgjiM2w1MdavtCJi6YeQRgk implements CheckCredentialProgressCallback {
    private final /* synthetic */ byte $id;
    /* renamed from: -$f0 */
    private final /* synthetic */ Object f140-$f0;

    private final /* synthetic */ void $m$0() {
        ((OnCheckCallback) this.f140-$f0).-com_android_internal_widget_LockPatternChecker$5-mthref-0();
    }

    private final /* synthetic */ void $m$1() {
        ((OnCheckCallback) this.f140-$f0).-com_android_internal_widget_LockPatternChecker$5-mthref-0();
    }

    public /* synthetic */ -$Lambda$E2sSlgjiM2w1MdavtCJi6YeQRgk(byte b, Object obj) {
        this.$id = b;
        this.f140-$f0 = obj;
    }

    public final void onEarlyMatched() {
        switch (this.$id) {
            case (byte) 0:
                $m$0();
                return;
            case (byte) 1:
                $m$1();
                return;
            default:
                throw new AssertionError();
        }
    }
}
