package com.example.homestay.ui.profile;

import com.example.homestay.data.network.entity.User;
import com.example.homestay.ui.base.MvpView;

public interface ProfileView extends MvpView {
    void loadData(User user);
}
