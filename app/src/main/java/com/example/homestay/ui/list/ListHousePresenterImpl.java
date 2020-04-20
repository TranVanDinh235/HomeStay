package com.example.homestay.ui.list;

import com.example.homestay.data.DataManager;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ListHousePresenterImpl<V extends ListHouseView> extends BasePresenter<V> implements ListHousePresenter<V> {

    @Inject
    public ListHousePresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V view) {

    }

    @Override
    public void onDetach() {

    }
}
