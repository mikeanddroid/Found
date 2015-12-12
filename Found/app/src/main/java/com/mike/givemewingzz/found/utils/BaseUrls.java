package com.mike.givemewingzz.found.utils;

import android.util.Log;

import com.mike.givemewingzz.found.apihelper.FoundApiHelper;
import com.mike.givemewingzz.found.parcelable.YelpAuth;

import retrofit.RequestInterceptor;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class BaseUrls {

    public static final String BASE_URL = "https://api.yelp.com";

    public static final String TAG = BaseUrls.class.getSimpleName();

    private static final String CONSUMER_KEY = "Ox-ughsjh_PCpSX6S6jYIA";
    private static final String CONSUMER_SECRET = "EFHJdZKBsu_KLoaSET1KqytTuUw";
    private static final String TOKEN = "NVR_cA0iYEiZwk_8BJh_ySTmyG5LPlhA";
    private static final String TOKEN_SECRET = "zFUI3i3hAxDMdpKOdSpVtGqdoac";

    public static final RequestInterceptor mIntercepter = new BaseUrlIntercepter();

    private static class BaseUrlIntercepter extends BaseRequestIntercepter {

        @Override
        public void intercept(RequestFacade request) {

            FoundApiHelper foundApiHelper = new FoundApiHelper();
            foundApiHelper.initializeAuth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
            YelpAuth yelpAuth = foundApiHelper.getAuthFromBundle().getParcelable(YelpAuth.YELP_AUTH);

            request.addEncodedQueryParam("oauth_consumer_key", yelpAuth.getOauth_consumer_key());
            request.addEncodedQueryParam("oauth_token", yelpAuth.getOauth_token());
            request.addEncodedQueryParam("oauth_signature_method", yelpAuth.getOauth_signature_method());
            request.addEncodedQueryParam("oauth_timestamp", yelpAuth.getOauth_timestamp());
            request.addEncodedQueryParam("oauth_nonce", yelpAuth.getOauth_nonce());
            request.addEncodedQueryParam("oauth_version", yelpAuth.getOauth_version());
            request.addEncodedQueryParam("oauth_signature", yelpAuth.getOauth_signature());

            Log.d(TAG, " :: Requst Headers Consumer key--> " + yelpAuth.getOauth_consumer_key());
            Log.d(TAG, " :: Requst Headers --> " + request);

        }
    }

}
