package com.mike.givemewingzz.found.data.models;

import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class Coordinates extends RealmObject {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
