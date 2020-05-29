package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Facilities {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("house_id")
    private int houseId;

    @Expose
    @SerializedName("wifi")
    private int wifi;

    @Expose
    @SerializedName("TV")
    private int TV;

    @Expose
    @SerializedName("air_condition")
    private int airCondition;

    @Expose
    @SerializedName("washing_machine")
    private int washingMachine;

    @Expose
    @SerializedName("shampoo")
    private int shampoo;

    @Expose
    @SerializedName("toilet_paper")
    private int toiletPaper;

    @Expose
    @SerializedName("mineral_water")
    private int mineralWater;

    @Expose
    @SerializedName("toothpaste")
    private int toothpaste;

    @Expose
    @SerializedName("soap")
    private int soap;

    @Expose
    @SerializedName("towel")
    private int towel;

    @Expose
    @SerializedName("dryer")
    private int dryer;

    @Expose
    @SerializedName("conditioner")
    private int conditioner;

    @Expose
    @SerializedName("shower_gel")
    private int showerGel;

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

    public int getWifi() {
        return wifi;
    }

    public void setWifi(int wifi) {
        this.wifi = wifi;
    }

    public int getTV() {
        return TV;
    }

    public void setTV(int TV) {
        this.TV = TV;
    }

    public int getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(int airCondition) {
        this.airCondition = airCondition;
    }

    public int getWashingMachine() {
        return washingMachine;
    }

    public void setWashingMachine(int washingMachine) {
        this.washingMachine = washingMachine;
    }

    public int getShampoo() {
        return shampoo;
    }

    public void setShampoo(int shampoo) {
        this.shampoo = shampoo;
    }

    public int getToiletPaper() {
        return toiletPaper;
    }

    public void setToiletPaper(int toiletPaper) {
        this.toiletPaper = toiletPaper;
    }

    public int getMineralWater() {
        return mineralWater;
    }

    public void setMineralWater(int mineralWater) {
        this.mineralWater = mineralWater;
    }

    public int getToothpaste() {
        return toothpaste;
    }

    public void setToothpaste(int toothpaste) {
        this.toothpaste = toothpaste;
    }

    public int getSoap() {
        return soap;
    }

    public void setSoap(int soap) {
        this.soap = soap;
    }

    public int getTowel() {
        return towel;
    }

    public void setTowel(int towel) {
        this.towel = towel;
    }

    public int getDryer() {
        return dryer;
    }

    public void setDryer(int dryer) {
        this.dryer = dryer;
    }

    public int getConditioner() {
        return conditioner;
    }

    public void setConditioner(int conditioner) {
        this.conditioner = conditioner;
    }

    public int getShowerGel() {
        return showerGel;
    }

    public void setShowerGel(int showerGel) {
        this.showerGel = showerGel;
    }
}
