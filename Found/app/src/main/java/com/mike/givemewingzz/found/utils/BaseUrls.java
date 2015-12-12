package com.mike.givemewingzz.found.utils;

import android.util.Log;

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

//            YelpAPI yelpAPI = new YelpAPI();
//            yelpAPI.initializeAuth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
//
//            YelpAuth yelpAuth = yelpAPI.getAuthFromBundle().getParcelable(YelpAuth.YELP_AUTH);

//            request.addHeader("Authorization",
//                    "oauth_signature_method=" + yelpAuth.getOauth_signature_method() + "," +
//                    "oauth_consumer_key="+yelpAuth.getOauth_consumer_key() + "," +
//                    "oauth_version=" + yelpAuth.getOauth_version() + "," +
//                    "oauth_timestamp="+System.currentTimeMillis() + "," +
//                    "oauth_nonce=" + yelpAuth.getOauth_nonce() + "," +
//                    "oauth_token="+yelpAuth.getOauth_token() + "," +
//                    "oauth_nonce=" + yelpAuth.getOauth_nonce() + "," +
//                    "oauth_signature=" + yelpAuth.getOauth_signature());
//
//            Log.d(BASE_URL, " :: Requst Headers --> " + request);

//            request.addEncodedQueryParam("oauth_consumer_key",yelpAuth.getOauth_consumer_key());
//            request.addEncodedQueryParam("oauth_token",yelpAuth.getOauth_token());
//            request.addEncodedQueryParam("oauth_signature_method",yelpAuth.getOauth_signature_method());
//            request.addEncodedQueryParam("oauth_timestamp",yelpAuth.getOauth_timestamp());
//            request.addEncodedQueryParam("oauth_nonce",yelpAuth.getOauth_nonce());
//            request.addEncodedQueryParam("oauth_version",yelpAuth.getOauth_version());
//            request.addEncodedQueryParam("oauth_signature",yelpAuth.getOauth_signature());

            Log.d(BASE_URL, " :: Requst Headers --> " + request);

        }
    }

}
