package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Families {

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("house_id")
    private int houseId;

    @Expose
    @SerializedName("no_smoking")
    private int noSmoking;

    @Expose
    @SerializedName("children")
    private int children;

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

    public int getNoSmoking() {
        return noSmoking;
    }

    public void setNoSmoking(int noSmoking) {
        this.noSmoking = noSmoking;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }
}
