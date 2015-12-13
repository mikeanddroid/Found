package com.mike.givemewingzz.found.utils;

import android.content.Context;

import io.realm.RealmConfiguration;

/**
 * Created by GiveMeWingzz on 11/27/2015.
 */
public class DBHelper {

    public static final long SCHEMA_VERSION = 1;
    public static final String REALM_NAME = "feelinbored.realm";
    public String tesintPR;

    public static RealmConfiguration getRealmConfig(Context context) {
        return new RealmConfiguration.Builder(context)
                .name(REALM_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .build();
    }

}
