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

    // update firebase token
    public static final String ENDPOINT_UPDATE_TOKEN = AppConstants.IP_ADDRESS
            + "/auth/firebase-token";

    // profile
    public static final String ENDPOINT_USER_INFO = AppConstants.IP_ADDRESS
            + "/user/{id}";

    // list house
    public static final String ENDPOINT_TOPIC_ITEM_DATA = AppConstants.IP_ADDRESS
            + "/house/topic-item/{id}";

    // favorites house
    public static final String ENDPOINT_FAVORITES_HOUSE_DATA = AppConstants.IP_ADDRESS
            + "/house/favorites/{id}";

    //house
    public static final String ENDPOINT_HOUSE_DATA = AppConstants.IP_ADDRESS
            + "/house/{id}";

    //search
    public static final String ENDPOINT_SEARCH_HOUSE_DATA = AppConstants.IP_ADDRESS
            + "/search/house";

    public static final String ENDPOINT_SEARCH_HOST_DATA = AppConstants.IP_ADDRESS
            + "/search/host";

    public static final String ENDPOINT_SEARCH_ADDRESS_DATA = AppConstants.IP_ADDRESS
            + "/search/address";

    // trips
    public static final String ENDPOINT_TRIPS_UPCOMING_DATA = AppConstants.IP_ADDRESS
            + "/trips/upcoming/{id}";

    public static final String ENDPOINT_TRIPS_FINISH_DATA = AppConstants.IP_ADDRESS
            + "/trips/finish/{id}";

    // booking
    public static final String ENDPOINT_BOOKING = AppConstants.IP_ADDRESS
            + "/house/booking";

    public static final String ENDPOINT_UN_BOOKING = AppConstants.IP_ADDRESS
            + "/house/unbooking";
}
