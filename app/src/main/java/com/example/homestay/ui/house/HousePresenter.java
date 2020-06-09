package com.example.homestay.ui.house;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface HousePresenter<V extends HouseView> extends Presenter<V> {
    void getData(String houseId);
}
