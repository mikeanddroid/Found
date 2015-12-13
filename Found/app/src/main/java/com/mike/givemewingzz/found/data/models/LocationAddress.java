package com.mike.givemewingzz.found.data.models;

import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class LocationAddress extends RealmObject {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
