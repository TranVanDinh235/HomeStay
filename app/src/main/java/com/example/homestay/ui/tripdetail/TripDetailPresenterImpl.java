package com.example.homestay.ui.tripdetail;

import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.request.BookingBody;
import com.example.homestay.data.network.request.UnBookingBody;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.ui.trips.TripsPresenter;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

@PerActivity
public class TripDetailPresenterImpl<V extends TripDetailView> extends BasePresenter<V> implements TripDetailPresenter<V> {

    @Inject
    public TripDetailPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void unBooking(String bookingId, List<Long> dateList, String houseId) {
        try {
            UnBookingBody data = new UnBookingBody(bookingId, dateList, houseId);
            String json = new Gson().toJson(data);
            JSONObject body = new JSONObject(json);
            getCompositeDisposable().add(getDataManager().doServerApiUnBookingCall(body)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(response -> {
                        getView().onUnBookingSuccess();
                    }, Throwable::printStackTrace));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
