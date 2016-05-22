package com.example.user.projectse;


import java.sql.Date;
import java.sql.Time;

public class Reminder {
    Date date;
    Time time;
    String description;

    public Reminder(Date date,String des) {

        this.date = date;
        description=des;
        time.setHours(12);      // ### deafault time set to 12:00 --> need to decide
        time.setMinutes(0);
        time.setSeconds(0);

    }
    public Reminder(Date date,String des,Time t) {

        this.date = date;
        description=des;
        time=t;

    }
}
