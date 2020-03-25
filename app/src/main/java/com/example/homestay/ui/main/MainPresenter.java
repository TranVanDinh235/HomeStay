package com.example.homestay.ui.main;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

@PerActivity
public interface MainPresenter<V extends MvpView> extends Presenter<V> {

}
