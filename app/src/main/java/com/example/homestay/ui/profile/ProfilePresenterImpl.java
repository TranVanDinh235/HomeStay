package com.example.homestay.ui.profile;

import com.androidnetworking.error.ANError;
import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ProfilePresenterImpl<V extends ProfileView> extends BasePresenter<V>
        implements ProfilePresenter<V> {

    @Inject
    public ProfilePresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public boolean isUserLoggedInMode() {
        return getDataManager().isUserLoggedInMode();
    }
}
