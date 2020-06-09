package com.example.homestay.ui.house;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.data.event.AddGuestEvent;
import com.example.homestay.data.event.AddRoomEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuestDialog extends Dialog {
    @BindView(R.id.dialog_guest_adult)
    TextView adultTextView;

    @BindView(R.id.dialog_guest_child)
    TextView childTextView;

    private int adult = 2;
    private int child = 0;

    public GuestDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_guest);
        ButterKnife.bind(this);
        adultTextView.setText(String.valueOf(adult));
        childTextView.setText(String.valueOf(child));
    }

    @OnClick(R.id.dialog_guest_adult_add)
    void onAdultAddClick(View view){
        adult++;
        adultTextView.setText(String.valueOf(adult));
    }

    @OnClick(R.id.dialog_guest_adult_minus)
    void onAdultMinusClick(View view){
        if (adult > 1) {
            adult--;
            adultTextView.setText(String.valueOf(adult));
        }
    }

    @OnClick(R.id.dialog_guest_child_add)
    void onChildAddClick(View view){
        child++;
        childTextView.setText(String.valueOf(child));
    }

    @OnClick(R.id.dialog_guest_child_minus)
    void onChildMinusClick(View view){
        if (child > 0) {
            child--;
            childTextView.setText(String.valueOf(child));
        }
    }

    @OnClick(R.id.dialog_guest_apply)
    void onApplyButtonClick(View view){
        EventBus.getDefault().post(new AddGuestEvent(adult, child));
        dismiss();
    }
}
