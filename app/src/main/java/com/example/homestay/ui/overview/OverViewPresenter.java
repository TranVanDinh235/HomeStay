package com.example.homestay.ui.overview;

import com.example.homestay.ui.base.Presenter;

public interface OverViewPresenter<V extends OverViewView> extends Presenter<V> {
    void getData(String houseId);
}
