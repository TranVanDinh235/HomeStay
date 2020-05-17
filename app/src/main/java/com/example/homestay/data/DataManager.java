package com.example.homestay.data;

import com.example.homestay.data.cache.CacheHelper;
import com.example.homestay.data.db.DbHelper;
import com.example.homestay.data.network.ApiHelper;
import com.example.homestay.data.prefs.PrefHelper;

public interface DataManager extends DbHelper, ApiHelper, PrefHelper, CacheHelper {
}
