package com.mike.givemewingzz.found.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by GiveMeWingzz on 12/17/2015.
 */
public class HexagonView extends View {

    private Path hexagonPath;
    private Path hexagonBorderPath;
    private float radius;
    private float width, height;
    private int maskColor;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public HexagonView(Context context) {
        super(context);
        init();
    }

    public HexagonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HexagonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        hexagonPath = new Path();
        hexagonBorderPath = new Path();
        maskColor = 0xFF01FF77;
//        maskColor = Color.parseColor("#80000000");
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setMaskColor(int maskColor) {
        this.maskColor = maskColor;
        invalidate();
    }

    private void calculatePath() {
        float triangleHeight = (float) (Math.sqrt(3) * radius / 2);
        Log.d("calculatePath ", "Radius :: --> " + radius);
        Log.d("calculatePath ", "triangleHeight :: Height --> " + triangleHeight);

        float centerX = width / 2;
        float centerY = height / 2;

        Log.d("calculatePath ", "Vals : " + "centerX --> " + centerX + " :: centerY --> " + centerY);

        hexagonPath.moveTo(centerX, centerY + radius);
        Log.d("calculatePath ", "hexagonPath : " + "Origin --> " + centerX + " :: Dest --> " + (centerY + radius));

        hexagonPath.lineTo(centerX - triangleHeight, centerY + radius / 2);
        Log.d("calculatePath ", "hexagonPath : " + "Last Point --> " + (centerX - triangleHeight) + " :: Specified Point --> " + (centerY + radius / 2));

        hexagonPath.lineTo(centerX - triangleHeight, centerY - radius / 2);
        Log.d("calculatePath ", "hexagonPath : " + "Last Point --> " + (centerX - triangleHeight) + " :: Specified Point --> " + (centerY - radius / 2));

        hexagonPath.lineTo(centerX, centerY - radius);
        Log.d("calculatePath ", "hexagonPath : " + "Last Point --> " + (centerX) + " :: Specified Point --> " + (centerY - radius));

        hexagonPath.lineTo(centerX + triangleHeight, centerY - radius / 2);
        Log.d("calculatePath ", "hexagonPath : " + "Last Point --> " + (centerX) + " :: Specified Point --> " + (centerY - radius));

        hexagonPath.lineTo(centerX + triangleHeight, centerY + radius / 2);
        Log.d("calculatePath ", "hexagonPath : " + "Last Point --> " + (centerX) + " :: Specified Point --> " + (centerY + radius / 2));

        hexagonPath.moveTo(centerX, centerY + radius);
        Log.d("calculatePath ", "hexagonPath : " + "Origin End--> " + (centerX) + " :: Dest End--> " + (centerY + radius));

        /////// Border ///////////

        float radiusBorder = radius - 5;
        Log.d("calculatePath ", "Radius Border :: --> " + radiusBorder);

        float triangleBorderHeight = (float) (Math.sqrt(3) * radiusBorder / 2);
        Log.d("calculatePath ", "triangleBorderHeight :: Height --> " + triangleBorderHeight);

        hexagonBorderPath.moveTo(centerX, centerY + radiusBorder);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Origin --> " + centerX + " :: Dest --> " + (centerY + radiusBorder));

        hexagonBorderPath.lineTo(centerX - triangleBorderHeight, centerY + radiusBorder / 2);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Last Point --> " + (centerX - triangleBorderHeight) + " :: Specified Point --> " + (centerY + radiusBorder / 2));

        hexagonBorderPath.lineTo(centerX - triangleBorderHeight, centerY - radiusBorder / 2);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Last Point --> " + (centerX - triangleBorderHeight) + " :: Specified Point --> " + (centerY - radiusBorder / 2));

        hexagonBorderPath.lineTo(centerX, centerY - radiusBorder);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Last Point --> " + (centerX) + " :: Specified Point --> " + (centerY - radiusBorder));

        hexagonBorderPath.lineTo(centerX + triangleBorderHeight, centerY - radiusBorder / 2);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Last Point --> " + (centerX + triangleBorderHeight) + " :: Specified Point --> " + (centerY - radiusBorder / 2));

        hexagonBorderPath.lineTo(centerX + triangleBorderHeight, centerY + radiusBorder / 2);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Last Point --> " + (centerX + triangleBorderHeight) + " :: Dest --> " + (centerY + radiusBorder / 2));

        hexagonBorderPath.moveTo(centerX, centerY + radiusBorder);
        Log.d("calculatePath ", "hexagonBorderPath : " + "Origin End--> " + (centerX) + " :: Dest End--> " + (centerY + radiusBorder));
        invalidate();
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

//        c.clipPath(hexagonBorderPath, Region.Op.DIFFERENCE);
//        c.drawColor(Color.WHITE);
//        c.save();
//
//        c.clipPath(hexagonPath, Region.Op.DIFFERENCE);
//        c.drawColor(maskColor);
//        c.save();


        //////// Testing Stuff //////////

        Paint paint = new Paint();
//        ColorFilter filter = new PorterDuffColorFilter(Color.parseColor("#F4511E"), PorterDuff.Mode.SRC_IN);
//        paint.setColorFilter(filter);
        paint.setColor(Color.parseColor("#00C853"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setAlpha(99);
        paint.setStrokeWidth(8f);
        paint.setShadowLayer(30, 0, 0, Color.parseColor("#BDBDBD"));
        c.drawPath(hexagonPath, paint);
        c.save();

//        Paint paint2 = new Paint();
//        paint2.setColor(Color.parseColor("#333333"));
//        paint2.setStrokeWidth(2);
//        c.drawPath(hexagonPath, paint2);
//        c.save();


    }

    // getting the view size and default radius
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);

        Log.d("OnMeasure ", "Vals : " + "Width --> " + width + " :: Height --> " + height);

        radius = height / 2 - 8;
        calculatePath();
    }

}
