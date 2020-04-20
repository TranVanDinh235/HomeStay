package com.example.homestay.data.network.model;

import com.example.homestay.data.network.model.entity.Topic;
import com.google.android.gms.common.api.Api;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TopicResponse extends ApiResponse {

    @Expose
    @SerializedName("topics")
    private List<Topic> topics;

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
}
