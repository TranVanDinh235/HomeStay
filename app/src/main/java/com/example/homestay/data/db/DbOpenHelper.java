package com.example.homestay.data.db;

import android.content.Context;

import com.example.homestay.di.ApplicationContext;
import com.example.homestay.di.DatabaseInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DbOpenHelper {
    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
//        super(context, name);
    }
}
