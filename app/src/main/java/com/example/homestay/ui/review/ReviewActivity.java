package com.example.homestay.ui.review;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class ReviewActivity extends BaseActivity implements ReviewView {

    @Inject
    ReviewPresenter<ReviewView> mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(ReviewActivity.this);
    }

    @Override
    protected void setUp() {

    }
}
