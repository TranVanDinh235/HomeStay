package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {
    @Expose
    @SerializedName("house_id")
    private int houseId;

    @Expose
    @SerializedName("host_id")
    private int hostId;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("photo")
    private String photo;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("price")
    private String price;

    @Expose
    @SerializedName("guests")
    private int guests;

    @Expose
    @SerializedName("max_guests")
    private int maxGuests;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("guest_id")
    private int guestId;

    @Expose
    @SerializedName("state")
    private int state;

    @Expose
    @SerializedName("start_date")
    private int startDate;

    @Expose
    @SerializedName("end_date")
    private int endDate;

    @Expose
    @SerializedName("num_guest")
    private int numGuest;

    @Expose
    @SerializedName("cost")
    private String cost;

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
    @SerializedName("time")
    private int time;

    @Expose
    @SerializedName("total_review")
    private int totalReview;

    @Expose
    @SerializedName("rating")
    private float rating;

    @Expose
    @SerializedName("num_of_day")
    private int numOfDay;

    @Expose
    @SerializedName("addition_fee")
    private String additionFee;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public int getNumGuest() {
        return numGuest;
    }

    public void setNumGuest(int numGuest) {
        this.numGuest = numGuest;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumOfDay() {
        return numOfDay;
    }

    public void setNumOfDay(int numOfDay) {
        this.numOfDay = numOfDay;
    }

    public String getAdditionFee() {
        return additionFee;
    }

    public void setAdditionFee(String additionFee) {
        this.additionFee = additionFee;
    }
}
