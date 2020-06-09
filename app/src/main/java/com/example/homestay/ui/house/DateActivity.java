package com.example.homestay.ui.house;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.example.homestay.R;
import com.example.homestay.data.event.SelectedDateEvent;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.utils.AppConstants;
import com.example.homestay.utils.DateTimeUtils;
import com.example.homestay.utils.StringUtils;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static java.security.AccessController.getContext;

public class DateActivity extends BaseActivity {

    @BindView(R.id.layout_date_start)
    TextView mStartDateTextView;

    @BindView(R.id.layout_date_end)
    TextView mEndDateTextView;

    @BindView(R.id.calendar)
    DateRangeCalendarView mCalendarView;

    private List<Calendar> selectedCalendar = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        ButterKnife.bind(this);

        setUp();
    }


    @Override
    protected void setUp() {
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());

        Calendar min = Calendar.getInstance();

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 6);
        mCalendarView.setFonts(Typeface.createFromAsset(getAssets(),
                "fonts/Lora-Medium.ttf"));
        mCalendarView.setSelectableDateRange(min, max);
//        List<Calendar> disableDay = CalendarUtils.getDatesRange(min, today);
//        for(int i = 0; i < mHouseDateList.size(); i ++){
//            if(mHouseDateList.get(i).getState() == 2){
//                Calendar calendar = Calendar.getInstance();
//                long time = (long)mHouseDateList.get(i).getDate() * 1000;
//                calendar.setTimeInMillis(time);
//                disableDay.add(calendar);
//            }
//        }

        mCalendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onFirstDateSelected(@NotNull Calendar calendar) {
                String start = DateTimeUtils.calendarToString(calendar);
                mStartDateTextView.setText(start);
                mEndDateTextView.setText("");
            }

            @Override
            public void onDateRangeSelected(@NotNull Calendar startDate, @NotNull Calendar endDate) {
                String start = DateTimeUtils.calendarToString(startDate);
                mStartDateTextView.setText(start);
                String end = DateTimeUtils.calendarToString(endDate);
                mEndDateTextView.setText(end);
                selectedCalendar.clear();
                selectedCalendar.add(startDate);
                selectedCalendar.addAll(CalendarUtils.getDatesRange(startDate, endDate));
                selectedCalendar.add(endDate);
            }
        });
    }

    @OnClick(R.id.layout_date_apply)
    void onApplyButtonClick(View view){
        if(selectedCalendar != null && selectedCalendar.size() > 1) {
            final Intent data = new Intent();
            data.putExtra(AppConstants.TAG_DATA_DATE, new Gson().toJson(selectedCalendar.toArray()));
            setResult(Activity.RESULT_OK, data);
            finish();
        }
    }

    @OnClick(R.id.layout_date_finish)
    void onClose(View view){
        finish();
    }
}
