package com.example.morninggreeterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout layout;
    TextView textView;

    //notification
//    private static final String CHANNEl_ID = "Greeting Popup";
//
//    private static final int NOTIFICATION_ID = 100;
//
//    private static final int REQ_CODE = 100;
    //

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random r = new Random();
        int randomNumber = r.nextInt(25 - 1) + 1;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.imgRandom);
        // I have 25 images named img_0 to img_2, so...
        final String str = "img_" + randomNumber;


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = findViewById(R.id.bg);
        textView = findViewById(R.id.tv);

        Calendar calendar = Calendar.getInstance();
        int Hours = calendar.get(Calendar.HOUR_OF_DAY);
        //AlarmManager.setRepeating(flag, when, 24*60*60*1000, pendingindent)

        if (Hours > 5 && Hours < 13) {

            img.setImageDrawable
                    (
                            getResources().getDrawable(getResourceID(str, "drawable",
                                    getApplicationContext()))
                    );
        } else {
            textView.setText("It's a Morning App..!");

        }
        //notification
//        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.notification_icon, null);
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//        Bitmap largeIcon = bitmapDrawable.getBitmap();
//
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification notification;
//
//        Intent iNotify = new Intent(getApplicationContext(), MainActivity.class);
//
//        iNotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//
//        PendingIntent pi = PendingIntent.getActivity(this, REQ_CODE, iNotify, PendingIntent.FLAG_UPDATE_CURRENT);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notification = new Notification.Builder(this)
//                    .setLargeIcon(largeIcon)
//                    .setSmallIcon(R.drawable.notification_icon)
//                    .setContentText("New Massage")
//                    .setSubText("A blessed morning dear")
//                    .setChannelId(CHANNEl_ID)
//                    .setContentIntent(pi)
//                    .build();
//            nm.createNotificationChannel(new NotificationChannel(CHANNEl_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
//        } else {
//            notification = new Notification.Builder(this)
//                    .setLargeIcon(largeIcon)
//                    .setSmallIcon(R.drawable.notification_icon)
//                    .setContentText("New Massage")
//                    .setSubText("Have A Blessed Morning Dear")
//                    .setContentIntent(pi)
//                    .build();
//        }
//        nm.notify(NOTIFICATION_ID, notification);
        //
    }

    private int getResourceID
            (final String resName, final String resType, final Context ctx)
    {
        final int ResourceID =
                ctx.getResources().getIdentifier(resName, resType,
                        ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            return ResourceID;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {

            case R.id.settings:
                Intent intent = new Intent(MainActivity.this, SettingActivity2.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

