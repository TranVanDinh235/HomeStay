package com.example.homestay.ui.list;

import android.os.Bundle;


import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;

import javax.inject.Inject;

public class ListHouseActivity extends BaseActivity {

    @Inject
    ListHousePresenter<ListHouseView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setUp() {

    }
}
