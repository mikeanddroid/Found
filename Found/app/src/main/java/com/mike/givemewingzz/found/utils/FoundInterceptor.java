package com.mike.givemewingzz.found.utils;

import android.os.Bundle;
import android.util.Log;

import com.mike.givemewingzz.found.apihelper.FoundApiHelper;
import com.mike.givemewingzz.found.parcelable.YelpAuth;
import com.mike.givemewingzz.found.service.BaseRequestIntercepter;

import retrofit.RequestInterceptor;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class FoundInterceptor {

    public static final String TAG = FoundInterceptor.class.getSimpleName();
    public static final RequestInterceptor mIntercepter = new BaseUrlIntercepter();

    private static class BaseUrlIntercepter extends BaseRequestIntercepter {

        @Override
        public void intercept(RequestFacade request) {

            Bundle helper = FoundApiHelper.bundle;
            YelpAuth yelpAuth = helper.getParcelable(YelpAuth.YELP_AUTH);

            request.addEncodedQueryParam(FoundConstants.OAUTH_CONSUMER_KEY, yelpAuth.getOauth_consumer_key());
            request.addEncodedQueryParam(FoundConstants.OAUTH_TOKEN, yelpAuth.getOauth_token());
            request.addEncodedQueryParam(FoundConstants.OAUTH_SIGNATURE_METHOD, yelpAuth.getOauth_signature_method());
            request.addEncodedQueryParam(FoundConstants.OAUTH_TIMESTAMP, yelpAuth.getOauth_timestamp());
            request.addEncodedQueryParam(FoundConstants.OAUTH_NONCE, yelpAuth.getOauth_nonce());
            request.addEncodedQueryParam(FoundConstants.OAUTH_VERSION, yelpAuth.getOauth_version());
            request.addEncodedQueryParam(FoundConstants.OAUTH_SIGNATURE, yelpAuth.getOauth_signature());

            Log.d(TAG, " :: Requst Headers --> " + request);

        }
    }

}
