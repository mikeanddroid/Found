package com.mike.givemewingzz.found.Interfaces;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public interface MethodsWrapper {

    void createInitialRequest(String consumerKey, String consumerSecret, String token, String tokenSecret);
    void addQueryParams(String key, String value);
    void signRequest();

}
