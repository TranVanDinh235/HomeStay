package com.example.homestay.data.network;

import com.example.homestay.data.network.model.DiscoverResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper{
    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Single<DiscoverResponse> doServerApiGetDiscoverCall() {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_GET_DISCOVER_DATA)
                .addHeaders(mApiHeader.getPublicApiHeader())
                .build()
                .getObjectSingle(DiscoverResponse.class);
    }


}
