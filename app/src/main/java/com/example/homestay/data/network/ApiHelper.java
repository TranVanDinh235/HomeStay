package com.example.homestay.data.network;

import com.example.homestay.data.network.model.AuthResponse;
import com.example.homestay.data.network.model.CityResponse;
import com.example.homestay.data.network.model.HouseListResponse;
import com.example.homestay.data.network.model.HouseResponse;
import com.example.homestay.data.network.model.TopicResponse;
import com.example.homestay.data.network.model.UserResponse;

import org.json.JSONObject;

import io.reactivex.Single;

public interface ApiHelper {
    ApiHeader getApiHeader();

    Single<TopicResponse> doServerApiGetTopicCall();

    Single<CityResponse> doServerApiGetCityCall();

    Single<AuthResponse> doServerApiLoginNativeCall(JSONObject body);

    Single<AuthResponse> doServerApiLoginGoogleCall(JSONObject body);

    Single<AuthResponse> doServerApiLoginFacebookCall(JSONObject body);

    Single<UserResponse> doServerApiGetUserInfoCall(String userId);

    Single<HouseListResponse> doServerApiGetHouseByTopicItem(String topicItemId);

    Single<HouseListResponse> doServerApiGetHouseByCollection(String userId);

    Single<HouseResponse> doServerApiGetHouse(String houseId, String userId);

}
