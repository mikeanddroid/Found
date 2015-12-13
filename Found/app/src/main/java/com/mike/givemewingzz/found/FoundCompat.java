package com.mike.givemewingzz.found;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mike.givemewingzz.found.Interfaces.FoundHelperInterfaces;
import com.mike.givemewingzz.found.Interfaces.MethodsWrapper;
import com.mike.givemewingzz.found.apihelper.FoundApiHelper;

import io.realm.Realm;

/**
 * Created by GiveMeWingzz on 12/9/2015.
 */
public abstract class FoundCompat extends AppCompatActivity implements FoundHelperInterfaces{

    private FoundApiHelper foundApiHelper;

    public abstract String getConsumerKey();
    public abstract String getConsumerSecret();
    public abstract String getToken();
    public abstract String getTokenSecret();

    MethodsWrapper methodsWrapper;
    protected Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foundApiHelper = new FoundApiHelper();
        methodsWrapper = foundApiHelper.getMethodsWrapper();
        realm = Realm.getDefaultInstance();

    }

    // Let the activities/fragments implement it since every request needs to be initialized and signed.
    @Override
    public void createInitialRequest() {
        foundApiHelper.createInitialRequest(getConsumerKey(), getConsumerSecret(), getToken(), getTokenSecret());
    }

    // Let the activities/fragments implement it since every request needs to be initialized and signed.
    @Override
    public void addQueryParams(String key, String value){
        foundApiHelper.addQueryParams(key, value);
    }

    // Let the activities/fragments implement it since every request needs to be initialized and signed.
    @Override
    public void signRequest() {
        foundApiHelper.signRequest();
    }

    @Override
    protected void onDestroy() {

        if (realm != null) {
            realm.close();
        }

        super.onDestroy();
    }

}
