package com.example.homestay.ui.house;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.event.SelectedDateEvent;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.review.ReviewAdapter;
import com.example.homestay.utils.CommonUtils;
import com.example.homestay.utils.StringUtils;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HouseActivity extends BaseActivity implements HouseView {

    private static final String EXTRA_HOUSE_ID = "house id";

    @Inject
    HousePresenter<HouseView> mPresenter;

    @Inject
    ReviewAdapter mAdapter;

    @BindView(R.id.layout_house_type_house)
    TextView typeHouseTextView;

    @BindView(R.id.layout_house_title)
    TextView titleTextView;

    @BindView(R.id.layout_house_tab)
    TabLayout tabLayout;

    @BindView(R.id.layout_house_view_pager)
    WrapperViewPager viewPager;

    @BindView(R.id.layout_house_description)
    TextView descriptionTextView;

    @BindView(R.id.layout_house_rating)
    TextView ratingTextView;

    @BindView(R.id.layout_house_rating_container)
    CardView ratingContainerCardView;

    @BindView(R.id.layout_house_rating_bar)
    RatingBar ratingBar;

    @BindView(R.id.layout_house_address)
    TextView addressTextView;

    @BindView(R.id.layout_house_price)
    TextView mPriceTextView;

    @BindView(R.id.layout_house_old_price)
    TextView mOldPriceTextView;

    @BindView(R.id.layout_house_review_rv)
    RecyclerView mReviewRecyclerView;

    @BindView(R.id.layout_house_photos_label)
    TextView mPhotoLabelTextView;

    @BindView(R.id.layout_house_photos_more)
    TextView mPhotoMoreTextView;

    @BindView(R.id.layout_house_review_label)
    TextView mReviewLabelTextView;

    @BindView(R.id.layout_house_review_more)
    TextView mReviewMoreTextView;

    @BindView(R.id.layout_house_like_icon)
    ImageView mLikeImageView;

    @BindView(R.id.layout_house_photo)
    ImageView mPhotoImageView;

    @BindView(R.id.layout_house_back_icon)
    ImageView mBackImageView;

    @BindView(R.id.layout_house_booking)
    ExtendedFloatingActionButton mBookingButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_house);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(HouseActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        String houseId = getIntent().getStringExtra(EXTRA_HOUSE_ID);
        mPresenter.getData(houseId);
    }

    public static Intent getIntentHouseActivity(Context context, String houseId) {
        Intent intent = new Intent(context, HouseActivity.class);
        intent.putExtra(EXTRA_HOUSE_ID, houseId);
        return intent;
    }

    @Override
    public void showHouse(HouseResponse response) {
        House house = response.getHouse();
        if(house.getPhoto() != null && !house.getPhoto().equalsIgnoreCase("")){
            Glide.with(this)
                    .load(house.getPhoto())
                    .asBitmap()
                    .centerCrop()
                    .into(mPhotoImageView);
        }
        if(house.getTime() != null){
            mLikeImageView.setImageResource(R.drawable.ic_heart);
        } else {
            mLikeImageView.setImageResource(R.drawable.ic_heart_);
        }
        typeHouseTextView.setText(CommonUtils.getHouseType(house.getType()));
        titleTextView.setText(StringUtils.cutString(house.getTitle()));
        addressTextView.setText(house.getAddress());
        HouseTabAdapter adapter = new HouseTabAdapter(getSupportFragmentManager() , house);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        descriptionTextView.setText(house.getDescription());
        if(house.getTotalReview() == 0){
            ratingContainerCardView.setVisibility(View.GONE);
        } else {
            ratingTextView.setText(String.valueOf(house.getRating()));
            ratingBar.setRating(house.getRating());
        }

        String reviewLabel = "Bình luận " + "(" + house.getReviews().size() +")";
        mReviewLabelTextView.setText(reviewLabel);

        String photoLabel = "Hình ảnh " + "(" + house.getPhotos().size() +")";
        mReviewLabelTextView.setText(photoLabel);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mReviewRecyclerView.setLayoutManager(layoutManager);
        mReviewRecyclerView.setItemAnimator(new DefaultItemAnimator());
        if(house.getReviews().size() > 2) {
            mReviewRecyclerView.setAdapter(new ReviewAdapter(house.getReviews().subList(0, 2)));
        } else
            mReviewRecyclerView.setAdapter(new ReviewAdapter(house.getReviews()));

        if(house.getPrice() != null) {
            if (house.getPromotion() == 0) {
                mPriceTextView.setText(StringUtils.toRate(house.getPrice()));

                mOldPriceTextView.setVisibility(View.GONE);
                mPriceTextView.setVisibility(View.VISIBLE);
            } else {
                mOldPriceTextView.setText(StringUtils.toRate(house.getPrice()));
                mPriceTextView.setText(StringUtils.toRate(house.getPrice(), house.getPromotion()));

                mOldPriceTextView.setVisibility(View.VISIBLE);
                mPriceTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnClick(R.id.layout_house_back_icon)
    void onClickBackIcon(View view){
        finish();
    }

    @OnClick(R.id.layout_house_like_icon)
    void onClickLikeIcon(View view){

    }

    @OnClick(R.id.layout_house_photos_more)
    void onClickPhotoLabel(View view){

    }

    @OnClick(R.id.layout_house_review_more)
    void onClickReviewLabel(View view){

    }

    @OnClick(R.id.layout_house_booking)
    void onBookingButtonClick(View view){
        CalendarDialog dialogFragment = new CalendarDialog(new ArrayList<>());
        dialogFragment.show(getSupportFragmentManager(), "tag 1");
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(SelectedDateEvent event) {
        new Handler().postDelayed(() -> {
            List<Calendar> calendarList = event.getCalendarList();

        }, 100);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
