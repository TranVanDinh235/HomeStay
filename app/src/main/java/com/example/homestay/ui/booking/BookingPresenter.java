package com.example.homestay.ui.booking;

import com.example.homestay.data.network.request.BookingBody;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.Presenter;

import java.util.Calendar;
import java.util.List;

@PerActivity
public interface BookingPresenter<V extends BookingView> extends Presenter<V> {
    void bookingHouse(List<Calendar> calendars, int houseId, int numGuest, int cost, int promotion, int adult, int child, int numOfDay);
    void getData(String houseId);
}