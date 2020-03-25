package com.example.homestay.ui.message;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface MessagePresenter<V extends MvpView> extends Presenter<V> {
}