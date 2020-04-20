package com.example.homestay.data.network.model;

import com.example.homestay.data.network.model.entity.City;
import com.example.homestay.data.network.model.entity.Topic;
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
