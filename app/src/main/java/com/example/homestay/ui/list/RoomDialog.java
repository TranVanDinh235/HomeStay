package com.example.homestay.ui.list;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.data.event.AddRoomEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoomDialog extends Dialog {

    @BindView(R.id.dialog_room_bedroom)
    TextView bedroomTextView;

    @BindView(R.id.dialog_room_bathroom)
    TextView bathroomTextView;

    @BindView(R.id.dialog_room_guest)
    TextView guestTextView;

    private int bedroom = 1;
    private int bathroom = 1;
    private int guest = 1;

    public RoomDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_room);
        ButterKnife.bind(this);
        bedroomTextView.setText(String.valueOf(bedroom));
        bathroomTextView.setText(String.valueOf(bathroom));
        guestTextView.setText(String.valueOf(guest));
    }

    @OnClick(R.id.dialog_room_bedroom_add)
    void onBedroomAddClick(View view){
        bedroom++;
        bedroomTextView.setText(String.valueOf(bedroom));
    }

    @OnClick(R.id.dialog_room_bedroom_minus)
    void onBedroomMinusClick(View view){
        if (bedroom > 1) {
            bedroom--;
            bedroomTextView.setText(String.valueOf(bedroom));
        }
    }

    @OnClick(R.id.dialog_room_bathroom_add)
    void onBathroomAddClick(View view){
        bathroom++;
        bathroomTextView.setText(String.valueOf(bathroom));
    }

    @OnClick(R.id.dialog_room_bathroom_minus)
    void onBathroomMinusClick(View view){
        if (bathroom > 1) {
            bathroom--;
            bathroomTextView.setText(String.valueOf(bathroom));
        }
    }

    @OnClick(R.id.dialog_room_guest_add)
    void onGuestAddClick(View view){
        guest++;
        guestTextView.setText(String.valueOf(guest));
    }

    @OnClick(R.id.dialog_room_guest_minus)
    void onGuestMinusClick(View view){
        if (guest > 1) {
            guest--;
            guestTextView.setText(String.valueOf(guest));
        }
    }

    @OnClick(R.id.dialog_room_apply)
    void onApplyButtonClick(View view){
        EventBus.getDefault().post(new AddRoomEvent(bedroom, bathroom, guest));
        dismiss();
    }
}
