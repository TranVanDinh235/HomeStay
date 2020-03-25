package com.example.homestay.ui.marked;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface MarkedPresenter<V extends MvpView> extends Presenter<V> {
}
