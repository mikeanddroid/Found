package com.mike.givemewingzz.found.data.models;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class Business extends RealmObject {

    private boolean is_claimed;
    private double rating;
    private String mobile_url;
    private String rating_img_url;
    private int review_count;
    private String name;
    private String rating_img_url_small;
    private String url;

    @SerializedName("categories")
    private RealmList<BusinessCategories> businessCategories;

    private String menu_date_updated;
    private String phone;
    private String snippet_text;
    private String image_url;
    private String snippet_image_url;
    private String display_phone;
    private String rating_img_url_large;
    private String menu_provider;
    private String id;
    private boolean is_closed=false;

    @SerializedName("location")
    private LocationData locationData;

    public boolean is_claimed() {
        return is_claimed;
    }

    public void setIs_claimed(boolean is_claimed) {
        this.is_claimed = is_claimed;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getRating_img_url() {
        return rating_img_url;
    }

    public void setRating_img_url(String rating_img_url) {
        this.rating_img_url = rating_img_url;
    }

    public int getReview_count() {
        return review_count;
    }

    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating_img_url_small() {
        return rating_img_url_small;
    }

    public void setRating_img_url_small(String rating_img_url_small) {
        this.rating_img_url_small = rating_img_url_small;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RealmList<BusinessCategories> getBusinessCategories() {
        return businessCategories;
    }

    public void setBusinessCategories(RealmList<BusinessCategories> businessCategories) {
        this.businessCategories = businessCategories;
    }

    public String getMenu_date_updated() {
        return menu_date_updated;
    }

    public void setMenu_date_updated(String menu_date_updated) {
        this.menu_date_updated = menu_date_updated;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSnippet_text() {
        return snippet_text;
    }

    public void setSnippet_text(String snippet_text) {
        this.snippet_text = snippet_text;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSnippet_image_url() {
        return snippet_image_url;
    }

    public void setSnippet_image_url(String snippet_image_url) {
        this.snippet_image_url = snippet_image_url;
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public String getRating_img_url_large() {
        return rating_img_url_large;
    }

    public void setRating_img_url_large(String rating_img_url_large) {
        this.rating_img_url_large = rating_img_url_large;
    }

    public String getMenu_provider() {
        return menu_provider;
    }

    public void setMenu_provider(String menu_provider) {
        this.menu_provider = menu_provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean is_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }
}
