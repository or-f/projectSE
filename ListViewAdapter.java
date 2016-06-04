package com.example.user.projectse;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Nenezoid on 6/4/2016.
 */
public class ListViewAdapter extends CursorAdapter {

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
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvId = (TextView) view.findViewById(R.id.idTxt);
        TextView tvName = (TextView) view.findViewById(R.id.nameTxt);
        TextView tvLocation = (TextView) view.findViewById(R.id.locTxt);
        TextView tvDate = (TextView) view.findViewById(R.id.dateTxt);
        TextView tvTime = (TextView) view.findViewById(R.id.timeTxt);
        TextView tvType = (TextView) view.findViewById(R.id.typeTxt);
        // Extract properties from cursor
        String id = cursor.getString(0);
        String title = cursor.getString(1);
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
    }
}
