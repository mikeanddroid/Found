package com.mike.givemewingzz.found.utils;

import retrofit.RequestInterceptor;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public abstract class BaseRequestIntercepter implements RequestInterceptor {

    /**
     * Called for every request. Add data using methods on the supplied {@link RequestFacade}.
     *
     * @param request
     */
    @Override
    public void intercept(RequestFacade request) {

    }
}
