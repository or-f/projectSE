package com.example.user.projectse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "EventsManager";

    protected static final String TABLE_EVENTS = "events";

    public static final String EVENT_ID = "id";
    public static final String EVENT_NAME = "EventName";
    public static final String EVENT_LOC = "EventLocation";
    public static final String EVENT_TIME = "EventTime";
    public static final String EVENT_DATE = "EventDate";
    
    public static final String EVENT_TYPE = "event_type";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + EVENT_ID + " INTEGER PRIMARY KEY," + EVENT_NAME + " TEXT,"+EVENT_LOC + " TEXT,"+ EVENT_DATE + "TEXT"
                + EVENT_TIME + " TEXT, " + EVENT_TYPE + " TEXT)";         // create table with fields: event id(int), event name(string),
        db.execSQL(CREATE_EVENTS_TABLE);
    }   ///###### change table values


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }
}
