package android.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.GrowingArrayUtils;
import java.lang.reflect.Array;
import java.util.IdentityHashMap;
import libcore.util.EmptyArray;

/*  JADX ERROR: NullPointerException in pass: ReSugarCode
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.ReSugarCode.initClsEnumMap(ReSugarCode.java:159)
    	at jadx.core.dex.visitors.ReSugarCode.visit(ReSugarCode.java:44)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
    	at java.lang.Iterable.forEach(Iterable.java:75)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
    	at jadx.core.ProcessClass.process(ProcessClass.java:37)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class SpannableStringBuilder implements CharSequence, GetChars, Spannable, Editable, Appendable, GraphicsOperations {
    private static final int END_MASK = 15;
    private static final int MARK = 1;
    private static final InputFilter[] NO_FILTERS = null;
    private static final int PARAGRAPH = 3;
    private static final int POINT = 2;
    private static final int SPAN_ADDED = 2048;
    private static final int SPAN_END_AT_END = 32768;
    private static final int SPAN_END_AT_START = 16384;
    private static final int SPAN_START_AT_END = 8192;
    private static final int SPAN_START_AT_START = 4096;
    private static final int SPAN_START_END_MASK = 61440;
    private static final int START_MASK = 240;
    private static final int START_SHIFT = 4;
    private static final String TAG = "SpannableStringBuilder";
    private InputFilter[] mFilters;
    private int mGapLength;
    private int mGapStart;
    private IdentityHashMap<Object, Integer> mIndexOfSpan;
    private int mLowWaterMark;
    private int[] mOrderSortBuffer;
    private int[] mPrioSortBuffer;
    private int mSpanCount;
    private int[] mSpanEnds;
    private int[] mSpanFlags;
    private int mSpanInsertCount;
    private int[] mSpanMax;
    private int[] mSpanOrder;
    private int[] mSpanStarts;
    private Object[] mSpans;
    private char[] mText;
    private int mTextWatcherDepth;

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.text.SpannableStringBuilder.<clinit>():void, dex: 
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
        	at jadx.core.ProcessClass.process(ProcessClass.java:29)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:51)
        	at java.lang.Iterable.forEach(Iterable.java:75)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:51)
        	at jadx.core.ProcessClass.process(ProcessClass.java:37)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
        Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
        	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1227)
        	at com.android.dx.io.OpcodeInfo.getName(OpcodeInfo.java:1234)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:581)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:74)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:104)
        	... 9 more
        */
    static {
        /*
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.text.SpannableStringBuilder.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: android.text.SpannableStringBuilder.<clinit>():void");
    }

    public SpannableStringBuilder() {
        this(PhoneConstants.MVNO_TYPE_NONE);
    }

    public SpannableStringBuilder(CharSequence text) {
        this(text, 0, text.length());
    }

    public SpannableStringBuilder(CharSequence text, int start, int end) {
        this.mFilters = NO_FILTERS;
        int srclen = end - start;
        if (srclen < 0) {
            throw new StringIndexOutOfBoundsException();
        }
        this.mText = ArrayUtils.newUnpaddedCharArray(GrowingArrayUtils.growSize(srclen));
        this.mGapStart = srclen;
        this.mGapLength = this.mText.length - srclen;
        TextUtils.getChars(text, start, end, this.mText, 0);
        this.mSpanCount = 0;
        this.mSpanInsertCount = 0;
        this.mSpans = EmptyArray.OBJECT;
        this.mSpanStarts = EmptyArray.INT;
        this.mSpanEnds = EmptyArray.INT;
        this.mSpanFlags = EmptyArray.INT;
        this.mSpanMax = EmptyArray.INT;
        this.mSpanOrder = EmptyArray.INT;
        this.mPrioSortBuffer = EmptyArray.INT;
        this.mOrderSortBuffer = EmptyArray.INT;
        if (text instanceof Spanned) {
            Spanned sp = (Spanned) text;
            Object[] spans = sp.getSpans(start, end, Object.class);
            for (int i = 0; i < spans.length; i++) {
                if (!(spans[i] instanceof NoCopySpan)) {
                    int st = sp.getSpanStart(spans[i]) - start;
                    int en = sp.getSpanEnd(spans[i]) - start;
                    int fl = sp.getSpanFlags(spans[i]);
                    if (st < 0) {
                        st = 0;
                    }
                    if (st > end - start) {
                        st = end - start;
                    }
                    if (en < 0) {
                        en = 0;
                    }
                    if (en > end - start) {
                        en = end - start;
                    }
                    setSpan(false, spans[i], st, en, fl);
                }
            }
            restoreInvariants();
        }
    }

    public static SpannableStringBuilder valueOf(CharSequence source) {
        if (source instanceof SpannableStringBuilder) {
            return (SpannableStringBuilder) source;
        }
        return new SpannableStringBuilder(source);
    }

    public char charAt(int where) {
        int len = length();
        if (where < 0) {
            throw new IndexOutOfBoundsException("charAt: " + where + " < 0");
        } else if (where >= len) {
            throw new IndexOutOfBoundsException("charAt: " + where + " >= length " + len);
        } else if (where >= this.mGapStart) {
            return this.mText[this.mGapLength + where];
        } else {
            return this.mText[where];
        }
    }

    public int length() {
        return this.mText.length - this.mGapLength;
    }

    private void resizeFor(int size) {
        int oldLength = this.mText.length;
        if (size + 1 > oldLength) {
            char[] newText = ArrayUtils.newUnpaddedCharArray(GrowingArrayUtils.growSize(size));
            System.arraycopy(this.mText, 0, newText, 0, this.mGapStart);
            int newLength = newText.length;
            int delta = newLength - oldLength;
            int after = oldLength - (this.mGapStart + this.mGapLength);
            System.arraycopy(this.mText, oldLength - after, newText, newLength - after, after);
            this.mText = newText;
            this.mGapLength += delta;
            if (this.mGapLength < 1) {
                new Exception("mGapLength < 1").printStackTrace();
            }
            if (this.mSpanCount != 0) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    int[] iArr;
                    if (this.mSpanStarts[i] > this.mGapStart) {
                        iArr = this.mSpanStarts;
                        iArr[i] = iArr[i] + delta;
                    }
                    if (this.mSpanEnds[i] > this.mGapStart) {
                        iArr = this.mSpanEnds;
                        iArr[i] = iArr[i] + delta;
                    }
                }
                calcMax(treeRoot());
            }
        }
    }

    private void moveGapTo(int where) {
        if (where != this.mGapStart) {
            boolean atEnd = where == length();
            int overlap;
            if (where < this.mGapStart) {
                overlap = this.mGapStart - where;
                System.arraycopy(this.mText, where, this.mText, (this.mGapStart + this.mGapLength) - overlap, overlap);
            } else {
                overlap = where - this.mGapStart;
                System.arraycopy(this.mText, (this.mGapLength + where) - overlap, this.mText, this.mGapStart, overlap);
            }
            if (this.mSpanCount != 0) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    int flag;
                    int start = this.mSpanStarts[i];
                    int end = this.mSpanEnds[i];
                    if (start > this.mGapStart) {
                        start -= this.mGapLength;
                    }
                    if (start > where) {
                        start += this.mGapLength;
                    } else if (start == where) {
                        flag = (this.mSpanFlags[i] & 240) >> 4;
                        if (flag == 2 || (atEnd && flag == 3)) {
                            start += this.mGapLength;
                        }
                    }
                    if (end > this.mGapStart) {
                        end -= this.mGapLength;
                    }
                    if (end > where) {
                        end += this.mGapLength;
                    } else if (end == where) {
                        flag = this.mSpanFlags[i] & 15;
                        if (flag == 2 || (atEnd && flag == 3)) {
                            end += this.mGapLength;
                        }
                    }
                    this.mSpanStarts[i] = start;
                    this.mSpanEnds[i] = end;
                }
                calcMax(treeRoot());
            }
            this.mGapStart = where;
        }
    }

    public SpannableStringBuilder insert(int where, CharSequence tb, int start, int end) {
        return replace(where, where, tb, start, end);
    }

    public SpannableStringBuilder insert(int where, CharSequence tb) {
        return replace(where, where, tb, 0, tb.length());
    }

    public SpannableStringBuilder delete(int start, int end) {
        if (TextUtils.DEBUG_LOG) {
            TextUtils.printDebugLog(TAG, "[delete] start " + start + "," + end);
        }
        SpannableStringBuilder ret = replace(start, end, PhoneConstants.MVNO_TYPE_NONE, 0, 0);
        if (TextUtils.DEBUG_LOG) {
            TextUtils.printDebugLog(TAG, "[delete] end ");
        }
        if (this.mGapLength > length() * 2) {
            resizeFor(length());
        }
        return ret;
    }

    public void clear() {
        replace(0, length(), PhoneConstants.MVNO_TYPE_NONE, 0, 0);
        this.mSpanInsertCount = 0;
    }

    public void clearSpans() {
        for (int i = this.mSpanCount - 1; i >= 0; i--) {
            Object what = this.mSpans[i];
            int ostart = this.mSpanStarts[i];
            int oend = this.mSpanEnds[i];
            if (ostart > this.mGapStart) {
                ostart -= this.mGapLength;
            }
            if (oend > this.mGapStart) {
                oend -= this.mGapLength;
            }
            this.mSpanCount = i;
            this.mSpans[i] = null;
            sendSpanRemoved(what, ostart, oend);
        }
        if (this.mIndexOfSpan != null) {
            this.mIndexOfSpan.clear();
        }
        this.mSpanInsertCount = 0;
    }

    public SpannableStringBuilder append(CharSequence text) {
        int length = length();
        return replace(length, length, text, 0, text.length());
    }

    public SpannableStringBuilder append(CharSequence text, Object what, int flags) {
        int start = length();
        append(text);
        setSpan(what, start, length(), flags);
        return this;
    }

    public SpannableStringBuilder append(CharSequence text, int start, int end) {
        int length = length();
        return replace(length, length, text, start, end);
    }

    public SpannableStringBuilder append(char text) {
        return append(String.valueOf(text));
    }

    private boolean removeSpansForChange(int start, int end, boolean textIsRemoved, int i) {
        boolean z = false;
        if ((i & 1) != 0 && resolveGap(this.mSpanMax[i]) >= start && removeSpansForChange(start, end, textIsRemoved, leftChild(i))) {
            return true;
        }
        if (i >= this.mSpanCount) {
            return false;
        }
        if ((this.mSpanFlags[i] & 33) != 33 || this.mSpanStarts[i] < start || this.mSpanStarts[i] >= this.mGapStart + this.mGapLength || this.mSpanEnds[i] < start || this.mSpanEnds[i] >= this.mGapStart + this.mGapLength || (!textIsRemoved && this.mSpanStarts[i] <= start && this.mSpanEnds[i] >= this.mGapStart)) {
            if (resolveGap(this.mSpanStarts[i]) <= end && (i & 1) != 0) {
                z = removeSpansForChange(start, end, textIsRemoved, rightChild(i));
            }
            return z;
        }
        this.mIndexOfSpan.remove(this.mSpans[i]);
        removeSpan(i);
        return true;
    }

    private void change(int start, int end, CharSequence cs, int csStart, int csEnd) {
        int i;
        int replacedLength = end - start;
        int replacementLength = csEnd - csStart;
        int nbNewChars = replacementLength - replacedLength;
        boolean changed = false;
        for (i = this.mSpanCount - 1; i >= 0; i--) {
            int spanStart = this.mSpanStarts[i];
            if (spanStart > this.mGapStart) {
                spanStart -= this.mGapLength;
            }
            int spanEnd = this.mSpanEnds[i];
            if (spanEnd > this.mGapStart) {
                spanEnd -= this.mGapLength;
            }
            if ((this.mSpanFlags[i] & 51) == 51) {
                int ost = spanStart;
                int oen = spanEnd;
                int clen = length();
                if (spanStart > start && spanStart <= end) {
                    spanStart = end;
                    while (spanStart < clen) {
                        if (spanStart > end) {
                            if (charAt(spanStart - 1) == 10) {
                                break;
                            }
                        }
                        spanStart++;
                    }
                }
                if (spanEnd > start && spanEnd <= end) {
                    spanEnd = end;
                    while (spanEnd < clen) {
                        if (spanEnd > end) {
                            if (charAt(spanEnd - 1) == 10) {
                                break;
                            }
                        }
                        spanEnd++;
                    }
                }
                if (!(spanStart == ost && spanEnd == oen)) {
                    setSpan(false, this.mSpans[i], spanStart, spanEnd, this.mSpanFlags[i]);
                    changed = true;
                }
            }
            int flags = 0;
            if (spanStart == start) {
                flags = 4096;
            } else if (spanStart == end + nbNewChars) {
                flags = 8192;
            }
            if (spanEnd == start) {
                flags |= 16384;
            } else if (spanEnd == end + nbNewChars) {
                flags |= 32768;
            }
            int[] iArr = this.mSpanFlags;
            iArr[i] = iArr[i] | flags;
        }
        if (changed) {
            restoreInvariants();
        }
        moveGapTo(end);
        if (nbNewChars >= this.mGapLength) {
            resizeFor((this.mText.length + nbNewChars) - this.mGapLength);
        }
        boolean textIsRemoved = replacementLength == 0;
        if (replacedLength > 0) {
            while (this.mSpanCount > 0) {
                if (!removeSpansForChange(start, end, textIsRemoved, treeRoot())) {
                    break;
                }
            }
        }
        this.mGapStart += nbNewChars;
        this.mGapLength -= nbNewChars;
        if (this.mGapLength < 1) {
            new Exception("mGapLength < 1").printStackTrace();
        }
        TextUtils.getChars(cs, csStart, csEnd, this.mText, start);
        if (replacedLength > 0) {
            boolean atEnd = this.mGapStart + this.mGapLength == this.mText.length;
            for (i = 0; i < this.mSpanCount; i++) {
                this.mSpanStarts[i] = updatedIntervalBound(this.mSpanStarts[i], start, nbNewChars, (this.mSpanFlags[i] & 240) >> 4, atEnd, textIsRemoved);
                this.mSpanEnds[i] = updatedIntervalBound(this.mSpanEnds[i], start, nbNewChars, this.mSpanFlags[i] & 15, atEnd, textIsRemoved);
            }
            restoreInvariants();
        }
        if (cs instanceof Spanned) {
            Spanned sp = (Spanned) cs;
            Object[] spans = sp.getSpans(csStart, csEnd, Object.class);
            for (i = 0; i < spans.length; i++) {
                int st = sp.getSpanStart(spans[i]);
                int en = sp.getSpanEnd(spans[i]);
                if (st < csStart) {
                    st = csStart;
                }
                if (en > csEnd) {
                    en = csEnd;
                }
                if (getSpanStart(spans[i]) < 0) {
                    int copySpanStart = (st - csStart) + start;
                    int copySpanEnd = (en - csStart) + start;
                    int copySpanFlags = sp.getSpanFlags(spans[i]) | 2048;
                    int flagsEnd = copySpanFlags & 15;
                    if (!(isInvalidParagraphStart(copySpanStart, (copySpanFlags & 240) >> 4) || isInvalidParagraphEnd(copySpanEnd, flagsEnd))) {
                        setSpan(false, spans[i], copySpanStart, copySpanEnd, copySpanFlags);
                    }
                }
            }
            restoreInvariants();
        }
    }

    private int updatedIntervalBound(int offset, int start, int nbNewChars, int flag, boolean atEnd, boolean textIsRemoved) {
        if (offset >= start && offset < this.mGapStart + this.mGapLength) {
            if (flag == 2) {
                if (textIsRemoved || offset > start) {
                    return this.mGapStart + this.mGapLength;
                }
            } else if (flag == 3) {
                if (atEnd) {
                    return this.mGapStart + this.mGapLength;
                }
            } else if (textIsRemoved || offset < this.mGapStart - nbNewChars) {
                return start;
            } else {
                return this.mGapStart;
            }
        }
        return offset;
    }

    private void removeSpan(int i) {
        Object object = this.mSpans[i];
        int start = this.mSpanStarts[i];
        int end = this.mSpanEnds[i];
        if (start > this.mGapStart) {
            start -= this.mGapLength;
        }
        if (end > this.mGapStart) {
            end -= this.mGapLength;
        }
        int count = this.mSpanCount - (i + 1);
        System.arraycopy(this.mSpans, i + 1, this.mSpans, i, count);
        System.arraycopy(this.mSpanStarts, i + 1, this.mSpanStarts, i, count);
        System.arraycopy(this.mSpanEnds, i + 1, this.mSpanEnds, i, count);
        System.arraycopy(this.mSpanFlags, i + 1, this.mSpanFlags, i, count);
        System.arraycopy(this.mSpanOrder, i + 1, this.mSpanOrder, i, count);
        this.mSpanCount--;
        invalidateIndex(i);
        this.mSpans[this.mSpanCount] = null;
        restoreInvariants();
        sendSpanRemoved(object, start, end);
    }

    public SpannableStringBuilder replace(int start, int end, CharSequence tb) {
        return replace(start, end, tb, 0, tb.length());
    }

    public SpannableStringBuilder replace(int start, int end, CharSequence tb, int tbstart, int tbend) {
        checkRange("replace", start, end);
        if (TextUtils.DEBUG_LOG) {
            TextUtils.printDebugLog(TAG, "[replace] start");
        }
        for (InputFilter filter : this.mFilters) {
            CharSequence repl = filter.filter(tb, tbstart, tbend, this, start, end);
            if (repl != null) {
                tb = repl;
                tbstart = 0;
                tbend = repl.length();
            }
        }
        int origLen = end - start;
        int newLen = tbend - tbstart;
        if (origLen == 0 && newLen == 0 && !hasNonExclusiveExclusiveSpanAt(tb, tbstart)) {
            return this;
        }
        TextWatcher[] textWatchers = (TextWatcher[]) getSpans(start, start + origLen, TextWatcher.class);
        sendBeforeTextChanged(textWatchers, start, origLen, newLen);
        boolean adjustSelection = (origLen == 0 || newLen == 0) ? false : true;
        int selectionStart = 0;
        int selectionEnd = 0;
        if (adjustSelection) {
            selectionStart = Selection.getSelectionStart(this);
            selectionEnd = Selection.getSelectionEnd(this);
        }
        change(start, end, tb, tbstart, tbend);
        if (adjustSelection) {
            boolean changed = false;
            if (selectionStart > start && selectionStart < end) {
                selectionStart = start + Math.toIntExact((((long) newLen) * ((long) (selectionStart - start))) / ((long) origLen));
                changed = true;
                setSpan(false, Selection.SELECTION_START, selectionStart, selectionStart, 34);
            }
            if (selectionEnd > start && selectionEnd < end) {
                selectionEnd = start + Math.toIntExact((((long) newLen) * ((long) (selectionEnd - start))) / ((long) origLen));
                changed = true;
                setSpan(false, Selection.SELECTION_END, selectionEnd, selectionEnd, 34);
            }
            if (changed) {
                restoreInvariants();
            }
        }
        sendTextChanged(textWatchers, start, origLen, newLen);
        sendAfterTextChanged(textWatchers);
        sendToSpanWatchers(start, end, newLen - origLen);
        if (TextUtils.DEBUG_LOG) {
            TextUtils.printDebugLog(TAG, "[replace] end");
        }
        return this;
    }

    private static boolean hasNonExclusiveExclusiveSpanAt(CharSequence text, int offset) {
        if (text instanceof Spanned) {
            Spanned spanned = (Spanned) text;
            for (Object span : spanned.getSpans(offset, offset, Object.class)) {
                if (spanned.getSpanFlags(span) != 33) {
                    return true;
                }
            }
        }
        return false;
    }

    private void sendToSpanWatchers(int replaceStart, int replaceEnd, int nbNewChars) {
        int i;
        int spanStart;
        int spanEnd;
        int[] iArr;
        for (i = 0; i < this.mSpanCount; i++) {
            int spanFlags = this.mSpanFlags[i];
            if ((spanFlags & 2048) == 0) {
                spanStart = this.mSpanStarts[i];
                spanEnd = this.mSpanEnds[i];
                if (spanStart > this.mGapStart) {
                    spanStart -= this.mGapLength;
                }
                if (spanEnd > this.mGapStart) {
                    spanEnd -= this.mGapLength;
                }
                int newReplaceEnd = replaceEnd + nbNewChars;
                boolean spanChanged = false;
                int previousSpanStart = spanStart;
                if (spanStart > newReplaceEnd) {
                    if (nbNewChars != 0) {
                        previousSpanStart -= nbNewChars;
                        spanChanged = true;
                    }
                } else if (spanStart >= replaceStart && !((spanStart == replaceStart && (spanFlags & 4096) == 4096) || (spanStart == newReplaceEnd && (spanFlags & 8192) == 8192))) {
                    spanChanged = true;
                }
                int previousSpanEnd = spanEnd;
                if (spanEnd > newReplaceEnd) {
                    if (nbNewChars != 0) {
                        previousSpanEnd -= nbNewChars;
                        spanChanged = true;
                    }
                } else if (spanEnd >= replaceStart && !((spanEnd == replaceStart && (spanFlags & 16384) == 16384) || (spanEnd == newReplaceEnd && (spanFlags & 32768) == 32768))) {
                    spanChanged = true;
                }
                if (spanChanged) {
                    sendSpanChanged(this.mSpans[i], previousSpanStart, previousSpanEnd, spanStart, spanEnd);
                }
                iArr = this.mSpanFlags;
                iArr[i] = iArr[i] & -61441;
            }
        }
        for (i = 0; i < this.mSpanCount; i++) {
            if ((this.mSpanFlags[i] & 2048) != 0) {
                iArr = this.mSpanFlags;
                iArr[i] = iArr[i] & -2049;
                spanStart = this.mSpanStarts[i];
                spanEnd = this.mSpanEnds[i];
                if (spanStart > this.mGapStart) {
                    spanStart -= this.mGapLength;
                }
                if (spanEnd > this.mGapStart) {
                    spanEnd -= this.mGapLength;
                }
                sendSpanAdded(this.mSpans[i], spanStart, spanEnd);
            }
        }
    }

    public void setSpan(Object what, int start, int end, int flags) {
        setSpan(true, what, start, end, flags);
    }

    private void setSpan(boolean send, Object what, int start, int end, int flags) {
        checkRange("setSpan", start, end);
        int flagsStart = (flags & 240) >> 4;
        if (isInvalidParagraphStart(start, flagsStart)) {
            throw new RuntimeException("PARAGRAPH span must start at paragraph boundary");
        }
        int flagsEnd = flags & 15;
        if (isInvalidParagraphEnd(end, flagsEnd)) {
            throw new RuntimeException("PARAGRAPH span must end at paragraph boundary");
        } else if (flagsStart == 2 && flagsEnd == 1 && start == end) {
            if (send) {
                Log.e(TAG, "SPAN_EXCLUSIVE_EXCLUSIVE spans cannot have a zero length");
            }
        } else {
            int nstart = start;
            int nend = end;
            if (start > this.mGapStart) {
                start += this.mGapLength;
            } else if (start == this.mGapStart && (flagsStart == 2 || (flagsStart == 3 && start == length()))) {
                start += this.mGapLength;
            }
            if (end > this.mGapStart) {
                end += this.mGapLength;
            } else if (end == this.mGapStart && (flagsEnd == 2 || (flagsEnd == 3 && end == length()))) {
                end += this.mGapLength;
            }
            if (this.mIndexOfSpan != null) {
                Integer index = (Integer) this.mIndexOfSpan.get(what);
                if (index != null) {
                    int i = index.intValue();
                    int ostart = this.mSpanStarts[i];
                    int oend = this.mSpanEnds[i];
                    if (ostart > this.mGapStart) {
                        ostart -= this.mGapLength;
                    }
                    if (oend > this.mGapStart) {
                        oend -= this.mGapLength;
                    }
                    this.mSpanStarts[i] = start;
                    this.mSpanEnds[i] = end;
                    this.mSpanFlags[i] = flags;
                    if (send) {
                        restoreInvariants();
                        sendSpanChanged(what, ostart, oend, nstart, nend);
                    }
                    return;
                }
            }
            this.mSpans = GrowingArrayUtils.append(this.mSpans, this.mSpanCount, what);
            this.mSpanStarts = GrowingArrayUtils.append(this.mSpanStarts, this.mSpanCount, start);
            this.mSpanEnds = GrowingArrayUtils.append(this.mSpanEnds, this.mSpanCount, end);
            this.mSpanFlags = GrowingArrayUtils.append(this.mSpanFlags, this.mSpanCount, flags);
            this.mSpanOrder = GrowingArrayUtils.append(this.mSpanOrder, this.mSpanCount, this.mSpanInsertCount);
            invalidateIndex(this.mSpanCount);
            this.mSpanCount++;
            this.mSpanInsertCount++;
            int sizeOfMax = (treeRoot() * 2) + 1;
            if (this.mSpanMax.length < sizeOfMax) {
                this.mSpanMax = new int[sizeOfMax];
            }
            if (send) {
                restoreInvariants();
                sendSpanAdded(what, nstart, nend);
            }
        }
    }

    private final boolean isInvalidParagraphStart(int start, int flagsStart) {
        if (flagsStart != 3 || start == 0 || start == length() || charAt(start - 1) == 10) {
            return false;
        }
        return true;
    }

    private final boolean isInvalidParagraphEnd(int end, int flagsEnd) {
        if (flagsEnd != 3 || end == 0 || end == length() || charAt(end - 1) == 10) {
            return false;
        }
        return true;
    }

    public void removeSpan(Object what) {
        if (this.mIndexOfSpan != null) {
            Integer i = (Integer) this.mIndexOfSpan.remove(what);
            if (i != null) {
                removeSpan(i.intValue());
            }
        }
    }

    private int resolveGap(int i) {
        return i > this.mGapStart ? i - this.mGapLength : i;
    }

    public int getSpanStart(Object what) {
        int i = -1;
        if (this.mIndexOfSpan == null) {
            return -1;
        }
        Integer i2 = (Integer) this.mIndexOfSpan.get(what);
        if (i2 != null) {
            i = resolveGap(this.mSpanStarts[i2.intValue()]);
        }
        return i;
    }

    public int getSpanEnd(Object what) {
        int i = -1;
        if (this.mIndexOfSpan == null) {
            return -1;
        }
        Integer i2 = (Integer) this.mIndexOfSpan.get(what);
        if (i2 != null) {
            i = resolveGap(this.mSpanEnds[i2.intValue()]);
        }
        return i;
    }

    public int getSpanFlags(Object what) {
        int i = 0;
        if (this.mIndexOfSpan == null) {
            return 0;
        }
        Integer i2 = (Integer) this.mIndexOfSpan.get(what);
        if (i2 != null) {
            i = this.mSpanFlags[i2.intValue()];
        }
        return i;
    }

    public <T> T[] getSpans(int queryStart, int queryEnd, Class<T> kind) {
        return getSpans(queryStart, queryEnd, kind, true);
    }

    public <T> T[] getSpans(int queryStart, int queryEnd, Class<T> kind, boolean sort) {
        if (kind == null) {
            return ArrayUtils.emptyArray(Object.class);
        }
        if (this.mSpanCount == 0) {
            return ArrayUtils.emptyArray(kind);
        }
        int count = countSpans(queryStart, queryEnd, kind, treeRoot());
        if (count == 0) {
            return ArrayUtils.emptyArray(kind);
        }
        Object[] ret = (Object[]) Array.newInstance(kind, count);
        if (sort) {
            this.mPrioSortBuffer = checkSortBuffer(this.mPrioSortBuffer, count);
            this.mOrderSortBuffer = checkSortBuffer(this.mOrderSortBuffer, count);
        }
        getSpansRec(queryStart, queryEnd, kind, treeRoot(), ret, this.mPrioSortBuffer, this.mOrderSortBuffer, 0, sort);
        if (sort) {
            sort(ret, this.mPrioSortBuffer, this.mOrderSortBuffer);
        }
        return ret;
    }

    private int countSpans(int queryStart, int queryEnd, Class kind, int i) {
        int count = 0;
        if ((i & 1) != 0) {
            int left = leftChild(i);
            int spanMax = this.mSpanMax[left];
            if (spanMax > this.mGapStart) {
                spanMax -= this.mGapLength;
            }
            if (spanMax >= queryStart) {
                count = countSpans(queryStart, queryEnd, kind, left);
            }
        }
        if (i >= this.mSpanCount) {
            return count;
        }
        int spanStart = this.mSpanStarts[i];
        if (spanStart > this.mGapStart) {
            spanStart -= this.mGapLength;
        }
        if (spanStart > queryEnd) {
            return count;
        }
        int spanEnd = this.mSpanEnds[i];
        if (spanEnd > this.mGapStart) {
            spanEnd -= this.mGapLength;
        }
        if (spanEnd >= queryStart && ((spanStart == spanEnd || queryStart == queryEnd || !(spanStart == queryEnd || spanEnd == queryStart)) && kind.isInstance(this.mSpans[i]))) {
            count++;
        }
        if ((i & 1) != 0) {
            return count + countSpans(queryStart, queryEnd, kind, rightChild(i));
        }
        return count;
    }

    private <T> int getSpansRec(int queryStart, int queryEnd, Class<T> kind, int i, T[] ret, int[] priority, int[] insertionOrder, int count, boolean sort) {
        if ((i & 1) != 0) {
            int left = leftChild(i);
            int spanMax = this.mSpanMax[left];
            if (spanMax > this.mGapStart) {
                spanMax -= this.mGapLength;
            }
            if (spanMax >= queryStart) {
                count = getSpansRec(queryStart, queryEnd, kind, left, ret, priority, insertionOrder, count, sort);
            }
        }
        if (i >= this.mSpanCount) {
            return count;
        }
        int spanStart = this.mSpanStarts[i];
        if (spanStart > this.mGapStart) {
            spanStart -= this.mGapLength;
        }
        if (spanStart <= queryEnd) {
            int spanEnd = this.mSpanEnds[i];
            if (spanEnd > this.mGapStart) {
                spanEnd -= this.mGapLength;
            }
            if (spanEnd >= queryStart && (spanStart == spanEnd || queryStart == queryEnd || !(spanStart == queryEnd || spanEnd == queryStart))) {
                if (kind.isInstance(this.mSpans[i])) {
                    int spanPriority = this.mSpanFlags[i] & Spanned.SPAN_PRIORITY;
                    int target = count;
                    if (sort) {
                        priority[target] = spanPriority;
                        insertionOrder[target] = this.mSpanOrder[i];
                    } else if (spanPriority != 0) {
                        int j = 0;
                        while (j < count) {
                            if (spanPriority > (getSpanFlags(ret[j]) & Spanned.SPAN_PRIORITY)) {
                                break;
                            }
                            j++;
                        }
                        System.arraycopy(ret, j, ret, j + 1, count - j);
                        target = j;
                    }
                    ret[target] = this.mSpans[i];
                    count++;
                }
            }
            if (count < ret.length && (i & 1) != 0) {
                count = getSpansRec(queryStart, queryEnd, kind, rightChild(i), ret, priority, insertionOrder, count, sort);
            }
        }
        return count;
    }

    private final int[] checkSortBuffer(int[] buffer, int size) {
        if (size > buffer.length) {
            return ArrayUtils.newUnpaddedIntArray(GrowingArrayUtils.growSize(size));
        }
        return buffer;
    }

    private final <T> void sort(T[] array, int[] priority, int[] insertionOrder) {
        int i;
        int size = array.length;
        for (i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i, array, size, priority, insertionOrder);
        }
        for (i = size - 1; i > 0; i--) {
            T v = array[0];
            int prio = priority[0];
            int insertOrder = insertionOrder[0];
            array[0] = array[i];
            priority[0] = priority[i];
            insertionOrder[0] = insertionOrder[i];
            siftDown(0, array, i, priority, insertionOrder);
            array[i] = v;
            priority[i] = prio;
            insertionOrder[i] = insertOrder;
        }
    }

    private final <T> void siftDown(int index, T[] array, int size, int[] priority, int[] insertionOrder) {
        T v = array[index];
        int prio = priority[index];
        int insertOrder = insertionOrder[index];
        int left = (index * 2) + 1;
        while (left < size) {
            if (left < size - 1 && compareSpans(left, left + 1, priority, insertionOrder) < 0) {
                left++;
            }
            if (compareSpans(index, left, priority, insertionOrder) >= 0) {
                break;
            }
            array[index] = array[left];
            priority[index] = priority[left];
            insertionOrder[index] = insertionOrder[left];
            index = left;
            left = (left * 2) + 1;
        }
        array[index] = v;
        priority[index] = prio;
        insertionOrder[index] = insertOrder;
    }

    private final int compareSpans(int left, int right, int[] priority, int[] insertionOrder) {
        int priority1 = priority[left];
        int priority2 = priority[right];
        if (priority1 == priority2) {
            return Integer.compare(insertionOrder[left], insertionOrder[right]);
        }
        return Integer.compare(priority2, priority1);
    }

    public int nextSpanTransition(int start, int limit, Class kind) {
        if (this.mSpanCount == 0) {
            return limit;
        }
        if (kind == null) {
            kind = Object.class;
        }
        return nextSpanTransitionRec(start, limit, kind, treeRoot());
    }

    private int nextSpanTransitionRec(int start, int limit, Class kind, int i) {
        if ((i & 1) != 0) {
            int left = leftChild(i);
            if (resolveGap(this.mSpanMax[left]) > start) {
                limit = nextSpanTransitionRec(start, limit, kind, left);
            }
        }
        if (i >= this.mSpanCount) {
            return limit;
        }
        int st = resolveGap(this.mSpanStarts[i]);
        int en = resolveGap(this.mSpanEnds[i]);
        if (st > start && st < limit && kind.isInstance(this.mSpans[i])) {
            limit = st;
        }
        if (en > start && en < limit && kind.isInstance(this.mSpans[i])) {
            limit = en;
        }
        if (st >= limit || (i & 1) == 0) {
            return limit;
        }
        return nextSpanTransitionRec(start, limit, kind, rightChild(i));
    }

    public CharSequence subSequence(int start, int end) {
        return new SpannableStringBuilder(this, start, end);
    }

    public void getChars(int start, int end, char[] dest, int destoff) {
        checkRange("getChars", start, end);
        if (end <= this.mGapStart) {
            System.arraycopy(this.mText, start, dest, destoff, end - start);
        } else if (start >= this.mGapStart) {
            System.arraycopy(this.mText, this.mGapLength + start, dest, destoff, end - start);
        } else {
            System.arraycopy(this.mText, start, dest, destoff, this.mGapStart - start);
            System.arraycopy(this.mText, this.mGapStart + this.mGapLength, dest, (this.mGapStart - start) + destoff, end - this.mGapStart);
        }
    }

    public String toString() {
        int len = length();
        char[] buf = new char[len];
        getChars(0, len, buf, 0);
        return new String(buf);
    }

    public String substring(int start, int end) {
        char[] buf = new char[(end - start)];
        getChars(start, end, buf, 0);
        return new String(buf);
    }

    public int getTextWatcherDepth() {
        return this.mTextWatcherDepth;
    }

    private void sendBeforeTextChanged(TextWatcher[] watchers, int start, int before, int after) {
        this.mTextWatcherDepth++;
        for (TextWatcher beforeTextChanged : watchers) {
            beforeTextChanged.beforeTextChanged(this, start, before, after);
        }
        this.mTextWatcherDepth--;
    }

    private void sendTextChanged(TextWatcher[] watchers, int start, int before, int after) {
        int n = watchers.length;
        this.mTextWatcherDepth++;
        for (int i = 0; i < n; i++) {
            if (TextUtils.DEBUG_LOG) {
                TextUtils.printDebugLog(TAG, "[sendTextChanged] " + watchers[i]);
            }
            watchers[i].onTextChanged(this, start, before, after);
        }
        this.mTextWatcherDepth--;
    }

    private void sendAfterTextChanged(TextWatcher[] watchers) {
        this.mTextWatcherDepth++;
        for (TextWatcher afterTextChanged : watchers) {
            afterTextChanged.afterTextChanged(this);
        }
        this.mTextWatcherDepth--;
    }

    private void sendSpanAdded(Object what, int start, int end) {
        for (SpanWatcher onSpanAdded : (SpanWatcher[]) getSpans(start, end, SpanWatcher.class)) {
            onSpanAdded.onSpanAdded(this, what, start, end);
        }
    }

    private void sendSpanRemoved(Object what, int start, int end) {
        for (SpanWatcher onSpanRemoved : (SpanWatcher[]) getSpans(start, end, SpanWatcher.class)) {
            onSpanRemoved.onSpanRemoved(this, what, start, end);
        }
    }

    private void sendSpanChanged(Object what, int oldStart, int oldEnd, int start, int end) {
        for (SpanWatcher onSpanChanged : (SpanWatcher[]) getSpans(Math.min(oldStart, start), Math.min(Math.max(oldEnd, end), length()), SpanWatcher.class)) {
            onSpanChanged.onSpanChanged(this, what, oldStart, oldEnd, start, end);
        }
    }

    private static String region(int start, int end) {
        return "(" + start + " ... " + end + ")";
    }

    private void checkRange(String operation, int start, int end) {
        if (end < start) {
            throw new IndexOutOfBoundsException(operation + " " + region(start, end) + " has end before start");
        }
        int len = length();
        if (start > len || end > len) {
            throw new IndexOutOfBoundsException(operation + " " + region(start, end) + " ends beyond length " + len);
        } else if (start < 0 || end < 0) {
            throw new IndexOutOfBoundsException(operation + " " + region(start, end) + " starts before 0");
        }
    }

    public void drawText(Canvas c, int start, int end, float x, float y, Paint p) {
        checkRange("drawText", start, end);
        if (end <= this.mGapStart) {
            c.drawText(this.mText, start, end - start, x, y, p);
        } else if (start >= this.mGapStart) {
            c.drawText(this.mText, start + this.mGapLength, end - start, x, y, p);
        } else {
            char[] buf = TextUtils.obtain(end - start);
            getChars(start, end, buf, 0);
            c.drawText(buf, 0, end - start, x, y, p);
            TextUtils.recycle(buf);
        }
    }

    public void drawTextRun(Canvas c, int start, int end, int contextStart, int contextEnd, float x, float y, boolean isRtl, Paint p) {
        checkRange("drawTextRun", start, end);
        int contextLen = contextEnd - contextStart;
        int len = end - start;
        if (contextEnd <= this.mGapStart) {
            c.drawTextRun(this.mText, start, len, contextStart, contextLen, x, y, isRtl, p);
        } else if (contextStart >= this.mGapStart) {
            c.drawTextRun(this.mText, start + this.mGapLength, len, contextStart + this.mGapLength, contextLen, x, y, isRtl, p);
        } else {
            char[] buf = TextUtils.obtain(contextLen);
            getChars(contextStart, contextEnd, buf, 0);
            c.drawTextRun(buf, start - contextStart, len, 0, contextLen, x, y, isRtl, p);
            TextUtils.recycle(buf);
        }
    }

    public float measureText(int start, int end, Paint p) {
        checkRange("measureText", start, end);
        if (end <= this.mGapStart) {
            return p.measureText(this.mText, start, end - start);
        }
        if (start >= this.mGapStart) {
            return p.measureText(this.mText, this.mGapLength + start, end - start);
        }
        char[] buf = TextUtils.obtain(end - start);
        getChars(start, end, buf, 0);
        float ret = p.measureText(buf, 0, end - start);
        TextUtils.recycle(buf);
        return ret;
    }

    public int getTextWidths(int start, int end, float[] widths, Paint p) {
        checkRange("getTextWidths", start, end);
        if (end <= this.mGapStart) {
            return p.getTextWidths(this.mText, start, end - start, widths);
        }
        if (start >= this.mGapStart) {
            return p.getTextWidths(this.mText, this.mGapLength + start, end - start, widths);
        }
        char[] buf = TextUtils.obtain(end - start);
        getChars(start, end, buf, 0);
        int ret = p.getTextWidths(buf, 0, end - start, widths);
        TextUtils.recycle(buf);
        return ret;
    }

    public float getTextRunAdvances(int start, int end, int contextStart, int contextEnd, boolean isRtl, float[] advances, int advancesPos, Paint p) {
        int contextLen = contextEnd - contextStart;
        int len = end - start;
        if (end <= this.mGapStart) {
            return p.getTextRunAdvances(this.mText, start, len, contextStart, contextLen, isRtl, advances, advancesPos);
        } else if (start >= this.mGapStart) {
            return p.getTextRunAdvances(this.mText, start + this.mGapLength, len, contextStart + this.mGapLength, contextLen, isRtl, advances, advancesPos);
        } else {
            char[] buf = TextUtils.obtain(contextLen);
            getChars(contextStart, contextEnd, buf, 0);
            float ret = p.getTextRunAdvances(buf, start - contextStart, len, 0, contextLen, isRtl, advances, advancesPos);
            TextUtils.recycle(buf);
            return ret;
        }
    }

    @Deprecated
    public int getTextRunCursor(int contextStart, int contextEnd, int dir, int offset, int cursorOpt, Paint p) {
        int contextLen = contextEnd - contextStart;
        if (contextEnd <= this.mGapStart) {
            return p.getTextRunCursor(this.mText, contextStart, contextLen, dir, offset, cursorOpt);
        } else if (contextStart >= this.mGapStart) {
            return p.getTextRunCursor(this.mText, contextStart + this.mGapLength, contextLen, dir, offset + this.mGapLength, cursorOpt) - this.mGapLength;
        } else {
            char[] buf = TextUtils.obtain(contextLen);
            getChars(contextStart, contextEnd, buf, 0);
            int ret = p.getTextRunCursor(buf, 0, contextLen, dir, offset - contextStart, cursorOpt) + contextStart;
            TextUtils.recycle(buf);
            return ret;
        }
    }

    public void setFilters(InputFilter[] filters) {
        if (filters == null) {
            throw new IllegalArgumentException();
        }
        this.mFilters = filters;
    }

    public InputFilter[] getFilters() {
        return this.mFilters;
    }

    public boolean equals(Object o) {
        if ((o instanceof Spanned) && toString().equals(o.toString())) {
            Spanned other = (Spanned) o;
            Object[] otherSpans = other.getSpans(0, other.length(), Object.class);
            if (this.mSpanCount == otherSpans.length) {
                for (int i = 0; i < this.mSpanCount; i++) {
                    SpannableStringBuilder thisSpan = this.mSpans[i];
                    Spanned otherSpan = otherSpans[i];
                    if (thisSpan == this) {
                        if (other != otherSpan || getSpanStart(thisSpan) != other.getSpanStart(otherSpan) || getSpanEnd(thisSpan) != other.getSpanEnd(otherSpan) || getSpanFlags(thisSpan) != other.getSpanFlags(otherSpan)) {
                            return false;
                        }
                    } else if (!thisSpan.equals(otherSpan) || getSpanStart(thisSpan) != other.getSpanStart(otherSpan) || getSpanEnd(thisSpan) != other.getSpanEnd(otherSpan) || getSpanFlags(thisSpan) != other.getSpanFlags(otherSpan)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hash = (toString().hashCode() * 31) + this.mSpanCount;
        for (int i = 0; i < this.mSpanCount; i++) {
            SpannableStringBuilder span = this.mSpans[i];
            if (span != this) {
                hash = (hash * 31) + span.hashCode();
            }
            hash = (((((hash * 31) + getSpanStart(span)) * 31) + getSpanEnd(span)) * 31) + getSpanFlags(span);
        }
        return hash;
    }

    private int treeRoot() {
        return Integer.highestOneBit(this.mSpanCount) - 1;
    }

    private static int leftChild(int i) {
        return i - (((i + 1) & (~i)) >> 1);
    }

    private static int rightChild(int i) {
        return (((i + 1) & (~i)) >> 1) + i;
    }

    private int calcMax(int i) {
        int max = 0;
        if ((i & 1) != 0) {
            max = calcMax(leftChild(i));
        }
        if (i < this.mSpanCount) {
            max = Math.max(max, this.mSpanEnds[i]);
            if ((i & 1) != 0) {
                max = Math.max(max, calcMax(rightChild(i)));
            }
        }
        this.mSpanMax[i] = max;
        return max;
    }

    private void restoreInvariants() {
        if (this.mSpanCount != 0) {
            int i;
            for (i = 1; i < this.mSpanCount; i++) {
                if (this.mSpanStarts[i] < this.mSpanStarts[i - 1]) {
                    Object span = this.mSpans[i];
                    int start = this.mSpanStarts[i];
                    int end = this.mSpanEnds[i];
                    int flags = this.mSpanFlags[i];
                    int insertionOrder = this.mSpanOrder[i];
                    int j = i;
                    do {
                        this.mSpans[j] = this.mSpans[j - 1];
                        this.mSpanStarts[j] = this.mSpanStarts[j - 1];
                        this.mSpanEnds[j] = this.mSpanEnds[j - 1];
                        this.mSpanFlags[j] = this.mSpanFlags[j - 1];
                        this.mSpanOrder[j] = this.mSpanOrder[j - 1];
                        j--;
                        if (j <= 0) {
                            break;
                        }
                    } while (start < this.mSpanStarts[j - 1]);
                    this.mSpans[j] = span;
                    this.mSpanStarts[j] = start;
                    this.mSpanEnds[j] = end;
                    this.mSpanFlags[j] = flags;
                    this.mSpanOrder[j] = insertionOrder;
                    invalidateIndex(j);
                }
            }
            calcMax(treeRoot());
            if (this.mIndexOfSpan == null) {
                this.mIndexOfSpan = new IdentityHashMap();
            }
            i = this.mLowWaterMark;
            while (i < this.mSpanCount) {
                Integer existing = (Integer) this.mIndexOfSpan.get(this.mSpans[i]);
                if (existing == null || existing.intValue() != i) {
                    this.mIndexOfSpan.put(this.mSpans[i], Integer.valueOf(i));
                }
                i++;
            }
            this.mLowWaterMark = Integer.MAX_VALUE;
        }
    }

    private void invalidateIndex(int i) {
        this.mLowWaterMark = Math.min(i, this.mLowWaterMark);
    }
}
