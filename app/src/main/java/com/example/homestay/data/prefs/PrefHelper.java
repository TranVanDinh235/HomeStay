package com.example.homestay.data.prefs;

public interface PrefHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    Long getCurrentUserId();

    void setCurrentUserId(Long userId);
}
