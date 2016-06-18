package com.example.user.projectse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

        DBHelper db;
        ProfileHelper pdb;

    FrameLayout layout;
    TextView tv;
    ViewGroup.LayoutParams params;
    Button but_sign;
    ImageView sign;
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dashboard_layout);
            db = new DBHelper(this);
            pdb=new ProfileHelper(this);

            final Cursor cursor = db.getAllEvents();
            /**Creating all buttons instances*/
            // Dashboard News feed button
            but_sign=(Button) findViewById(R.id.but_sign);
            layout = (FrameLayout) findViewById(R.id.popup);
            but_sign.setVisibility(View.INVISIBLE);
            layout.setVisibility(View.INVISIBLE);
            sign=(ImageView)findViewById(R.id.sign_img);
            sign.setVisibility(View.INVISIBLE);
            final Button btn_add = (Button) findViewById(R.id.add_event_but);
            // Dashboard Friends button
            final Button btn_view = (Button) findViewById(R.id.view_but);
            // Dashboard Messages button

            // Dashboard Events button
            Button btn_profile = (Button) findViewById(R.id.btn_Profile);
            // Dashboard Photos button
            Button btn_settings = (Button) findViewById(R.id.settings_but);

            if(pdb.isEmpty()==true)
            {
                but_sign.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                sign.setVisibility(View.VISIBLE);

                btn_add.setVisibility(View.INVISIBLE);

                btn_view.setVisibility(View.INVISIBLE);
                but_sign.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Launching News Feed Screen
                        Context context = getApplicationContext();
                        Intent i = new Intent(context, ProfileActivity.class);

                        btn_add.setVisibility(View.VISIBLE);

                        btn_view.setVisibility(View.VISIBLE);
                        but_sign.setVisibility(View.INVISIBLE);
                        layout.setVisibility(View.INVISIBLE);
                        sign.setVisibility(View.INVISIBLE);
                        startActivity(i);
                    }
                });
            }
else {

                btn_add.setVisibility(View.VISIBLE);

                btn_view.setVisibility(View.VISIBLE);
                but_sign.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.INVISIBLE);
                sign.setVisibility(View.INVISIBLE);
                /**
                 * Handling all button click events
                 * */
                // Listening to Add button click
                btn_add.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Launching News Feed Screen
                        Context context = getApplicationContext();
                        Intent i = new Intent(context, NewEventActivity.class);
                        startActivity(i);
                    }
                });

                // Listening View Events button click
                btn_view.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Launching News Feed Screen
                        Context context = getApplicationContext();
                        Intent i = new Intent(context, ViewActivity.class);
                        startActivity(i);
                    }
                });

                // Listening to edit profile button click
                btn_profile.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Launching News Feed Screen
                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(i);
                    }
                });

                // Listening to settings button click
                btn_settings.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // Launching News Feed Screen
                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        startActivity(i);

                    }
                });

            }
        }

    }

