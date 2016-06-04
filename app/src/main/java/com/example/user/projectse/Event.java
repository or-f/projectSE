package com.example.user.projectse;

import java.sql.Date;
import java.sql.Time;


public class Event {

    int id;
    Date date;
    Time time;
    String location, title, eventType;
    Reminder reminder[]; // array of reminders
    int reminders; // how many reminders


    public Event(Date date, Time time, String location, String eventType, String title) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
        this.title = title;
       /* Date d=date;
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
        }*/
         id=-1;

    }
    public Event(Date date, Time time, String location, String eventType, String title,int id) {
        this.id=id;
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
        this.title = title;
        /*Date d=date;
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
        id=-1;*/

    }
    public void addReminder(Date d, Time t, String desc) // add reminder to the array
    {
        reminder[reminders]=new Reminder(d,title,t);
        reminders=reminders+1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Reminder[] getReminder() {
        return reminder;
    }

    public void setReminder(Reminder[] reminder) {
        this.reminder = reminder;
    }

    public int getReminders() {
        return reminders;
    }

    public void setReminders(int reminders) {
        this.reminders = reminders;
    }
}
