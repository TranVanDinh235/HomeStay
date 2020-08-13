package com.example.homestay.ui.upcoming;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.homestay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QRCodeDialog extends AppCompatDialogFragment {

    private Bitmap bmp;

    @BindView(R.id.layout_qr_code_photo)
    ImageView qrCodeImageView;

    private Dialog dialog;

    QRCodeDialog(Bitmap bmp){
        this.bmp = bmp;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_qr_code, null);
        ButterKnife.bind(this, view);

        dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);

        qrCodeImageView.setImageBitmap(bmp);

        dialog.show();
        return dialog;
    }

    @OnClick(R.id.layout_qr_code_apply)
    void onApplyButtonClick(View view){
        dialog.dismiss();
    }
}
