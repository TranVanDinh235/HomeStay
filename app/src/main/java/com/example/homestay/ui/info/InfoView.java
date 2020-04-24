package com.example.homestay.ui.info;

import com.example.homestay.data.network.model.entity.User;
import com.example.homestay.ui.base.MvpView;

public interface InfoView extends MvpView {
    void loadData(User user);
}
