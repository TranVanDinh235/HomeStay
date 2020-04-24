package com.example.homestay.ui.discover;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface DiscoverPresenter<V extends MvpView> extends Presenter<V> {
    void onViewPrepared();

    void loadTopic();

    void loadCity();
}
