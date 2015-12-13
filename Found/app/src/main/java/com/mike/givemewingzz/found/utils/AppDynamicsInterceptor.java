package com.mike.givemewingzz.found.utils;

import com.appdynamics.eumagent.runtime.HttpRequestTracker;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by GiveMeWingzz on 11/29/2015.
 */
public class AppDynamicsInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        HttpRequestTracker tracker = com.appdynamics.eumagent.runtime.Instrumentation.beginHttpRequest(request.url());

        Response response = chain.proceed(request);

        tracker.withResponseCode(response.code())
                .withResponseHeaderFields(response.headers().toMultimap())
                .reportDone();

        return response;

    }
}
