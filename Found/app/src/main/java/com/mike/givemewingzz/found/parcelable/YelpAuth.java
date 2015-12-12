package com.mike.givemewingzz.found.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GiveMeWingzz on 12/11/2015.
 */
public class YelpAuth implements Parcelable{

    public static final String YELP_AUTH = "YELP_AUTH";

    private String oauth_signature_method;
    private String oauth_consumer_key;
    private String oauth_version;
    private String oauth_timestamp;
    private String oauth_nonce;
    private String oauth_token;
    private String oauth_signature;

    public YelpAuth(){

            this.oauth_signature_method = "";
            this.oauth_consumer_key = "";
            this.oauth_version = "";
            this.oauth_timestamp = "";
            this.oauth_nonce = "";
            this.oauth_token = "";
            this.oauth_signature = "";

    }

    protected YelpAuth(Parcel in) {

        oauth_signature_method = in.readString();
        oauth_consumer_key = in.readString();
        oauth_version = in.readString();
        oauth_timestamp = in.readString();
        oauth_nonce = in.readString();
        oauth_token = in.readString();
        oauth_signature = in.readString();

    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(oauth_signature_method);
        dest.writeString(oauth_consumer_key);
        dest.writeString(oauth_version);
        dest.writeString(oauth_timestamp);
        dest.writeString(oauth_nonce);
        dest.writeString(oauth_token);
        dest.writeString(oauth_signature);

    }

    public static final Creator<YelpAuth> CREATOR = new Creator<YelpAuth>() {
        @Override
        public YelpAuth createFromParcel(Parcel in) {
            return new YelpAuth(in);
        }

        @Override
        public YelpAuth[] newArray(int size) {
            return new YelpAuth[0];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    public String getOauth_signature_method() {
        return oauth_signature_method;
    }

    public void setOauth_signature_method(String oauth_signature_method) {
        this.oauth_signature_method = oauth_signature_method;
    }

    public String getOauth_consumer_key() {
        return oauth_consumer_key;
    }

    public void setOauth_consumer_key(String oauth_consumer_key) {
        this.oauth_consumer_key = oauth_consumer_key;
    }

    public String getOauth_version() {
        return oauth_version;
    }

    public void setOauth_version(String oauth_version) {
        this.oauth_version = oauth_version;
    }

    public String getOauth_timestamp() {
        return oauth_timestamp;
    }

    public void setOauth_timestamp(String oauth_timestamp) {
        this.oauth_timestamp = oauth_timestamp;
    }

    public String getOauth_nonce() {
        return oauth_nonce;
    }

    public void setOauth_nonce(String oauth_nonce) {
        this.oauth_nonce = oauth_nonce;
    }

    public String getOauth_token() {
        return oauth_token;
    }

    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getOauth_signature() {
        return oauth_signature;
    }

    public void setOauth_signature(String oauth_signature) {
        this.oauth_signature = oauth_signature;
    }

}
