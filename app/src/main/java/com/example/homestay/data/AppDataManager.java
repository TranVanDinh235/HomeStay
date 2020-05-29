package com.example.homestay.data;

import android.content.Context;

import com.example.homestay.data.db.DbHelper;
import com.example.homestay.data.network.ApiHeader;
import com.example.homestay.data.network.ApiHelper;
import com.example.homestay.data.network.response.AuthResponse;
import com.example.homestay.data.network.response.CityResponse;
import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.data.network.response.TopicResponse;
import com.example.homestay.data.network.response.UserResponse;
import com.example.homestay.data.prefs.PrefHelper;
import com.example.homestay.di.ApplicationContext;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager{

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PrefHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PrefHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
//        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }

    @Override
    public String getRefreshToken() {
        return mPreferencesHelper.getRefreshToken();
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mPreferencesHelper.setRefreshToken(refreshToken);
    }

    @Override
    public String getCurrentUsername() {
        return mPreferencesHelper.getCurrentUsername();
    }

    @Override
    public void setCurrentUsername(String username) {
        mPreferencesHelper.setCurrentUsername(username);
    }

    @Override
    public String getCurrentUserEmail() {
        return mPreferencesHelper.getCurrentUserEmail();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPreferencesHelper.setCurrentUserEmail(email);
    }

    @Override
    public Boolean isUserLoggedInMode() {
        return mPreferencesHelper.isUserLoggedInMode();
    }

    @Override
    public void setUserLoggedInMode(Boolean isLogged) {
        mPreferencesHelper.setUserLoggedInMode(isLogged);
    }

    @Override
    public Integer getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Integer userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPreferencesHelper.getCurrentUserProfilePicUrl();
    }

    @Override
    public void setCurrentUserProfilePicUrl(String url) {
        mPreferencesHelper.setCurrentUserProfilePicUrl(url);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<TopicResponse> doServerApiGetTopicCall() {
        return mApiHelper.doServerApiGetTopicCall();
    }

    @Override
    public Single<CityResponse> doServerApiGetCityCall() {
        return mApiHelper.doServerApiGetCityCall();
    }

    @Override
    public Single<AuthResponse> doServerApiLoginNativeCall(JSONObject body) {
        return mApiHelper.doServerApiLoginNativeCall(body);
    }

    @Override
    public Single<AuthResponse> doServerApiLoginGoogleCall(JSONObject body) {
        return mApiHelper.doServerApiLoginGoogleCall(body);
    }

    @Override
    public Single<AuthResponse> doServerApiLoginFacebookCall(JSONObject body) {
        return mApiHelper.doServerApiLoginFacebookCall(body);
    }

    @Override
    public Single<UserResponse> doServerApiGetUserInfoCall(String userId) {
        return mApiHelper.doServerApiGetUserInfoCall(userId);
    }

    @Override
    public Single<ListHouseResponse> doServerApiGetListHouseTopicItemCall(String topicItemId) {
        return mApiHelper.doServerApiGetListHouseTopicItemCall(topicItemId);
    }

    @Override
    public Single<ListHouseResponse> doServerApiGetListHouseSearchCall(String searchStr) {
        return null;
    }
}
