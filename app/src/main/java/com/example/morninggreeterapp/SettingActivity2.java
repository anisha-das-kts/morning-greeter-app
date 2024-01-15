package com.example.morninggreeterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.view.Menu;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.cert.CertPathBuilder;
import java.text.DateFormat;

public class SettingActivity2 extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static final String CHANNEL_NAME = "ALARM_NOTIFICATION";
    public static final String CHANNEL_DESCRIPTION = "ALARM_NOTIFICATION";
    public static final String CHANNEL_ID = "Greeting Popup";
    private static final int NOTIFICATION_ID = 100;
    private static final int REQ_CODE = 100;
    public SwitchCompat switchCompat;
    public Boolean aBoolean = true;
    public String timeText;
    Notification notification = null;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        tv1 = (TextView) findViewById(R.id.tv1);
        //createNotificationChannel();
//        Button button = (Button) findViewById(R.id.btnSetAlarm);
//        switchCompat = (SwitchCompat) findViewById(R.id.switchButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(switchCompat.isChecked()){
//                    aBoolean = true;
//                }
//            }
//        });

    }


    private Notification.Builder setContentIntent(PendingIntent pi) {
        return null;
    }
    //============</ Activity >==================


    //============< Buttons >==================
    public void BtnAlarmOnClick(View view) {
        //-------< BtnAlarmOnClick >--------
        //*click Button to set Time

            DialogFragment timePickerDialog = new TimePickerFragment();
        Button button = (Button) findViewById(R.id.btnSetAlarm);
        switchCompat = (SwitchCompat) findViewById(R.id.switchButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switchCompat.isChecked()){
                    timePickerDialog.show(getSupportFragmentManager(), "time picker");
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                    int time = Integer.parseInt(((TextView)findViewById(R.id.txtViewAlarm)).getText().toString());
//                    long triggerTime = System.currentTimeMillis()*(time*1000);
//                    Intent intent = new Intent(SettingActivity2.this,MyReceiver.class);
//                    PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingActivity2.this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//                    alarmManager.set(AlarmManager.RTC_WAKEUP,triggerTime,pendingIntent);
                }else{
                    Toast.makeText(getApplicationContext(),"Switch on the Alarm Button to Set Alarm",Toast.LENGTH_SHORT).show();
                }
            }


        });
           // if(aBoolean) {
              //  timePickerDialog.show(getSupportFragmentManager(), "time picker");
           // }
           // }
            //-------</ BtnAlarmOnClick >--------

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onTimeSet(android.widget.TimePicker timePicker, int intHourOfDay, int intMinute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,intHourOfDay);
        c.set(Calendar.MINUTE,intMinute);
        c.set(Calendar.SECOND,0);
        updateTimeText(c);


        TextView textViewPicked=(TextView) findViewById(R.id.txtViewAlarm);

        //textViewPicked.setText(("TimePicked=" + intHourOfDay + ":" + intMinute ));
        textViewPicked.setText(timeText);
        startAlarm(c);
       //startAlarm(c);


    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if (c.before(Calendar.getInstance())) {
                c.add(Calendar.DATE, 1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        }
    }

        private void repeatAlarm(Calendar calendar){
            AlarmManager  alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent i = new Intent(this,MyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,i,0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,
                        pendingIntent);
            }
        }




//    @RequiresApi(api = Build.VERSION_CODES.N)
//    private void startAlarm(Calendar c) {
//        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this,MyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,1,intent,0);
//        if(c.before(Calendar.getInstance())){
//            c.add(Calendar.DATE,1);
//        }
//        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateTimeText(Calendar c) {
         timeText = "Alarm set for:";
         timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        //scheduleNotification(notification,Integer.parseInt(time));
    }


//============</ Buttons >==================
}