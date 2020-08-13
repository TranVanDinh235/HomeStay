package com.example.homestay.ui.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.event.AddRoomEvent;
import com.example.homestay.data.event.SelectedDateEvent;
import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.booking.BookingActivity;
import com.example.homestay.ui.filter.FilterActivity;
import com.example.homestay.ui.house.HouseActivity;
import com.example.homestay.ui.overview.OverViewActivity;
import com.example.homestay.ui.upcoming.QRCodeDialog;
import com.example.homestay.utils.AppConstants;
import com.example.homestay.utils.PullLoadMoreUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.homestay.utils.AppConstants.TAG_SEARCH_STRING;
import static com.example.homestay.utils.AppConstants.TAG_TOPIC_ITEM_ID;

public class ListHouseActivity extends BaseActivity implements ListHouseView, ListHouseAdapter.Callback {

    @Inject
    ListHousePresenter<ListHouseView> mPresenter;

    @Inject
    ListHouseAdapter mListHouseAdapter;

    @BindView(R.id.layout_list_house_title)
    TextView mTitleTextView;

    @BindView(R.id.layout_list_house_date_start)
    TextView mStartDateTextView;

    @BindView(R.id.layout_list_house_date_end)
    TextView mEndDateTextView;

    @BindView(R.id.layout_list_house_room)
    TextView mRoomTextView;

    @BindView(R.id.layout_list_house_total)
    TextView mTotalTextView;

    @BindView(R.id.layout_list_house_rv)
    RecyclerView mListHouseRecyclerView;

    private String mSearchString;
    private String mTopicItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_house);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(ListHouseActivity.this);
        mListHouseAdapter.setCallback(this);
        setUp();
    }

    @Override
    protected void setUp() {
        mTopicItemId = getIntent().getStringExtra(TAG_TOPIC_ITEM_ID);
        mSearchString = getIntent().getStringExtra(TAG_SEARCH_STRING);
        if(!TextUtils.isEmpty(mTopicItemId)){
            mPresenter.getDataTopicItemListHouse(mTopicItemId);
        } else if(!TextUtils.isEmpty(mSearchString)){
            mPresenter.getDataSearchListHouse(mSearchString);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListHouseRecyclerView.setLayoutManager(layoutManager);
        mListHouseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListHouseRecyclerView.setAdapter(mListHouseAdapter);
        mListHouseRecyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void showListHouse(ListHouseResponse response) {
        String total = "(" + response.getHouseList().getHouses().size() + " kết quả)";
        mTotalTextView.setText(total);
        mListHouseAdapter.addItem(response.getHouseList().getHouses());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.layout_list_house_filter)
    void onFilterClick(View view){
        Intent intent = new Intent(this, FilterActivity.class);
        startActivityForResult(intent, AppConstants.LAUNCH_FILTER_ACTIVITY);
    }

    @Override
    public void onItemClick(int houseId) {
        startActivityForResult(OverViewActivity.getIntentOverviewActivity(this, String.valueOf(houseId)), AppConstants.LAUNCH_ACTIVITY_OVERVIEW);
    }

    @OnClick(R.id.layout_list_house_back_icon)
    void onClose(View view){
        finish();
    }

    @OnClick(R.id.layout_list_house_room)
    void onRoomClick(View view){
        RoomDialog dialog = new RoomDialog(this);
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
    public void onEvent(AddRoomEvent event) {
        new Handler().postDelayed(() -> {
            String room = event.getBedroom() + " " + getString(R.string.bed_room) + " - " + event.getBathroom() + " " +
                    getString(R.string.bathroom) + " - " + event.getGuest() + " " + getString(R.string.guest);
            mRoomTextView.setText(room);
        }, 100);
    }
}
