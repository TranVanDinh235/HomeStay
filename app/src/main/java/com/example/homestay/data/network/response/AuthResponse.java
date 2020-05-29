package com.example.homestay.data.network.response;

import com.example.homestay.data.network.entity.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AuthResponse extends ApiResponse{

    @Expose
    @SerializedName("user")
    private User user;

    @Expose
    @SerializedName("access_token")
    private String accessToken;

    @Expose
    @SerializedName("refresh_token")
    private String refreshToken;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
