package com.example.homestay.data.network.response;

import com.example.homestay.data.network.entity.SearchResult;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse extends ApiResponse{
    @Expose
    @SerializedName("data")
    private List<SearchResult> data;

    public List<SearchResult> getData() {
        return data;
    }

    public void setData(List<SearchResult> data) {
        this.data = data;
    }
}
