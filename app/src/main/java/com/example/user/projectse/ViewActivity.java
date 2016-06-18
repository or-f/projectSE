package com.example.user.projectse;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewActivity extends Activity implements AdapterView.OnItemSelectedListener{
    String temp;
    DBHelper locdb;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    public AlarmManager alarmManager;
    Spinner spinner;
    String typeOfEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);
     //   Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ////setSupportActionBar(toolbar);
        locdb = new DBHelper(this);
        Cursor cursor = locdb.getAllupcomingEvents();
        populateListView();
         alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        spinner = (Spinner) findViewById(R.id.filter);// link variable spinner to the spinner on content_main
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Select Event Type");
        categories.add("Test");
        categories.add("Assignment");
        categories.add("Quiz");
        categories.add("Personal");
        categories.add("Other");
        spinner.setPrompt("select event type");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
       //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void populateListView()
    {
        ListView listView = (ListView) findViewById(R.id.listView);

        Cursor cur = locdb.getAllupcomingEvents();
        ListViewAdapter dataAdapter = new ListViewAdapter(this, cur,0);
        listView.setAdapter(dataAdapter);
    }


    public void populateListViewbyType(String type)
    {
        ListView listView = (ListView) findViewById(R.id.listView);

        Cursor cur =locdb.getAllupcomingAssignments(typeOfEvent);
        ListViewAdapter dataAdapter = new ListViewAdapter(this, cur,0);
        listView.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        typeOfEvent = parent.getItemAtPosition(position).toString();
        if(typeOfEvent.length()>10)
            populateListView();
        else
        populateListViewbyType(typeOfEvent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class ListViewAdapter extends CursorAdapter {
        DBHelper tmpdb;
        public ListViewAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        public ListViewAdapter(Context context, Cursor c, int flags) {
            super(context, c, flags);
        }
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View newView=LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
            Calendar c=Calendar.getInstance();
            c.add(Calendar.MONTH,-1);
            c.add(Calendar.DAY_OF_MONTH,1);
            Date current = new Date(c.getTime().getTime());
            if(cursor.getString(cursor.getColumnIndex("date")).compareTo(current.toString())<=0) {
                newView.setBackgroundColor(Color.rgb(169, 5, 0));
                return newView ;
            }
            c.add(Calendar.DAY_OF_MONTH,1);
            current = new Date(c.getTime().getTime());
            if(cursor.getString(cursor.getColumnIndex("date")).compareTo(current.toString())<=0) {
                newView.setBackgroundColor(Color.rgb(255, 28, 0));
                return newView ;
            }
            c.add(Calendar.DAY_OF_MONTH,2);
            current = new Date(c.getTime().getTime());
            if(cursor.getString(cursor.getColumnIndex("date")).compareTo(current.toString())<=0) {
                newView.setBackgroundColor(Color.rgb(255, 126, 0));
                return newView ;
            }
            c.add(Calendar.DAY_OF_MONTH,2);
            current = new Date(c.getTime().getTime());
            if(cursor.getString(cursor.getColumnIndex("date")).compareTo(current.toString())<=0) {
                newView.setBackgroundColor(Color.rgb(73, 250, 0));
                return newView ;
            }
            c.add(Calendar.DAY_OF_MONTH,2);
            current = new Date(c.getTime().getTime());
            if(cursor.getString(cursor.getColumnIndex("date")).compareTo(current.toString())<=0) {
                newView.setBackgroundColor(Color.rgb(73, 255, 0));
                return newView ;
            }
            newView.setBackgroundColor(Color.rgb(15, 163, 37));
            return newView ;
        }

        @Override
        public void bindView(final View view, final Context context, final Cursor cursor) {
            // Find fields to populate in inflated template
            tmpdb = new DBHelper(context);
            Button EditRowBut = (Button) view.findViewById(R.id.buttonEdit);
            Button DelRowBut = (Button) view.findViewById(R.id.buttonDel);
           // final TextView tvId = (TextView) view.findViewById(R.id.idTxt);
            TextView tvName = (TextView) view.findViewById(R.id.nameTxt);
            TextView tvLocation = (TextView) view.findViewById(R.id.locTxt);
            TextView tvDate = (TextView) view.findViewById(R.id.dateTxt);
            TextView tvTime = (TextView) view.findViewById(R.id.timeTxt);
            TextView tvType = (TextView) view.findViewById(R.id.typeTxt);

            // Extract properties from cursor
            final String id = cursor.getString(0);
            final String title = cursor.getString(1);
            String location = cursor.getString(2);
            Date date = Date.valueOf(cursor.getString(3));
            Calendar c=Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MONTH,1);

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            String date2=fmt.format(c.getTime());
            String time = cursor.getString(4);
            String type = cursor.getString(5);
            // Populate fields with extracted properties
            //tvId.setText(id);
            tvName.setText(title);
            tvLocation.setText(location);
            tvDate.setText(date2);
            tvTime.setText(time);
            tvType.setText(type);
            DelRowBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int delete = Integer.parseInt(id);
                    Cursor cursor = tmpdb.getEvent(delete);
                    int index = cursor.getColumnIndex("reminders");
                    if (cursor.moveToFirst()) {
                        String array = cursor.getString(index); // 0 matches the index of NUMBER in your projection.
                        if (array != null && array.length() > 1) {
                            if (array.length() > 1) {
                                String ids = "";
                                String deleteid = "";
                                for (int i = 1; i < array.length() - 1; i++) {

                                    if(array.charAt(i) != ',' && array.charAt(i) != '*')
                                    {
                                        ids = ids.concat(String.valueOf(array.charAt(i)));
                                        deleteid = deleteid.concat(String.valueOf(array.charAt(i)));
                                    }
                                    else
                                    {
                                        int d = Integer.parseInt(deleteid);
                                        cancelNotifications(d);
                                        deleteid = "";
                                    }
                                }
                                //s = ids;
                                Toast.makeText(getApplicationContext(), "deleted: " + ids, Toast.LENGTH_LONG);
                            }
                        }
                    }
                    tmpdb.deletePerson(delete, temp);
                    //tmpdb.deletePerson(delete);
                    notifyDataSetChanged();
                    Toast.makeText(context, title + " was deleted, reminders: " + temp + " deleted", Toast.LENGTH_LONG).show();
                    populateListView();
                }
            });
            EditRowBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int edit = Integer.parseInt(id);
                    tmpdb.deletePerson(edit,temp);
                    //tmpdb.deletePerson(edit);
                    notifyDataSetChanged();
                    Intent i = new Intent(getApplicationContext(), NewEventActivity.class);
                    startActivity(i);
                    //tmpdb.updateEvent();
                    finish();

                    //populateListView();
                }
            }

            );
        }
    }
    public void cancelNotifications(int not_id){

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(ViewActivity.this, AlarmReceiver.class);
        pendingIntent =PendingIntent.getBroadcast(ViewActivity.this, not_id, alarmIntent, 0);
        alarmManager.cancel(pendingIntent);
    }
}
