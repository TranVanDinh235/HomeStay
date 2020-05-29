package com.example.homestay.ui.info;

import android.os.Bundle;


import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.homestay.R;
import com.example.homestay.data.network.entity.User;
import com.example.homestay.ui.base.BaseActivity;
import com.google.android.material.appbar.AppBarLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoActivity extends BaseActivity implements InfoView{

    @Inject
    InfoPresenter<InfoView> mPresenter;

    @BindView(R.id.layout_info_appbar)
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(InfoActivity.this);

        setUp();
    }
    @Override
    protected void setUp() {
        CoordinatorLayout.LayoutParams layoutParam = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        layoutParam.setBehavior(new ToolbarBehavior());

        mPresenter.getUserInfo();
    }

    @Override
    public void loadData(User user) {

    }
}
