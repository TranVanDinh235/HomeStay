package com.example.homestay.ui.collection;

import com.androidnetworking.error.ANError;
import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class CollectionPresenterImpl<V extends CollectionView> extends BasePresenter<V>
        implements CollectionPresenter<V> {

    @Inject
    public CollectionPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public boolean isUserLoggedInMode() {
        return getDataManager().isUserLoggedInMode();
    }

    @Override
    public void getListHouse() {
        getView().showLoading();
        String userId = String.valueOf(getDataManager().getCurrentUserId());
        getCompositeDisposable().add(getDataManager().doServerApiGetHouseByCollection(userId)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().hideLoading();
                    getView().showCollection(response);
                }, throwable -> {

                }));
    }
}
