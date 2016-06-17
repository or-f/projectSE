package com.example.user.projectse;

import android.app.Application;

public class MyAlarm extends Application {
    public int notificationCount=0;
    private static final int MAX_SIZE =9999;
    @Override
    public void onCreate() {
        super.onCreate();

    }

    public void incrementCount(){
        notificationCount ++;
        if(notificationCount>MAX_SIZE)
            notificationCount=0;
    }

    public int getNotificationCount(){
        return notificationCount;
    }
}