package com.example.homestay.ui.upcoming;

import com.example.homestay.ui.base.Presenter;

public interface UpcomingPresenter<V extends UpcomingView> extends Presenter<V> {
    void getTripsUpcomingData();
}
