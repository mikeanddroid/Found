package com.mike.givemewingzz.found.service;

import android.util.Log;

import com.mike.givemewingzz.found.data.models.BaseWrapper;
import com.mike.givemewingzz.found.data.models.Business;
import com.mike.givemewingzz.found.data.models.BusinessCategories;
import com.mike.givemewingzz.found.data.models.LocationData;
import com.mike.givemewingzz.found.utils.BaseClient;
import com.mike.givemewingzz.found.utils.BaseRetrofitInterface;
import com.mike.givemewingzz.found.utils.FoundUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by GiveMeWingzz on 12/9/2015.
 */
public class GetSearchResult {

    public static final String TAG = GetSearchResult.class.getSimpleName();

    public static void call(String term, final String location, int limit) {

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        baseRetrofitInterface.searchQueries(term, location, limit, new Callback<BaseWrapper>() {
            @Override
            public void success(BaseWrapper baseWrapper, Response response) {

                BusinessCategories businessCategories = new BusinessCategories();
                LocationData locationData = new LocationData();

                String json = null;
                try {
                    json = FoundUtils.inputStreamToString(response.getBody().in(), "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String categories = null;
                String locations = null;

                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray jsonArray = jsonObject.getJSONArray("businesses");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONArray object = jsonArray.getJSONObject(i).getJSONArray("categories");
                        JSONObject objectLoc = jsonArray.getJSONObject(i).getJSONObject("location");
                        categories = object.toString();

                        locations = objectLoc.getJSONArray("address").toString();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {

                    RealmList<Business> businesses = baseWrapper.getBusinesses();

                    Realm realm = Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.clear(Business.class);

                    if (businesses != null) {
                        realm.copyToRealmOrUpdate(businesses);
                    }

                    realm.commitTransaction();
                    // Todo: Publish Success

                } catch (NullPointerException npe) {
                    Log.e(TAG, "Missing element somewhere in getPlans response", npe);

                    // Todo: Publish error
                }

                List<String> testVal = FoundUtils.removedChars(categories, ",", 1, 2); // For Categories
                List<String> locVal = FoundUtils.removedChars(locations, ",", 1, 1); // For Location

                Log.d(TAG, "Response testVal Length: " + testVal.size());

                for (String vals : testVal) {
                    Log.d(TAG, "Response testVals: " + vals);
                    businessCategories.setCategory(vals);
                }

                for (String vals : locVal) {
                    Log.d(TAG, "Response Location: " + vals);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Response Error: " + error);
                System.out.println(TAG + " URL :> " + error.getResponse().getUrl());
            }
        });

    }


}
