package com.mike.givemewingzz.found.data.models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class LocationData extends RealmObject {

    private String city;
    private String display_address;
    private String geo_accuracy;
    private RealmList<Neighborhood> neighborhoods;
    private String postal_code;
    private String country_code;
    private RealmList<LocationAddress> address;
    private Coordinates coordinate;
    private String state_code;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDisplay_address() {
        return display_address;
    }

    public void setDisplay_address(String display_address) {
        this.display_address = display_address;
    }

    public String getGeo_accuracy() {
        return geo_accuracy;
    }

    public void setGeo_accuracy(String geo_accuracy) {
        this.geo_accuracy = geo_accuracy;
    }

    public RealmList<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(RealmList<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public RealmList<LocationAddress> getAddress() {
        return address;
    }

    public void setAddress(RealmList<LocationAddress> address) {
        this.address = address;
    }

    public Coordinates getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinates coordinate) {
        this.coordinate = coordinate;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }
}
