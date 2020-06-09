package com.example.homestay;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.ApiHeader;
import com.example.homestay.data.network.TokenAuthenticator;
import com.example.homestay.di.component.ApplicationComponent;
import com.example.homestay.di.component.DaggerApplicationComponent;
import com.example.homestay.di.module.ApplicationModule;
import com.google.firebase.FirebaseApp;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

import static com.example.homestay.utils.AppConstants.CHANNEL_ID;


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
        getDeviceTokenFireBase();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    private void getDeviceTokenFireBase() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    getString(R.string.app_name), importance);
            notificationChannel.setShowBadge(true);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        FirebaseApp.initializeApp(getApplicationContext());
    }
}
