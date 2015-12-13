package com.mike.givemewingzz.found.service;

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

    public static void call(String term, String location, int limit) {

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        baseRetrofitInterface.searchQueries(term, location,limit, new Callback<YelpWrapper>() {

            @Override
            public void success(YelpWrapper yelpWrapper, Response response) {
                Log.d(TAG, "Response : " + response.getBody().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Response Error: " + error.getResponse().getBody().toString());
                System.out.println(TAG + " URL :> " + error.getResponse().getUrl());
            }
        });

    }

}
