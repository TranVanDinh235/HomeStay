package com.example.homestay.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homestay.di.ActivityContext;
import com.example.homestay.di.PerActivity;
import com.example.homestay.ui.discover.DiscoverPresenter;
import com.example.homestay.ui.discover.DiscoverPresenterImpl;
import com.example.homestay.ui.discover.DiscoverView;
import com.example.homestay.ui.main.MainPresenter;
import com.example.homestay.ui.main.MainPresenterImpl;
import com.example.homestay.ui.main.MainView;
import com.example.homestay.ui.marked.MarkedPresenter;
import com.example.homestay.ui.marked.MarkedPresenterImpl;
import com.example.homestay.ui.marked.MarkedView;
import com.example.homestay.ui.message.MessagePresenter;
import com.example.homestay.ui.message.MessagePresenterImpl;
import com.example.homestay.ui.message.MessageView;
import com.example.homestay.ui.more.MorePresenter;
import com.example.homestay.ui.more.MorePresenterImpl;
import com.example.homestay.ui.more.MoreView;
import com.example.homestay.utils.rx.AppSchedulerProvider;
import com.example.homestay.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {
    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    DiscoverPresenter<DiscoverView> provideDiscoverPresenter(
            DiscoverPresenterImpl<DiscoverView> presenter) {
        return presenter;
    }

    @Provides
    MarkedPresenter<MarkedView> provideMarkedPresenter(
            MarkedPresenterImpl<MarkedView> presenter) {
        return presenter;
    }

    @Provides
    MessagePresenter<MessageView> provideMessagePresenter(
            MessagePresenterImpl<MessageView> presenter) {
        return presenter;
    }

    @Provides
    MorePresenter<MoreView> provideMorePresenter(
            MorePresenterImpl<MoreView> presenter) {
        return presenter;
    }

    @Provides
    MainPresenter<MainView> provideMainPresenter(
            MainPresenterImpl<MainView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity){
        return new LinearLayoutManager(activity);
    }

}
