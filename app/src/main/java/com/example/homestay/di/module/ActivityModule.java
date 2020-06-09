package com.example.homestay.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homestay.data.network.entity.SearchResult;
import com.example.homestay.di.ActivityContext;
import com.example.homestay.ui.explore.ExplorePresenter;
import com.example.homestay.ui.explore.ExplorePresenterImpl;
import com.example.homestay.ui.explore.ExploreView;
import com.example.homestay.ui.explore.adapter.CityAdapter;
import com.example.homestay.ui.explore.adapter.TopicAdapter;
import com.example.homestay.ui.favorites.FavoritesPresenter;
import com.example.homestay.ui.favorites.FavoritesPresenterImpl;
import com.example.homestay.ui.favorites.FavoritesView;
import com.example.homestay.ui.finish.FinishPresenterImpl;
import com.example.homestay.ui.house.HousePresenter;
import com.example.homestay.ui.house.HousePresenterImpl;
import com.example.homestay.ui.house.HouseView;
import com.example.homestay.ui.info.InfoPresenter;
import com.example.homestay.ui.info.InfoPresenterImpl;
import com.example.homestay.ui.info.InfoView;
import com.example.homestay.ui.list.ListHouseAdapter;
import com.example.homestay.ui.list.ListHousePresenter;
import com.example.homestay.ui.list.ListHousePresenterImpl;
import com.example.homestay.ui.list.ListHouseView;
import com.example.homestay.ui.login.LoginPresenterImpl;
import com.example.homestay.ui.login.LoginPresenter;
import com.example.homestay.ui.login.LoginView;
import com.example.homestay.ui.main.MainPresenter;
import com.example.homestay.ui.main.MainPresenterImpl;
import com.example.homestay.ui.main.MainView;
import com.example.homestay.ui.map.MapPresenter;
import com.example.homestay.ui.map.MapPresenterImpl;
import com.example.homestay.ui.map.MapView;
import com.example.homestay.ui.finish.FinishPresenter;
import com.example.homestay.ui.finish.FinishView;
import com.example.homestay.ui.profile.ProfilePresenter;
import com.example.homestay.ui.profile.ProfilePresenterImpl;
import com.example.homestay.ui.profile.ProfileView;
import com.example.homestay.ui.review.ReviewAdapter;
import com.example.homestay.ui.search.SearchBarPresenter;
import com.example.homestay.ui.search.SearchPresenter;
import com.example.homestay.ui.search.SearchPresenterImpl;
import com.example.homestay.ui.search.SearchView;
import com.example.homestay.ui.splash.SplashPresenter;
import com.example.homestay.ui.splash.SplashPresenterImpl;
import com.example.homestay.ui.splash.SplashView;
import com.example.homestay.ui.trips.TripsPresenter;
import com.example.homestay.ui.trips.TripsPresenterImpl;
import com.example.homestay.ui.trips.TripsView;
import com.example.homestay.ui.upcoming.UpcomingAdapter;
import com.example.homestay.ui.upcoming.UpcomingPresenter;
import com.example.homestay.ui.upcoming.UpcomingPresenterImpl;
import com.example.homestay.ui.upcoming.UpcomingView;
import com.example.homestay.utils.rx.AppSchedulerProvider;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.otaliastudios.autocomplete.AutocompletePresenter;

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
    ExplorePresenter<ExploreView> provideDiscoverPresenter(
            ExplorePresenterImpl<ExploreView> presenter) {
        return presenter;
    }

    @Provides
    ProfilePresenter<ProfileView> provideProfilePresenter(
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
            LoginPresenterImpl<LoginView> presenter) {
        return presenter;
    }

    @Provides
    InfoPresenter<InfoView> provideInfoPresenter(
            InfoPresenterImpl<InfoView> presenter) {
        return presenter;
    }

    @Provides
    SplashPresenter<SplashView> provideSplashPresenter(
            SplashPresenterImpl<SplashView> presenter) {
        return presenter;
    }

    @Provides
    HousePresenter<HouseView> provideHousePresenter(
            HousePresenterImpl<HouseView> presenter) {
        return presenter;
    }

    @Provides
    SearchPresenter<SearchView> provideSearchPresenter(
            SearchPresenterImpl<SearchView> presenter) {
        return presenter;
    }

    @Provides
    AutocompletePresenter<SearchResult> provideSearchBarPresenter(
            SearchBarPresenter presenter) {
        return presenter;
    }

    @Provides
    MapPresenter<MapView> provideMapPresenter(
            MapPresenterImpl<MapView> presenter) {
        return presenter;
    }

    @Provides
    TripsPresenter<TripsView> provideTripsPresenter(
            TripsPresenterImpl<TripsView> presenter) {
        return presenter;
    }

    @Provides
    UpcomingPresenter<UpcomingView> provideUpcomingPresenter(
            UpcomingPresenterImpl<UpcomingView> presenter) {
        return presenter;
    }

    @Provides
    FavoritesPresenter<FavoritesView> provideFavoritesPresenter(
            FavoritesPresenterImpl<FavoritesView> presenter) {
        return presenter;
    }

    @Provides
    FinishPresenter<FinishView> provideFinishPresenter(
            FinishPresenterImpl<FinishView> presenter) {
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
        return new CityAdapter(new ArrayList<>());
    }

    @Provides
    TopicAdapter provideTopicAdapter() {
        return new TopicAdapter(new ArrayList<>());
    }

    @Provides
    ListHouseAdapter provideHouseListAdapter() {
        return new ListHouseAdapter(new ArrayList<>());
    }

    @Provides
    ReviewAdapter provideReviewAdapter() {
        return new ReviewAdapter(new ArrayList<>());
    }

    @Provides
    UpcomingAdapter provideUpcomingAdapter() {
        return new UpcomingAdapter(new ArrayList<>());
    }
}
