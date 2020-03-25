package com.example.homestay.ui.discover;

import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.model.DiscoverResponse;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class DiscoverPresenterImpl<V extends DiscoverView> extends BasePresenter<V>
        implements DiscoverPresenter<V> {

    @Inject
    public DiscoverPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadData() {
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager().doServerApiGetDiscoverCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getMvpView().hideLoading();
                    getMvpView().showData(response);
                }, throwable -> {

                }));
    }
}
