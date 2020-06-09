package com.example.homestay.ui.house;

import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface HouseView extends MvpView {
    void showHouse(HouseResponse response);
}
