package com.example.homestay.ui.collection;

import com.example.homestay.data.network.model.HouseListResponse;
import com.example.homestay.ui.base.MvpView;

public interface CollectionView extends MvpView {
    void showCollection(HouseListResponse response);
}
