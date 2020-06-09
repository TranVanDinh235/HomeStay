package com.example.homestay.ui.booking;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.data.event.AddRoomEvent;
import com.example.homestay.data.event.CloseDialogEvent;
import com.facebook.appevents.suggestedevents.ViewOnClickListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessDialog extends Dialog {

    @BindView(R.id.dialog_success_message)
    TextView mMessageTextView;

    @BindView(R.id.dialog_success_ok)
    ExtendedFloatingActionButton mOkeButton;

    String message;

    public SuccessDialog(@NonNull Context context) {
        super(context);
    }

    public SuccessDialog(@NonNull Context context, String message) {
        super(context);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_success);
        ButterKnife.bind(this);
        if(!TextUtils.isEmpty(message)) mMessageTextView.setText(message);
    }

    @OnClick(R.id.dialog_success_ok)
    void onClose(View view){
        EventBus.getDefault().post(new CloseDialogEvent());
        dismiss();
    }
}

