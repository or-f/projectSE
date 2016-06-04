package com.example.user.projectse;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends Activity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ////setSupportActionBar(toolbar);
        populateListView();

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

    private void populateListView()
    {
        DBHelper locdb = new DBHelper(this);
        Cursor cursor = locdb.getAllEvents();
        //Toast code for printing the whole db
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "no events found", Toast.LENGTH_LONG).show();
            return;}
        StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext()) {
            buffer.append("ID:" + cursor.getString(0) + "  Title: " + cursor.getString(1));
        }
        Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
        //end of toast code

        ListView listView = (ListView) findViewById(R.id.listView);
        //String[] columns = new String[]{DBHelper.EVENT_COLUMN_ID, DBHelper.EVENT_COLUMN_TITLE, DBHelper.EVENT_COLUMN_LOCATION, DBHelper.EVENT_COLUMN_DATE, DBHelper.EVENT_COLUMN_TIME, DBHelper.EVENT_COLUMN_TYPE};
        //int[] to = new int[] {R.id.idTxt,R.id.nameTxt,R.id.locTxt,R.id.timeTxt,R.id.dateTxt,R.id.typeTxt};
        Cursor cur = locdb.getAllEvents();
        ListViewAdapter dataAdapter = new ListViewAdapter(this, cur,0);
        listView.setAdapter(dataAdapter);






    }



}
