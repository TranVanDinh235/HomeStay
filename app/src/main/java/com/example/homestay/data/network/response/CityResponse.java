package com.example.homestay.data.network.response;

import com.example.homestay.data.network.entity.City;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityResponse extends ApiResponse{

    @Expose
    @SerializedName("cities")
    private ArrayList<City> cities;

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }
}
