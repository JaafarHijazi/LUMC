package com.mc.info.lumc;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PatientInfo extends AppCompatActivity {

    private int index;
    private MyDatabaseHandler dbHandler = new MyDatabaseHandler(this,null,null,1);
    private TextView txt,call;

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
            call = (TextView) findViewById(R.id.activity_doctor_info_phone);
            call.setText(p.getPhone());

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+call.getText().toString()));
                    startActivity(callIntent);

                }
            });


            txt = (TextView) findViewById(R.id.activity_doctor_info_address);
            txt.setText(p.getAddress().toString());
            txt = (TextView) findViewById(R.id.activity_doctor_info_email);
            txt.setText(p.getEmail());

            txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setType("message/rfc822");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{txt.getText().toString()});
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
                        startActivity(Intent.createChooser(emailIntent,"Sending Email"));
                    } catch (ActivityNotFoundException ex){
                        Toast toast=   Toast.makeText(PatientInfo.this,"Cannot Connect ",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

            });

        }
    }
}