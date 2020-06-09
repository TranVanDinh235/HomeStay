package com.example.homestay.ui.changepass;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.aminography.primecalendar.PrimeCalendar;
import com.aminography.primecalendar.civil.CivilCalendar;
import com.aminography.primecalendar.common.CalendarFactory;
import com.aminography.primecalendar.common.CalendarType;
import com.aminography.primecalendar.persian.PersianCalendar;
import com.aminography.primedatepicker.calendarview.PrimeCalendarView;
import com.aminography.primedatepicker.common.OnDayPickedListener;
import com.aminography.primedatepicker.common.PickType;
import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassActivity extends BaseActivity {

    @BindView(R.id.primeCalendarView)
    PrimeCalendarView calendarView;

    @BindView(R.id.dialog_date_start_container)
    ConstraintLayout dateStartLayout;

    @BindView(R.id.dialog_date_end_container)
    ConstraintLayout dateEndLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

//        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

//        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    protected void setUp() {
//        Calendar lastYear = Calendar.getInstance();
//        lastYear.add(Calendar.MONTH, -1);
//        Calendar nextYear = Calendar.getInstance();
//        nextYear.add(Calendar.MONTH, 1);
//        Calendar today = Calendar.getInstance();
        PrimeCalendar min = new CivilCalendar();
        PrimeCalendar max = new CivilCalendar();
        PrimeCalendar dis1 = new CivilCalendar();
        PrimeCalendar dis2 = new CivilCalendar();

        max.add(Calendar.MONTH, 6);
        dis1.add(Calendar.DATE, 5);
        dis2.add(Calendar.DATE, 12);

        ArrayList<PrimeCalendar> disableList = new ArrayList<>();
        disableList.add(dis1);
        disableList.add(dis2);
        Log.e("dinh", min.getTime().toString() + "  " + max.getTime().toString());

        calendarView.setTypeface(Typeface.DEFAULT);
        calendarView.goTo(CalendarFactory.newInstance(CalendarType.CIVIL), false);
        calendarView.setMinDateCalendar(min);
        calendarView.setLocale(new Locale("vi", "VN"));
        calendarView.setFlingOrientation(PrimeCalendarView.FlingOrientation.HORIZONTAL);
        calendarView.setPickType(PickType.RANGE_START);
        calendarView.setDisabledDaysList(disableList);
        calendarView.setOnDayPickedListener(new OnDayPickedListener() {
            @Override
            public void onDayPicked(PickType pickType, @Nullable PrimeCalendar singleDay, @Nullable PrimeCalendar startDay, @Nullable PrimeCalendar endDay, List<PrimeCalendar> multipleDays) {
                Log.d("log", "onDayPicked: ");
                if(pickType == PickType.RANGE_START){
                    calendarView.setPickedRangeStartCalendar(startDay);
                } else if(pickType == PickType.RANGE_END){
                    if(startDay != null && endDay != null) {
                        boolean check = false;
                        List<PrimeCalendar> selectedCalendar = DateTimeUtils.getListPrimeCalendar(startDay, endDay);
                        for (int i = 0; i < selectedCalendar.size(); i++) {
                            for (int j = 0; j < disableList.size(); j++)
                                if (selectedCalendar.get(i).equals(disableList.get(j))) {
                                    check = true;
                                }
                        }
                        if (check) {
                            calendarView.setPickedRangeEndCalendar(startDay);
                        }
                    }
                }
            }
        });
    }

    @OnClick(R.id.dialog_date_start_container)
    void onDateStartClick(View view){
        calendarView.setPickType(PickType.RANGE_START);
        dateStartLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        dateEndLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.gray50_100));

    }

    @OnClick(R.id.dialog_date_end_container)
    void onDateEndClick(View view){
        calendarView.setPickType(PickType.RANGE_END);
        dateStartLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.gray50_100));
        dateEndLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
    }
}
