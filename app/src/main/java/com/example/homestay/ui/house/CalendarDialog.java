package com.example.homestay.ui.house;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.archit.calendardaterangepicker.customviews.CalendarListener;
import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.example.homestay.R;
import com.example.homestay.data.event.SelectedDateEvent;
import com.example.homestay.data.network.entity.HouseDate;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarDialog extends Dialog {

    private List<HouseDate> mHouseDateList;

    @BindView(R.id.dialog_date_start)
    TextView mStartDateTextView;

    @BindView(R.id.dialog_date_end)
    TextView mEndDateTextView;

    @BindView(R.id.calendar)
    DateRangeCalendarView mCalendarView;

    @BindView(R.id.dialog_date_apply)
    ExtendedFloatingActionButton mApplyButton;

    private List<Calendar> selectedCalendar = new ArrayList<>();

    public CalendarDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_date_picker);
        ButterKnife.bind(this);
        setUpCalendarView();
    }

    void setUpCalendarView(){
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());

        Calendar min = Calendar.getInstance();

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 6);


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

            }

            @Override
            public void onDateRangeSelected(@NotNull Calendar startDate, @NotNull Calendar endDate) {

            }
        });
    }

    @OnClick(R.id.dialog_date_apply)
    void onApplyButtonClick(View view){
        if(selectedCalendar != null && selectedCalendar.size() > 1) EventBus.getDefault().post(new SelectedDateEvent(selectedCalendar));
        dismiss();
    }
}
