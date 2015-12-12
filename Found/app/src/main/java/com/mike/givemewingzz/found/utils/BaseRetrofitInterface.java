package com.mike.givemewingzz.found.utils;

import com.mike.givemewingzz.found.data.models.YelpWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public interface BaseRetrofitInterface {

    @POST("/v2/search/")
    void getSearchQuery(
            @Query("term")String term,
            @Query("location")String location,

//            @Query("oauth_consumer_key")String oauth_consumer_key,
//            @Query("oauth_token")String oauth_token,
//            @Query("oauth_signature_method")String oauth_signature_method,
//            @Query("oauth_nonce")String oauth_nonce,
//            @Query("oauth_timestamp")String oauth_timestamp,
//            @Query("oauth_version")String oauth_version,
//            @Query("oauth_signature")String oauth_signature,

            Callback<YelpWrapper> cb);

    @GET("/v2/search")
    void searchParams(@Query("term")String term,
                      @Query("location")String location,
                      @Query("oauth_consumer_key")String oauth_consumer_key,

                    @Query("oauth_token")String oauth_token,
                    @Query("oauth_signature_method")String oauth_signature_method,
                    @Query("oauth_nonce")String oauth_nonce,
                    @Query("oauth_timestamp")String oauth_timestamp,
                    @Query("oauth_version")String oauth_version,
                    @Query("oauth_signature")String oauth_signature,

                      Callback<YelpWrapper> cb);

}
