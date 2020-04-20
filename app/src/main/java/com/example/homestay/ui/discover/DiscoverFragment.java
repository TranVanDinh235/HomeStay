package com.example.homestay.ui.discover;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.example.homestay.R;
import com.example.homestay.data.network.model.CityResponse;
import com.example.homestay.data.network.model.TopicResponse;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;
import com.example.homestay.ui.discover.adapter.CityAdapter;
import com.example.homestay.ui.discover.adapter.TopicAdapter;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


@PerActivity
public class DiscoverFragment extends BaseFragment implements DiscoverView, CityAdapter.Callback {

    @Inject
    DiscoverPresenter<DiscoverView> mPresenter;

    @Inject
    CityAdapter mCityAdapter;

    @Inject
    TopicAdapter mTopicAdapter;

    @Inject
    LinearLayoutManager mLayoutManagerHorizontal;

    @Inject
    LinearLayoutManager mLayoutManagerVertical;

    @BindView(R.id.layout_date_picker) ConstraintLayout layoutDatePicker;

    @BindView(R.id.layout_discover_cites)
    RecyclerView mCityRecyclerView;

    @BindView(R.id.layout_discover_topics)
    RecyclerView mTopicRecyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_discover, container, false);


        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, root));
            mPresenter.onAttach(this);
            mCityAdapter.setCallback(this);
        }

        MaterialDatePicker.Builder<?> builder = setupDateSelectorBuilder();
        CalendarConstraints.Builder constraintBuilder = setupConstraintBuilder();

        layoutDatePicker.setOnClickListener(v -> {
            builder.setCalendarConstraints(constraintBuilder.build());
            MaterialDatePicker<?> picker = builder.build();
            picker.show(getFragmentManager(), picker.toString());
        });
        return root;
    }

    @Override
    protected void setUp(View view) {
        mLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
        mCityRecyclerView.setLayoutManager(mLayoutManagerHorizontal);
        mCityRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mCityRecyclerView.setAdapter(mCityAdapter);

        mLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        mTopicRecyclerView.setLayoutManager(mLayoutManagerVertical);
        mTopicRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTopicRecyclerView.setAdapter(mTopicAdapter);

        mPresenter.loadCity();
        mPresenter.loadTopic();
    }

    private MaterialDatePicker.Builder<?> setupDateSelectorBuilder(){
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        builder.setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR);
        builder.setTitleText("Test picker");

        return builder;
    }

    private CalendarConstraints.Builder setupConstraintBuilder(){
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        return constraintsBuilder;
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
}