package com.mike.givemewingzz.found.activities.fragments;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mike.givemewingzz.found.R;

import java.util.Random;

/**
 * Created by GiveMeWingzz on 12/22/2015.
 */
public class SplashScreen extends Fragment {

    private ImageView spamImageView;

    // Random Generation stuff
    private Random randomImage;
    static int randomColorChange;

    Runnable runnable;
    Handler mHandler, timerHandler;

    static int x = 0;
    static int y = 0;

    private LinearLayout splashHolder;

    int[] drawbleImageArray;
    Drawable[] drawbleImageBgArray;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.splash_screen, container, false);
        spamImageView = (ImageView) view.findViewById(R.id.splash_screen_spam_image);
        splashHolder = (LinearLayout) view.findViewById(R.id.splash_screen_holder);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRandom();

        mHandler = new Handler();

        runnable = new Runnable() {

            @Override
            public void run() {

                generateRandomImagesWithRandomBg();
                mHandler.postDelayed(this, 2000);

            }
        };

        mHandler.postDelayed(runnable, 2000);

    }

    private void initRandom() {

        drawbleImageArray = new int[]{
                R.drawable.cafe_white,
                R.drawable.local_bar_white,
                R.drawable.local_mall_white,
                R.drawable.local_dining_white
        };


    }

    public void setRandomImageResource() {
        spamImageView.setImageBitmap(BitmapFactory.decodeResource(getActivity().getResources(), drawbleImageArray[randomColorChange]));
    }

    private void generateRandomImagesWithRandomBg() {

        LinearLayout.LayoutParams absParams = (LinearLayout.LayoutParams) spamImageView
                .getLayoutParams();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        randomImage = new Random();

        randomColorChange = randomImage.nextInt(4);

        Random r = new Random();

        try {
            x = r.nextInt((int) Math.round(displaymetrics.widthPixels / 1.3) - 1 * spamImageView.getWidth());
            y = r.nextInt((int) Math.round(displaymetrics.heightPixels / 1.5) - 2 * spamImageView.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            absParams.leftMargin = r.nextInt(x) + 20;
            absParams.topMargin = r.nextInt(y) + 20;
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            spamImageView.setLayoutParams(absParams);
            setRandomImageResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
