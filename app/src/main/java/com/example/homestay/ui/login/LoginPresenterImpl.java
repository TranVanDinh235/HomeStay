package com.example.homestay.ui.login;

import com.example.homestay.R;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.network.request.LoginBody;
import com.example.homestay.ui.base.BasePresenter;
import com.example.homestay.utils.CommonUtils;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenterImpl<V extends LoginView> extends BasePresenter<V> implements LoginPresenter<V> {

    @Inject
    public LoginPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnSuccessListener(instanceIdResult -> {
                    String token = instanceIdResult.getToken();
                    getDataManager().setFireBaseToken(token);
                });
    }

    @Override
    public void onLoginClick(String email, String password) {
        String firebaseToken = getDataManager().getFireBaseToken();
        if (firebaseToken == null) return;
        if (email == null || email.isEmpty()) {
            getView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getView().onError(R.string.empty_password);
            return;
        }
        try {
            LoginBody loginBody = new LoginBody(email, password, firebaseToken);
            String json = new Gson().toJson(loginBody);
            JSONObject body = new JSONObject(json);
            getCompositeDisposable().add(getDataManager()
                    .doServerApiLoginGoogleCall(body)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(authResponse -> {
                        int statusCode = authResponse.getStatusCode();
                        if(statusCode == 200){
                            getDataManager().setUserLoggedInMode(true);
                            getDataManager().setAccessToken(authResponse.getAccessToken());
                            getDataManager().setRefreshToken(authResponse.getRefreshToken());
                            getDataManager().setCurrentUserId(authResponse.getUser().getId());
                            getDataManager().setCurrentUserEmail(authResponse.getUser().getEmail());
                            getDataManager().setCurrentUsername(authResponse.getUser().getName());
                            getDataManager().setCurrentUserProfilePicUrl(authResponse.getUser().getPic());

                            getView().openMainActivity();

                        }
                    }, throwable -> {
                        getView().hideLoading();
                        throwable.printStackTrace();
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGoogleLoginClick(String token) {
        String firebaseToken = getDataManager().getFireBaseToken();
        if (firebaseToken == null) return;
        try {
            LoginBody loginBody = new LoginBody(token, firebaseToken);
            String json = new Gson().toJson(loginBody);
            JSONObject body = new JSONObject(json);
            getCompositeDisposable().add(getDataManager()
                    .doServerApiLoginGoogleCall(body)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(authResponse -> {
                        int statusCode = authResponse.getStatusCode();
                        if(statusCode == 200){
                            getDataManager().setUserLoggedInMode(true);
                            getDataManager().setAccessToken(authResponse.getAccessToken());
                            getDataManager().setRefreshToken(authResponse.getRefreshToken());
                            getDataManager().setCurrentUserId(authResponse.getUser().getId());
                            getDataManager().setCurrentUserEmail(authResponse.getUser().getEmail());
                            getDataManager().setCurrentUsername(authResponse.getUser().getName());
                            getDataManager().setCurrentUserProfilePicUrl(authResponse.getUser().getPic());

                            getView().openMainActivity();
                        }
                    }, throwable -> {
                        getView().hideLoading();
                        throwable.printStackTrace();
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFacebookLoginClick(String token) {
        String firebaseToken = getDataManager().getFireBaseToken();
        if (firebaseToken == null) return;
        try {
            LoginBody loginBody = new LoginBody(token, firebaseToken);
            String json = new Gson().toJson(loginBody);
            JSONObject body = new JSONObject(json);
            getCompositeDisposable().add(getDataManager()
                    .doServerApiLoginFacebookCall(body)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .subscribe(authResponse -> {
                        int statusCode = authResponse.getStatusCode();
                        if(statusCode == 200){
                            getDataManager().setUserLoggedInMode(true);
                            getDataManager().setAccessToken(authResponse.getAccessToken());
                            getDataManager().setRefreshToken(authResponse.getRefreshToken());
                            getDataManager().setCurrentUserId(authResponse.getUser().getId());
                            getDataManager().setCurrentUserEmail(authResponse.getUser().getEmail());
                            getDataManager().setCurrentUsername(authResponse.getUser().getName());
                            getDataManager().setCurrentUserProfilePicUrl(authResponse.getUser().getPic());

                            getView().openMainActivity();

                        }
                    }, throwable -> {
                        getView().hideLoading();
                        throwable.printStackTrace();
                    }));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
