package com.mike.givemewingzz.found.customviews;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.mike.givemewingzz.found.R;

/**
 * Created by GiveMeWingzz on 12/16/2015.
 */
public class FoundButton extends View {

    int viewWidth = 0;
    int viewHeight = 0;
    int strokeWidth = 0;

    ColorStateList color;

    public FoundButton(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(null, 0);
        }
    }

    public FoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(attrs, 0);
        }
    }

    public FoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(attrs, defStyleAttr);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.d("On Draw ", "On Draw calling");

        Path path = new Path();
        float stdW = getViewWidth();
        float stdH = getViewHeight();

        Log.d("On Draw ", "Vals : " + "Width --> " + stdW + " :: Height --> " + stdH);

        float w3 = stdW / 3;
        float h2 = stdH / 2;

        path.moveTo(0, h2);
        h2 -= 5 / 2;
        path.rLineTo(w3, -h2);
        path.rLineTo(w3, 0);
        path.rLineTo(w3, h2);
        path.rLineTo(-w3, h2);
        path.rLineTo(-w3, 0);
        path.rLineTo(-w3, -h2);

        Shape s = new PathShape(path, stdW, stdH);
        ShapeDrawable d = new ShapeDrawable(s);
        Paint p = d.getPaint();
        p.setColor(getResources().getColor(R.color.black));
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(getStrokeWidth());

        canvas.drawPath(path, p);
        canvas.rotate(90);
        canvas.save();

    }

    private void init(AttributeSet attrs, int defStyle) {

        Log.d("On init ", "On init calling");

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FoundButton, defStyle, 0);

        viewWidth = a.getInteger(R.styleable.FoundButton_foundButtonWidth, 0);
        setViewWidth(viewWidth);
        viewHeight = a.getInteger(R.styleable.FoundButton_foundButtonHeight, 0);
        setViewHeight(viewHeight);

        Log.d("On Draw ", "Vals : " + "Width --> " + viewWidth + " :: Height --> " + viewHeight);

        color = a.getColorStateList(R.styleable.FoundButton_foundButtonStrokeColor);
        setColor(color);
        strokeWidth = a.getInteger(R.styleable.FoundButton_foundButtonStrokeWidth, 0);
        setStrokeWidth(strokeWidth);

        a.recycle();

    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public ColorStateList getColor() {
        return color;
    }

    public void setColor(ColorStateList color) {
        this.color = color;
    }
}
