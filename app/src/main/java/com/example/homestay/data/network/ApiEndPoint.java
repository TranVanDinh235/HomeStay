package com.example.homestay.data.network;

import com.example.homestay.BuildConfig;
import com.example.homestay.utils.AppConstants;

public final class ApiEndPoint {

    public static final String ENDPOINT_DISCOVER_DATA = AppConstants.IP_ADDRESS
            + "/topic/topic-item";
    public static final String ENDPOINT_CITY_DATA = AppConstants.IP_ADDRESS
            + "/city";
    public static final String ENDPOINT_LOGIN_NATIVE = AppConstants.IP_ADDRESS
            + "/auth/native";
    public static final String ENDPOINT_LOGIN_GOOGLE = AppConstants.IP_ADDRESS
            + "/auth/social/google";
    public static final String ENDPOINT_LOGIN_FACEBOOK = AppConstants.IP_ADDRESS
            + "/auth/social/facebook";
}
