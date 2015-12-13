package com.mike.givemewingzz.found.data.models;

import io.realm.RealmObject;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class BusinessCategories extends RealmObject {

   private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
