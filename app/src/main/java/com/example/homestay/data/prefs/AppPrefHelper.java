package com.example.homestay.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.homestay.di.ApplicationContext;
import com.example.homestay.di.PreferenceInfo;
import com.example.homestay.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPrefHelper implements PrefHelper {
    private static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
            = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_REFRESH_TOKEN = "PREF_KEY_REFRESH_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPrefHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }

    @Override
    public String getRefreshToken() {
        return mPrefs.getString(PREF_KEY_REFRESH_TOKEN, null);
    }

    @Override
    public void setRefreshToken(String refreshToken) {
        mPrefs.edit().putString(PREF_KEY_REFRESH_TOKEN, refreshToken).apply();
    }

    @Override
    public String getCurrentUsername() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null);
    }

    @Override
    public void setCurrentUsername(String username) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, username).apply();
    }

    @Override
    public String getCurrentUserEmail() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply();
    }

    @Override
    public Boolean isUserLoggedInMode() {
        return mPrefs.getBoolean(PREF_KEY_USER_LOGGED_IN_MODE, false);
    }

    @Override
    public void setUserLoggedInMode(Boolean isLogged) {
        mPrefs.edit().putBoolean(PREF_KEY_USER_LOGGED_IN_MODE, isLogged).apply();
    }

    @Override
    public Integer getCurrentUserId() {
        int userId = mPrefs.getInt(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX);
        return userId == AppConstants.NULL_INDEX ? null : userId;
    }

    @Override
    public void setCurrentUserId(Integer userId) {
        int id = userId == null ? AppConstants.NULL_INDEX : userId;
        mPrefs.edit().putInt(PREF_KEY_CURRENT_USER_ID, id).apply();
    }

    @Override
    public String getCurrentUserProfilePicUrl() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null);
    }

    @Override
    public void setCurrentUserProfilePicUrl(String url) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, url).apply();
    }
}
