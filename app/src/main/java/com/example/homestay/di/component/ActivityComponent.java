package com.example.homestay.di.component;

import com.example.homestay.di.PerActivity;
import com.example.homestay.di.module.ActivityModule;
import com.example.homestay.ui.discover.DiscoverFragment;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.ui.marked.MarkedFragment;
import com.example.homestay.ui.message.MessageFragment;
import com.example.homestay.ui.more.MoreFragement;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(DiscoverFragment discoverFragment);

    void inject(MarkedFragment markedFragment);

    void inject(MessageFragment messageFragment);

    void inject(MoreFragement moreFragement);

    void inject(MainActivity mainActivity);


}
