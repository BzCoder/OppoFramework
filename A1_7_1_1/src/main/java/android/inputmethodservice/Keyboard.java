package android.inputmethodservice;

import android.annotation.OppoHook;
import android.annotation.OppoHook.OppoHookType;
import android.annotation.OppoHook.OppoRomType;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.WindowManager;
import com.android.internal.R;
import com.color.util.ColorContextUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.xmlpull.v1.XmlPullParserException;

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
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:42)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class Keyboard {
    private static final boolean DEBUG = false;
    public static final int EDGE_BOTTOM = 8;
    public static final int EDGE_LEFT = 1;
    public static final int EDGE_RIGHT = 2;
    public static final int EDGE_TOP = 4;
    private static final int GRID_HEIGHT = 5;
    private static final int GRID_SIZE = 50;
    private static final int GRID_WIDTH = 10;
    public static final int KEYCODE_ALT = -6;
    public static final int KEYCODE_CANCEL = -3;
    public static final int KEYCODE_DELETE = -5;
    public static final int KEYCODE_DONE = -4;
    public static final int KEYCODE_GO = 10;
    public static final int KEYCODE_MODE_CHANGE = -2;
    public static final int KEYCODE_SHIFT = -1;
    public static final int KEYCODE_SPACE = 32;
    private static float SEARCH_DISTANCE = 0.0f;
    public static final int SECURITYKEYBOARD = 1;
    static final String TAG = "Keyboard";
    private static final String TAG_KEY = "Key";
    private static final String TAG_KEYBOARD = "Keyboard";
    private static final String TAG_ROW = "Row";
    public static final int UNLOCKKEYBOARD = 2;
    private int mCellHeight;
    private int mCellWidth;
    private Context mContext;
    private int mDefaultHeight;
    private int mDefaultHorizontalGap;
    private int mDefaultVerticalGap;
    private int mDefaultWidth;
    private int mDisplayHeight;
    private int mDisplayWidth;
    private int[][] mGridNeighbors;
    private int mKeyHeight;
    private int mKeyWidth;
    private int mKeyboardMode;
    private List<Key> mKeys;
    private CharSequence mLabel;
    private List<Key> mModifierKeys;
    private int mProximityThreshold;
    private int mSecurityType;
    private int[] mShiftKeyIndices;
    private Key[] mShiftKeys;
    private boolean mShifted;
    private int mTotalHeight;
    private int mTotalWidth;
    private ArrayList<Row> rows;

    public static class Key {
        private static final int[] KEY_STATE_NORMAL = null;
        private static final int[] KEY_STATE_NORMAL_OFF = null;
        private static final int[] KEY_STATE_NORMAL_ON = null;
        private static final int[] KEY_STATE_PRESSED = null;
        private static final int[] KEY_STATE_PRESSED_OFF = null;
        private static final int[] KEY_STATE_PRESSED_ON = null;
        public int[] codes;
        public int edgeFlags;
        public int gap;
        public int height;
        public Drawable icon;
        public Drawable iconPreview;
        private Keyboard keyboard;
        public CharSequence label;
        public boolean modifier;
        public boolean on;
        public CharSequence popupCharacters;
        public int popupResId;
        public boolean pressed;
        public boolean repeatable;
        public boolean sticky;
        public CharSequence text;
        public int width;
        public int x;
        public int y;

        /*  JADX ERROR: Method load error
            jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.inputmethodservice.Keyboard.Key.<clinit>():void, dex: 
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:118)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:248)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:254)
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
            	... 6 more
            */
        static {
            /*
            // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.inputmethodservice.Keyboard.Key.<clinit>():void, dex: 
            */
            throw new UnsupportedOperationException("Method not decompiled: android.inputmethodservice.Keyboard.Key.<clinit>():void");
        }

        public Key(Row parent) {
            this.keyboard = parent.parent;
            this.height = parent.defaultHeight;
            this.width = parent.defaultWidth;
            this.gap = parent.defaultHorizontalGap;
            this.edgeFlags = parent.rowEdgeFlags;
        }

        public Key(Resources res, Row parent, int x, int y, XmlResourceParser parser) {
            int[] iArr;
            this(parent);
            this.x = x;
            this.y = y;
            TypedArray a = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard);
            this.width = Keyboard.getDimensionOrFraction(a, 0, this.keyboard.mDisplayWidth, parent.defaultWidth);
            this.height = Keyboard.getDimensionOrFraction(a, 1, this.keyboard.mDisplayHeight, parent.defaultHeight);
            this.gap = Keyboard.getDimensionOrFraction(a, 2, this.keyboard.mDisplayWidth, parent.defaultHorizontalGap);
            a.recycle();
            a = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_Key);
            this.x += this.gap;
            if (this.keyboard.mSecurityType == 1 || (this.keyboard.mSecurityType == 2 && ColorContextUtil.isOppoStyle(this.keyboard.mContext))) {
                this.y += this.keyboard.mDefaultVerticalGap;
            }
            TypedValue codesValue = new TypedValue();
            a.getValue(0, codesValue);
            if (codesValue.type == 16 || codesValue.type == 17) {
                iArr = new int[1];
                iArr[0] = codesValue.data;
                this.codes = iArr;
            } else if (codesValue.type == 3) {
                this.codes = parseCSV(codesValue.string.toString());
            }
            this.iconPreview = a.getDrawable(7);
            if (this.iconPreview != null) {
                this.iconPreview.setBounds(0, 0, this.iconPreview.getIntrinsicWidth(), this.iconPreview.getIntrinsicHeight());
            }
            this.popupCharacters = a.getText(2);
            this.popupResId = a.getResourceId(1, 0);
            this.repeatable = a.getBoolean(6, false);
            this.modifier = a.getBoolean(4, false);
            this.sticky = a.getBoolean(5, false);
            this.edgeFlags = a.getInt(3, 0);
            this.edgeFlags |= parent.rowEdgeFlags;
            this.icon = a.getDrawable(10);
            if (this.icon != null) {
                this.icon.setBounds(0, 0, this.icon.getIntrinsicWidth(), this.icon.getIntrinsicHeight());
            }
            this.label = a.getText(9);
            this.text = a.getText(8);
            if (this.codes == null && !TextUtils.isEmpty(this.label)) {
                iArr = new int[1];
                iArr[0] = this.label.charAt(0);
                this.codes = iArr;
            }
            a.recycle();
            if (Keyboard.DEBUG && y == 0 && this.keyboard.mSecurityType == 2) {
                Log.d("Keyboard", "label = " + this.label + ", x = " + x + ", width = " + this.width);
            }
        }

        public void onPressed() {
            this.pressed = !this.pressed;
        }

        public void onReleased(boolean inside) {
            boolean z;
            boolean z2 = false;
            if (this.pressed) {
                z = false;
            } else {
                z = true;
            }
            this.pressed = z;
            if (this.sticky && inside) {
                if (!this.on) {
                    z2 = true;
                }
                this.on = z2;
            }
        }

        int[] parseCSV(String value) {
            int count = 0;
            int lastIndex = 0;
            if (value.length() > 0) {
                count = 1;
                while (true) {
                    lastIndex = value.indexOf(",", lastIndex + 1);
                    if (lastIndex <= 0) {
                        break;
                    }
                    count++;
                }
            }
            int[] values = new int[count];
            count = 0;
            StringTokenizer st = new StringTokenizer(value, ",");
            while (st.hasMoreTokens()) {
                int count2 = count + 1;
                try {
                    values[count] = Integer.parseInt(st.nextToken());
                } catch (NumberFormatException e) {
                    Log.e("Keyboard", "Error parsing keycodes " + value);
                }
                count = count2;
            }
            return values;
        }

        public boolean isInside(int x, int y) {
            boolean leftEdge = (this.edgeFlags & 1) > 0;
            boolean rightEdge = (this.edgeFlags & 2) > 0;
            boolean topEdge = (this.edgeFlags & 4) > 0;
            boolean bottomEdge = (this.edgeFlags & 8) > 0;
            if ((x >= this.x || (leftEdge && x <= this.x + this.width)) && ((x < this.x + this.width || (rightEdge && x >= this.x)) && ((y >= this.y || (topEdge && y <= this.y + this.height)) && (y < this.y + this.height || (bottomEdge && y >= this.y))))) {
                return true;
            }
            return false;
        }

        public int squaredDistanceFrom(int x, int y) {
            int xDist = (this.x + (this.width / 2)) - x;
            int yDist = (this.y + (this.height / 2)) - y;
            return (xDist * xDist) + (yDist * yDist);
        }

        public int[] getCurrentDrawableState() {
            int[] states = KEY_STATE_NORMAL;
            if (this.on) {
                if (this.pressed) {
                    return KEY_STATE_PRESSED_ON;
                }
                return KEY_STATE_NORMAL_ON;
            } else if (this.sticky) {
                if (this.pressed) {
                    return KEY_STATE_PRESSED_OFF;
                }
                return KEY_STATE_NORMAL_OFF;
            } else if (this.pressed) {
                return KEY_STATE_PRESSED;
            } else {
                return states;
            }
        }
    }

    public static class Row {
        public int defaultHeight;
        public int defaultHorizontalGap;
        public int defaultWidth;
        ArrayList<Key> mKeys;
        public int mode;
        private Keyboard parent;
        public int rowEdgeFlags;
        public int verticalGap;

        public Row(Keyboard parent) {
            this.mKeys = new ArrayList();
            this.parent = parent;
        }

        public Row(Resources res, Keyboard parent, XmlResourceParser parser) {
            this.mKeys = new ArrayList();
            this.parent = parent;
            TypedArray a = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard);
            this.defaultWidth = Keyboard.getDimensionOrFraction(a, 0, parent.mDisplayWidth, parent.mDefaultWidth);
            this.defaultHeight = Keyboard.getDimensionOrFraction(a, 1, parent.mDisplayHeight, parent.mDefaultHeight);
            this.defaultHorizontalGap = Keyboard.getDimensionOrFraction(a, 2, parent.mDisplayWidth, parent.mDefaultHorizontalGap);
            this.verticalGap = Keyboard.getDimensionOrFraction(a, 3, parent.mDisplayHeight, parent.mDefaultVerticalGap);
            a.recycle();
            a = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_Row);
            this.rowEdgeFlags = a.getInt(0, 0);
            this.mode = a.getResourceId(1, 0);
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android.inputmethodservice.Keyboard.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android.inputmethodservice.Keyboard.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: android.inputmethodservice.Keyboard.<clinit>():void");
    }

    public Keyboard(Context context, int xmlLayoutResId) {
        this(context, xmlLayoutResId, 0);
    }

    public Keyboard(Context context, int xmlLayoutResId, int modeId, int width, int height) {
        Key[] keyArr = new Key[2];
        keyArr[0] = null;
        keyArr[1] = null;
        this.mShiftKeys = keyArr;
        this.mShiftKeyIndices = new int[]{-1, -1};
        this.rows = new ArrayList();
        this.mSecurityType = 0;
        this.mContext = null;
        this.mDisplayWidth = width;
        this.mDisplayHeight = height;
        this.mDefaultHorizontalGap = 0;
        this.mDefaultWidth = this.mDisplayWidth / 10;
        this.mDefaultVerticalGap = 0;
        this.mDefaultHeight = this.mDefaultWidth;
        this.mKeys = new ArrayList();
        this.mModifierKeys = new ArrayList();
        this.mKeyboardMode = modeId;
        loadKeyboard(context, context.getResources().getXml(xmlLayoutResId));
    }

    public Keyboard(Context context, int xmlLayoutResId, int modeId) {
        Key[] keyArr = new Key[2];
        keyArr[0] = null;
        keyArr[1] = null;
        this.mShiftKeys = keyArr;
        this.mShiftKeyIndices = new int[]{-1, -1};
        this.rows = new ArrayList();
        this.mSecurityType = 0;
        this.mContext = null;
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        this.mDisplayWidth = dm.widthPixels;
        this.mDisplayHeight = dm.heightPixels;
        this.mDefaultHorizontalGap = 0;
        this.mDefaultWidth = this.mDisplayWidth / 10;
        this.mDefaultVerticalGap = 0;
        this.mDefaultHeight = this.mDefaultWidth;
        this.mKeys = new ArrayList();
        this.mModifierKeys = new ArrayList();
        this.mKeyboardMode = modeId;
        loadKeyboard(context, context.getResources().getXml(xmlLayoutResId));
    }

    @OppoHook(level = OppoHookType.NEW_FIELD, note = "Suying.You@Plf.SDK : Add for 3.1", property = OppoRomType.ROM)
    public Keyboard(Context context, int xmlLayoutResId, int modeId, int securityType) {
        Key[] keyArr = new Key[2];
        keyArr[0] = null;
        keyArr[1] = null;
        this.mShiftKeys = keyArr;
        this.mShiftKeyIndices = new int[]{-1, -1};
        this.rows = new ArrayList();
        this.mSecurityType = 0;
        this.mContext = null;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.mDisplayWidth = wm.getDefaultDisplay().getWidth();
        this.mDisplayHeight = wm.getDefaultDisplay().getHeight();
        if (context.getResources().getConfiguration().orientation == 2 && securityType == 2 && this.mDisplayWidth > this.mDisplayHeight) {
            int value = this.mDisplayWidth;
            this.mDisplayWidth = this.mDisplayHeight;
            this.mDisplayHeight = value;
        }
        this.mDefaultHorizontalGap = 0;
        this.mDefaultWidth = this.mDisplayWidth / 10;
        this.mDefaultVerticalGap = 0;
        this.mDefaultHeight = this.mDefaultWidth;
        this.mKeys = new ArrayList();
        this.mModifierKeys = new ArrayList();
        this.mKeyboardMode = modeId;
        this.mSecurityType = securityType;
        this.mContext = context;
        loadKeyboard(context, context.getResources().getXml(xmlLayoutResId));
    }

    public Keyboard(Context context, int layoutTemplateResId, CharSequence characters, int columns, int horizontalPadding) {
        this(context, layoutTemplateResId);
        int x = 0;
        int y = 0;
        int column = 0;
        this.mTotalWidth = 0;
        Row row = new Row(this);
        row.defaultHeight = this.mDefaultHeight;
        row.defaultWidth = this.mDefaultWidth;
        row.defaultHorizontalGap = this.mDefaultHorizontalGap;
        row.verticalGap = this.mDefaultVerticalGap;
        row.rowEdgeFlags = 12;
        int maxColumns = columns == -1 ? Integer.MAX_VALUE : columns;
        for (int i = 0; i < characters.length(); i++) {
            char c = characters.charAt(i);
            if (column >= maxColumns || (this.mDefaultWidth + x) + horizontalPadding > this.mDisplayWidth) {
                x = 0;
                y += this.mDefaultVerticalGap + this.mDefaultHeight;
                column = 0;
            }
            Key key = new Key(row);
            key.x = x;
            key.y = y;
            key.label = String.valueOf(c);
            int[] iArr = new int[1];
            iArr[0] = c;
            key.codes = iArr;
            column++;
            x += key.width + key.gap;
            this.mKeys.add(key);
            row.mKeys.add(key);
            if (x > this.mTotalWidth) {
                this.mTotalWidth = x;
            }
        }
        this.mTotalHeight = this.mDefaultHeight + y;
        this.rows.add(row);
    }

    final void resize(int newWidth, int newHeight) {
        if (this.mSecurityType == 1 && this.mContext != null && newWidth != this.mContext.getResources().getDisplayMetrics().widthPixels) {
            return;
        }
        if (this.mSecurityType != 2 || newWidth != 0) {
            int numRows = this.rows.size();
            int rowIndex = 0;
            while (rowIndex < numRows) {
                int keyIndex;
                Key key;
                Row row = (Row) this.rows.get(rowIndex);
                int numKeys = row.mKeys.size();
                int totalGap = 0;
                int totalWidth = 0;
                for (keyIndex = 0; keyIndex < numKeys; keyIndex++) {
                    key = (Key) row.mKeys.get(keyIndex);
                    if (keyIndex > 0) {
                        totalGap += key.gap;
                    }
                    totalWidth += key.width;
                }
                if (totalGap + totalWidth > newWidth) {
                    int x = 0;
                    float scaleFactor = ((float) (newWidth - totalGap)) / ((float) totalWidth);
                    keyIndex = 0;
                    while (keyIndex < numKeys) {
                        key = (Key) row.mKeys.get(keyIndex);
                        key.width = (int) (((float) key.width) * scaleFactor);
                        key.x = x;
                        x += key.width + key.gap;
                        if (DEBUG && rowIndex == 0 && keyIndex < 5) {
                            Log.d("Keyboard", "resize -> type = " + this.mSecurityType + ", newWidth = " + newWidth + ", totalWidth = " + totalWidth + ", scaleFactor = " + scaleFactor + ", width = " + key.width + ", x = " + key.x);
                        }
                        keyIndex++;
                    }
                }
                rowIndex++;
            }
            this.mTotalWidth = newWidth;
        }
    }

    public List<Key> getKeys() {
        return this.mKeys;
    }

    public List<Key> getModifierKeys() {
        return this.mModifierKeys;
    }

    protected int getHorizontalGap() {
        return this.mDefaultHorizontalGap;
    }

    protected void setHorizontalGap(int gap) {
        this.mDefaultHorizontalGap = gap;
    }

    protected int getVerticalGap() {
        return this.mDefaultVerticalGap;
    }

    protected void setVerticalGap(int gap) {
        this.mDefaultVerticalGap = gap;
    }

    protected int getKeyHeight() {
        return this.mDefaultHeight;
    }

    protected void setKeyHeight(int height) {
        this.mDefaultHeight = height;
    }

    protected int getKeyWidth() {
        return this.mDefaultWidth;
    }

    protected void setKeyWidth(int width) {
        this.mDefaultWidth = width;
    }

    public int getHeight() {
        return this.mTotalHeight;
    }

    public int getMinWidth() {
        return this.mTotalWidth;
    }

    public boolean setShifted(boolean shiftState) {
        for (Key shiftKey : this.mShiftKeys) {
            if (shiftKey != null) {
                shiftKey.on = shiftState;
            }
        }
        if (this.mShifted == shiftState) {
            return false;
        }
        this.mShifted = shiftState;
        return true;
    }

    public boolean isShifted() {
        return this.mShifted;
    }

    public int[] getShiftKeyIndices() {
        return this.mShiftKeyIndices;
    }

    public int getShiftKeyIndex() {
        return this.mShiftKeyIndices[0];
    }

    private void computeNearestNeighbors() {
        this.mCellWidth = ((getMinWidth() + 10) - 1) / 10;
        this.mCellHeight = ((getHeight() + 5) - 1) / 5;
        this.mGridNeighbors = new int[50][];
        int[] indices = new int[this.mKeys.size()];
        int gridWidth = this.mCellWidth * 10;
        int gridHeight = this.mCellHeight * 5;
        int x = 0;
        while (x < gridWidth) {
            int y = 0;
            while (y < gridHeight) {
                int count = 0;
                for (int i = 0; i < this.mKeys.size(); i++) {
                    Key key = (Key) this.mKeys.get(i);
                    if (key.squaredDistanceFrom(x, y) < this.mProximityThreshold || key.squaredDistanceFrom((this.mCellWidth + x) - 1, y) < this.mProximityThreshold || key.squaredDistanceFrom((this.mCellWidth + x) - 1, (this.mCellHeight + y) - 1) < this.mProximityThreshold || key.squaredDistanceFrom(x, (this.mCellHeight + y) - 1) < this.mProximityThreshold) {
                        int count2 = count + 1;
                        indices[count] = i;
                        count = count2;
                    }
                }
                int[] cell = new int[count];
                System.arraycopy(indices, 0, cell, 0, count);
                this.mGridNeighbors[((y / this.mCellHeight) * 10) + (x / this.mCellWidth)] = cell;
                y += this.mCellHeight;
            }
            x += this.mCellWidth;
        }
    }

    public int[] getNearestKeys(int x, int y) {
        if (this.mGridNeighbors == null) {
            computeNearestNeighbors();
        }
        if (x >= 0 && x < getMinWidth() && y >= 0 && y < getHeight()) {
            int index = ((y / this.mCellHeight) * 10) + (x / this.mCellWidth);
            if (index < 50) {
                return this.mGridNeighbors[index];
            }
        }
        return new int[0];
    }

    protected Row createRowFromXml(Resources res, XmlResourceParser parser) {
        return new Row(res, this, parser);
    }

    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, XmlResourceParser parser) {
        return new Key(res, parent, x, y, parser);
    }

    private void loadKeyboard(Context context, XmlResourceParser parser) {
        boolean inKey = false;
        boolean inRow = false;
        int row = 0;
        int x = 0;
        int y = 0;
        Key key = null;
        Row currentRow = null;
        Resources res = context.getResources();
        while (true) {
            try {
                int event = parser.next();
                if (event == 1) {
                    break;
                } else if (event == 2) {
                    String tag = parser.getName();
                    if (TAG_ROW.equals(tag)) {
                        inRow = true;
                        x = 0;
                        currentRow = createRowFromXml(res, parser);
                        this.rows.add(currentRow);
                        boolean skipRow = (currentRow.mode == 0 || currentRow.mode == this.mKeyboardMode) ? false : true;
                        if (skipRow) {
                            skipToEndOfRow(parser);
                            inRow = false;
                        }
                    } else if (TAG_KEY.equals(tag)) {
                        inKey = true;
                        key = createKeyFromXml(res, currentRow, x, y, parser);
                        this.mKeys.add(key);
                        if (key.codes[0] == -1) {
                            for (int i = 0; i < this.mShiftKeys.length; i++) {
                                if (this.mShiftKeys[i] == null) {
                                    this.mShiftKeys[i] = key;
                                    this.mShiftKeyIndices[i] = this.mKeys.size() - 1;
                                    break;
                                }
                            }
                            this.mModifierKeys.add(key);
                        } else if (key.codes[0] == -6) {
                            this.mModifierKeys.add(key);
                        }
                        currentRow.mKeys.add(key);
                    } else if ("Keyboard".equals(tag)) {
                        parseKeyboardAttributes(res, parser);
                    }
                } else if (event == 3) {
                    if (inKey) {
                        inKey = false;
                        if (DEBUG && y == 0 && this.mSecurityType == 2) {
                            Log.d("Keyboard", "END_TAG -> gap = " + key.gap + ", width = " + key.width);
                        }
                        x += key.gap + key.width;
                        if (x > this.mTotalWidth) {
                            this.mTotalWidth = x;
                        }
                    } else if (inRow) {
                        inRow = false;
                        y = (y + currentRow.verticalGap) + currentRow.defaultHeight;
                        row++;
                    }
                }
            } catch (Exception e) {
                Log.e("Keyboard", "Parse error:" + e);
                e.printStackTrace();
            }
        }
        this.mTotalHeight = y - this.mDefaultVerticalGap;
    }

    private void skipToEndOfRow(XmlResourceParser parser) throws XmlPullParserException, IOException {
        while (true) {
            int event = parser.next();
            if (event == 1) {
                return;
            }
            if (event == 3 && parser.getName().equals(TAG_ROW)) {
                return;
            }
        }
    }

    private void parseKeyboardAttributes(Resources res, XmlResourceParser parser) {
        TypedArray a = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard);
        this.mDefaultWidth = getDimensionOrFraction(a, 0, this.mDisplayWidth, this.mDisplayWidth / 10);
        this.mDefaultHeight = getDimensionOrFraction(a, 1, this.mDisplayHeight, 50);
        this.mDefaultHorizontalGap = getDimensionOrFraction(a, 2, this.mDisplayWidth, 0);
        this.mDefaultVerticalGap = getDimensionOrFraction(a, 3, this.mDisplayHeight, 0);
        this.mProximityThreshold = (int) (((float) this.mDefaultWidth) * SEARCH_DISTANCE);
        this.mProximityThreshold *= this.mProximityThreshold;
        a.recycle();
    }

    static int getDimensionOrFraction(TypedArray a, int index, int base, int defValue) {
        TypedValue value = a.peekValue(index);
        if (value == null) {
            return defValue;
        }
        if (value.type == 5) {
            return a.getDimensionPixelOffset(index, defValue);
        }
        if (value.type == 6) {
            return Math.round(a.getFraction(index, base, base, (float) defValue));
        }
        return defValue;
    }
}
