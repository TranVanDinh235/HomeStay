package com.example.homestay.data.network;

import com.example.homestay.data.network.request.BookingBody;
import com.example.homestay.data.network.request.LoginBody;
import com.example.homestay.data.network.response.ApiResponse;
import com.example.homestay.data.network.response.AuthResponse;
import com.example.homestay.data.network.response.CityResponse;
import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.data.network.response.SearchResponse;
import com.example.homestay.data.network.response.TokenResponse;
import com.example.homestay.data.network.response.TopicResponse;
import com.example.homestay.data.network.response.UserResponse;
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

    @Override
    public Single<ListHouseResponse> doServerApiGetListHouseTopicItemCall(String topicItemId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_TOPIC_ITEM_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addPathParameter("id", topicItemId)
                .build()
                .getObjectSingle(ListHouseResponse.class);
    }

    @Override
    public Single<ListHouseResponse> doServerApiGetFavoritesHouseItemCall(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_FAVORITES_HOUSE_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addHeaders("x-access-token", mApiHeader.getProtectedApiHeader().getAccessToken())
                .addPathParameter("id", userId)
                .build()
                .getObjectSingle(ListHouseResponse.class);
    }

    @Override
    public Single<ListHouseResponse> doServerApiGetListHouseSearchCall(String searchStr) {
        return null;
    }

    @Override
    public Single<HouseResponse> doServerApiGetHouseDataCall(String houseId, String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_HOUSE_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addPathParameter("id", houseId)
                .addQueryParameter("user_id", userId)
                .build()
                .getObjectSingle(HouseResponse.class);
    }

    @Override
    public Single<SearchResponse> doServerApiSearchHouseDataCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SEARCH_HOUSE_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(SearchResponse.class);
    }

    @Override
    public Single<SearchResponse> doServerApiSearchHostDataCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SEARCH_HOST_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(SearchResponse.class);
    }

    @Override
    public Single<SearchResponse> doServerApiSearchAddressDataCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SEARCH_ADDRESS_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(SearchResponse.class);
    }

    @Override
    public Single<ListHouseResponse> doServerApiTripsUpcomingDataCall(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_TRIPS_UPCOMING_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addPathParameter("id", userId)
                .build()
                .getObjectSingle(ListHouseResponse.class);
    }

    @Override
    public Single<ListHouseResponse> doServerApiTripsFinishDataCall(String userId) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_TRIPS_FINISH_DATA)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addPathParameter("id", userId)
                .build()
                .getObjectSingle(ListHouseResponse.class);
    }

    @Override
    public Single<ApiResponse> doServerApiBookingCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_BOOKING)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(ApiResponse.class);
    }

    @Override
    public Single<ApiResponse> doServerApiUnBookingCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UN_BOOKING)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(ApiResponse.class);
    }

    @Override
    public Single<AuthResponse> doServerApiUpdateFirebaseTokenCall(JSONObject body) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_UPDATE_TOKEN)
                .addHeaders("Content-Type", "application/json")
                .addHeaders("Accept", "application/json")
                .addJSONObjectBody(body)
                .build()
                .getObjectSingle(AuthResponse.class);
    }


}
