package com.example.user.projectse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProfileHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserProfile.db";
    private static final int DATABASE_VERSION = 1;
    public static final String PROFILE_TABLE_NAME = "profile";
    public static final String PROFILE_COLUMN_ID = "_id";
    public static final String PROFILE_COLUMN_NAME = "name";
    public static final String PROFILE_COLUMN_INSTITUDE = "institude";
    public static final String PROFILE_COLUMN_DEP = "dep";
    public static final String PROFILE_COLUMN_EMAIL = "mail";
    public static final String PROFILE_COLUMN_AGE= "age";
    public static final String PROFILE_COLUMN_RITALIN= "ritalin";
    public static final String PROFILE_COLUMN_DIAGNOSE= "diagnose";

    public ProfileHelper(Context context) {
        super(context, "UserProfile.db" , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PROFILE_TABLE_NAME + "(" +
                        PROFILE_COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        PROFILE_COLUMN_NAME + " TEXT, " +
                        PROFILE_COLUMN_INSTITUDE + " TEXT, " +
                        PROFILE_COLUMN_DEP + " TEXT, " +
                        PROFILE_COLUMN_AGE + " INTEGER,  " +
                        PROFILE_COLUMN_EMAIL + " TEXT, " +
                        PROFILE_COLUMN_RITALIN + " INTEGER, " +
                        PROFILE_COLUMN_DIAGNOSE + " INTEGER  )"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProfile(Student student) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PROFILE_COLUMN_NAME, student.getName());
        contentValues.put(PROFILE_COLUMN_INSTITUDE, student.getUnivercity());
        contentValues.put(PROFILE_COLUMN_DEP, student.getDeparpment());
        contentValues.put(PROFILE_COLUMN_AGE, student.getAge());
        contentValues.put(PROFILE_COLUMN_EMAIL, student.getEmailAdd());
        contentValues.put(PROFILE_COLUMN_RITALIN,student.getHasRitalin());
        contentValues.put(PROFILE_COLUMN_DIAGNOSE,student.getHasDiagnose());
        db.insert(PROFILE_TABLE_NAME, null, contentValues);
        return true;
    }
}