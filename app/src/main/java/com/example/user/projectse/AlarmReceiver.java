package com.example.user.projectse;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String TAG = "BANANEALARM";
    Intent intent;
    PendingIntent pendingIntent;
    NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context," BroadcastReceiver has received alarm intent." ,Toast.LENGTH_LONG).show();
        Intent service1 = new Intent(context, AlarmService.class);
        //service1.putExtras(intent.getExtras());
        service1.putExtra("title", intent.getStringExtra("title"));
        service1.putExtra("count", intent.getIntExtra("count", -1));
        service1.putExtra("date", intent.getLongExtra("date", -1));
        service1.putExtra("time", intent.getStringExtra("time"));
        service1.putExtra("loc",intent.getStringExtra("loc"));
        service1.putExtra("type", intent.getStringExtra("type"));
        service1.putExtra("eventdate", intent.getLongExtra("eventdate",-1));
        context.startService(service1);

    }
}

