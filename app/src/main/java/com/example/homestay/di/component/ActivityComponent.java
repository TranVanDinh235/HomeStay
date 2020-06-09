package com.example.homestay.di.component;

import com.example.homestay.di.PerActivity;
import com.example.homestay.di.module.ActivityModule;
import com.example.homestay.ui.favorites.FavoritesFragment;
import com.example.homestay.ui.explore.ExploreFragment;
import com.example.homestay.ui.filter.FilterActivity;
import com.example.homestay.ui.house.HouseActivity;
import com.example.homestay.ui.info.InfoActivity;
import com.example.homestay.ui.list.ListHouseActivity;
import com.example.homestay.ui.login.LoginActivity;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.ui.map.MapActivity;
import com.example.homestay.ui.finish.FinishFragment;
import com.example.homestay.ui.photo.PhotoActivity;
import com.example.homestay.ui.profile.ProfileFragment;
import com.example.homestay.ui.search.SearchActivity;
import com.example.homestay.ui.splash.SplashActivity;
import com.example.homestay.ui.trips.TripsFragment;
import com.example.homestay.ui.upcoming.UpcomingFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(ExploreFragment exploreFragment);

    void inject(ProfileFragment profileFragment);

    void inject(MainActivity mainActivity);

    void inject(ListHouseActivity listHouseActivity);

    void inject(LoginActivity loginActivity);

    void inject(InfoActivity infoActivity);

    void inject(SplashActivity splashActivity);

    void inject(HouseActivity houseActivity);

    void inject(PhotoActivity photoActivity);

    void inject(SearchActivity searchActivity);

    void inject(FilterActivity filterActivity);

    void inject(MapActivity mapActivity);

    void inject(UpcomingFragment upcomingFragment);

    void inject(TripsFragment tripsFragment);

    void inject(FinishFragment finishFragment);

    void inject(FavoritesFragment favoritesFragment);

}
