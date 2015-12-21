package com.mike.givemewingzz.found.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.mike.givemewingzz.found.R;
import com.mike.givemewingzz.found.utils.FoundUtils;

/**
 * Created by GiveMeWingzz on 12/16/2015.
 */
public class FoundTextView extends TextView {

    private int font;

    public FoundTextView(Context context) {
        super(context);
        if (!isInEditMode()) {
            init(null, 0);
        }
    }

    public FoundTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (!isInEditMode()) {
            init(attrs, 0);
        }
    }

    public FoundTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (!isInEditMode()) {
            init(attrs, defStyleAttr);
        }
    }

    private void init(AttributeSet attrs, int defStyle) {

        Log.d("On init ", "On Text init calling");

        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.FoundTextView, defStyle, 0);

        font = a.getInteger(R.styleable.FoundTextView_foundFont, 0);
        Typeface tf;

        if (font == getResources().getInteger(R.integer.font_roboto_bold)) {
            tf = FoundUtils.getTfRobotBold();
        } else if (font == getResources().getInteger(R.integer.font_roboto_normal)) {
            tf = FoundUtils.getTfRobotoRegular();
        } else if (font == getResources().getInteger(R.integer.font_roboto_light)) {
            tf = FoundUtils.getTfRobotoLight();
        } else {
            tf = FoundUtils.getTfRobotoRegular();
        }

        setTypeface(tf);

        a.recycle();
    }

    public void setTypeface(int typeface) {
        Typeface tf;
        if (typeface == getResources().getInteger(R.integer.font_roboto_bold)) {
            tf = FoundUtils.getTfRobotBold();
        } else if (typeface == getResources().getInteger(R.integer.font_roboto_normal)) {
            tf = FoundUtils.getTfRobotMedium();
        } else if (typeface == getResources().getInteger(R.integer.font_roboto_light)) {
            tf = FoundUtils.getTfRobotoLight();
        } else {
            tf = FoundUtils.getTfRobotoRegular();
        }
        super.setTypeface(tf);
    }

}
