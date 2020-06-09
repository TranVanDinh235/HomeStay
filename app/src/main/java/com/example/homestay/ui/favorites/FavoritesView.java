package com.example.homestay.ui.favorites;

import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.ui.base.MvpView;

public interface FavoritesView extends MvpView {
    void showData(ListHouseResponse response);
}
