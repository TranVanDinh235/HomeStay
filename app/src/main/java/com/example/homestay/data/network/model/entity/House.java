package com.example.homestay.data.network.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class House {
    @Expose
    @SerializedName("id")
    private int id;

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
    @SerializedName("promotion")
    private int promotion;

    @Expose
    @SerializedName("type")
    private int type;

    @Expose
    @SerializedName("stars")
    private int starts;

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("minimum_stay")
    private int minimumStay;

    @Expose
    @SerializedName("guests")
    private int guests;

    @Expose
    @SerializedName("max_guests")
    private int maxGuests;

    @Expose
    @SerializedName("check_in_start")
    private int checkInStart;

    @Expose
    @SerializedName("check_in_end")
    private int checkInEnd;

    @Expose
    @SerializedName("check_out")
    private int checkOut;

    @Expose
    @SerializedName("instant_booking")
    private int instantBooking;

    @Expose
    @SerializedName("cancellation_policy")
    private int cancellationPolicy;

    @Expose
    @SerializedName("house_rules")
    private String houseRules;

    @Expose
    @SerializedName("area")
    private int area;

    @Expose
    @SerializedName("bedrooms")
    private int bedrooms;

    @Expose
    @SerializedName("bathrooms")
    private int bathrooms;

    @Expose
    @SerializedName("beds")
    private int beds;

    @Expose
    @SerializedName("bed_type")
    private int bedType;

    @Expose
    @SerializedName("house_manual")
    private String houseManual;

    @Expose
    @SerializedName("facilities")
    private String facilities;

    @Expose
    @SerializedName("kitchen_facilities")
    private String kitchenFacilities;

    @Expose
    @SerializedName("room_facilities")
    private String roomFacilities;

    @Expose
    @SerializedName("entertainment")
    private String entertainment;

    @Expose
    @SerializedName("special_facilities")
    private String specialFacilities;

    @Expose
    @SerializedName("families")
    private String families;

    @Expose
    @SerializedName("views")
    private int views;

    @Expose
    @SerializedName("approved")
    private int approved;

    @Expose
    @SerializedName("city_id")
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(int minimumStay) {
        this.minimumStay = minimumStay;
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

    public int getCheckInStart() {
        return checkInStart;
    }

    public void setCheckInStart(int checkInStart) {
        this.checkInStart = checkInStart;
    }

    public int getCheckInEnd() {
        return checkInEnd;
    }

    public void setCheckInEnd(int checkInEnd) {
        this.checkInEnd = checkInEnd;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public int getInstantBooking() {
        return instantBooking;
    }

    public void setInstantBooking(int instantBooking) {
        this.instantBooking = instantBooking;
    }

    public int getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(int cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public String getHouseRules() {
        return houseRules;
    }

    public void setHouseRules(String houseRules) {
        this.houseRules = houseRules;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBedType() {
        return bedType;
    }

    public void setBedType(int bedType) {
        this.bedType = bedType;
    }

    public String getHouseManual() {
        return houseManual;
    }

    public void setHouseManual(String houseManual) {
        this.houseManual = houseManual;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getKitchenFacilities() {
        return kitchenFacilities;
    }

    public void setKitchenFacilities(String kitchenFacilities) {
        this.kitchenFacilities = kitchenFacilities;
    }

    public String getRoomFacilities() {
        return roomFacilities;
    }

    public void setRoomFacilities(String roomFacilities) {
        this.roomFacilities = roomFacilities;
    }

    public String getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(String entertainment) {
        this.entertainment = entertainment;
    }

    public String getSpecialFacilities() {
        return specialFacilities;
    }

    public void setSpecialFacilities(String specialFacilities) {
        this.specialFacilities = specialFacilities;
    }

    public String getFamilies() {
        return families;
    }

    public void setFamilies(String families) {
        this.families = families;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
