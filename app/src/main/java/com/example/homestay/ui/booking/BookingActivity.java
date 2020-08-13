package com.example.homestay.ui.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.homestay.App;
import com.example.homestay.R;
import com.example.homestay.data.event.AddRoomEvent;
import com.example.homestay.data.event.CloseDialogEvent;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.data.network.entity.Review;
import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.house.GuestDialog;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.utils.AppConstants;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.StringUtils;
import com.facebook.appevents.suggestedevents.ViewOnClickListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingActivity extends BaseActivity implements BookingView{

    @Inject
    BookingPresenter<BookingView> mPresenter;

    @BindView(R.id.layout_booking_photo)
    ImageView mPhotoImageView;

    @BindView(R.id.layout_booking_title_house)
    TextView mTitleTextView;

    @BindView(R.id.layout_booking_address)
    TextView mAddressView;

    @BindView(R.id.layout_booking_rating)
    RatingBar mRatingBar;

    @BindView(R.id.layout_booking_review)
    TextView mReviewTextView;

    @BindView(R.id.layout_booking_date_start)
    TextView mDateStartTextView;

    @BindView(R.id.layout_booking_date_end)
    TextView mDateEndTextView;

    @BindView(R.id.layout_booking_guest)
    TextView mGuestTextView;

    @BindView(R.id.layout_booking_price_one)
    TextView mPriceOneTextView;

    @BindView(R.id.layout_booking_num)
    TextView mNumTextView;

    @BindView(R.id.layout_booking_total)
    TextView mTotalTextView;

    @BindView(R.id.layout_booking_additional)
    TextView mAdditionalTextView;

    @BindView(R.id.layout_booking_promotion)
    TextView mPromotionTextView;

    @BindView(R.id.layout_booking_price_final)
    TextView mFinalTextView;

    @BindView(R.id.layout_booking_btn)
    ExtendedFloatingActionButton mBookingButton;

    private List<Calendar> mCalendarList = new ArrayList<>();

    private int mHouseId = 0;
    private int mAdult = 1;
    private int mChild = 0;
    private int mGuest = 1;
    private int mNumOfDay = 1;
    private int mPromotion = 0;
    private int mCost = 0;

    private House mHouse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }


    @Override
    protected void setUp() {
        if(getIntent() == null) return;
        mHouseId = getIntent().getIntExtra(AppConstants.TAG_DATA_HOUSE_ID, 0);
        mAdult = getIntent().getIntExtra(AppConstants.TAG_DATA_ADULT, 1);
        mChild = getIntent().getIntExtra(AppConstants.TAG_DATA_CHILD, 0);
        mGuest = mAdult + mChild;
        String json = getIntent().getStringExtra(AppConstants.TAG_DATA_DATE);
        Type type = new TypeToken<Calendar[]>() {
        }.getType();
        Calendar[] data = new Gson().fromJson(json, type);
        mCalendarList = Arrays.asList(data);
        mPresenter.getData(String.valueOf(mHouseId));
    }

    void initData(){
        mTitleTextView.setText(StringUtils.cutString(mHouse.getTitle(), 40));
        mAddressView.setText(StringUtils.cutString(mHouse.getAddress(), 40));

        if(mHouse.getPhoto() != null) {
            Glide.with(this)
                    .load(mHouse.getPhoto())
                    .asBitmap()
                    .centerCrop()
                    .into(mPhotoImageView);
        }

        if(mHouse.getTotalReview() > 0){
            String review = " (" + mHouse.getTotalReview() + ")";
            mReviewTextView.setText(review);
            mRatingBar.setRating(mHouse.getRating());
            mReviewTextView.setVisibility(View.VISIBLE);
            mRatingBar.setVisibility(View.VISIBLE);
        } else {
            mReviewTextView.setVisibility(View.GONE);
            mRatingBar.setVisibility(View.GONE);
        }

        String startDate = DateTimeUtils.calendarToString(mCalendarList.get(0));
        mDateStartTextView.setText(startDate);
        String endDate = "- " + DateTimeUtils.calendarToString(mCalendarList.get(mCalendarList.size() - 1));
        mDateEndTextView.setText(endDate);

        String numGuest = mGuest + " khách: " + mAdult + " người lớn - " + mChild + " trẻ em";
        mGuestTextView.setText(numGuest);

        mPriceOneTextView.setText(StringUtils.toRate(mHouse.getPrice()));

        mNumOfDay = mCalendarList.size() - 1;
        String num = mNumOfDay + " " + getString(R.string.night);
        mNumTextView.setText(num);

        int total = Integer.parseInt(mHouse.getPrice()) * mNumOfDay;
        mTotalTextView.setText(StringUtils.toRate(String.valueOf(total)));

        int add = mHouse.getMaxGuests() - mGuest;
        int additionFee = 0;
        if(add > 0){
            additionFee = add * Integer.parseInt(mHouse.getAdditionFee());
        }
        mAdditionalTextView.setText(StringUtils.toRate(String.valueOf(additionFee)));

        mPromotion = mHouse.getPromotion();
        String promotion = mPromotion + "%";
        mPromotionTextView.setText(promotion);

        mCost = (int) (1.0f*((100- mPromotion)/100*(total + additionFee)))/1000*1000;
        mFinalTextView.setText(StringUtils.toRate(String.valueOf(mCost)));
    }

    @OnClick(R.id.layout_booking_btn)
    void onBookingButtonClick(View view){
        if(mCalendarList.size() > 1 && mHouseId != 0) mPresenter.bookingHouse(mCalendarList, mHouseId, mGuest, mCost, mPromotion, mAdult, mChild, mNumOfDay);
    }

    @Override
    public void onBookingSuccess() {
        SuccessDialog dialog = new SuccessDialog(this, getString(R.string.booking_success_message));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void showHouse(HouseResponse response) {
        mHouse = response.getHouse();
        initData();
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(CloseDialogEvent event) {
        new Handler().postDelayed(() -> {
            startActivity(MainActivity.getIntentMainActivity(getApplicationContext(), 0));
        }, 1000);
    }

    @OnClick(R.id.layout_booking_close)
    void onClose(View view){
        finish();
    }
}
