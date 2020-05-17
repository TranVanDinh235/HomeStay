package com.example.homestay.data;

import android.content.Context;

import com.example.homestay.data.cache.CacheHelper;
import com.example.homestay.data.db.DbHelper;
import com.example.homestay.data.network.ApiHeader;
import com.example.homestay.data.network.ApiHelper;
import com.example.homestay.data.network.model.AuthResponse;
import com.example.homestay.data.network.model.CityResponse;
import com.example.homestay.data.network.model.HouseListResponse;
import com.example.homestay.data.network.model.HouseResponse;
import com.example.homestay.data.network.model.TopicResponse;
import com.example.homestay.data.network.model.UserResponse;
import com.example.homestay.data.prefs.PrefHelper;
import com.example.homestay.di.ApplicationContext;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDataManager implements DataManager{

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PrefHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;
    private final CacheHelper mCacheHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PrefHelper preferencesHelper,
                          ApiHelper apiHelper,
                          CacheHelper cacheHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mCacheHelper = cacheHelper;
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
    public Single<HouseListResponse> doServerApiGetHouseByTopicItem(String topicItemId) {
        return mApiHelper.doServerApiGetHouseByTopicItem(topicItemId);
    }

    @Override
    public Single<HouseListResponse> doServerApiGetHouseByCollection(String userId) {
        return mApiHelper.doServerApiGetHouseByCollection(userId);
    }

    @Override
    public Single<HouseResponse> doServerApiGetHouse(String houseId, String userId) {
        return mApiHelper.doServerApiGetHouse(houseId, userId);
    }

    @Override
    public List<CalendarDay> getSelectedDates() {
        return mCacheHelper.getSelectedDates();
    }

    @Override
    public void setSelectedDates(List<CalendarDay> selectedDates) {
        mCacheHelper.setSelectedDates(selectedDates);
    }

    @Override
    public void removeSelectedDates() {
        mCacheHelper.removeSelectedDates();
    }
}
