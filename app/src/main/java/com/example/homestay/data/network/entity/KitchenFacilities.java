package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KitchenFacilities {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("house_id")
    private int houseId;

    @Expose
    @SerializedName("electric_stove")
    private int electricStove;

    @Expose
    @SerializedName("microwave")
    private int microwave;

    @Expose
    @SerializedName("fridge")
    private int fridge;

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

    public int getElectricStove() {
        return electricStove;
    }

    public void setElectricStove(int electricStove) {
        this.electricStove = electricStove;
    }

    public int getMicrowave() {
        return microwave;
    }

    public void setMicrowave(int microwave) {
        this.microwave = microwave;
    }

    public int getFridge() {
        return fridge;
    }

    public void setFridge(int fridge) {
        this.fridge = fridge;
    }
}
