package com.example.homestay.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchBody {
    @Expose
    @SerializedName("content")
    private String searchStr;

    public SearchBody() {
    }

    public SearchBody(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
}
