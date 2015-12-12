package com.mike.givemewingzz.found.service;

import android.content.Context;
import android.util.Log;

import com.mike.givemewingzz.found.apihelper.YelpAPI;
import com.mike.givemewingzz.found.data.models.YelpWrapper;
import com.mike.givemewingzz.found.parcelable.YelpAuth;
import com.mike.givemewingzz.found.utils.BaseClient;
import com.mike.givemewingzz.found.utils.BaseRetrofitInterface;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GiveMeWingzz on 12/11/2015.
 */
public class GetSearchParams {

    public static final String TAG = GetSearchParams.class.getSimpleName();

    private static final String CONSUMER_KEY = "Ox-ughsjh_PCpSX6S6jYIA";
    private static final String CONSUMER_SECRET = "EFHJdZKBsu_KLoaSET1KqytTuUw";
    private static final String TOKEN = "NVR_cA0iYEiZwk_8BJh_ySTmyG5LPlhA";
    private static final String TOKEN_SECRET = "zFUI3i3hAxDMdpKOdSpVtGqdoac";
    private static final String SIGNATURE = "WZsM2rnMaIVe+vRhGSCctB+j4QM=";

    public static void call(final Context context, String term, String location) {

        YelpAPI yelpAPI = new YelpAPI();
        yelpAPI.initializeAuth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
        YelpAuth yelpAuth = yelpAPI.getAuthFromBundle().getParcelable(YelpAuth.YELP_AUTH);

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        baseRetrofitInterface.searchParams(term, location,

                yelpAuth.getOauth_consumer_key(),
                yelpAuth.getOauth_token(),
                yelpAuth.getOauth_signature_method(),
                yelpAuth.getOauth_timestamp(),
                yelpAuth.getOauth_nonce(),
                yelpAuth.getOauth_version(),
                yelpAuth.getOauth_signature()

                , new Callback<YelpWrapper>() {
            @Override
            public void success(YelpWrapper yelpWrapper, Response response) {
                Log.d(TAG, response.getBody().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error : " + error.getMessage());
            }
        });
    }

}
