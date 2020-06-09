package com.example.homestay.ui.tripdetail;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.homestay.R;
import com.example.homestay.data.event.CloseDialogEvent;
import com.example.homestay.data.event.ConfirmDialogEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmDialog extends Dialog {

    @BindView(R.id.dialog_confirm_message)
    TextView mMessageTextView;

    private String message;

    public ConfirmDialog(@NonNull Context context) {
        super(context);
    }

    public ConfirmDialog(@NonNull Context context, String message) {
        super(context);
        this.message = message;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_confirm);
        ButterKnife.bind(this);
        if(!TextUtils.isEmpty(message)) mMessageTextView.setText(message);
    }

    @OnClick(R.id.dialog_confirm_ok)
    void onConfirm(View view){
        EventBus.getDefault().post(new ConfirmDialogEvent());
        dismiss();
    }

    @OnClick(R.id.dialog_confirm_cancel)
    void onClose(View view){
        dismiss();
    }
}
