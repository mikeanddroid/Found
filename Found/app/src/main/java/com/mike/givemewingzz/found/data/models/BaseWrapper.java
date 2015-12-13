package com.mike.givemewingzz.found.data.models;

import io.realm.RealmList;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class BaseWrapper {

    public String total;
    public RealmList<Business> businesses;

    public RealmList<Business> getBusinesses() {
        return businesses;
    }

}
