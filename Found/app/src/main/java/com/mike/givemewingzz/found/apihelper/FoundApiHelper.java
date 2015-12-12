package com.mike.givemewingzz.found.apihelper;

import android.os.Bundle;
import android.util.Log;

import com.mike.givemewingzz.found.parcelable.YelpAuth;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;

/**
 * Created by GiveMeWingzz on 12/12/2015.
 */
public class FoundApiHelper {

    private static final String TAG = FoundApiHelper.class.getSimpleName();

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "dinner";
    private static final String DEFAULT_LOCATION = "San Francisco, CA";
    private static final String SEARCH_PATH = "/v2/search";

    OAuthService service;
    Token accessToken;

    LinkedHashMap<String, String> map;
    YelpAuth yelpAuth;
    Bundle bundle;

    public FoundApiHelper() {
        map = new LinkedHashMap<>();
    }

    /**
     * Setup the Yelp API OAuth credentials.
     *
     * @param consumerKey    Consumer key
     * @param consumerSecret Consumer secret
     * @param token          Token
     * @param tokenSecret    Token secret
     */
    public void initializeAuth(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        this.service =
                new ServiceBuilder().provider(TwoStepOAuth.class).apiKey(consumerKey)
                        .apiSecret(consumerSecret).build();
        this.accessToken = new Token(token, tokenSecret);

        // Create complete oauth request
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + SEARCH_PATH);
        request.addQuerystringParameter("term", "Starbucks");
        request.addQuerystringParameter("location", "Seattle,WA");
        request.addQuerystringParameter("limit", String.valueOf(3));

        this.service.signRequest(this.accessToken, request);

        String oauth_signature_method = (String) request.getOauthParameters().values().toArray()[0];
        String oauth_consumer_key = (String) request.getOauthParameters().values().toArray()[1];
        String oauth_version = (String) request.getOauthParameters().values().toArray()[2];
        String oauth_timestamp = (String) request.getOauthParameters().values().toArray()[3];
        String oauth_nonce = (String) request.getOauthParameters().values().toArray()[4];
        String oauth_token = (String) request.getOauthParameters().values().toArray()[5];
        String oauth_signature = (String) request.getOauthParameters().values().toArray()[6];

        request.addQuerystringParameter("oauth_signature_method", oauth_signature_method);
        request.addQuerystringParameter("oauth_consumer_key", oauth_consumer_key);
        request.addQuerystringParameter("oauth_version", oauth_version);
        request.addQuerystringParameter("oauth_timestamp", oauth_timestamp);
        request.addQuerystringParameter("oauth_nonce", oauth_nonce);
        request.addQuerystringParameter("oauth_token", oauth_token);
        request.addQuerystringParameter("oauth_signature", oauth_signature);

        String s = null;
        try {
            s = URLDecoder.decode((String) request.getOauthParameters().values().toArray()[6], "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        yelpAuth = new YelpAuth();

        yelpAuth.setOauth_signature_method(oauth_signature_method);
        yelpAuth.setOauth_consumer_key(oauth_consumer_key);
        yelpAuth.setOauth_version(oauth_version);
        yelpAuth.setOauth_timestamp(oauth_timestamp);
        yelpAuth.setOauth_nonce(oauth_nonce);
        yelpAuth.setOauth_token(oauth_token);
        yelpAuth.setOauth_signature(oauth_signature);

        bundle = new Bundle();
        bundle.putParcelable(YelpAuth.YELP_AUTH, yelpAuth);

        setAuth(bundle);

        Log.d(TAG, "Auth Signature = " + s);

    }

    private void setAuth(Bundle bundle){
        this.bundle = bundle;
    }

    public Bundle getAuthFromBundle(){
        return bundle;
    }

}
