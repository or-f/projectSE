package com.example.user.projectse;

import java.sql.Date;
import java.sql.Time;


public class Event {

    Date date;
    Time time;
    String location, title,eventType;
    Reminder reminder[]; // array of reminders
    int reminders; // how many reminders


    public Event(Date date, Time time, String location, String eventType, String title) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
        this.title = title;
        Date d=date;
        int type=0;   // how many reminders to create determined by the event type
        if(eventType=="Test")
            type=3;
        else if(eventType=="Assignment")
            type=2;
        else type=1;

        Time t=new Time(0); // ### need to decide about the reminder time configuration
        t.setHours(12);
        t.setMinutes(0);
        t.setSeconds(0);
        int day =date.getDate();
        reminder = new Reminder[3];
        day=day-1;          //create one reminder for one day before the event
        d.setDate(day);    //// ### UNCOMPLETED: resolve decrementing issue (need to decrement date and not only day)
        for(reminders=0; reminders<type;)
        {
            addReminder(d,t,title);
            day=day-7;
            d.setDate(day);         ////###UNCOMPLETED: resolve decrementing issue (need to decrement date and not only day)
            // the amount of decrementing should be deteremined
        }


    }

    public void addReminder(Date d, Time t, String desc) // add reminder to the array
    {
        reminder[reminders]=new Reminder(d,title,t);
        reminders=reminders+1;
    }

}
