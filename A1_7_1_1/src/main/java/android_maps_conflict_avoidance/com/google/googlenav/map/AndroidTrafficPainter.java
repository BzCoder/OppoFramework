package android_maps_conflict_avoidance.com.google.googlenav.map;

import android.graphics.AvoidXfermode;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.Xfermode;
import android_maps_conflict_avoidance.com.google.googlenav.labs.android.TrafficWithLabelsLab;
import android_maps_conflict_avoidance.com.google.googlenav.map.TrafficRenderer.Path;
import android_maps_conflict_avoidance.com.google.googlenav.map.TrafficRenderer.TrafficPainter;

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
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:12)
    	at jadx.core.ProcessClass.process(ProcessClass.java:32)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:292)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
    */
public class AndroidTrafficPainter implements TrafficPainter {
    private static final float[] MAJOR_DASH_INTERVALS = null;
    private static final float[] MINOR_DASH_INTERVALS = null;
    private Canvas canvas;
    private boolean isPreserveLabels;
    private Xfermode mainXfermode;
    private final PathEffect majorDash;
    private final PathEffect minorDash;
    private final Paint paint;
    private final AvoidXfermode preserveLabels;

    private static class AndroidPath implements Path {
        private final android.graphics.Path path;

        private AndroidPath() {
            this.path = new android.graphics.Path();
        }

        public void lineTo(int x, int y) {
            this.path.lineTo((float) (x >> 8), (float) (y >> 8));
        }

        public void moveTo(int x, int y) {
            this.path.moveTo((float) (x >> 8), (float) (y >> 8));
        }

        public android.graphics.Path getPath() {
            return this.path;
        }
    }

    /*  JADX ERROR: Method load error
        jadx.core.utils.exceptions.DecodeException: Load method exception: bogus opcode: 0073 in method: android_maps_conflict_avoidance.com.google.googlenav.map.AndroidTrafficPainter.<clinit>():void, dex: 
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
        // Can't load method instructions: Load method exception: bogus opcode: 0073 in method: android_maps_conflict_avoidance.com.google.googlenav.map.AndroidTrafficPainter.<clinit>():void, dex: 
        */
        throw new UnsupportedOperationException("Method not decompiled: android_maps_conflict_avoidance.com.google.googlenav.map.AndroidTrafficPainter.<clinit>():void");
    }

    public AndroidTrafficPainter() {
        this.majorDash = new DashPathEffect(MAJOR_DASH_INTERVALS, 0.0f);
        this.minorDash = new DashPathEffect(MINOR_DASH_INTERVALS, 6.0f);
        this.preserveLabels = new AvoidXfermode(-12566464, 253, Mode.AVOID);
        this.canvas = null;
        this.paint = new Paint();
    }

    public void setup(Canvas canvas) {
        this.canvas = canvas;
        this.paint.setAntiAlias(true);
        this.paint.setStrokeJoin(Join.ROUND);
        this.paint.setStrokeCap(Cap.ROUND);
        this.paint.setStyle(Style.STROKE);
        this.isPreserveLabels = TrafficWithLabelsLab.INSTANCE.isActive();
        this.mainXfermode = this.isPreserveLabels ? this.preserveLabels : null;
    }

    public void addTrafficLine(Path path, int color, int width) {
        PathEffect pathEffect;
        width >>= 8;
        if (this.isPreserveLabels && color == -788529153) {
            color = -1;
            width += 2;
        }
        boolean isDashed = color == -6553600;
        this.paint.setStrokeWidth((float) width);
        this.paint.setColor(color);
        Paint paint = this.paint;
        if (isDashed) {
            pathEffect = this.majorDash;
        } else {
            pathEffect = null;
        }
        paint.setPathEffect(pathEffect);
        this.paint.setXfermode(this.mainXfermode);
        android.graphics.Path graphicsPath = ((AndroidPath) path).getPath();
        this.canvas.drawPath(graphicsPath, this.paint);
        if (isDashed) {
            this.paint.setPathEffect(this.minorDash);
            this.paint.setColor(-16777216);
            this.paint.setXfermode(null);
            this.canvas.drawPath(graphicsPath, this.paint);
        }
    }

    public Path createPathObject() {
        return new AndroidPath();
    }
}
