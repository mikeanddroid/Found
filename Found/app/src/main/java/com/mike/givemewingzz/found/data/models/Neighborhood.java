package com.mike.givemewingzz.found.data.models;

import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class Neighborhood extends RealmObject {

    private String places;

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }
}
