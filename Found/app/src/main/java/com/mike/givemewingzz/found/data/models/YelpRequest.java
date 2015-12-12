package com.mike.givemewingzz.found.data.models;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

/**
 * Created by GiveMeWingzz on 12/11/2015.
 */
public class YelpRequest extends OAuthRequest {

    public YelpRequest(Verb verb, String url) {
        super(verb, url);
    }

}
