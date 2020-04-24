package com.example.homestay.ui.profile;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface ProfilePresenter<V extends MvpView> extends Presenter<V> {
    boolean isUserLoggedInMode();
}
