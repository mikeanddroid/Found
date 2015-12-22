package com.mike.givemewingzz.found.activities;

import android.os.Bundle;

import com.mike.givemewingzz.found.R;

/**
 * Created by GiveMeWingzz on 12/22/2015.
 */
public class FoundSplash extends FoundCompat {

    // Not req in this activity unless we load some data in background
    @Override
    public String setConsumerKey() {
        return null;
    }

    // Not req in this activity unless we load some data in background
    @Override
    public String setConsumerSecret() {
        return null;
    }

    // Not req in this activity unless we load some data in background
    @Override
    public String setToken() {
        return null;
    }

    // Not req in this activity unless we load some data in background
    @Override
    public String setTokenSecret() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.found_splash);

    }
}
