package com.example.morninggreeterapp;

import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class NotificationHelper  extends ContextWrapper {
    public  static  final String channelID = "channelID";
    public static  final  String channelName = "Channel Name";
    private NotificationManager mManager;
    private Object bigPicture;
    public NotificationHelper(Context base){
        super(base);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if(mManager == null){
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationCompat.Builder getChanelNotification(){
        Intent i = new Intent(NotificationHelper.this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,1,i,PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Morning Wishes")
                .setContentText("Good Morning...Tap to see more...")
                .setSmallIcon(R.drawable.ic_sunny)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
    }
}
