package com.example.homestay.data;

import android.content.Context;

import com.example.homestay.data.db.DbHelper;
import com.example.homestay.data.network.ApiHeader;
import com.example.homestay.data.network.ApiHelper;
import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.data.prefs.PrefHelper;
import com.example.homestay.di.ApplicationContext;

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
    public Long getCurrentUserId() {
        return mPreferencesHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserId(Long userId) {
        mPreferencesHelper.setCurrentUserId(userId);
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Single<DiscoverResponse> doServerApiGetDiscoverCall() {
        return mApiHelper.doServerApiGetDiscoverCall();
    }
}
