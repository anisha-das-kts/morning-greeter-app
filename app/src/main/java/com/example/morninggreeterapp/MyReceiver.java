package com.example.morninggreeterapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;


public class MyReceiver extends BroadcastReceiver {

    public static final String CHANNEL_ID = "NOTIFICATION_CHANNEL";
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
        public void onReceive (Context context, Intent intent){
        Vibrator vib = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(2000);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChanelNotification();
           notificationHelper.getManager().notify(1,nb.build());
           Uri noti = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
           Ringtone ringtone = RingtoneManager.getRingtone(context,noti);
           ringtone.play();
        }
    }