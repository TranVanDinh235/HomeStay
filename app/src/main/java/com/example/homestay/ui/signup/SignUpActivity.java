package com.example.homestay.ui.signup;

import android.os.Bundle;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.login.LoginActivity;

import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

//        mPresenter.onAttach(LoginActivity.this);

        setUp();
    }


    @Override
    protected void setUp() {

    }
}
