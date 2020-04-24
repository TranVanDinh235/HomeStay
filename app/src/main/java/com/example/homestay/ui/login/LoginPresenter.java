package com.example.homestay.ui.login;

import com.example.homestay.ui.base.MvpView;
import com.example.homestay.ui.base.Presenter;

public interface LoginPresenter<V extends LoginView> extends Presenter<V> {

    void onLoginClick(String email, String password);

    void onGoogleLoginClick(String token);

    void onFacebookLoginClick(String token);
}
