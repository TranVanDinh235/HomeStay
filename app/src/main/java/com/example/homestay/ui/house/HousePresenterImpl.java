package com.example.homestay.ui.house;

import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class HousePresenterImpl<V extends HouseView> extends BasePresenter<V> implements HousePresenter<V>{

    @Inject
    public HousePresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
