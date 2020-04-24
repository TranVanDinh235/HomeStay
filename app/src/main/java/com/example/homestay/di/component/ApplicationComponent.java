package com.example.homestay.di.component;

import android.app.Application;
import android.content.Context;

import com.example.homestay.App;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.TokenAuthenticator;
import com.example.homestay.data.network.TokenInterceptor;
import com.example.homestay.di.ApplicationContext;
import com.example.homestay.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}