package com.example.homestay.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homestay.R;
import com.example.homestay.data.eventbus.SelectedDatesEvent;
import com.example.homestay.data.network.model.CityResponse;
import com.example.homestay.data.network.model.TopicResponse;
import com.example.homestay.di.PerActivity;
import com.example.homestay.di.component.ActivityComponent;
import com.example.homestay.ui.base.BaseFragment;
import com.example.homestay.ui.calendar.CalendarActivity;
import com.example.homestay.ui.discover.adapter.CityAdapter;
import com.example.homestay.ui.discover.adapter.TopicAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.layout_discover_check_in_date)
    TextView mCheckInDateTextView;

    @BindView(R.id.layout_discover_check_in_day_of_week)
    TextView mCheckInDayOfWeekTextView;

    @BindView(R.id.layout_discover_check_in_month)
    TextView mCheckInMonthTextView;

    @BindView(R.id.layout_discover_check_out_date)
    TextView mCheckOutDateTextView;

    @BindView(R.id.layout_discover_check_out_day_of_week)
    TextView mCheckOutDayOfWeekTextView;

    @BindView(R.id.layout_discover_check_out_month)
    TextView mCheckOutMonthTextView;

    private List<CalendarDay> mSelectedDates;

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
        layoutDatePicker.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), CalendarActivity.class);
            startActivityForResult(intent, 66);
        });

        EventBus.getDefault().register(this);

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

        CalendarDay today = CalendarDay.today();
        CalendarDay tomorrow = CalendarDay.from(today.getDate().plusDays(1));
        displayDateSelected(today, tomorrow);

        mSelectedDates = new ArrayList<>();
        mPresenter.loadCity();
        mPresenter.loadTopic();

    }

    private void displayDateSelected(CalendarDay startDay, CalendarDay endDay){
        mCheckInDateTextView.setText(String.valueOf(startDay.getDay()));
        mCheckInMonthTextView.setText(String.valueOf(startDay.getMonth()));
        mCheckInDayOfWeekTextView.setText(String.valueOf(startDay.getDate().getDayOfWeek().getValue()));

        mCheckOutDateTextView.setText(String.valueOf(endDay.getDay()));
        mCheckOutMonthTextView.setText(String.valueOf(endDay.getMonth()));
        mCheckOutDayOfWeekTextView.setText(String.valueOf(endDay.getDate().getDayOfWeek().getValue()));
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSelectedDatesEvent(SelectedDatesEvent event) {
        mSelectedDates = event.getSelectedDates();
        displayDateSelected(mSelectedDates.get(0), mSelectedDates.get(mSelectedDates.size() - 1));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}