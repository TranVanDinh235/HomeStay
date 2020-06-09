package com.example.homestay.ui.house;

import android.app.Activity;
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

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.civil.CivilCalendar;
import com.aminography.primedatepicker.picker.PrimeDatePicker;
import com.aminography.primedatepicker.picker.callback.MultipleDaysPickCallback;
import com.aminography.primedatepicker.picker.callback.RangeDaysPickCallback;
import com.aminography.primedatepicker.picker.theme.DarkThemeFactory;
import com.aminography.primedatepicker.picker.theme.LightThemeFactory;
import com.bumptech.glide.Glide;
import com.example.homestay.R;
import com.example.homestay.data.event.AddGuestEvent;
import com.example.homestay.data.event.AddRoomEvent;
import com.example.homestay.data.event.SelectedDateEvent;
import com.example.homestay.data.network.entity.Booking;
import com.example.homestay.data.network.entity.House;
import com.example.homestay.data.network.response.HouseResponse;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.booking.BookingActivity;
import com.example.homestay.ui.list.RoomDialog;
import com.example.homestay.ui.review.ReviewAdapter;
import com.example.homestay.utils.AppConstants;
import com.example.homestay.utils.CommonUtils;
import com.example.homestay.utils.StringUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kotlin.jvm.functions.Function1;

public class HouseActivity extends BaseActivity implements HouseView, OnMapReadyCallback {

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

    private List<Calendar> selectedDate = new ArrayList<>();

    private House house;

    private GoogleMap mMap;
    private Marker mMarker;

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
        String houseId = getIntent().getStringExtra(AppConstants.TAG_DATA_HOUSE_ID);
        mPresenter.getData(houseId);
        setUpCalendar();
    }

    @Override
    public void showHouse(HouseResponse response) {
        house = response.getHouse();
        initData();
    }

    void setUpCalendar(){

        RangeDaysPickCallback callback = new RangeDaysPickCallback() {
            @Override
            public void onRangeDaysPicked(PrimeCalendar startDay, PrimeCalendar endDay) {

            }
        };

        LightThemeFactory themeFactory = new LightThemeFactory() {
            @Override
            public String getTypefacePath() {
                return "fonts/roboto_regular.ttf";
            }

            @Override
            public int getCalendarViewPickedDayInRangeBackgroundColor() {
                return R.color.colorPrimary;
            }

            @Override
            public int getCalendarViewPickedDayInRangeLabelTextColor() {
                return R.color.gray900;
            }

            @NotNull
            @Override
            public Function1<PrimeCalendar, String> getCalendarViewWeekLabelFormatter() {
                return super.getCalendarViewWeekLabelFormatter();
            }
        };

//            override val calendarViewWeekLabelFormatter: LabelFormatter
//            get() = { primeCalendar ->
//                    when (primeCalendar[Calendar.DAY_OF_WEEK]) {
//                Calendar.SATURDAY,
//                        Calendar.SUNDAY -> String.format("%s😍", primeCalendar.weekDayNameShort)
//                else -> String.format("%s😁", primeCalendar.weekDayNameShort)
//            }
//
//
//            override val calendarViewWeekLabelTextColors: SparseIntArray
//            get() = SparseIntArray(7).apply {
//                val red = getColor(R.color.red300)
//                val green = getColor(R.color.green400)
//                put(Calendar.SATURDAY, red)
//                put(Calendar.SUNDAY, red)
//                put(Calendar.MONDAY, green)
//                put(Calendar.TUESDAY, green)
//                put(Calendar.WEDNESDAY, green)
//                put(Calendar.THURSDAY, green)
//                put(Calendar.FRIDAY, green)
//            }
//
//            // Other customizations...
//        }

//        CivilCalendar today = new CivilCalendar();  // Causes a Civil date picker, also today as the starting date
//
//        datePicker = PrimeDatePicker.Companion.bottomSheetWith(today) // or dialogWith(today)
//                .pickRangeDays(callback)  // Passing callback is optional, can be set later using setDayPickCallback()
//                .minPossibleDate(today)// Optional
//                .firstDayOfWeek(Calendar.MONDAY)// Optional
//                .applyTheme(themeFactory)                    // Optional
//                .build();
    }

    void initData(){
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
        titleTextView.setText(StringUtils.cutString(house.getTitle(), 50));
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

        String reviewLabel = "Nhận xét " + "(" + house.getReviews().size() +")";
        mReviewLabelTextView.setText(reviewLabel);

        String photoLabel = "Hình ảnh " + "(" + house.getPhotos().size() +")";
        mPhotoLabelTextView.setText(photoLabel);

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
        Intent intent = new Intent(this, DateActivity.class);
        startActivityForResult(intent, AppConstants.LAUNCH_ACTIVITY_DATE);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(SelectedDateEvent event) {
        new Handler().postDelayed(() -> {
            List<Calendar> calendarList = event.getCalendarList();
            Intent intent = new Intent(this, BookingActivity.class);
            intent.putExtra(AppConstants.TAG_DATA_CALENDAR, new Gson().toJson(calendarList));
            intent.putExtra(AppConstants.TAG_DATA_HOUSE_ID, house.getId());
            startActivityForResult(intent, AppConstants.LAUNCH_BOOKING_ACTIVITY);
        }, 100);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(AddGuestEvent event) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra(AppConstants.TAG_DATA_DATE, new Gson().toJson(selectedDate));
        intent.putExtra(AppConstants.TAG_DATA_ADULT, event.getAdult());
        intent.putExtra(AppConstants.TAG_DATA_CHILD, event.getChild());
        intent.putExtra(AppConstants.TAG_DATA_HOUSE_ID, house.getId());
        startActivity(intent);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private Marker createMarker (LatLng latLng){
        if (mMap == null) {
            return null;
        }
        if(mMarker != null) mMarker.remove();
        MarkerOptions mOptions = new MarkerOptions().position(latLng);
        mOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        mMarker = mMap.addMarker(mOptions);
        return mMarker;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppConstants.LAUNCH_ACTIVITY_DATE) {
            if(resultCode == Activity.RESULT_OK) {
                final String result = data.getStringExtra(AppConstants.TAG_DATA_DATE);
                Type type = new TypeToken<Calendar[]>() {
                }.getType();
                selectedDate = Arrays.asList(new Gson().fromJson(result, type));
                GuestDialog dialog = new GuestDialog(this);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        }
    }
}
