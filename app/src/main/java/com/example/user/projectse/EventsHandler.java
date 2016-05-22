package com.example.user.projectse;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import java.sql.Date;
        import java.sql.Time;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.List;

public class EventsHandler {

    private SQLiteHelper dbHelper;

    public EventsHandler(Context context) {
        dbHelper = new SQLiteHelper(context);
    }


    int addContact(Event events) {     //change name to addEvent

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.EVENT_NAME, events.getTitle());
        values.put(dbHelper.EVENT_LOC, events.getLocation()); ///#########!!!!
        //////////////////////////////////////////////////////////////////////
        values.put(dbHelper.EVENT_TYPE, events.getEventType());
        values.put(dbHelper.EVENT_TIME, events.getTime().toString());
        values.put(dbHelper.EVENT_DATE, events.getDate().toString());

        long insertId = db.insert(dbHelper.TABLE_EVENTS, null, values);
        db.close();
        return (int)insertId;           // returns the ID event assigned by the database
    }


    Event getContact(int id) { //////////############!!!!!!!!
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Event events;
        Cursor cursor = db.query(dbHelper.TABLE_EVENTS, new String[] { dbHelper.EVENT_ID,
                        dbHelper.EVENT_NAME, dbHelper.EVENT_LOC, dbHelper.EVENT_TYPE,dbHelper.EVENT_DATE.toString(),dbHelper.EVENT_TIME.toString()}, dbHelper.EVENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null); ///// check tostring() function, maybe should be getDate.tostring()


        if (cursor != null)
            cursor.moveToFirst();
        try {
            Date date = (Date) format.parse(cursor.getString(0));
            format = new SimpleDateFormat("HH:mm");
            Time time = (Time)format.parse(cursor.getString(1));
            events = new Event(date, time, cursor.getString(2), cursor.getString(5),cursor.getString(1),Integer.parseInt(cursor.getString(0)));
            return events;
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return null; /////////// fix try/catch

    }


    public List<Event> getAllContacts()  {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Event> eventsList = new ArrayList<Event>();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_EVENTS;
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {     //// change the use of constructor
            do {

                Date date = null;
                try {
                    date = (Date) format.parse(cursor.getString(0));

                format = new SimpleDateFormat("HH:mm");
                Time time = (Time) format.parse(cursor.getString(1));
                Event events = new Event(date, time, cursor.getString(2), cursor.getString(5), cursor.getString(1), Integer.parseInt(cursor.getString(0)));
                eventsList.add(events);
            }
                catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }

        return eventsList;
    }


    public int updateContact(Event events) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.EVENT_NAME, events.getTitle());
        values.put(dbHelper.EVENT_TIME, events.getTime().toString()); //// check if tostring is working
        values.put(dbHelper.EVENT_TYPE, events.getEventType());
        values.put(dbHelper.EVENT_LOC, events.getLocation());
        values.put(dbHelper.EVENT_DATE ,events.getDate().toString()); //// check if tostring is working
        return db.update(dbHelper.TABLE_EVENTS, values, dbHelper.EVENT_ID + " = ?",
                new String[] { String.valueOf(events.getId()) });
    }


    public void deleteContact(Event events) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_EVENTS, dbHelper.EVENT_ID + " = ?",
                new String[] { String.valueOf(events.getId()) });
        db.close();
    }


    public int getContactsCount() {

        String countQuery = "SELECT  * FROM " + dbHelper.TABLE_EVENTS;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}