package com.example.homestay.ui.photo;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.login.LoginActivity;

import butterknife.ButterKnife;

public class PhotoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
