package com.example.homestay.ui.explore;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface ExplorePresenter<V extends MvpView> extends Presenter<V> {
    void onViewPrepared();

    void loadTopic();

    void loadCity();
}
