package com.example.homestay.data.network.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QRCode {
    @Expose
    @SerializedName("user_id")
    private String userId;

    @Expose
    @SerializedName("booking_id")
    private String bookingId;

    public QRCode(String userId, String bookingId) {
        this.userId = userId;
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}
