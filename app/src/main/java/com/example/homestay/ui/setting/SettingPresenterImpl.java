package com.example.homestay.ui.setting;

import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class SettingPresenterImpl<V extends SettingView> extends BasePresenter<V> implements SettingPresenter<V> {

    @Inject
    public SettingPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void logoutUser() {
        setUserAsLoggedOut();
        getView().logoutSuccess();
    }
}
