package com.mike.givemewingzz.found.service;

import android.content.Context;
import android.util.Log;

import com.mike.givemewingzz.found.data.models.YelpWrapper;
import com.mike.givemewingzz.found.utils.BaseClient;
import com.mike.givemewingzz.found.utils.BaseRetrofitInterface;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GiveMeWingzz on 12/9/2015.
 */
public class GetSearchResult {

    public static final String TAG = GetSearchResult.class.getSimpleName();

    private static final String CONSUMER_KEY = "Ox-ughsjh_PCpSX6S6jYIA";
    private static final String CONSUMER_SECRET = "EFHJdZKBsu_KLoaSET1KqytTuUw";
    private static final String TOKEN = "NVR_cA0iYEiZwk_8BJh_ySTmyG5LPlhA";
    private static final String TOKEN_SECRET = "zFUI3i3hAxDMdpKOdSpVtGqdoac";
    private static final String SIGNATURE = "WZsM2rnMaIVe+vRhGSCctB+j4QM=";

    public static void call(final Context context, String term, String location, int limit) {

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

//        YelpAPI yelpAPI = new YelpAPI();
//        yelpAPI.initializeAuth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
//
//        YelpAuth yelpAuth = yelpAPI.getAuthFromBundle().getParcelable(YelpAuth.YELP_AUTH);

        baseRetrofitInterface.getSearchQuery(term, location, new Callback<YelpWrapper>() {

            @Override
            public void success(YelpWrapper yelpWrapper, Response response) {
                Log.d(TAG, response.getBody().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error : " + error.getMessage());
                System.out.println(TAG + " URL :> " + error.getResponse().getUrl());
            }
        });

    }

}
