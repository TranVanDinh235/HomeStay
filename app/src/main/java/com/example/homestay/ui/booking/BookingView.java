package com.example.homestay.ui.booking;

import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface BookingView extends MvpView {
    void onBookingSuccess();
    void showHouse(HouseResponse response);

}
