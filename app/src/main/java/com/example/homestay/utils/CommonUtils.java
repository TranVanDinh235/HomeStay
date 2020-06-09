/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.homestay.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.util.TypedValue;

import org.kobjects.base64.Base64;

import androidx.annotation.AttrRes;

import com.example.homestay.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;


public final class CommonUtils {

    private static final String TAG = "CommonUtils";

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.CHINESE).format(new Date());
    }

    public static Calendar getClearedUtc() {
        Calendar utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utc.clear();
        return utc;
    }

    public static int resolveOrThrow(Context context, @AttrRes int attributeResId) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(attributeResId, typedValue, true)) {
            return typedValue.data;
        }
        throw new IllegalArgumentException(context.getResources().getResourceName(attributeResId));
    }

    public static String getHouseType(int type){
        switch (type) {
            case 0: return AppConstants.ENTIRE_HOUSE;
            case 1: return AppConstants.CONDOMINIUM;
            case 2: return AppConstants.SERVICE_APARTMENT;
            case 3: return AppConstants.STUDIO_APARTMENT;
            case 4: return AppConstants.OTHER;
        }
        return AppConstants.OTHER;
    }

    public static String encrypt(String key, String plainTex) {

        Cipher cipher;
        PublicKey publicKey = stringToPublicKey(key);

        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] plaintBytes = plainTex.getBytes("UTF-8");
            byte[] cipherData = cipher.doFinal(plaintBytes);
            return Base64.encode(cipherData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static PublicKey stringToPublicKey(String publicKeyString) {
        try {
            X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
            return KeyFactory.getInstance("RSA").generatePublic(spec);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String replace(String dataEncrypted){
        dataEncrypted = dataEncrypted.replaceAll("\n", "");
        dataEncrypted = dataEncrypted.replaceAll("\r", "");
        return dataEncrypted;
    }

    public static String getHouseTabTitle(int position){
        switch (position){
            case 0: return AppConstants.DETAIL;
            case 1: return AppConstants.FACILITIES;
            case 2: return AppConstants.PRICE;
            case 3: return AppConstants.HOUSE_RULES;
        }
        return "";
    }

    public static String getTripsTabTitle(int position){
        switch (position){
            case 0: return AppConstants.UPCOMING;
            case 1: return AppConstants.FINISH;
            case 2: return AppConstants.FAVORITES;
        }
        return "";
    }

    public static String getRatingText(float rating){
        if(rating >= 4.0) return AppConstants.VERY_GOOD;
        if(rating >= 3.0) return AppConstants.GOOD;
        if(rating >= 2.0) return AppConstants.NORMAL;
        if(rating >= 1.0) return AppConstants.BAD;
        return "";
    }
}
