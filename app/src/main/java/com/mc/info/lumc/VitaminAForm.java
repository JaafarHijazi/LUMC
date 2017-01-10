package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VitaminAForm extends AppCompatActivity {

    private Bundle extras;
    private Patient p;

    Button save;
    EditText value,date;
    TextView examName,textName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin_a_form);

        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("patient");

        save = (Button) findViewById(R.id.vitamin_a_form_vitamin_a_btnInsert);
        value = (EditText) findViewById(R.id.vitamin_a_form_vitamin_a_value) ;
        examName = (TextView) findViewById(R.id.txtVitaminATitle);
        textName = (TextView) findViewById(R.id.vitamin_a_form_vitamin_a_name);
        date = (EditText) findViewById(R.id.vitamin_a_form_vitamin_a_date) ;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Examination e;
                MedicalData data = new MedicalData(null,textName.getText().toString(),value.getText().toString());
                e = new Examination(null,examName.getText().toString(),date.getText().toString(), Examination.examType.VITAMIN_A);
                DBHandler.addExamination(e);
                DBHandler.addMedicalData(e,data);
                finish();
            }
        });
    }

}
