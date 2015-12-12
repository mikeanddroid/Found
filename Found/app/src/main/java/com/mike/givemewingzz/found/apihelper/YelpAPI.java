package com.mike.givemewingzz.found.apihelper;

import android.os.Bundle;
import android.util.Log;

import com.mike.givemewingzz.found.data.models.YelpWrapper;
import com.mike.givemewingzz.found.parcelable.YelpAuth;
import com.mike.givemewingzz.found.utils.BaseClient;
import com.mike.givemewingzz.found.utils.BaseRetrofitInterface;
import com.mike.givemewingzz.found.utils.FoundConstants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Code sample for accessing the Yelp API V2.
 * <p/>
 * This program demonstrates the capability of the Yelp API version 2.0 by using the Search API to
 * query for businesses by a search term and location, and the Business API to query additional
 * information about the top result from the search query.
 * <p/>
 * <p/>
 * See <a href="http://www.yelp.com/developers/documentation">Yelp Documentation</a> for more info.
 */
public class YelpAPI {

    private static final String API_HOST = "api.yelp.com";
    private static final String DEFAULT_TERM = "dinner";
    private static final String DEFAULT_LOCATION = "San Francisco, CA";
    private static final String SEARCH_PATH = "/v2/search";

    OAuthService service;
    Token accessToken;

    LinkedHashMap<String, String> map;
    YelpAuth yelpAuth;
    Bundle bundle;


    public YelpAPI() {
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
        setRequest();

    }

    private void setRequest(){
        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        setAuthParams(request);
    }

    private void setAuthParams(OAuthRequest request) {

        this.service.signRequest(this.accessToken, request);

        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        for(int i =0;i<request.getOauthParameters().size();i++){
            Log.d("Response val",""+request.getOauthParameters().values().toArray()[i]);
        }

        String oauth_signature_method = (String)request.getOauthParameters().values().toArray()[0];
        String oauth_consumer_key = (String)request.getOauthParameters().values().toArray()[1];
        String oauth_version = (String)request.getOauthParameters().values().toArray()[2];
        String oauth_timestamp = (String)request.getOauthParameters().values().toArray()[3];
        String oauth_nonce = (String)request.getOauthParameters().values().toArray()[4];
        String oauth_token = (String)request.getOauthParameters().values().toArray()[5];
        String oauth_signature = (String)request.getOauthParameters().values().toArray()[6];

        baseRetrofitInterface.searchParams("Starbucks", "Seattle,WA",

                oauth_consumer_key,
                oauth_token,
                oauth_signature_method,
                oauth_timestamp,
                oauth_nonce,
                oauth_version,
                oauth_signature

                , new Callback<YelpWrapper>() {
                    @Override
                    public void success(YelpWrapper yelpWrapper, retrofit.client.Response response) {
                        Log.d("Response", response.getBody().toString());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("Response Error", "Error : " + error.getMessage());
                    }
                });

        yelpAuth = new YelpAuth();

        for(Map.Entry<String, String> mapEntry : request.getOauthParameters().entrySet()){
            Log.d("Request Auth","Key : " + mapEntry.getKey() + ": Value : "+ mapEntry.getValue());
            map.put(mapEntry.getKey(), mapEntry.getValue());
        }

        yelpAuth.setOauth_signature_method(map.get(FoundConstants.OAUTH_SIGNATURE_METHOD));
        yelpAuth.setOauth_consumer_key(map.get(FoundConstants.OAUTH_CONSUMER_KEY));
        yelpAuth.setOauth_version(map.get(FoundConstants.OAUTH_VERSION));
        yelpAuth.setOauth_timestamp(map.get(FoundConstants.OAUTH_TIMESTAMP));
        yelpAuth.setOauth_nonce(map.get(FoundConstants.OAUTH_NONCE));
        yelpAuth.setOauth_token(map.get(FoundConstants.OAUTH_TOKEN));
        yelpAuth.setOauth_signature(map.get(FoundConstants.OAUTH_SIGNATURE));

        bundle = new Bundle();
        bundle.putParcelable(YelpAuth.YELP_AUTH, yelpAuth);
        setAuth(bundle);

    }

