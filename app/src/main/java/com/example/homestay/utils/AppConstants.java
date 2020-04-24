/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.homestay.utils;

/**
 * Created by amitshekhar on 08/01/17.
 */

public final class AppConstants {
    public static final String IP_ADDRESS = "http://10.1.60.76:3030/homestay/v1/api";
//    public static final String IP_ADDRESS = "http://12.22.88.101:3030/homestay/v1/api";
//    public static final String IP_ADDRESS = "http://192.168.1.10:3030/homestay/v1/api";

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "homestay_mvp.db";
    public static final String PREF_NAME = "homestay_pref";

    public static final int NULL_INDEX = -1;

    public static final String SEED_DATABASE_OPTIONS = "seed/options.json";
    public static final String SEED_DATABASE_QUESTIONS = "seed/questions.json";

    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";

    public static final String ENTIRE_HOUSE = "NHÀ RIÊNG";
    public static final String CONDOMINIUM = "CĂN HỘ CHUNG CƯ";
    public static final String SERVICE_APARTMENT = "CĂN HỘ DỊCH VỤ";
    public static final String STUDIO_APARTMENT = "CĂN HỘ STUDIO";
    public static final String OTHER = "KHÁC";

    public static final String TAG_LIST_HOUSE_TYPE = "list house type";
    public static final String TAG_TOPIC_ITEM = "topic item";
    public static final String TAG_CITY = "city";
    public static final String TAG_LIST_HOUSE_ID = "list house id";
    public static final String TAG_LIST_HOUSE_PHOTO = "list house photo";
    public static final String TAG_LIST_HOUSE_TITLE = "list house title";
    public static final String TAG_LIST_HOUSE_SUB_TITLE = "list house sub title";



    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
