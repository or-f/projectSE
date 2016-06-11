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
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ViewActivity extends Activity{


    DBHelper locdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ////setSupportActionBar(toolbar);
        locdb = new DBHelper(this);
        Cursor cursor = locdb.getAllEvents();
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

    public void populateListView()
    {
        ListView listView = (ListView) findViewById(R.id.listView);

        Cursor cur = locdb.getAllEvents();
        ListViewAdapter dataAdapter = new ListViewAdapter(this, cur,0);
        listView.setAdapter(dataAdapter);
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
            return LayoutInflater.from(context).inflate(R.layout.list_layout, parent, false);
        }

        @Override
        public void bindView(final View view, final Context context, final Cursor cursor) {
            // Find fields to populate in inflated template
            tmpdb = new DBHelper(context);
            Button EditRowBut = (Button) view.findViewById(R.id.buttonEdit);
            Button DelRowBut = (Button) view.findViewById(R.id.buttonDel);
            final TextView tvId = (TextView) view.findViewById(R.id.idTxt);
            TextView tvName = (TextView) view.findViewById(R.id.nameTxt);
            TextView tvLocation = (TextView) view.findViewById(R.id.locTxt);
            TextView tvDate = (TextView) view.findViewById(R.id.dateTxt);
            TextView tvTime = (TextView) view.findViewById(R.id.timeTxt);
            TextView tvType = (TextView) view.findViewById(R.id.typeTxt);
            // Extract properties from cursor
            final String id = cursor.getString(0);
            final String title = cursor.getString(1);
            String location = cursor.getString(2);
            String date = cursor.getString(3);
            String time = cursor.getString(4);
            String type = cursor.getString(5);
            // Populate fields with extracted properties
            tvId.setText(id);
            tvName.setText(title);
            tvLocation.setText(location);
            tvDate.setText(date);
            tvTime.setText(time);
            tvType.setText(type);
            DelRowBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int r = cursor.getInt(cursor.getColumnIndex("_id"));
                    int delete = Integer.parseInt(id);
                    tmpdb.deletePerson(delete);
                    notifyDataSetChanged();
                    Toast.makeText(context, title + " was deleted", Toast.LENGTH_LONG).show();
populateListView();
                }
            });
            EditRowBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
