package com.example.homestay.data.network.entity;

import com.google.gson.annotations.SerializedName;

public class NotificationContent {
    @SerializedName("notify_id")
    private int notifyID;
    @SerializedName("title")
    private String title;
    @SerializedName("booking_id")
    private int bookingID;
    @SerializedName("content")
    private String content;

    public int getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(int notifyID) {
        this.notifyID = notifyID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
