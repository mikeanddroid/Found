package com.mike.givemewingzz.found.apihelper;

import android.os.Bundle;
import android.util.Log;

import com.mike.givemewingzz.found.Interfaces.MethodsWrapper;
import com.mike.givemewingzz.found.parcelable.YelpAuth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class FoundApiHelper implements MethodsWrapper {

    private static final String TAG = FoundApiHelper.class.getSimpleName();

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "dinner";
    private static final String DEFAULT_LOCATION = "San Francisco, CA";
    private static final String SEARCH_PATH = "/v2/search";

    private OAuthService service;
    private Token accessToken;
    public static Bundle bundle;

    private LinkedHashMap<String, String> queryParams;
    private MethodsWrapper methodsWrapper;

    public FoundApiHelper() {
        bundle = new Bundle();
        queryParams = new LinkedHashMap<>();
    }

    //// Init Helpers//
    /////////////

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * @param consumerKey    Consumer key
     * @param consumerSecret Consumer secret
     * @param token          Token
     * @param tokenSecret    Token secret
     */
    public void initializeApiHelper(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service = new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);
    }

    public void addQuerystringParameter(String key, String value) {
        queryParams.put(key, value);
    }

    public void signAuthRequest() {

        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + SEARCH_PATH);
        for (Map.Entry<String, String> param : getQueryParams().entrySet()) {
            request.addQuerystringParameter(param.getKey(), param.getValue());
        }

        service.signRequest(accessToken, request);

        Log.d(TAG, "Signing Success");

        YelpAuth yelpAuth = new YelpAuth();

        String oauth_signature_method = (String) request.getOauthParameters().values().toArray()[0];
        String oauth_consumer_key = (String) request.getOauthParameters().values().toArray()[1];
        String oauth_version = (String) request.getOauthParameters().values().toArray()[2];
        String oauth_timestamp = (String) request.getOauthParameters().values().toArray()[3];
        String oauth_nonce = (String) request.getOauthParameters().values().toArray()[4];
        String oauth_token = (String) request.getOauthParameters().values().toArray()[5];
        String oauth_signature = (String) request.getOauthParameters().values().toArray()[6];

        yelpAuth.setOauth_signature_method(oauth_signature_method);
        yelpAuth.setOauth_consumer_key(oauth_consumer_key);
        yelpAuth.setOauth_version(oauth_version);
        yelpAuth.setOauth_timestamp(oauth_timestamp);
        yelpAuth.setOauth_nonce(oauth_nonce);
        yelpAuth.setOauth_token(oauth_token);
        yelpAuth.setOauth_signature(oauth_signature);

        bundle.putParcelable(YelpAuth.YELP_AUTH, yelpAuth);
        setAuth(bundle);

    }

    public LinkedHashMap<String, String> getQueryParams() {

        if (queryParams == null) {
            return new LinkedHashMap<>();
        }

        return queryParams;
    }

    private void setAuth(Bundle bundle) {
        this.bundle = bundle;
    }

    public Bundle getAuthFromBundle() {
        return bundle;
    }

    public MethodsWrapper getMethodsWrapper() {
        return this;
    }

    @Override
    public void createInitialRequest(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        initializeApiHelper(consumerKey, consumerSecret, token, tokenSecret);
    }

    @Override
    public void addQueryParams(String key, String value) {
        addQuerystringParameter(key, value);
    }

    @Override
    public void signRequest() {
        signAuthRequest();
    }
}
