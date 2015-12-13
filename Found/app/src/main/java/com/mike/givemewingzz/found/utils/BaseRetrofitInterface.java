package com.mike.givemewingzz.found.utils;

import com.mike.givemewingzz.found.data.models.YelpWrapper;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public interface BaseRetrofitInterface {

    @GET("/v2/search")
    void searchQueries(@Query("term") String term, @Query("location") String location,@Query("limit") int limit, Callback<YelpWrapper> cb);

}
