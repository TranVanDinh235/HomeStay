package com.example.homestay.di.component;

import com.example.homestay.di.PerActivity;
import com.example.homestay.di.module.ActivityModule;
import com.example.homestay.ui.collection.CollectionFragment;
import com.example.homestay.ui.discover.DiscoverFragment;
import com.example.homestay.ui.discover.adapter.TopicAdapter;
import com.example.homestay.ui.info.InfoActivity;
import com.example.homestay.ui.list.ListHouseActivity;
import com.example.homestay.ui.login.LoginActivity;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.ui.message.MessageFragment;
import com.example.homestay.ui.profile.ProfileFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(DiscoverFragment discoverFragment);

    void inject(CollectionFragment markedFragment);

    void inject(MessageFragment messageFragment);

    void inject(ProfileFragment profileFragment);

    void inject(MainActivity mainActivity);

    void inject(ListHouseActivity listHouseActivity);

    void inject(LoginActivity loginActivity);

    void inject(InfoActivity infoActivity);

}
