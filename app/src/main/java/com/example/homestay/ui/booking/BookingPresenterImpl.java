package com.example.homestay.ui.booking;

import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.request.BookingBody;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class BookingPresenterImpl<V extends BookingView> extends BasePresenter<V> implements BookingPresenter<V> {
    @Inject
    public BookingPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void bookingHouse(List<Calendar> calendars, int houseId, int numGuest, int cost, int promotion, int adult, int child, int numOfDay) {
        String userId = getDataManager().getCurrentUserId() + "";
        try {
            BookingBody data = new BookingBody(userId, String.valueOf(houseId), DateTimeUtils.calendarToHouseDate(calendars), numGuest, cost, promotion, adult, child, numOfDay);
            String json = new Gson().toJson(data);
            JSONObject body = new JSONObject(json);
            getCompositeDisposable().add(getDataManager().doServerApiBookingCall(body)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {
                        getView().onBookingSuccess();
                    }, Throwable::printStackTrace));
        } catch (Exception e){
            e.printStackTrace();
        }
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
