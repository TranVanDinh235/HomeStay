package com.example.homestay.ui.explore;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.homestay.R;
import com.example.homestay.data.network.response.CityResponse;
import com.example.homestay.data.network.response.TopicResponse;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;
import com.example.homestay.ui.explore.adapter.CityAdapter;
import com.example.homestay.ui.explore.adapter.TopicAdapter;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@PerActivity
public class ExploreFragment extends BaseFragment implements ExploreView, CityAdapter.Callback {

    @Inject
    ExplorePresenter<ExploreView> mPresenter;

    @Inject
    CityAdapter mCityAdapter;

    @Inject
    TopicAdapter mTopicAdapter;

    @Inject
    LinearLayoutManager mLayoutManagerHorizontal;

    @Inject
    LinearLayoutManager mLayoutManagerVertical;

    @BindView(R.id.layout_discover_cites)
    ViewPager2 mCityViewPager;

    @BindView(R.id.layout_discover_topics)
    RecyclerView mTopicRecyclerView;

    @BindView(R.id.dots_indicator)
    DotsIndicator dotsIndicator;

    @BindView(R.id.layout_discover_card_bg)
    View mCardBgView;

    @BindView(R.id.layout_discover_search)
    CardView mSearchCardView;

    @BindView(R.id.layout_discover_icon_search)
    ImageView mIconSearchImageView;

    @BindView(R.id.layout_discover_edt_search)
    EditText mSearchEditText;

    @BindView(R.id.layout_discover_search_container)
    CardView mSearchContainerCardView;

    @BindView(R.id.layout_discover_photo)
    ImageView mPhotoImageView;

    @BindView(R.id.layout_discover_date)
    TextView mDateTextView;

    @BindView(R.id.layout_discover_room)
    TextView mRoomTextView;

    private boolean displaySearchContainerFlag = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_explore, container, false);


        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, root));
            mPresenter.onAttach(this);
            mCityAdapter.setCallback(this);
        }

        return root;
    }

    @Override
    protected void setUp(View view) {
        mCityViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mCityViewPager.setAdapter(mCityAdapter);
        dotsIndicator.setViewPager2(mCityViewPager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTopicRecyclerView.setLayoutManager(layoutManager);
        mTopicRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTopicRecyclerView.setAdapter(mTopicAdapter);
        mTopicRecyclerView.setNestedScrollingEnabled(false);

        mPresenter.loadCity();
        mPresenter.loadTopic();
    }

    @Override
    public void showTopic(TopicResponse response) {
        mTopicAdapter.addItem(response.getTopics());
    }

    @Override
    public void showCity(CityResponse response) {
        mCityAdapter.addItem(response.getCities());
    }

    @Override
    public void onCityItemClick() {

    }

    @OnClick(R.id.layout_discover_search)
    void onSearchClick(View view){
        if(!displaySearchContainerFlag){
            mCityViewPager.setVisibility(View.GONE);
            dotsIndicator.setVisibility(View.GONE);

            mPhotoImageView.setVisibility(View.VISIBLE);
            mSearchContainerCardView.setVisibility(View.VISIBLE);
            mCardBgView.setVisibility(View.VISIBLE);
        }
    }
}