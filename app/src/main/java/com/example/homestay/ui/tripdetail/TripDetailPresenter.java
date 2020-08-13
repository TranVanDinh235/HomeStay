package com.example.homestay.ui.tripdetail;

import com.example.homestay.ui.base.Presenter;

import java.util.List;

public interface TripDetailPresenter<V extends TripDetailView> extends Presenter<V> {
    void unBooking(String bookingId, List<Long> mDateList, String houseId);
}
