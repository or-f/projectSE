package com.example.user.projectse;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class AlarmService extends IntentService
{
    private int mNotificationCount;
    public static final int NOTIFICATION_ID = 0;
    private static final String TAG = "BANANEALARM";
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private String title;
    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // don't notify if they've played in last 24 hr
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, NewEventActivity.class);
        mNotificationCount=((MyAlarm) this.getApplication()).getNotificationCount();
        title=intent.getStringExtra("title");
        mIntent.putExtra("title", title);
        pendingIntent = PendingIntent.getActivity(context, mNotificationCount, mIntent, PendingIntent.FLAG_NO_CREATE );
        Resources res = this.getResources();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Long time=intent.getLongExtra("date", -1);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateText = df2.format(time);
        builder.setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.download)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.download))
                .setTicker("ID: " + intent.getIntExtra("count", -1))
                .setAutoCancel(false)
                .setContentTitle(title)
                .setContentText("ID: " + intent.getIntExtra("count", -1) + "  set to: " + dateText);
                Toast.makeText(getApplicationContext(), "AlarmService::onHandleIntent:: set when: " + dateText, Toast.LENGTH_LONG).show();
        notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(mNotificationCount, builder.build());
        Toast.makeText(getApplicationContext(), "AlarmService::onHandleIntent::  Notification ID: "+mNotificationCount, Toast.LENGTH_LONG).show();
        ((MyAlarm) this.getApplication()).incrementCount();
    }

}