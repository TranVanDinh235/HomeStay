<<<<<<< HEAD:app/src/main/java/com/example/homestay/ui/finish/FinishPresenterImpl.java
package com.example.homestay.ui.finish;
=======
package com.example.homestay.ui.trips;
>>>>>>> update_code:app/src/main/java/com/example/homestay/ui/trips/TripsPresenterImpl.java

import com.androidnetworking.error.ANError;
import com.example.homestay.data.DataManager;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

<<<<<<< HEAD:app/src/main/java/com/example/homestay/ui/finish/FinishPresenterImpl.java
public class FinishPresenterImpl<V extends FinishView> extends BasePresenter<V>
        implements FinishPresenter<V> {

    @Inject
    public FinishPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
=======
@PerActivity
public class TripsPresenterImpl<V extends TripsView> extends BasePresenter<V> implements TripsPresenter<V> {

    @Inject
    public TripsPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
>>>>>>> update_code:app/src/main/java/com/example/homestay/ui/trips/TripsPresenterImpl.java
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }

    @Override
    public void handleApiError(ANError error) {
        super.handleApiError(error);
    }
}
