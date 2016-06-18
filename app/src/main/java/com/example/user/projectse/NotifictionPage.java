package com.example.user.projectse;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by user on 18/06/2016.
 */
public class NotifictionPage extends Activity {
    DBHelper db;

    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_activity);
        Intent intent=getIntent();
        db=new DBHelper(this);
        Bundle extras=intent.getExtras();
        if(extras != null){
            String title = extras.getString("title");
            TextView titlet = (TextView) findViewById(R.id.titlet);
            titlet.setText(title);
            Cursor c=db.getEvent(title);
            if(c.moveToFirst()) {
                TextView eventtype = (TextView) findViewById(R.id.type);
                TextView datesyn = (TextView) findViewById(R.id.datelink);
                eventtype.setText(c.getString(c.getColumnIndex("type")));
                if (eventtype.toString() == "Assignment")
                    datesyn.setText(" Due to: ");
                else
                    datesyn.setText(" on: ");
                // extract the extra-data in the Notification

                // extract the extra-data in the Notification
                TextView datet = (TextView) findViewById(R.id.date);
                datet.setText(c.getString(c.getColumnIndex("date")));
                // extract the extra-data in the Notification
                TextView timet = (TextView) findViewById(R.id.time);
                timet.setText(c.getString(c.getColumnIndex("time")));
                // extract the extra-data in the Notification
                TextView loct = (TextView) findViewById(R.id.location);
                loct.setText(c.getString(c.getColumnIndex("location")));
            }
        }

        }


    }