package com.example.homestay.data.network;

import android.util.Log;

import com.example.homestay.di.ApiInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ApiHeader {
    private ProtectedApiHeader mProtectedApiHeader;
    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader, ProtectedApiHeader protectedApiHeader) {
        mPublicApiHeader = publicApiHeader;
        mProtectedApiHeader = protectedApiHeader;
    }

    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }

    public ProtectedApiHeader getProtectedApiHeader() {
        return mProtectedApiHeader;
    }

    public static final class PublicApiHeader {

        @Expose
        @SerializedName("x-access-token")
        private String mApiKey;

        @Inject
        public PublicApiHeader(@ApiInfo String apiKey) {
            mApiKey = apiKey;
        }

        public String getApiKey() {
            return mApiKey;
        }

        public void setApiKey(String apiKey) {
            mApiKey = apiKey;
        }
    }

    public static final class ProtectedApiHeader {

        @Expose
        @SerializedName("x-access-token")
        private String mAccessToken;

        public ProtectedApiHeader(String mAccessToken) {
            this.mAccessToken = mAccessToken;
        }

        public String getAccessToken() {
            return mAccessToken;
        }

        public void setAccessToken(String mAccessToken) {
            this.mAccessToken = mAccessToken;
        }
    }
}
