package com.example.homestay.ui.filter;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;

import butterknife.ButterKnife;

public class FilterActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        setUp();
    }

    @Override
    protected void setUp() {

    }
}
