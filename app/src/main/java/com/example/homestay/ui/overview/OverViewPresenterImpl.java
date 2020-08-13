package com.example.homestay.ui.overview;

import com.example.homestay.data.DataManager;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@PerActivity
public class OverViewPresenterImpl<V extends OverViewView> extends BasePresenter<V> implements OverViewPresenter<V> {

    @Inject
    public OverViewPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getData(String houseId) {
        Integer userId = getDataManager().getCurrentUserId();
        String userIdStr = userId != null ? String.valueOf(userId) : "0";
        getCompositeDisposable().add(getDataManager().doServerApiGetHouseDataCall(houseId, userIdStr)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().showHouse(response);
                }, throwable -> {

                }));
    }
}
