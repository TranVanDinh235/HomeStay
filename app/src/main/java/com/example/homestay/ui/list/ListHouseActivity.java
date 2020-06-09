package com.example.homestay.ui.list;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.recyclerview.widget.DefaultItemAnimator;

import com.example.homestay.R;
import com.example.homestay.data.network.response.ListHouseResponse;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.ui.house.HouseActivity;
import com.example.homestay.utils.PullLoadMoreUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.homestay.utils.AppConstants.TAG_SEARCH_STRING;
import static com.example.homestay.utils.AppConstants.TAG_TOPIC_ITEM_ID;

public class ListHouseActivity extends BaseActivity implements ListHouseView, PullLoadMoreRecyclerView.PullLoadMoreListener, ListHouseAdapter.Callback {

    @Inject
    ListHousePresenter<ListHouseView> mPresenter;

    @Inject
    ListHouseAdapter mListHouseAdapter;

    @BindView(R.id.layout_list_house_title)
    TextView mTitleTextView;

    @BindView(R.id.layout_list_house_date)
    TextView mDateTextView;

    @BindView(R.id.layout_list_house_room)
    TextView mRoomTextView;

    @BindView(R.id.layout_list_house_rv)
    PullLoadMoreRecyclerView mListHouseRecyclerView;

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
        setUpRecyclerView();
        if(!TextUtils.isEmpty(mTopicItemId)){
            mPresenter.getDataTopicItemListHouse(mTopicItemId);
        } else if(!TextUtils.isEmpty(mSearchString)){
            mPresenter.getDataSearchListHouse(mSearchString);
        }
    }

    void setUpRecyclerView(){
        mListHouseRecyclerView.setLinearLayout();
        mListHouseRecyclerView.setRefreshing(true);
        mListHouseRecyclerView.setFooterViewText(getString(R.string.loading));
        mListHouseRecyclerView.setOnPullLoadMoreListener(this);
        mListHouseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mListHouseRecyclerView.setAdapter(mListHouseAdapter);
    }


    @Override
    public void onRefresh() {
        mListHouseAdapter.clear();
        PullLoadMoreUtil.setLoadMore(true, mListHouseRecyclerView);
        mPresenter.getDataTopicItemListHouse(mTopicItemId);
    }

    @Override
    public void onLoadMore() {
        mListHouseRecyclerView.setPullLoadMoreCompleted();
    }

    @Override
    public void showListHouse(ListHouseResponse response) {
        mListHouseRecyclerView.setPullLoadMoreCompleted();
        mListHouseAdapter.addItem(response.getHouseList().getHouses());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(int houseId) {
        startActivityForResult(HouseActivity.getIntentHouseActivity(this, String.valueOf(houseId)), 102);
    }
}
