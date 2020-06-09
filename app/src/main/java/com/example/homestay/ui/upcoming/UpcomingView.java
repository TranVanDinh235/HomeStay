package com.example.homestay.ui.upcoming;

import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface UpcomingView extends MvpView {
    void showData(ListHouseResponse response);
}
