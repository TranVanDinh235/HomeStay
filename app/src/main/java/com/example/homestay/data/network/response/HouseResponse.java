package com.example.homestay.data.network.response;

import com.example.homestay.data.network.entity.House;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HouseResponse extends ApiResponse {

    @Expose
    @SerializedName("house")
    private House house;

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
