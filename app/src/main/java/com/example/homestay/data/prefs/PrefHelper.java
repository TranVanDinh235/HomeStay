package com.example.homestay.data.prefs;

public interface PrefHelper {

    String getAccessToken();

    void setAccessToken(String accessToken);

    String getRefreshToken();

    void setRefreshToken(String refreshToken);

    String getCurrentUsername();

    void setCurrentUsername(String username);

    String getCurrentUserEmail();

    void setCurrentUserEmail(String email);

    Boolean isUserLoggedInMode();

    void setUserLoggedInMode(Boolean isLogged);

    Integer getCurrentUserId();

    void setCurrentUserId(Integer userId);

    String getCurrentUserProfilePicUrl();

    void setCurrentUserProfilePicUrl(String url);

    String getFireBaseToken();

    void setFireBaseToken(String token);
}
