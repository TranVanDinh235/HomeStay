package com.example.homestay;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.ApiHeader;
import com.example.homestay.data.network.TokenAuthenticator;
import com.example.homestay.di.component.ApplicationComponent;
import com.example.homestay.di.component.DaggerApplicationComponent;
import com.example.homestay.di.module.ApplicationModule;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;


public class App extends Application {
    @Inject
    DataManager mDataManager;

    @Inject
    ApiHeader mApiHeader;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);

//        AppLogger.init();
        OkHttpClient okClient = new OkHttpClient.Builder()
                .authenticator(new TokenAuthenticator(mDataManager, mApiHeader))
                .dispatcher(dispatcher)
                .build();

        AndroidNetworking.initialize(getApplicationContext(), okClient);
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
