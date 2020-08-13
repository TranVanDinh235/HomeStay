package com.example.homestay.utils;

import android.app.Dialog;

class DialogUtils {
    public static Dialog dialog;

    public static void hideAlert() {
        if (dialog == null) {
            return;
        }
        dialog.dismiss();
    }

    public static void showDialogRoom(){

    }
}
