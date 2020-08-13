package com.example.homestay.ui.explore;

import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ExplorePresenterImpl<V extends ExploreView> extends BasePresenter<V>
        implements ExplorePresenter<V> {

    @Inject
    public ExplorePresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewPrepared() {

    }

    @Override
    public void loadTopic() {
        getCompositeDisposable().add(getDataManager().doServerApiGetTopicCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().showTopic(response);
                }, throwable -> {

                }));
    }

    @Override
    public void loadCity() {
        getCompositeDisposable().add(getDataManager().doServerApiGetCityCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().showCity(response);
                }, throwable -> {

                }));
    }
}
