package com.mc.info.lumc;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class DoctorInfo extends AppCompatActivity {

    private int index;
    private MyDatabaseHandler dbHandler = new MyDatabaseHandler(this,null,null,1);
    private TextView txt,call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
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
            call = (TextView) findViewById(R.id.activity_doctor_info_phone);
            call.setText(d.getPhone());

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+call.getText().toString()));
                    startActivity(callIntent);

                }
            });


            txt = (TextView) findViewById(R.id.activity_doctor_info_address);
            txt.setText(d.getAddress().toString());
            txt = (TextView) findViewById(R.id.activity_doctor_info_email);
            txt.setText(d.getEmail());

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
                        Toast toast=   Toast.makeText(DoctorInfo.this,"Cannot Connect ",Toast.LENGTH_LONG);
                        toast.show();
                    }
                }

            });

        }
    }
}
