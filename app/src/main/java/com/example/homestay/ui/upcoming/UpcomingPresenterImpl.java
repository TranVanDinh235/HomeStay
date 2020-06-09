package com.example.homestay.ui.upcoming;

import com.example.homestay.data.DataManager;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@PerActivity
public class UpcomingPresenterImpl<V extends UpcomingView> extends BasePresenter<V> implements UpcomingPresenter<V> {

    @Inject
    public UpcomingPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getTripsUpcomingData() {
        getView().showLoading();
        if(getDataManager().getCurrentUserId() == null) return;

        getCompositeDisposable().add(getDataManager().doServerApiTripsUpcomingDataCall(String.valueOf(getDataManager().getCurrentUserId()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().hideLoading();
                    getView().showData(response);
                }, throwable -> {

                }));
    }
}
