package com.example.homestay.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homestay.data.network.model.entity.City;
import com.example.homestay.data.network.model.entity.Topic;
import com.example.homestay.di.ActivityContext;
import com.example.homestay.ui.discover.DiscoverPresenter;
import com.example.homestay.ui.discover.DiscoverPresenterImpl;
import com.example.homestay.ui.discover.DiscoverView;
import com.example.homestay.ui.discover.adapter.CityAdapter;
import com.example.homestay.ui.discover.adapter.TopicAdapter;
import com.example.homestay.ui.info.InfoPresenter;
import com.example.homestay.ui.info.InfoPresenterImpl;
import com.example.homestay.ui.info.InfoView;
import com.example.homestay.ui.list.ListHousePresenter;
import com.example.homestay.ui.list.ListHousePresenterImpl;
import com.example.homestay.ui.list.ListHouseView;
import com.example.homestay.ui.login.LogPresenterImpl;
import com.example.homestay.ui.login.LoginPresenter;
import com.example.homestay.ui.login.LoginView;
import com.example.homestay.ui.main.MainPresenter;
import com.example.homestay.ui.main.MainPresenterImpl;
import com.example.homestay.ui.main.MainView;
import com.example.homestay.ui.collection.CollectionPresenter;
import com.example.homestay.ui.collection.CollectionPresenterImpl;
import com.example.homestay.ui.collection.CollectionView;
import com.example.homestay.ui.message.MessagePresenter;
import com.example.homestay.ui.message.MessagePresenterImpl;
import com.example.homestay.ui.message.MessageView;
import com.example.homestay.ui.profile.ProfilePresenter;
import com.example.homestay.ui.profile.ProfilePresenterImpl;
import com.example.homestay.ui.profile.ProfileView;
import com.example.homestay.utils.rx.AppSchedulerProvider;
import com.example.homestay.utils.rx.SchedulerProvider;

import java.util.ArrayList;

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
    CollectionPresenter<CollectionView> provideMarkedPresenter(
            CollectionPresenterImpl<CollectionView> presenter) {
        return presenter;
    }

    @Provides
    MessagePresenter<MessageView> provideMessagePresenter(
            MessagePresenterImpl<MessageView> presenter) {
        return presenter;
    }

    @Provides
    ProfilePresenter<ProfileView> provideMorePresenter(
            ProfilePresenterImpl<ProfileView> presenter) {
        return presenter;
    }

    @Provides
    MainPresenter<MainView> provideMainPresenter(
            MainPresenterImpl<MainView> presenter) {
        return presenter;
    }

    @Provides
    ListHousePresenter<ListHouseView> provideListHousePresenter(
            ListHousePresenterImpl<ListHouseView> presenter) {
        return presenter;
    }

    @Provides
    LoginPresenter<LoginView> provideLoginPresenter(
            LogPresenterImpl<LoginView> presenter) {
        return presenter;
    }

    @Provides
    InfoPresenter<InfoView> provideInfoPresenter(
            InfoPresenterImpl<InfoView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity){
        return new LinearLayoutManager(activity){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
    }

    @Provides
    CityAdapter provideCityAdapter() {
        return new CityAdapter(new ArrayList<City>());
    }

    @Provides
    TopicAdapter provideTopicAdapter() {
        return new TopicAdapter(new ArrayList<Topic>());
    }
}
