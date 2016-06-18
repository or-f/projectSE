package com.example.user.projectse;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    ProfileHelper userProfile;
    EditText name_, university_, dep_, age_, email_;
    CheckBox cbRita, cbDia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        userProfile = new ProfileHelper(this);  // database for user info
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = getApplicationContext();
                    //input testing goes here

                    if (name_.getText().length() < 1)    // validate name
                        Toast.makeText(context, "Enter Title!", Toast.LENGTH_LONG).show();

                    //Add age testings for int

                    String s = age_.getText().toString(); // An ugly solution for the casting problem invokes from getting the age to TextView
                    if (s.compareTo("") == 0 || s.length() < 1)
                        try {
                            int age2 = Integer.parseInt(s);
                        } catch (Exception e) {
                            Toast.makeText(context, "please enter a valid age", Toast.LENGTH_LONG).show();
                        }
                    else {
                        int age2 = Integer.parseInt(s);
                        Student student = new Student(name_.getText().toString(), university_.getText().toString(), dep_.getText().toString(), age2, email_.getText().toString());
                        Toast.makeText(context, " Name: " + student.name + " location: " + student.university + " type: " + student.deparpment + " Date: " + student.age, Toast.LENGTH_LONG).show();

                        if (cbRita.isChecked()) { // Fetching for the checkboxs
                            student.hasRitalin = 1;
                        }

                        if (cbDia.isChecked()) {
                            student.hasDiagnose = 1;
                        }
                        //Here we're gonna call the DB function to add all that info into a single row
                        userProfile.insertProfile(student);
                        finish();
                    }
                }
            });
        }
        name_ = (EditText) findViewById(R.id.stdName);
        university_ = (EditText) findViewById(R.id.stdInstitude);
        dep_ = (EditText) findViewById(R.id.stdDep);
        age_ = (EditText) findViewById((R.id.stdAge));
        email_ = (EditText) findViewById((R.id.stdEmail));
        cbRita = (CheckBox) findViewById((R.id.cbRit));
        cbDia = (CheckBox) findViewById((R.id.cbDiag));
    }
}