    /**
     * Creates and sends a request to the Search API by term and location.
     * <p/>
     * See <a href="http://www.yelp.com/developers/documentation/v2/search_api">Yelp Search API V2</a>
     * for more info.
     *
     * @param term     <tt>String</tt> of the search term to be queried
     * @param location <tt>String</tt> of the location
     * @return <tt>String</tt> JSON Response
     */
    public String searchResultsByQuery(String term, String location, int SEARCH_LIMIT) {

        OAuthRequest request = createOAuthRequest(SEARCH_PATH);
        request.addQuerystringParameter("term", term);
        request.addQuerystringParameter("location", location);
        request.addQuerystringParameter("limit", String.valueOf(SEARCH_LIMIT));

        String requestAndGet = sRAGR(request);
        Log.i("Response Body : ", requestAndGet);
        return requestAndGet;

    }

    private String sRAGR(OAuthRequest request){

        this.service.signRequest(this.accessToken, request);
        Response response = request.send();

        Log.d("Response RAR URL:: ", request.getUrl());

        String responseBody = response.getBody();
        return responseBody;

    }

    /**
     * Creates and returns an {@link OAuthRequest} based on the API endpoint specified.
     *
     * @param path API endpoint to be queried
     * @return <tt>OAuthRequest</tt>
     */
    private OAuthRequest createOAuthRequest(String path) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
        Log.d("OAuthRequest URL : > ", request.getUrl());
        return request;
    }

    /**
     * Sends an {@link OAuthRequest} and returns the {@link Response} body.
     *
     * @param request {@link OAuthRequest} corresponding to the API request
     * @return <tt>String</tt> body of API response
     */
    private String sendRequestAndGetResponse(OAuthRequest request) {
        System.out.println("Response Querying " + request.getCompleteUrl());
        this.service.signRequest(this.accessToken, request);

        for (Map.Entry<String, String> e : request.getHeaders().entrySet()) {
            String key = e.getKey();
            Object value = e.getValue();

            String[] splitValues = ((String) value).split(",");

            for (String values : splitValues) {
                String[] splitter = values.split("=");
                for (int i = 0; i < splitter.length - 1; i++) {
                    // String last = splitter[splitter.length - 1]; OR
                    String hashKey = splitter[i].trim();
                    String val = values.substring(values.lastIndexOf('=') + 2).trim();
                    String hashValue = val.substring(0, val.length() - 1).trim();
                    map.put(hashKey, hashValue);
                }
            }
        }

        yelpAuth = new YelpAuth();

        yelpAuth.setOauth_signature_method(map.get(FoundConstants.OAUTH_SIGNATURE_METHOD));
        yelpAuth.setOauth_consumer_key(map.get(FoundConstants.OAUTH_CONSUMER_KEY));
        yelpAuth.setOauth_version(map.get(FoundConstants.OAUTH_VERSION));
        yelpAuth.setOauth_timestamp(map.get(FoundConstants.OAUTH_TIMESTAMP));
        yelpAuth.setOauth_nonce(map.get(FoundConstants.OAUTH_NONCE));
        yelpAuth.setOauth_token(map.get(FoundConstants.OAUTH_TOKEN));
        yelpAuth.setOauth_signature(map.get(FoundConstants.OAUTH_SIGNATURE));

        bundle = new Bundle();
        bundle.putParcelable(YelpAuth.YELP_AUTH, yelpAuth);

        //setAuth(bundle);

        Response response = request.send();

        Log.d("Response RAR URL:: ", request.getUrl());

        String responseBody = response.getBody();
        return responseBody;
    }

    private void setAuth(Bundle bundle){
        this.bundle = bundle;
    }

    public Bundle getAuthFromBundle(){
        return bundle;
    }

    // No use for now
    public void queryForSearchResults(String term, String location, int SEARCH_LIMIT) {

        String searchResponse = searchResultsByQuery(term, location, SEARCH_LIMIT);

        JSONParser parser = new JSONParser();
        JSONObject response = null;
        try {
            response = (JSONObject) parser.parse(searchResponse);
            Log.d("JSON RESPONSE --> ", response.toJSONString());

        } catch (ParseException pe) {
            System.out.println("Error: could not parse JSON response:");
            System.out.println(searchResponse);
            System.exit(1);
        }

    }

}
