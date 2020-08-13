package com.example.homestay.ui.finish;

import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface FinishView extends MvpView {
    void showData(ListHouseResponse response);
}
