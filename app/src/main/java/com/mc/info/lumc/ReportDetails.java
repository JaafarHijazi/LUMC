package com.mc.info.lumc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;

public class ReportDetails extends AppCompatActivity implements Serializable{
    Bundle extras;
    TextView txtName;
    Patient p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("take");
        txtName = (TextView) findViewById(R.id.activity_report_details_name);
        txtName.setText(p.getFirstName() + " " + p.getLastName());
    }
}
