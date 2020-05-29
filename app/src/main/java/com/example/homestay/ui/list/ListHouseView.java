package com.example.homestay.ui.list;

import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface ListHouseView extends MvpView {
    void showListHouse(ListHouseResponse response);
}
