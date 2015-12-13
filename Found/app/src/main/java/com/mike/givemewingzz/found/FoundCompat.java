package com.mike.givemewingzz.found;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mike.givemewingzz.found.Interfaces.FoundHelperInterfaces;
import com.mike.givemewingzz.found.apihelper.FoundApiHelper;

import java.util.Map;

/**
 * Created by GiveMeWingzz on 12/9/2015.
 */
public abstract class FoundCompat extends AppCompatActivity implements FoundHelperInterfaces{

    private FoundApiHelper foundApiHelper;

    public abstract String getConsumerKey();
    public abstract String getConsumerSecret();
    public abstract String getToken();
    public abstract String getTokenSecret();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foundApiHelper = new FoundApiHelper();
    }

    // Let the activities/fragments implement it since every request needs to be initialized and signed.
    @Override
    public void createInitialRequest() {
        foundApiHelper.initializeApiHelper(getConsumerKey(), getConsumerSecret(), getToken(),getTokenSecret());
    }

    // Let the activities/fragments implement it since every request needs to be initialized and signed.
    @Override
    public void addQueryParams(String key, String value){
        foundApiHelper.addQuerystringParameter(key, value);
    }

    // Let the activities/fragments implement it since every request needs to be initialized and signed.
    @Override
    public void signRequest() {
        for(Map.Entry<String, String> entries: foundApiHelper.getQueryParams().entrySet()){
            foundApiHelper.addQuerystringParameter(entries.getKey(), entries.getValue());
        }
        foundApiHelper.signAuthRequest();
    }

}
