package com.example.homestay.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("password")
    private String password;

    @Expose
    @SerializedName("firebase_token")
    private String firebaseToken;

    @Expose
    @SerializedName("token")
    private String token;

    public LoginBody(String email, String password, String firebaseToken) {
        this.email = email;
        this.password = password;
        this.firebaseToken = firebaseToken;
    }

    public LoginBody(String token, String firebaseToken) {
        this.firebaseToken = firebaseToken;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
