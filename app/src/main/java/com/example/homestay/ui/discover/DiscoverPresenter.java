package com.example.homestay.ui.discover;

import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.List;

@PerActivity
public interface DiscoverPresenter<V extends MvpView> extends Presenter<V> {

    List<CalendarDay> getSelectedDate();

    void setSelectedDate(List<CalendarDay> selectedDates);

    void onViewPrepared();

    void loadTopic();

    void loadCity();
}
