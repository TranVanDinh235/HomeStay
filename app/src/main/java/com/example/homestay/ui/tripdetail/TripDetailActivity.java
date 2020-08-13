package com.example.homestay.ui.tripdetail;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.aminography.primecalendar.common.operators.Date;
import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.event.CloseDialogEvent;
import com.example.homestay.data.event.ConfirmDialogEvent;
import com.example.homestay.data.network.entity.Booking;
import com.example.homestay.data.network.entity.HouseDate;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.booking.SuccessDialog;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.utils.AppConstants;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripDetailActivity extends BaseActivity implements TripDetailView{

    @BindView(R.id.layout_trip_detail_photo)
    ImageView mPhotoImageView;

    @BindView(R.id.layout_trip_detail_title_house)
    TextView mTitleTextView;

    @BindView(R.id.layout_trip_detail_address)
    TextView mAddressTextView;

    @BindView(R.id.layout_trip_detail_review)
    TextView mReviewTextView;

    @BindView(R.id.layout_trip_detail_rating)
    RatingBar mRatingBar;

    @BindView(R.id.layout_trip_detail_date_start)
    TextView mStartDateTextView;

    @BindView(R.id.layout_trip_detail_date_end)
    TextView mEndDateTextView;

    @BindView(R.id.layout_trip_detail_num_guest)
    TextView mNumGuestTextView;

    @BindView(R.id.layout_trip_detail_price_one)
    TextView mPriceTextView;

    @BindView(R.id.layout_trip_detail_num)
    TextView mNumDateTextView;

    @BindView(R.id.layout_trip_detail_total)
    TextView mTotalTextView;

    @BindView(R.id.layout_trip_detail_additional)
    TextView mAdditionalTextView;

    @BindView(R.id.layout_trip_detail_promotion)
    TextView mPromotionTextView;

    @BindView(R.id.layout_trip_detail_price_final)
    TextView mFinalTextView;

    @Inject
    TripDetailPresenter<TripDetailView> mPresenter;

    private Booking mBooking;

    private List<Long> mDateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(TripDetailActivity.this);

        setUp();
    }

    @Override
    protected void setUp() {
        if(getIntent() == null) return;;
        String json = getIntent().getStringExtra(AppConstants.TAG_DATA_BOOKING);
        Type type = new TypeToken<Booking>() {
        }.getType();
        mBooking = new Gson().fromJson(json, type);
        initData();
    }

    void initData(){
        Glide.with(this)
                .load(mBooking.getPhoto())
                .asBitmap()
                .centerCrop()
                .into(mPhotoImageView);
        mTitleTextView.setText(StringUtils.cutString(mBooking.getTitle(), 30));
        mAddressTextView.setText(StringUtils.cutString(mBooking.getAddress(), 30));
        if (mBooking.getTotalReview() == 0) {
            mRatingBar.setVisibility(View.GONE);
            mReviewTextView.setVisibility(View.GONE);
        } else {
            String review = "(" + mBooking.getTotalReview() + ")";
            mReviewTextView.setText(review);
            mRatingBar.setRating(mBooking.getRating());
            mRatingBar.setVisibility(View.VISIBLE);
            mReviewTextView.setVisibility(View.VISIBLE);
        }

        mDateList = DateTimeUtils.getListHouseDate(mBooking.getStartDate(), mBooking.getEndDate());

        String startDate = DateTimeUtils.houseDateToString(mBooking.getStartDate());
        mStartDateTextView.setText(startDate);
        String endDate = "- " + DateTimeUtils.houseDateToString(mBooking.getEndDate());
        mEndDateTextView.setText(endDate);

        String adult = mBooking.getAdult() + " " + getString(R.string.adult);
        if(mBooking.getChild() == 0){
            mNumGuestTextView.setText(adult);
        } else {
            String numGuest = adult + " - " + mBooking.getChild() + " " + getString(R.string.child);
            mNumGuestTextView.setText(numGuest);
        }

        mPriceTextView.setText(StringUtils.toRate(mBooking.getPrice()));
        String numOfDay = mBooking.getNumOfDay() + " " + getString(R.string.night);
        mNumDateTextView.setText(numOfDay);
        int total = Integer.parseInt(mBooking.getPrice()) * mBooking.getNumOfDay();
        mTotalTextView.setText(StringUtils.toRate(String.valueOf(total)));
        int numGuest = mBooking.getNumGuest();
        int guest = mBooking.getGuests();
        int additionFee = 0;
        if(numGuest > guest){
            additionFee = (numGuest - guest) * Integer.parseInt(mBooking.getAdditionFee());
        }
        mAdditionalTextView.setText(StringUtils.toRate(String.valueOf(additionFee)));
        int promotion = 0;
        if(mBooking.getPromotion() > 0){
            promotion = (total + additionFee) * mBooking.getPromotion();
        }
        mPromotionTextView.setText(StringUtils.toRate(String.valueOf(promotion)));
        mFinalTextView.setText(StringUtils.toRate(mBooking.getCost()));
    }

    @OnClick(R.id.layout_trip_detail_close)
    void onClose(View view){
        finish();
    }

    @OnClick(R.id.layout_trip_detail_unbooking)
    void onUnBookingClick(View view){
        ConfirmDialog dialog = new ConfirmDialog(this, getString(R.string.confirm_message));
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
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
    public void onEvent(ConfirmDialogEvent event) {
        new Handler().postDelayed(() -> {
            mPresenter.unBooking(String.valueOf(mBooking.getId()), mDateList, String.valueOf(mBooking.getHouseId()));
        }, 1000);
    }

    @Override
    public void onUnBookingSuccess() {
        startActivity(MainActivity.getIntentMainActivity(getApplicationContext(), 0));
    }
}
