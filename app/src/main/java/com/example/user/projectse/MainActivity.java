package com.example.user.projectse;

    import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dashboard_layout);

            /**
             * Creating all buttons instances
             * */
            // Dashboard News feed button
            Button btn_newsfeed = (Button) findViewById(R.id.add_event_but);

            // Dashboard Friends button
            Button btn_friends = (Button) findViewById(R.id.view_but);

            // Dashboard Messages button
            Button btn_messages = (Button) findViewById(R.id.btn_messages);

            // Dashboard Places button
            Button btn_places = (Button) findViewById(R.id.edit_but);

            // Dashboard Events button
            Button btn_events = (Button) findViewById(R.id.btn_events);

            // Dashboard Photos button
            Button btn_photos = (Button) findViewById(R.id.settings_but);

            /**
             * Handling all button click events
             * */

            // Listening to News Feed button click
            btn_newsfeed.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Context context=  getApplicationContext();
                    Intent i = new Intent(context, NewEventActivity.class);
                    startActivity(i);
                }
            });

            // Listening Friends button click
            btn_friends.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Context context=  getApplicationContext();
                    Intent i = new Intent(context, NewEventActivity.class);
                    startActivity(i);
                }
            });

            // Listening Messages button click
            btn_messages.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Context context=  getApplicationContext();
                    Intent i = new Intent(context,NewEventActivity.class);
                    startActivity(i);
                }
            });

            // Listening to Places button click
            btn_places.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Intent i = new Intent(getApplicationContext(), NewEventActivity.class);
                    startActivity(i);

                }
            });

            // Listening to Events button click
            btn_events.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Intent i = new Intent(getApplicationContext(), NewEventActivity.class);
                    startActivity(i);
                }
            });

            // Listening to Photos button click
            btn_photos.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Intent i = new Intent(getApplicationContext(), NewEventActivity.class);
                    startActivity(i);
                }
            });
        }
    }

