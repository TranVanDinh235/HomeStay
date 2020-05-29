package com.example.homestay.data.network.response;

import com.example.homestay.data.network.entity.Topic;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
