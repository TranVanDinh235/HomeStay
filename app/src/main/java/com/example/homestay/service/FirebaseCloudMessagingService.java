package com.example.homestay.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.homestay.R;
import com.example.homestay.data.DataManager;
import com.example.homestay.data.event.MessageEvent;
import com.example.homestay.data.network.entity.NotificationContent;
import com.example.homestay.ui.main.MainActivity;
import com.example.homestay.utils.AppConstants;
import com.example.homestay.utils.rx.SchedulerProvider;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

import static com.example.homestay.utils.AppConstants.CHANNEL_ID;
import static com.example.homestay.utils.AppConstants.GROUP_KEY_NOTIFICATION;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {

    private static final int NOTIFY_SUMMARY = 100;
    private Bitmap icon;

    private NotificationContent notice;

    @Inject
    DataManager dataManager;

    @Inject
    SchedulerProvider schedulerProvider;

    @Inject
    CompositeDisposable compositeDisposable;

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        updateToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData() == null) {
            return;
        }
        Map<String, String> data = remoteMessage.getData();
        String jsonData = new Gson().toJson(data);
        Log.e("FCMService", "line 54 -----: "+jsonData);
        notice = new NotificationContent();
//        EventBus.getDefault().post(new MessageEvent());
        if (!TextUtils.isEmpty(jsonData)) {
            notice = new Gson().fromJson(jsonData, NotificationContent.class);
        }

        if (icon == null || icon.isRecycled()) {
            icon = BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_logo);
        }
        EventBus.getDefault().post(new MessageEvent(true));
        buildNotification(
                MainActivity.getIntentMainActivity(this, AppConstants.OPEN_TAB_NOTIFICATION)
                , notice.getTitle()
                , notice.getContent()
                , (int) System.currentTimeMillis());

    }

    private void updateToken(String newToken) {
        if ( dataManager.getFireBaseToken() == null && TextUtils.isEmpty(dataManager.getFireBaseToken())) return;
        try {
            String data = "{\"new_token\":\""+ newToken + "\"," +
                    "\"id\":\"" + dataManager.getCurrentUserId() + "\"}";
            JSONObject body = new JSONObject(data);
            compositeDisposable.add(dataManager
                    .doServerApiUpdateFirebaseTokenCall(body)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(response -> {
                        dataManager.setFireBaseToken(response.getFirebaseToken());
                    }, Throwable::printStackTrace));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildNotification(Intent intent, String title, String message, int notifyID) {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, notifyID, intent, 0);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        setupNotification(title, message, defaultSoundUri, pendingIntent, notifyID);
    }

    public void setupNotification(String title, String message, Uri defaultSoundUri, PendingIntent
            pendingIntent, int notifyID) {
        NotificationCompat.Builder mBuilderGroup =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        mBuilderGroup.setSmallIcon(getNotificationIcon())
                .setLargeIcon(icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setGroupSummary(true)
                .setGroup(GROUP_KEY_NOTIFICATION)
                .setGroupSummary(true)
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_SUMMARY);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID).
                        setSmallIcon(getNotificationIcon())
                        .setLargeIcon(icon)
                        .setAutoCancel(true)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setGroup(GROUP_KEY_NOTIFICATION)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mBuilderGroup.setColor(this.getColor(R.color.colorPrimary));
            mBuilder.setColor(this.getColor(R.color.colorPrimary));
        }
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notifyID, mBuilder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mNotificationManager.notify(NOTIFY_SUMMARY, mBuilderGroup.build());
        }
    }

    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.mipmap.ic_launcher : R.mipmap.ic_launcher;
    }

    public static void cancelAllNotification(Context context) {

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }
}
