package com.example.user.projectse;

import java.sql.Date;
import java.sql.Time;


public class Event {
    int id;
    Date date;
    Time time;
    String location, title, eventType;
    int[] reminders_id; // array of reminders id
    int reminders; // how many reminders
    public Event(Date date, Time time, String location, String eventType, String title) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.eventType = eventType;
        this.title = title;
        reminders_id=new int[15];
        reminders = 0;
        id = -1;

    }

    public Event(Date date, Time time, String location, String eventType, String title, int id) {
        this.id = id;
        this.date = date;
        this.time = time;
        reminders = 0;
        this.location = location;
        this.eventType = eventType;
        this.title = title;

    }

    public void addReminder(int id) // add reminder to the array
    {
        reminders_id[reminders] =id;
        reminders = reminders + 1;
    }

    public String remindersIDtostring()
    {
        String str="";
        for(int i=0;i<reminders;i++)
        {
            str=str.concat(","+Integer.toString(reminders_id[i]) );
        }
        str=str.concat("*" );
        return str;
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


    public void setReminder(int[] reminder) {
        this.reminders_id = reminder;
    }

    public int getReminders() {
        return reminders;
    }

    public void setReminders(int reminders) {
        this.reminders = reminders;
    }
}
