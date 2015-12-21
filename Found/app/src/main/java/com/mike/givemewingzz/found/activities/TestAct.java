package com.mike.givemewingzz.found.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.mike.givemewingzz.found.R;
import com.mike.givemewingzz.found.activities.fragments.TestFragment;

/**
 * Created by GiveMeWingzz on 12/15/2015.
 */
public class TestAct extends AppCompatActivity {

    FrameLayout frameLayout;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        frameLayout = (FrameLayout) findViewById(R.id.view_container);

        TestFragment testFragment = TestFragment.getInstance();

        getSupportFragmentManager().beginTransaction().add(R.id.view_container, testFragment, TestFragment.class.getSimpleName()).commit();

    }

}
