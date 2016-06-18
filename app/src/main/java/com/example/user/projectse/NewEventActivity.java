package com.example.user.projectse;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NewEventActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Button dateBut,timeBut;
    int year_x=-1, month_x=-1, day_x=-1, hour_x=-1,minute_x=-1;
    static final int DIALOG_ID = 0;
    static final int DIALOG_ID2 = 1;
    Spinner spinner;
    TextView title_x, location_x;
    String typeOfEvent;
    DBHelper database;
    private TextView EventName, EventDate, EventType;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    int mNotificationCount;
    public AlarmManager alarmManager;
    Date d;
    static final String NOTIFICATION_COUNT = "notificationCount";
    private static final String TAG = "ALARM! ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        showDialogOnButtonClick();
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            mNotificationCount = savedInstanceState.getInt(NOTIFICATION_COUNT);
        }
        database = new DBHelper(this);
        //   A D D    B U T T O N ==fab
        // when clicking on the Add button a new event is created using the data from the user
        // the event object is passed to the database:
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() { //add button listener
                @Override
                public void onClick(View v) {  // onclick ADD button
                    Context context=  getApplicationContext();  //function for using Toast (for displaying messages on screen)
                    // verify all inputs are valid
                    if(title_x.getText().length()<1)    // validate title
                        Toast.makeText(context, "enter title", Toast.LENGTH_LONG).show();
                    else if(location_x.getText().length()<1)  // validate location
                        Toast.makeText(context, "enter location " , Toast.LENGTH_LONG).show();
                    else if(typeOfEvent.length()>14) // ensure event type was chosen
                        Toast.makeText(context, "select event type "+typeOfEvent , Toast.LENGTH_LONG).show();
                    else if(year_x== -1 || month_x==-1 || day_x==-1) // ensure a date was chosen
                        Toast.makeText(context, "choose date" , Toast.LENGTH_LONG).show();
                    else if(hour_x== -1 || minute_x==-1) // ensure time is chosen
                        Toast.makeText(context, "choose time " , Toast.LENGTH_LONG).show();

                    else        // if all inputs are valid create event object to send to database
                    {
                        int reminders_amount=6;
                        Time time=new Time(0);
                        time.setHours(hour_x);
                        time.setMinutes(minute_x);
                        time.setSeconds(0);/*
                        Date d=new Date(year_x,month_x,day_x);
                        d.setDate(day_x);
                        d.setMonth(month_x);
                        d.setYear(year_x-1900);*/
                        // create the Event object
                        Event event =new Event(d,time,location_x.getText().toString(),typeOfEvent,title_x.getText().toString());
                        //TEST to see the event created:
                       Toast.makeText(context, " title: "+event.title +" loction: " +  event.location +" type: "+ event.eventType+ " Date: " + event.date.toString()+ d.toString()+ " time: "+ event.time.toString(), Toast.LENGTH_LONG).show();
                        // send event object to database

                        Calendar c=Calendar.getInstance();
                        //c.set(d.getYear(), d.getMonth(), d.getDay(), time.getHours(), time.getMinutes());
                        c.setTime(d);
                        c.set(1, year_x); //field YEAR=1
                        c.set(2,month_x); //field month=1
                        c.set(5,day_x); //field DAY_OF_MONTH=5
                        c.set(11,hour_x);// HOUR_OF_DAY = 11;
                        c.set(12, minute_x);// MINUTE = 12;
                        c.set(13, 0);//SECOND = 13
                        c.set(14, 0);// MILLISECOND = 14;
                        c.add(Calendar.SECOND, -180);
                        for(int i=0;i<reminders_amount;i++)
                        {
                            updateUI();
                            event.addReminder(mNotificationCount);
                            setAlarm(c, event.getTitle());
                                c.add(Calendar.SECOND, 25);
                                incrementCount();
                        }
                        database.insertEvent(event);
                        //  return to main page
                        finish();
                    }
                }
            });
        }
        title_x= (EditText)findViewById(R.id.titleText); // link variable title_x to EditText title
        location_x= (EditText)findViewById(R.id.locationText);// link variable location_x to EditText location
        spinner = (Spinner) findViewById(R.id.spinnere);// link variable spinner to the spinner on content_main
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
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        typeOfEvent = parent.getItemAtPosition(position).toString();  // save the type of the event
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    public void showDialogOnButtonClick() /// buttons function
    {
        dateBut = (Button)findViewById(R.id.dateButton);
        timeBut=(Button)findViewById(R.id.timeButton);
        timeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID2);
            }
        });
        dateBut.setOnClickListener (
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID) {       // date picker dialog
            DatePickerDialog temp= new DatePickerDialog(this, dpicker, year_x, month_x, day_x);
            Calendar currentDate = Calendar.getInstance();  //new calendar object
            temp.getDatePicker().setMinDate(currentDate.getTimeInMillis()); // set the minimun available date for today
            return temp;    //return the date chosen
        }
        if(id == DIALOG_ID2) {      // time picker dialog
            return new TimePickerDialog(this,1, tpicker, hour_x,minute_x,true);
        }
        return null;
    }
    // time listener- after user picked time
    protected TimePickerDialog.OnTimeSetListener tpicker = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour_x=hourOfDay;
            minute_x=minute;
            timeBut.setText( hour_x+ ":" + minute_x);
        }
    };

    private DatePickerDialog.OnDateSetListener dpicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        // date listener- after user picked a date
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x=year;
            month_x= monthOfYear;
            day_x= dayOfMonth;
            dateBut.setText( day_x + " / " + (month_x+1) + " / " + year_x);
            String str="";
            str=str.concat(Integer.toString(year)+"-"+Integer.toString(monthOfYear)+"-"+Integer.toString(dayOfMonth) );
            d=Date.valueOf(str);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onStart(){
        super.onStart();
        updateUI();

    }
    public void setAlarm(Calendar c,String s){
        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmIntent = new Intent(NewEventActivity.this, AlarmReceiver.class);
        alarmIntent.putExtra("title",s);    // add title
        alarmIntent.putExtra("date",c.getTime().getTime()); // add date
        alarmIntent.putExtra("count", mNotificationCount); // save intent id
        pendingIntent =PendingIntent.getBroadcast(NewEventActivity.this, mNotificationCount, alarmIntent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTime().getTime(), pendingIntent); // set reminder for this date and time
        Long time=c.getTime().getTime();
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateText = df2.format(time);
        //Toast.makeText(this, "NewEventActivity::setAlarm:: id's: "+s+" Alarm Scheduled for: "
        //        + dateText+ " notification count="+mNotificationCount,Toast.LENGTH_LONG).show();
    }
    private int getInterval(){
        int seconds = 60;
        int milliseconds = 1000;
        return (seconds  * milliseconds);
    }
    public void updateUI(){
        mNotificationCount =((MyAlarm) this.getApplication()).getNotificationCount();
    }

    public void incrementCount(){
        ((MyAlarm) this.getApplication()).incrementCount();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(this.getIntent().getExtras() != null){
            //Toast.makeText(this,"NewEventActivity::onResume:: extras: " + this.getIntent().getExtras(), Toast.LENGTH_LONG).show();
            updateUI();
        }
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(NOTIFICATION_COUNT, mNotificationCount);
        super.onSaveInstanceState(savedInstanceState);
    }
}








