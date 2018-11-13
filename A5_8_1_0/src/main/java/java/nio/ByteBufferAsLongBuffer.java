package java.nio;

import libcore.io.Memory;

class ByteBufferAsLongBuffer extends LongBuffer {
    /* renamed from: -assertionsDisabled */
    static final /* synthetic */ boolean f56-assertionsDisabled = (ByteBufferAsLongBuffer.class.desiredAssertionStatus() ^ 1);
    protected final ByteBuffer bb;
    protected final int offset;
    private final ByteOrder order;

    ByteBufferAsLongBuffer(ByteBuffer bb, int mark, int pos, int lim, int cap, int off, ByteOrder order) {
        super(mark, pos, lim, cap);
        this.bb = bb.duplicate();
        this.isReadOnly = bb.isReadOnly;
        if (bb instanceof DirectByteBuffer) {
            this.address = bb.address + ((long) off);
        }
        this.bb.order(order);
        this.order = order;
        this.offset = off;
    }

    public LongBuffer slice() {
        int pos = position();
        int lim = limit();
        if (f56-assertionsDisabled || pos <= lim) {
            int rem = pos <= lim ? lim - pos : 0;
            int off = (pos << 3) + this.offset;
            if (f56-assertionsDisabled || off >= 0) {
                return new ByteBufferAsLongBuffer(this.bb, -1, 0, rem, rem, off, this.order);
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public LongBuffer duplicate() {
        return new ByteBufferAsLongBuffer(this.bb, markValue(), position(), limit(), capacity(), this.offset, this.order);
    }

    public LongBuffer asReadOnlyBuffer() {
        return new ByteBufferAsLongBuffer(this.bb.asReadOnlyBuffer(), markValue(), position(), limit(), capacity(), this.offset, this.order);
    }

    protected int ix(int i) {
        return (i << 3) + this.offset;
    }

    public long get() {
        return get(nextGetIndex());
    }

    public long get(int i) {
        return this.bb.getLongUnchecked(ix(checkIndex(i)));
    }

    public LongBuffer get(long[] dst, int offset, int length) {
        Buffer.checkBounds(offset, length, dst.length);
        if (length > remaining()) {
            throw new BufferUnderflowException();
        }
        this.bb.getUnchecked(ix(this.position), dst, offset, length);
        this.position += length;
        return this;
    }

    public LongBuffer put(long x) {
        put(nextPutIndex(), x);
        return this;
    }

    public LongBuffer put(int i, long x) {
        if (this.isReadOnly) {
            throw new ReadOnlyBufferException();
        }
        this.bb.putLongUnchecked(ix(checkIndex(i)), x);
        return this;
    }

    public LongBuffer put(long[] src, int offset, int length) {
        Buffer.checkBounds(offset, length, src.length);
        if (length > remaining()) {
            throw new BufferOverflowException();
        }
        this.bb.putUnchecked(ix(this.position), src, offset, length);
        this.position += length;
        return this;
    }

    public LongBuffer compact() {
        if (this.isReadOnly) {
            throw new ReadOnlyBufferException();
        }
        int pos = position();
        int lim = limit();
        if (f56-assertionsDisabled || pos <= lim) {
            int rem = pos <= lim ? lim - pos : 0;
            if (this.bb instanceof DirectByteBuffer) {
                Memory.memmove(this, ix(0), this, ix(pos), (long) (rem << 3));
            } else {
                System.arraycopy(this.bb.array(), ix(pos), this.bb.array(), ix(0), rem << 3);
            }
            position(rem);
            limit(capacity());
            discardMark();
            return this;
        }
        throw new AssertionError();
    }

    public boolean isDirect() {
        return this.bb.isDirect();
    }

    public boolean isReadOnly() {
        return this.isReadOnly;
    }

    public ByteOrder order() {
        return this.order;
    }
}
