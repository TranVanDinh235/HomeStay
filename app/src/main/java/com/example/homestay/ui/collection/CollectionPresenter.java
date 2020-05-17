package com.example.homestay.ui.collection;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface CollectionPresenter<V extends MvpView> extends Presenter<V> {
    boolean isUserLoggedInMode();

    void getListHouse();
}
