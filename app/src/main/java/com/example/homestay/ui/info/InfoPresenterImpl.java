package com.example.homestay.ui.info;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class InfoPresenterImpl<V extends InfoView> extends BasePresenter<V> implements InfoPresenter<V> {

    @Inject
    public InfoPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getUserInfo() {
        Log.e("tag", "refresh_token: "+getDataManager().getRefreshToken());
        Log.e("tag", "access_token: "+getDataManager().getAccessToken());
        String userId = String.valueOf(getDataManager().getCurrentUserId());
        getCompositeDisposable().add(getDataManager()
                .doServerApiGetUserInfoCall(userId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(authResponse -> {
                }, throwable -> {
                    getView().hideLoading();
                    throwable.printStackTrace();
                }));
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void handleApiError(ANError error) {
        super.handleApiError(error);
    }
}
