package com.example.homestay.data.network;

import com.example.homestay.data.network.entity.SearchResult;
import com.example.homestay.data.network.request.BookingBody;
import com.example.homestay.data.network.response.ApiResponse;
import com.example.homestay.data.network.response.AuthResponse;
import com.example.homestay.data.network.response.CityResponse;
import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.data.network.response.SearchResponse;
import com.example.homestay.data.network.response.TokenResponse;
import com.example.homestay.data.network.response.TopicResponse;
import com.example.homestay.data.network.response.UserResponse;

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

    Single<ListHouseResponse> doServerApiGetListHouseTopicItemCall(String topicItemId);

    Single<ListHouseResponse> doServerApiGetFavoritesHouseItemCall(String userId);

    Single<ListHouseResponse> doServerApiGetListHouseSearchCall(String searchStr);

    Single<HouseResponse> doServerApiGetHouseDataCall(String houseId, String userId);

    Single<SearchResponse> doServerApiSearchHouseDataCall(JSONObject body);

    Single<SearchResponse> doServerApiSearchHostDataCall(JSONObject body);

    Single<SearchResponse> doServerApiSearchAddressDataCall(JSONObject body);

    Single<ListHouseResponse> doServerApiTripsUpcomingDataCall(String userId);

    Single<ListHouseResponse> doServerApiTripsFinishDataCall(String userId);

    Single<ApiResponse> doServerApiBookingCall(JSONObject body);

    Single<ApiResponse> doServerApiUnBookingCall(JSONObject body);

    Single<AuthResponse> doServerApiUpdateFirebaseTokenCall(JSONObject body);


}
