package com.example.user.projectse;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

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
        Intent mIntent = null;
        try {
            mIntent = new Intent(this, Class.forName("com.example.user.projectse.NotifictionPage"));
            mNotificationCount=((MyAlarm) this.getApplication()).getNotificationCount();
            title=intent.getStringExtra("title");
            mIntent.putExtra("title", title);
            mIntent.putExtra("date", intent.getStringExtra("date"));
            mIntent.putExtra("time", intent.getStringExtra("time"));
            mIntent.putExtra("type", intent.getStringExtra("type"));
            mIntent.putExtra("loc", intent.getStringExtra("loc"));
            mIntent.putExtra("count", mNotificationCount);
            mIntent.putExtra("eventdate", intent.getLongExtra("eventdate", -1));
            pendingIntent = PendingIntent.getActivity(context, mNotificationCount, mIntent,PendingIntent.FLAG_ONE_SHOT);
            Resources res = this.getResources();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
            Long time=intent.getLongExtra("eventdate", -1);
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String dateText = df2.format(time);
            builder.setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.download)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.download))
                    .setAutoCancel(true)
                    .setContentTitle(intent.getStringExtra("type")+":  " + title)
                    .setContentText("on " +dateText + "  at:  " + intent.getStringExtra("time"));
           // Toast.makeText(getApplicationContext(), "AlarmService::onHandleIntent:: set when: " + dateText, Toast.LENGTH_LONG).show();
            notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
         //   builder.addExtras(ext);
            notificationManager.notify(mNotificationCount, builder.build());
           // Toast.makeText(getApplicationContext(), "AlarmService::onHandleIntent::  Notification ID: "+mNotificationCount, Toast.LENGTH_LONG).show();
            ((MyAlarm) this.getApplication()).incrementCount();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}