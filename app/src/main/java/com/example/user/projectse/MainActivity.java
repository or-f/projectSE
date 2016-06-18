package com.example.user.projectse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

        DBHelper db;
        ProfileHelper pdb;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.dashboard_layout);
            db = new DBHelper(this);
            pdb=new ProfileHelper(this);
            final Cursor cursor = db.getAllEvents();
            /*
            if(pdb.isEmpty())
            {
                Context context =  getApplicationContext();
                Intent i = new Intent(context, ViewActivity.class);
                startActivity(i);
            }*/

            /**Creating all buttons instances*/
            // Dashboard News feed button
            Button btn_add = (Button) findViewById(R.id.add_event_but);
            // Dashboard Friends button
            Button btn_view = (Button) findViewById(R.id.view_but);

            // Dashboard Messages button
            Button btn_messages = (Button) findViewById(R.id.btn_messages);

            // Dashboard Places button
            Button btn_edit = (Button) findViewById(R.id.edit_but);

            // Dashboard Events button
            Button btn_profile = (Button) findViewById(R.id.btn_Profile);

            // Dashboard Photos button
            Button btn_settings = (Button) findViewById(R.id.settings_but);

            /**
             * Handling all button click events
             * */
            // Listening to Add button click
            btn_add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Context context=  getApplicationContext();
                    Intent i = new Intent(context, NewEventActivity.class);
                    startActivity(i);
                }
            });

            // Listening View Events button click
            btn_view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Context context =  getApplicationContext();
                    Intent i = new Intent(context, ViewActivity.class);
                    startActivity(i);


                }
            });

            // Listening Messages button click
            btn_messages.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Context context=  getApplicationContext();
                    Toast.makeText(context,"a",Toast.LENGTH_LONG).show();
                    Cursor s= db.getAllEvents();
                    Toast.makeText(context,"b",Toast.LENGTH_LONG).show();
                    String str=s.getString(1);
                    Toast.makeText(context,str,Toast.LENGTH_LONG).show();
                    //Intent i = new Intent(context,NewEventActivity.class);
                   // startActivity(i);
                }
            });

            // Listening to Places button click
            btn_edit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // Launching News Feed Screen
                    Intent i = new Intent(getApplicationContext(), NewEventActivity.class);
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

            // Listening to Photos button click
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

