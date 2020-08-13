package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class User {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("user_name")
    private String name;

    @Expose
    @SerializedName("pic_url")
    private String pic;

    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("birth_day")
    private Date birthDay;

    @Expose
    @SerializedName("facebook")
    private String facebook;

    @Expose
    @SerializedName("address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
