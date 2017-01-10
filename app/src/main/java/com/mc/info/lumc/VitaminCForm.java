package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VitaminCForm extends AppCompatActivity {

    private Bundle extras;
    private Patient p;

    Button save;
    EditText value,date;
    TextView examName,textName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin_c_form);

        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("patient");

        save = (Button) findViewById(R.id.vitamin_c_form_vitamin_c_btnInsert);
        value = (EditText) findViewById(R.id.vitamin_c_form_vitamin_c_value) ;
        examName = (TextView) findViewById(R.id.txtVitaminCTitle);
        textName = (TextView) findViewById(R.id.vitamin_c_form_vitamin_c_name);
        date = (EditText) findViewById(R.id.vitamin_c_form_vitamin_c_date) ;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Examination e;
                MedicalData data = new MedicalData(null,textName.getText().toString(),value.getText().toString());
                e = new Examination(null,examName.getText().toString(),date.getText().toString(), Examination.examType.VITAMIN_C);
                DBHandler.addExamination(e);
                DBHandler.addMedicalData(e,data);
                finish();
            }
        });
    }
}
