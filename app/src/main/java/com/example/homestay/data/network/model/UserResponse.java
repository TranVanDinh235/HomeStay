package com.example.homestay.data.network.model;

import com.example.homestay.data.network.model.entity.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse extends ApiResponse{

    @Expose
    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
