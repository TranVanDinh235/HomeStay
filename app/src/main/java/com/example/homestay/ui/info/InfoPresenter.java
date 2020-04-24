package com.example.homestay.ui.info;

import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

public interface InfoPresenter<V extends InfoView> extends Presenter<V> {
    void getUserInfo();
}
