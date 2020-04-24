package com.example.homestay.data.network;

import android.util.Log;

import com.example.homestay.data.network.model.AuthResponse;
import com.example.homestay.data.network.model.CityResponse;
import com.example.homestay.data.network.model.TopicResponse;
import com.example.homestay.data.network.model.UserResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper{
    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<TopicResponse> doServerApiGetTopicCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_DISCOVER_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .build()
                .getObjectSingle(TopicResponse.class);
    }

    @Override
    public Single<CityResponse> doServerApiGetCityCall() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CITY_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .build()
                .getObjectSingle(CityResponse.class);
    }

    @Override
    public Single<AuthResponse> doServerApiLoginNativeCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN_NATIVE)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(AuthResponse.class);
    }

    @Override
    public Single<AuthResponse> doServerApiLoginGoogleCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN_GOOGLE)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(AuthResponse.class);
    }

    @Override
    public Single<AuthResponse> doServerApiLoginFacebookCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN_FACEBOOK)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(AuthResponse.class);
    }

    @Override
    public Single<UserResponse> doServerApiGetUserInfoCall(String id) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_USER_INFO)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addHeaders("x-access-token", mApiHeader.getProtectedApiHeader().getAccessToken())
                .addPathParameter("id", id)
                .build()
                .getObjectSingle(UserResponse.class);
    }


}
