package com.example.homestay.ui.filter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homestay.R;
import com.example.homestay.ui.base.BaseActivity;
import com.example.homestay.utils.StringUtils;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity {

    @BindView(R.id.slider)
    RangeSlider slider;

    @BindView(R.id.layout_filter_price_max)
    TextView mMaxPriceTextView;

    @BindView(R.id.layout_filter_price_min)
    TextView mMinPriceTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        setUp();
    }

    @Override
    protected void setUp() {
        slider.setValues(100000f, 9999999f);
        slider.addOnSliderTouchListener(rangeSliderTouchListener);
    }

    private final RangeSlider.OnSliderTouchListener rangeSliderTouchListener =
            new RangeSlider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(RangeSlider slider) {
                }

                @Override
                public void onStopTrackingTouch(RangeSlider slider) {
                    float min = slider.getValues().get(0);
                    float max = slider.getValues().get(1);

                    mMaxPriceTextView.setText(StringUtils.toRate(String.valueOf((int)max)));
                    mMinPriceTextView.setText(StringUtils.toRate(String.valueOf((int)min)));
                }
            };

    @OnClick(R.id.layout_filter_close)
    void onClose(View view){
        finish();
    }
}
