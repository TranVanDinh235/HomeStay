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
//    public static final String IP_ADDRESS = "http://10.1.60.93:3030/homestay/v1/api";
//    public static final String IP_ADDRESS = "http://10.0.2.2:3030/homestay/v1/api";
//    public static final String IP_ADDRESS = "http://192.168.43.126:3030/homestay/v1/api";
    public static final String IP_ADDRESS = "http://10.1.60.68:3030/homestay/v1/api";
//    public static final String IP_ADDRESS = "http://12.22.88.102:3030/homestay/v1/api";

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

    public static final String FACILITIES = "Tiện nghi";
    public static final String PRICE = "Giá phòng";
    public static final String REVIEW = "Bình luận";
    public static final String DETAIL = "Chi tiết";
    public static final String CANCELLATION_POLICY = "Chính sách huỷ";
    public static final String HOUSE_RULES = "Quy tắc chỗ ở";

    public static final String TAG_LIST_HOUSE_TYPE = "list house type";
    public static final String TAG_TOPIC_ITEM = "topic item";
    public static final String TAG_CITY = "city";
    public static final String TAG_LIST_HOUSE_SUB_TITLE = "list house sub title";
    public static final String TAG_TOPIC_ITEM_ID = "tag topic item id";
    public static final String TAG_SEARCH_STRING = "tag search string";

    public static final String VERY_GOOD = "Rất tốt";
    public static final String GOOD = "Tốt";
    public static final String NORMAL = "Bình thường";
    public static final String BAD = "Khá tệ";

    public static final String UPCOMING = "Đang diễn ra";
    public static final String FINISH = "Kết thúc";
    public static final String FAVORITES = "Yêu thích";
    public static final String TAG_DATA_CALENDAR = "tag data calendar";
    public static final int LAUNCH_BOOKING_ACTIVITY = 22;
    public static final String TAG_DATA_HOUSE_ID = "tag data house id";
    public static final String TAG_DATA_HOUSE = "tag data house";
    public static final int LAUNCH_ACTIVITY_OVERVIEW = 23;
    public static final String TAG_DATA_USER = "tag data user";
    public static final int LAUNCH_FILTER_ACTIVITY = 24;
    public static final int LAUNCH_SIGN_UP_ACTIVITY = 25;
    public static final int OPEN_TAB_NOTIFICATION = 26;
    public static final String CHANNEL_ID = "notify homestay app id";
    public static final String GROUP_KEY_NOTIFICATION = "com.example.homestay.notification_group";
    public static final String TAG_DATA_BOOKING = "tag data booking";
    public static final String TAG_ROOM_DIALOG = "tag room dialog";

    public static final int LAUNCH_ACTIVITY_DATE = 27;
    public static final String TAG_DATA_DATE = "tag extra date";
    public static final String TAG_DATA_ADULT = "tag data adult";
    public static final String TAG_DATA_CHILD = "tag data child";

    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
