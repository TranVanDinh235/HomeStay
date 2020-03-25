package com.example.homestay.ui.discover;

import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.ui.base.MvpView;

public interface DiscoverView extends MvpView {
    void showData(DiscoverResponse discoverResponse);

}