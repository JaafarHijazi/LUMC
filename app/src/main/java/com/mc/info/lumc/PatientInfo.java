package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PatientInfo extends AppCompatActivity {

    private int index;
    private MyDatabaseHandler dbHandler = new MyDatabaseHandler(this,null,null,1);
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            index = extras.getInt("take");
            Patient p = dbHandler.getPatientById(index);
            txt = (TextView) findViewById(R.id.activity_patient_info_FirstName);
            txt.setText(p.getFirstName());
            txt = (TextView) findViewById(R.id.activity_patient_info_LastName);
            txt.setText(p.getLastName());
            txt = (TextView) findViewById(R.id.activity_patient_info_Phone);
            txt.setText(p.getPhone());
            txt = (TextView) findViewById(R.id.activity_patient_info_Address);
            txt.setText(p.getAddress().toString());
            txt = (TextView) findViewById(R.id.activity_patient_info_email);
            txt.setText(p.getEmail());
        }
    }
}

