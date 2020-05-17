package com.example.homestay.data.network;

import com.example.homestay.BuildConfig;
import com.example.homestay.utils.AppConstants;

public final class ApiEndPoint {

    // auth
    public static final String ENDPOINT_REFRESH_TOKEN = AppConstants.IP_ADDRESS
            + "/auth/refresh-token";

    // discover
    public static final String ENDPOINT_DISCOVER_DATA = AppConstants.IP_ADDRESS
            + "/topic/topic-item";
    public static final String ENDPOINT_CITY_DATA = AppConstants.IP_ADDRESS
            + "/city";

    // login
    public static final String ENDPOINT_LOGIN_NATIVE = AppConstants.IP_ADDRESS
            + "/auth/native";
    public static final String ENDPOINT_LOGIN_GOOGLE = AppConstants.IP_ADDRESS
            + "/auth/social/google";
    public static final String ENDPOINT_LOGIN_FACEBOOK = AppConstants.IP_ADDRESS
            + "/auth/social/facebook";

    // profile
    public static final String ENDPOINT_USER_INFO = AppConstants.IP_ADDRESS
            + "/user/{id}";

    public static final String ENDPOINT_TOPIC_ITEM_DATA = AppConstants.IP_ADDRESS
            + "/house/topic-item/{id}";

    // collection
    public static final String ENDPOINT_COLLECTION_DATA = AppConstants.IP_ADDRESS
            + "/house/collection/{id}";

    // house
    public static final String ENDPOINT_HOUSE_DATA = AppConstants.IP_ADDRESS
            + "/house/{id}/{user_id}";
}
