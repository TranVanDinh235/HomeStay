package com.example.homestay.ui.house;

import android.app.Dialog;
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
import com.example.homestay.R;
import com.example.homestay.data.event.SelectedDateEvent;
import com.example.homestay.data.network.entity.HouseDate;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalendarDialog extends AppCompatDialogFragment {

    private List<HouseDate> mHouseDateList;

    @BindView(R.id.layout_date_start)
    TextView mStartDateTextView;

    @BindView(R.id.layout_date_end)
    TextView mEndDateTextView;

    @BindView(R.id.calendarView)
    CalendarView mCalendarView;

    @BindView(R.id.layout_date_apply)
    ExtendedFloatingActionButton mApplyButton;

    private Dialog dialog;

    CalendarDialog(List<HouseDate> houseDateList){
        this.mHouseDateList = houseDateList;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_date_picker, null);
        ButterKnife.bind(this, view);

        dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);


        setUpCalendarView();

        dialog.show();
        return dialog;
    }

    void setUpCalendarView(){
        Calendar today = Calendar.getInstance();
        Calendar min = Calendar.getInstance();
        min.set(2020,1,1);

        Calendar max = Calendar.getInstance();
        max.set(2022, 1, 1);

        mCalendarView.setMinimumDate(min);
        mCalendarView.setMaximumDate(max);
        mCalendarView.setDisabledDays(CalendarUtils.getDatesRange(min, today));
        mCalendarView.setOnDayClickListener(eventDay -> {
            Calendar day = eventDay.getCalendar();
        });

    }

    @OnClick(R.id.layout_date_apply)
    void onApplyButtonClick(View view){
        List<Calendar> calendars = mCalendarView.getSelectedDates();
        if(calendars != null && calendars.size() > 1) EventBus.getDefault().post(new SelectedDateEvent(calendars));
        dialog.dismiss();
    }
}
