package com.example.homestay.ui.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.house.HouseActivity;
import com.example.homestay.utils.AppConstants;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OverViewActivity extends BaseActivity implements OverViewView{

    @Inject OverViewPresenter<OverViewView> mPresenter;

    @BindView(R.id.layout_overview_title)
    TextView mTitleTextView;

    @BindView(R.id.layout_overview_address)
    TextView mAddressTextView;

    @BindView(R.id.layout_overview_rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.layout_overview_photo)
    ImageView mPhotoImageView;

    private House mHouse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_overview);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(OverViewActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        String houseId = getIntent().getStringExtra(AppConstants.TAG_DATA_HOUSE_ID);
        mPresenter.getData(houseId);
    }

    @Override
    public void showHouse(HouseResponse response) {
        mHouse = response.getHouse();
        mTitleTextView.setText(mHouse.getTitle());
        mAddressTextView.setText(mHouse.getAddress());
        ratingBar.setRating(mHouse.getRating());
        if(mHouse.getPhoto() != null && !mHouse.getPhoto().equalsIgnoreCase("")){
            Glide.with(this)
                    .load(mHouse.getPhoto())
                    .asBitmap()
                    .centerCrop()
                    .into(mPhotoImageView);
        }
    }

    @OnClick(R.id.layout_overview_more)
    void onMoreClick(View view){
        Intent intent = new Intent(this, HouseActivity.class);
        intent.putExtra(AppConstants.TAG_DATA_HOUSE_ID, String.valueOf(mHouse.getId()));
        startActivity(intent);
    }

    public static Intent getIntentOverviewActivity(Context context, String houseId) {
        Intent intent = new Intent(context, OverViewActivity.class);
        intent.putExtra(AppConstants.TAG_DATA_HOUSE_ID, houseId);
        return intent;
    }
}
