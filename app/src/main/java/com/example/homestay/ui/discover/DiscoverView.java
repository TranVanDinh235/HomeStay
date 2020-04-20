package com.example.homestay.ui.discover;

import com.example.homestay.data.network.model.CityResponse;
import com.example.homestay.data.network.model.TopicResponse;
import com.example.homestay.ui.base.MvpView;

public interface DiscoverView extends MvpView {
    void showCity(CityResponse response);
    void showTopic(TopicResponse response);

}