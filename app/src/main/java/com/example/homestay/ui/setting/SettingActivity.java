package com.example.homestay.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.login.LoginActivity;
import com.example.homestay.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements SettingView{

    @Inject
    SettingPresenter<SettingView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {

    }

    @OnClick(R.id.layout_setting_finish)
    void onFinishIconClick(View view){
        finish();
    }

    @OnClick(R.id.layout_setting_logout)
    void onLogoutClick(View view){
        mPresenter.logoutUser();
    }

    @Override
    public void logoutSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
