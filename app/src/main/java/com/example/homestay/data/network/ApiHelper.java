package com.example.homestay.data.network;

import com.example.homestay.data.network.model.DiscoverResponse;

import io.reactivex.Single;

public interface ApiHelper {
    ApiHeader getApiHeader();

    Single<DiscoverResponse> doServerApiGetDiscoverCall();

}
