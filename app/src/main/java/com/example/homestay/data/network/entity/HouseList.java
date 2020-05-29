package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HouseList {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("topic_id")
    private int topicId;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("subtitle")
    private String subtitle;

    @Expose
    @SerializedName("photo")
    private String photo;

    @Expose
    @SerializedName("cover_photo")
    private String coverPhoto;

    @Expose
    @SerializedName("views")
    private int views;

    @Expose
    @SerializedName("houses")
    private List<House> houses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
