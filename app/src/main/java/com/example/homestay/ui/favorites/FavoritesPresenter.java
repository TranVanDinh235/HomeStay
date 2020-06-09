package com.example.homestay.ui.favorites;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface FavoritesPresenter<V extends MvpView> extends Presenter<V> {
    void getFavoritesHouse();
}
