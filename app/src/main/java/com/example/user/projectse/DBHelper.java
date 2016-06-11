package com.example.user.projectse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper  extends SQLiteOpenHelper {

// define table values
    public static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;
    public static final String EVENT_TABLE_NAME = "event";
    public static final String EVENT_COLUMN_ID = "_id";
    public static final String EVENT_COLUMN_TITLE = "title";
    public static final String EVENT_COLUMN_LOCATION = "location";
    public static final String EVENT_COLUMN_DATE = "date";
    public static final String EVENT_COLUMN_TIME = "time";
    public static final String EVENT_COLUMN_TYPE= "type";
    // ## TO DO ##  need to add reminders

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    //when database file is created
    public void onCreate(SQLiteDatabase db) {
        // create table in database file
        db.execSQL("CREATE TABLE " + EVENT_TABLE_NAME + "(" +
                        EVENT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        EVENT_COLUMN_TITLE + " TEXT, " +
                        EVENT_COLUMN_LOCATION + " TEXT, " +
                        EVENT_COLUMN_DATE + " TEXT, " + EVENT_COLUMN_TIME + " TEXT, " + EVENT_COLUMN_TYPE + " TEXT  )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);
        onCreate(db);

    }

    public boolean insertEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COLUMN_TITLE, event.getTitle());
        contentValues.put(EVENT_COLUMN_LOCATION, event.getLocation());
        contentValues.put(EVENT_COLUMN_DATE, event.getDate().toString());
        contentValues.put(EVENT_COLUMN_TIME, event.getTime().toString());
        contentValues.put(EVENT_COLUMN_TYPE, event.getEventType());

        db.insert(EVENT_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COLUMN_TITLE, event.getTitle());
        contentValues.put(EVENT_COLUMN_LOCATION, event.getLocation());
        contentValues.put(EVENT_COLUMN_DATE, event.getDate().toString());
        contentValues.put(EVENT_COLUMN_TIME, event.getTime().toString());
        contentValues.put(EVENT_COLUMN_TYPE, event.getEventType());
        db.update(EVENT_TABLE_NAME, contentValues, EVENT_COLUMN_ID + " = ? ", new String[]{Integer.toString(event.getId())});
        return true;
    }

    public Cursor getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + EVENT_TABLE_NAME + " WHERE " +
                EVENT_COLUMN_ID + "=?", new String[] { Integer.toString(id) } );
        return res;
    }
    public Cursor getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + EVENT_TABLE_NAME, null );
        return res;
    }

    public Integer deletePerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Integer id_deleted= db.delete(EVENT_TABLE_NAME,
                EVENT_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) });

        return id_deleted;
    }



}
