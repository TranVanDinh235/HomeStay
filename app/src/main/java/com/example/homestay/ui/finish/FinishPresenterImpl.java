package com.example.homestay.ui.finish;

import com.androidnetworking.error.ANError;
import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class FinishPresenterImpl<V extends FinishView> extends BasePresenter<V>
        implements FinishPresenter<V> {

    @Inject
    public FinishPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getTripsFinishData() {
        if(getDataManager().getCurrentUserId() == null) return;

        getCompositeDisposable().add(getDataManager().doServerApiTripsFinishDataCall(String.valueOf(getDataManager().getCurrentUserId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().showData(response);
                }, throwable -> {

                }));
    }
}
