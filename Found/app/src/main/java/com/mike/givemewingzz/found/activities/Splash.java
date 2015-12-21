package com.mike.givemewingzz.found.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mike.givemewingzz.found.R;

import java.util.Random;

/**
 * Created by GiveMeWingzz on 12/14/2015.
 */
public class Splash extends FoundCompat {

    ImageView clickMe;
    TextView countingText, gameHeader, targetCounting, header_level_count,
            timerCount;

    LinearLayout background;

    RelativeLayout.LayoutParams mLayoutParams;
    static WindowManager.LayoutParams mParams;
    static DisplayMetrics metrics;
    static int x = 0;
    static int y = 0;
    static int count = 0;

    AlertDialog alert;
    AlertDialog.Builder builder;

    Runnable r;
    Runnable timerRunnable;

    int timer;

    Handler mHandler, timerHandler;
    static String levelup;
    static int gameLevelCount = 0;
    static int leveltargetCount = 20;
    static int targetCounter = 0;
    public static int timerCounter = 180;
    int newtimer_counter = 30;
    int count_starts_in = 4;

    //For custom Dialog
    //CustomDialog dialog;

    //private int[] drawbleArray = new int[] { R.drawable.gradient,R.drawable.change1,R.drawable.change2,R.drawable.change2,R.drawable.change3 };

    //private int[] drawbleArray = { Color.parseColor("#00B050"),	Color.parseColor("#FFC000"),Color.parseColor("#DB1351"),Color.parseColor("#B61C83"),Color.parseColor("#0070C0") };

    Drawable[] drawbleArray;

    private Random randomBackground;
    static int RandomColorChange;

    public static final String SPLASH_KEY = "SPLASH_KEY";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getPreferences(Activity.MODE_PRIVATE);

        if (prefs.getBoolean(SPLASH_KEY, false)) {
            //startApp();
            return;
        } else {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(SPLASH_KEY, true);
            editor.apply();
        }

        setContentView(R.layout.gamelayout);

        drawbleArray = new Drawable[]
                {
                        getResources().getDrawable(R.drawable.ic_menu_gallery),
                        getResources().getDrawable(R.drawable.ic_menu_manage),
                        getResources().getDrawable(R.drawable.ic_menu_send),
                        getResources().getDrawable(R.drawable.ic_menu_camera)
                };

        //dialog = new CustomDialog(this);

        final Animation animScale = AnimationUtils.loadAnimation(this,
                R.anim.anim_scale);

        clickMe = (ImageView) findViewById(R.id.clickMeImage);
        countingText = (TextView) findViewById(R.id.count);
        gameHeader = (TextView) findViewById(R.id.game_header_level_TextView);
        targetCounting = (TextView) findViewById(R.id.targetCount);
        header_level_count = (TextView) findViewById(R.id.game_header_level_count);
        timerCount = (TextView) findViewById(R.id.Timer_Count);

        background = (LinearLayout) findViewById(R.id.gameRelativeLayout);

        gameLevelCount++;

        mHandler = new Handler();
        timerHandler = new Handler();

        timerRunnable = new Runnable() {

            @Override
            public void run() {

                timerCounter--;
                timerCount.setText(String.valueOf(timerCounter));
                timerHandler.postDelayed(this, 1000);

                if (timerCounter == 0) {

                    try {

                        timerHandler.removeCallbacks(timerRunnable);


                        Toast.makeText(getApplicationContext(), "Time's Up..",
                                Toast.LENGTH_SHORT).show();

                    } finally {
                    }

                }

            }
        };

        timerHandler.postDelayed(timerRunnable, 1000);

        r = new Runnable() {

            @Override
            public void run() {

                IncrementAndChangeValue();

                mHandler.postDelayed(this, 800);

            }
        };

        mHandler.postDelayed(r, 800);

        clickMe.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                countingText.startAnimation(animScale);
                v.startAnimation(animScale);

                count++;
                countingText.setText(String.valueOf(count));

                if (count == Integer.valueOf(targetCounting.getText()
                        .toString())) {

                    OpenDialog();
                    LevelUp();
                    mHandler.postDelayed(r, 800);

                }

            }
        });

    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void RandomBackground() {

        //If Random Colors
        //background.setBackgroundColor(drawbleArray[RandomColorChange]);

        background.setBackground(drawbleArray[RandomColorChange]);

    }

