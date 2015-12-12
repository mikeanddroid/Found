package com.mike.givemewingzz.found.utils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mike.givemewingzz.found.BuildConfig;
import com.squareup.okhttp.OkHttpClient;

import io.realm.RealmObject;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class BaseClient {

    private BaseClient() {

    }

    public static BaseRetrofitInterface getBBSIClient() {
        return LazySIDCInterface.INSTANCE;
    }

    private static class LazySIDCInterface {
        private static final BaseRetrofitInterface INSTANCE = initializeInterface();

        private static BaseRetrofitInterface initializeInterface() {
            // Create the necessary GSON to handle exclusion of Realm pieces
            Gson gson = new GsonBuilder()
                    .setExclusionStrategies(new ExclusionStrategy() {
                        @Override
                        public boolean shouldSkipField(FieldAttributes f) {
                            return f.getDeclaringClass().equals(RealmObject.class);
                        }

                        @Override
                        public boolean shouldSkipClass(Class<?> clazz) {
                            return false;
                        }
                    })
                    .create();

            // Configure OkHttp+AppD
            OkHttpClient client = new OkHttpClient();
            client.interceptors().add(new AppDynamicsInterceptor());

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setClient(new OkClient(client))
                    .setRequestInterceptor(BaseUrls.mIntercepter)
                    .setEndpoint(BaseUrls.BASE_URL)
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .build();

            return restAdapter.create(BaseRetrofitInterface.class);
        }
    }

}
