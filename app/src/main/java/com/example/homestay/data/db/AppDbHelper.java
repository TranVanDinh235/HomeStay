package com.example.homestay.data.db;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDbHelper implements DbHelper{

//    private final DaoSession mDaoSession;

    @Inject
    public AppDbHelper(DbOpenHelper dbOpenHelper) {
//        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

}
