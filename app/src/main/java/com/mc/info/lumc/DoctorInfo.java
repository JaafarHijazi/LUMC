package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DoctorInfo extends AppCompatActivity {

    private int index;
    private MyDatabaseHandler dbHandler = new MyDatabaseHandler(this,null,null,1);
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        setTitle("Doctor's Page");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            index = extras.getInt("take");
            Doctor d = dbHandler.getDoctorById(index);
            txt = (TextView) findViewById(R.id.activity_doctor_info_firstName);
            txt.setText(d.getFirstName());
            txt = (TextView) findViewById(R.id.activity_doctor_info_lastName);
            txt.setText(d.getLastName());
            txt = (TextView) findViewById(R.id.activity_doctor_info_specialty);
            txt.setText(d.getSpecialty());
            txt = (TextView) findViewById(R.id.activity_doctor_info_phone);
            txt.setText(d.getPhone());
            txt = (TextView) findViewById(R.id.activity_doctor_info_address);
            txt.setText(d.getAddress().toString());
            txt = (TextView) findViewById(R.id.activity_doctor_info_email);
            txt.setText(d.getEmail());
        }
    }
}
