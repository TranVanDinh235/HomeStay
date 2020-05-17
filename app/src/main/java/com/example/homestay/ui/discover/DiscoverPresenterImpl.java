package com.example.homestay.ui.discover;

import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class DiscoverPresenterImpl<V extends DiscoverView> extends BasePresenter<V>
        implements DiscoverPresenter<V> {

    private List<CalendarDay> mSelectedDates = null;

    @Inject
    public DiscoverPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public List<CalendarDay> getSelectedDate() {
        return mSelectedDates;
    }

    @Override
    public void setSelectedDate(List<CalendarDay> selectedDates) {
        this.mSelectedDates = selectedDates;
    }

    @Override
    public void onViewPrepared() {

    }

    @Override
    public void loadTopic() {
        getView().showLoading();

        getCompositeDisposable().add(getDataManager().doServerApiGetTopicCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().hideLoading();
                    getView().showTopic(response);
                }, throwable -> {

                }));
    }

    @Override
    public void loadCity() {
        getView().showLoading();

        getCompositeDisposable().add(getDataManager().doServerApiGetCityCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getView().hideLoading();
                    getView().showCity(response);
                }, throwable -> {

                }));
    }
}
