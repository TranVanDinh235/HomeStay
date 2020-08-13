package com.example.homestay.ui.overview;

import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface OverViewView extends MvpView {
    void showHouse(HouseResponse response);
}