//	public void CustomDialog() {
//
//		timerHandler.removeCallbacks(timerRunnable);
//		dialog.show();
//
//	}

    public void IncrementAndChangeValue() {

        LinearLayout.LayoutParams absParams = (LinearLayout.LayoutParams) clickMe
                .getLayoutParams();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        Random r = new Random();

        try {
            x = r.nextInt(displaymetrics.widthPixels - 1 * clickMe.getWidth());
            y = r.nextInt(displaymetrics.heightPixels - 2 * clickMe.getHeight());
        } catch (Exception e) {

            e.printStackTrace();
        }

        try {
            absParams.leftMargin = r.nextInt(x);
            absParams.topMargin = r.nextInt(y);
        } catch (Exception e) {

            e.printStackTrace();
        }

        try {
            clickMe.setLayoutParams(absParams);
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void LevelUp() {

        gameLevelCount++;
        header_level_count.setText(String.valueOf(gameLevelCount));

    }

    public void OpenDialog() {

        timerHandler.removeCallbacks(timerRunnable);

        randomBackground = new Random();

        RandomColorChange = randomBackground.nextInt(4);

        builder = new AlertDialog.Builder(Splash.this);
        builder.setIcon(getResources().getDrawable(R.drawable.ic_menu_camera));
        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("Continue",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getApplicationContext(),
                                "Positive clicked", Toast.LENGTH_SHORT).show();

                        if (gameLevelCount == Integer
                                .valueOf(header_level_count.getText()
                                        .toString())) {

                            count = 0;
                            countingText.setText(String.valueOf(count));
                            RandomBackground();
                            NewTimer2();
                            leveltargetCount += 5;
                            targetCounting.setText(String
                                    .valueOf(leveltargetCount));

                        } else {

                            Toast.makeText(getApplicationContext(),
                                    "Game level and header not equal",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

        builder.setCancelable(false);
        alert = builder.create();
        alert.show();

    }

    public void NewTimer2() {

        timerCounter += 5;
        timerCount.setText(String.valueOf(timerCounter));

        timerRunnable = new Runnable() {

            @Override
            public void run() {

                timerCounter--;
                timerCount.setText(String.valueOf(timerCounter));
                timerHandler.postDelayed(this, 1000);

                if (timerCounter == 0) {


                    try {

                        timerHandler.removeCallbacks(timerRunnable);

                        Toast.makeText(getApplicationContext(), "Time's Up..",
                                Toast.LENGTH_SHORT).show();

                    } finally {
                    }

                }

            }
        };

        timerHandler.postDelayed(timerRunnable, 1000);

    }


    public void onResumeDialog() {

        timerCount.setText(String.valueOf(timerCounter));
        timerRunnable = new Runnable() {

            @Override
            public void run() {


                timerCounter--;
                timerCount.setText(String.valueOf(timerCounter));
                timerHandler.postDelayed(this, 1000);

                if (timerCounter == 0) {

                    try {

                        timerHandler.removeCallbacks(timerRunnable);

                        Toast.makeText(getApplicationContext(), "Time's Up..",
                                Toast.LENGTH_SHORT).show();

                    } finally {

                    }

                }

            }
        };

        timerHandler.postDelayed(timerRunnable, 1000);
        toResumeImageWithSameValue();

    }

    public void toResumeImageWithSameValue() {

        r = new Runnable() {

            @Override
            public void run() {

                IncrementAndChangeValue();

                mHandler.postDelayed(this, 800);

            }
        };

        mHandler.postDelayed(r, 800);

    }

    public void GotoThis() {
        count = 0;
        header_level_count.setText(String.valueOf(count));
    }

    public void ResetOnDestroy() {
        count = 0;
        gameLevelCount = 1;
        header_level_count.setText(String.valueOf(gameLevelCount));
        timerCounter = 180;
        timerCount.setText(String.valueOf(timerCounter));
        timerHandler.removeCallbacks(timerRunnable);
        mHandler.removeCallbacks(r);
        leveltargetCount = 20;
        targetCounting.setText(String.valueOf(leveltargetCount));
    }

    public void OnReset() {
        count = 0;
        gameLevelCount = 1;
        header_level_count.setText(String.valueOf(gameLevelCount));
        timerCounter = 180;
        timerCount.setText(String.valueOf(timerCounter));
        mHandler.removeCallbacks(r);
        leveltargetCount = 20;
        targetCounting.setText(String.valueOf(leveltargetCount));
    }

    public void OnPauseGame() {

        mHandler.removeCallbacks(r);

    }

    public void OnResetGame() {

        mHandler.removeCallbacks(r);

    }

    public void OnPauseDialog() {

        timerHandler.removeCallbacks(timerRunnable);

        builder = new AlertDialog.Builder(Splash.this);

        builder.setIcon(getResources().getDrawable(
                R.drawable.ic_menu_camera));
        builder.setTitle(getResources().getString(
                R.string.app_name));
        builder.setNeutralButton(getResources().getString(R.string.app_name), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                onResumeDialog();

            }
        });

        builder.setCancelable(false);
        alert = builder.create();
        alert.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ResetOnDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public String setConsumerKey() {
        return null;
    }

    @Override
    public String setConsumerSecret() {
        return null;
    }

    @Override
    public String setToken() {
        return null;
    }

    @Override
    public String setTokenSecret() {
        return null;
    }
}
