package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomFacilities {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("house_id")
    private int houseId;

    @Expose
    @SerializedName("window")
    private int window;

    @Expose
    @SerializedName("balcony")
    private int balcony;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public int getBalcony() {
        return balcony;
    }

    public void setBalcony(int balcony) {
        this.balcony = balcony;
    }
}
