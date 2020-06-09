package com.example.homestay.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingBody {
    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("house_id")
    private String houseId;

    @Expose
    @SerializedName("dates")
    private List<Long> dates;

    @Expose
    @SerializedName("num_guest")
    private int numGuest;

    @Expose
    @SerializedName("cost")
    private int cost;

    @Expose
    @SerializedName("promotion")
    private int promotion;

    @Expose
    @SerializedName("adult")
    private int adult;

    @Expose
    @SerializedName("child")
    private int child;

    @Expose
    @SerializedName("num_of_day")
    private int numOfDay;

    public BookingBody(String userId, String houseId, List<Long> dates, int numGuest, int cost, int promotion, int adult, int child, int numOfDay) {
        this.userId = userId;
        this.houseId = houseId;
        this.dates = dates;
        this.numGuest = numGuest;
        this.cost = cost;
        this.promotion = promotion;
        this.adult = adult;
        this.child = child;
        this.numOfDay = numOfDay;
    }

    public BookingBody(String userId, String houseId, List<Long> dates) {
        this.userId = userId;
        this.houseId = houseId;
        this.dates = dates;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public List<Long> getDates() {
        return dates;
    }

    public void setDates(List<Long> dates) {
        this.dates = dates;
    }

    public int getNumGuest() {
        return numGuest;
    }

    public void setNumGuest(int numGuest) {
        this.numGuest = numGuest;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getNumOfDay() {
        return numOfDay;
    }

    public void setNumOfDay(int numOfDay) {
        this.numOfDay = numOfDay;
    }
}
