package com.example.user.projectse;

        import android.app.Activity;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.TextView;

        import java.util.List;


public class EventsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Event> EventList;

    public EventsAdapter(Activity activity, List<Event> contacts){

        this.activity = activity;
        this.EventList = contacts;
    }

    @Override
    public int getCount() {
        return EventList.size();
    }

    @Override
    public Object getItem(int position) {
        return EventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.contact_entry, null);

        TextView title= (TextView) convertView.findViewById(R.id.title);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);
        TextView eventtype = (TextView) convertView.findViewById(R.id.eventtype);
        Button delete = (Button) convertView.findViewById(R.id.delete);

        Event list = EventList.get(position);

        title.setText(list.getTitle());
        location.setText(list.getLocation());
        eventtype.setText(list.getEventType());
        time.setText(list.getTime().toString());
        date.setText(list.getDate().toString());
        delete.setOnClickListener(new ListItemClickListener(position, list));

        return convertView;
    }


    private class ListItemClickListener implements View.OnClickListener {

        int position;
        Event list;

        public ListItemClickListener(int position, Event list){
            this.position = position;
            this.list = list;
        }

        @Override
        public void onClick(View v) {

            EventsHandler db = new EventsHandler(activity);
            db.deleteContact(list);
            EventList.remove(position);
            notifyDataSetChanged();
        }
    }
}
