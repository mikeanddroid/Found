package com.mike.givemewingzz.found;

import android.app.Application;

import com.mike.givemewingzz.found.utils.DBHelper;

import io.realm.Realm;

/**
 * Created by GiveMeWingzz on 12/9/2015.
 */
public class FoundApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Todo : Have to add after the retrofit integration.
         Realm.setDefaultConfiguration(DBHelper.getRealmConfig(this));
    }

}
