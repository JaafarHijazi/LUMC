package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VitaminBForm extends AppCompatActivity {

    private Bundle extras;
    private Patient p;

    Button save;
    TextView examName,textName;
    EditText date ,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin_b_form);
        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("patient");

        save = (Button) findViewById(R.id.vitamin_b_form_vitamin_b_btnInsert);
        value = (EditText) findViewById(R.id.vitamin_b_form_vitamin_b_value) ;
        examName = (TextView) findViewById(R.id.txtVitaminBTitle);
        textName = (TextView) findViewById(R.id.vitamin_b_form_vitamin_b_name);
        date = (EditText) findViewById(R.id.vitamin_b_form_vitamin_b_date) ;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Examination e;
                MedicalData data = new MedicalData(null,textName.getText().toString(),value.getText().toString());
                e = new Examination(null,examName.getText().toString(),date.getText().toString(), Examination.examType.VITAMIN_B);
                DBHandler.addExamination(p,e);
                DBHandler.addMedicalData(e,data);
                finish();
            }
        });
    }

}
