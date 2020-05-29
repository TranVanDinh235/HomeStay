package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entertainment {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("house_id")
    private int houseId;

    @Expose
    @SerializedName("landscape")
    private int landscape;

    @Expose
    @SerializedName("pool")
    private int pool;

    @Expose
    @SerializedName("golf")
    private int golf;

    @Expose
    @SerializedName("BBQ")
    private int BBQ;

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

    public int getLandscape() {
        return landscape;
    }

    public void setLandscape(int landscape) {
        this.landscape = landscape;
    }

    public int getPool() {
        return pool;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public int getGolf() {
        return golf;
    }

    public void setGolf(int golf) {
        this.golf = golf;
    }

    public int getBBQ() {
        return BBQ;
    }

    public void setBBQ(int BBQ) {
        this.BBQ = BBQ;
    }
}
