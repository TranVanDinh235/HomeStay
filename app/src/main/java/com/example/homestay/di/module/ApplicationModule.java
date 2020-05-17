package com.example.homestay.di.module;

import android.app.Application;
import android.content.Context;

import com.example.homestay.R;
import com.example.homestay.data.AppDataManager;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.cache.AppCache;
import com.example.homestay.data.cache.CacheHelper;
import com.example.homestay.data.db.AppDbHelper;
import com.example.homestay.data.db.DbHelper;
import com.example.homestay.data.network.ApiHeader;
import com.example.homestay.data.network.ApiHelper;
import com.example.homestay.data.network.AppApiHelper;
import com.example.homestay.data.network.TokenAuthenticator;
import com.example.homestay.data.network.TokenInterceptor;
import com.example.homestay.data.prefs.AppPrefHelper;
import com.example.homestay.data.prefs.PrefHelper;
import com.example.homestay.di.ApiInfo;
import com.example.homestay.di.ApplicationContext;
import com.example.homestay.di.DatabaseInfo;
import com.example.homestay.di.PreferenceInfo;
import com.example.homestay.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return "";
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PrefHelper providePreferencesHelper(AppPrefHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(PrefHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                preferencesHelper.getAccessToken());
    }

    @Provides
    @Singleton
    CacheHelper provideCacheHelper(AppCache appCache) {return appCache;}

    @Provides
    @Singleton
    TokenAuthenticator provideTokenAuthenticator(DataManager dataManager, ApiHeader apiHeader) {return new TokenAuthenticator(dataManager, apiHeader);}
}
