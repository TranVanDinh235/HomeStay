package com.example.homestay.data.network.response;

import com.example.homestay.data.network.entity.HouseList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListHouseResponse extends ApiResponse {
    @Expose
    @SerializedName("house_list")
    private HouseList houseList;

    public HouseList getHouseList() {
        return houseList;
    }

    public void setHouseList(HouseList houseList) {
        this.houseList = houseList;
    }
}
