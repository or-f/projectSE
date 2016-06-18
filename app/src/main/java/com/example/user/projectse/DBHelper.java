package com.example.user.projectse;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.Calendar;


public class DBHelper  extends SQLiteOpenHelper {

    // define table values
    public static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 9;
    public static final String EVENT_TABLE_NAME = "event";
    public static final String EVENT_COLUMN_ID = "_id";
    public static final String EVENT_COLUMN_TITLE = "title";
    public static final String EVENT_COLUMN_LOCATION = "location";
    public static final String EVENT_COLUMN_DATE = "date";
    public static final String EVENT_COLUMN_TIME = "time";
    public static final String EVENT_COLUMN_TYPE= "type";
    public static final String EVENT_REMINDERS= "reminders";
    private static final String TEMP_TABLE= "temp";
    // ## TO DO ##  need to add reminders
    private NotificationManager mgr;
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    //when database file is created
    public void onCreate(SQLiteDatabase db) {
        // create table in database file

        db.execSQL("CREATE TABLE " + EVENT_TABLE_NAME + "(" +
                        EVENT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        EVENT_COLUMN_TITLE + " TEXT, " +
                        EVENT_COLUMN_LOCATION + " TEXT, " +
                        EVENT_COLUMN_DATE + " TEXT, " + EVENT_COLUMN_TIME + " TEXT, " +
                        EVENT_COLUMN_TYPE + " TEXT, " + EVENT_REMINDERS + " TEXT  )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE_NAME);
      onCreate(db);
/*
        String ADDCOL= "ALTER TABLE "+EVENT_TABLE_NAME +" ADD COLUMN "+ EVENT_REMINDERS +" TEXT ";
        db.execSQL(ADDCOL);/*
        String TEMP_CREATE_CONTACTS_TABLE = "CREATE TABLE " + TEMP_TABLE + "(" +
                EVENT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                EVENT_COLUMN_TITLE + " TEXT, " +
                EVENT_COLUMN_LOCATION + " TEXT, " +
                EVENT_COLUMN_DATE + " TEXT, " + EVENT_COLUMN_TIME + " TEXT, " + EVENT_COLUMN_TYPE + " TEXT  )";
        db.execSQL(TEMP_CREATE_CONTACTS_TABLE);
        // Create an temporaty table that can store data of older version

        db.execSQL("INSERT INTO " + TEMP_TABLE + " SELECT " +  EVENT_COLUMN_ID + ", "
                +  EVENT_COLUMN_TITLE + ", " + EVENT_COLUMN_LOCATION + ", " +  EVENT_COLUMN_DATE +
                ", " +  EVENT_COLUMN_TIME + ", " +  EVENT_COLUMN_TYPE + " FROM " + EVENT_TABLE_NAME);

// Insert data into temporary table from existing older version database (that doesn't contains email //column)

        db.execSQL("DROP TABLE "+ EVENT_TABLE_NAME);
// Remove older version database table

        String CREATE_CONTACTS_TABLE =  "CREATE TABLE " + EVENT_TABLE_NAME + "(" +
                EVENT_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                EVENT_COLUMN_TITLE + " TEXT, " +
                EVENT_COLUMN_LOCATION + " TEXT, " +
                EVENT_COLUMN_DATE + " TEXT, " + EVENT_COLUMN_TIME + " TEXT, " + EVENT_COLUMN_TYPE
                + " TEXT " + EVENT_REMINDERS + "TEXT )";
        db.execSQL(CREATE_CONTACTS_TABLE);

// Create new table with email column
        db.execSQL("INSERT INTO " + EVENT_TABLE_NAME + " SELECT " +  EVENT_COLUMN_ID + ", "
                +  EVENT_COLUMN_TITLE + ", " +  EVENT_COLUMN_LOCATION + ", " +  EVENT_COLUMN_DATE
                + ", "  + EVENT_COLUMN_TIME + ", "+  EVENT_COLUMN_TYPE + " FROM " + TEMP_TABLE);
// Insert data ffrom temporary table that doesn't have email column so left it that column name as null.
        db.execSQL("DROP TABLE " + TEMP_TABLE);*/
    }

    public boolean insertEvent(Event event) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COLUMN_TITLE, event.getTitle());
        contentValues.put(EVENT_COLUMN_LOCATION, event.getLocation());
        contentValues.put(EVENT_COLUMN_DATE, event.getDate().toString());
        contentValues.put(EVENT_COLUMN_TIME, event.getTime().toString());
        contentValues.put(EVENT_COLUMN_TYPE, event.getEventType());
        contentValues.put(EVENT_REMINDERS, event.remindersIDtostring());
        db.insert(EVENT_TABLE_NAME, null, contentValues);
        db.close(); // Closing database connection
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
        contentValues.put(EVENT_REMINDERS, event.remindersIDtostring());
        db.update(EVENT_TABLE_NAME, contentValues, EVENT_COLUMN_ID + " = ? ", new String[]{Integer.toString(event.getId())});
        return true;
    }

    public Cursor getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EVENT_TABLE_NAME + " WHERE " +
                EVENT_COLUMN_ID + "=?", new String[]{Integer.toString(id) } );
        return res;

    }

    public Cursor getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + EVENT_TABLE_NAME +" ORDER BY "+ EVENT_COLUMN_DATE+" ASC ", null );
        return res;
    }

    public Cursor getAllupcomingEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH,-1);
        c.add(Calendar.DAY_OF_MONTH,-1);
        Date current = new Date(c.getTime().getTime());
        Cursor res = db.rawQuery( "SELECT * FROM " + EVENT_TABLE_NAME +" WHERE " +EVENT_COLUMN_DATE+ "> ?"+" ORDER BY "+ EVENT_COLUMN_DATE+" ASC ",  new String[] { current.toString()} );

        return res;
    }
    public Cursor getAllupcomingAssignments(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar c=Calendar.getInstance();
        c.add(Calendar.MONTH,-1);
        c.add(Calendar.DAY_OF_MONTH,-1);
        Date current = new Date(c.getTime().getTime());
        Cursor res = db.rawQuery( "SELECT * FROM " + EVENT_TABLE_NAME +" WHERE " +EVENT_COLUMN_DATE+
                "> ?"+ " AND "+ EVENT_COLUMN_TYPE + " =? " + " ORDER BY "+ EVENT_COLUMN_DATE+" ASC "
                ,  new String[] { current.toString(), type} );

        return res;
    }

    public Integer deletePerson(int id,String s) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer id_deleted= db.delete(EVENT_TABLE_NAME,
                EVENT_COLUMN_ID + " = ? ", new String[] { Integer.toString(id) });
        return id_deleted;
    }



}

