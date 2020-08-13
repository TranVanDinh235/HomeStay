package com.example.homestay.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UnBookingBody {
    @Expose
    @SerializedName("booking_id")
    private String bookingId;

    @Expose
    @SerializedName("dates")
    private List<Long> dates;

    @Expose
    @SerializedName("house_id")
    private String houseId;

    public UnBookingBody(String bookingId, List<Long> dates, String houseId) {
        this.bookingId = bookingId;
        this.dates = dates;
        this.houseId = houseId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public List<Long> getDates() {
        return dates;
    }

    public void setDates(List<Long> dates) {
        this.dates = dates;
    }
}
