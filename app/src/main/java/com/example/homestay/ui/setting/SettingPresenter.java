package com.example.homestay.ui.setting;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface SettingPresenter<V extends MvpView> extends Presenter<V> {
    void logoutUser();
}
