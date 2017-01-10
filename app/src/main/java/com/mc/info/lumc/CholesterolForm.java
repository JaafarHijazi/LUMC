package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CholesterolForm extends AppCompatActivity {

    private Bundle extras;
    private Patient p;

    Button save;
    EditText valueTriglycerides,valueCholesterol,valueLDL,valueHDL,date;
    TextView examName,textNameTriglycerides,textNameCholesterol,textNameLDL,textNameHDL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cholesterol_form);

        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("patient");

        save = (Button) findViewById(R.id.btnInsertCholesterol);
        examName = (TextView) findViewById(R.id.txtCholesterolTitle);
        valueCholesterol = (EditText) findViewById(R.id.cholesterol_form_Cholesterol_value) ;
        valueTriglycerides = (EditText) findViewById(R.id.cholesterol_form_Triglycerides_value) ;
        valueLDL = (EditText) findViewById(R.id.cholesterol_form_LDL_value) ;
        valueHDL = (EditText) findViewById(R.id.cholesterol_form_HDl_value) ;
        date = (EditText) findViewById(R.id.cholesterol_form_date) ;
        textNameCholesterol = (TextView) findViewById(R.id.cholesterol_form_Cholesterol_name) ;
        textNameTriglycerides = (TextView) findViewById(R.id.cholesterol_form_Triglycerides_name) ;
        textNameLDL = (TextView) findViewById(R.id.cholesterol_form_LDL_name) ;
        textNameHDL = (TextView) findViewById(R.id.cholesterol_form_HDl_name) ;


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Examination e;
                MedicalData cholesterol,triglycerides,ldl,hdl;

                cholesterol = new MedicalData(null,textNameCholesterol.getText().toString(),valueCholesterol.getText().toString());
                triglycerides = new MedicalData(null,textNameTriglycerides.getText().toString(),valueTriglycerides.getText().toString());
                ldl = new MedicalData(null,textNameLDL.getText().toString(),valueLDL.getText().toString());
                hdl = new MedicalData(null,textNameHDL.getText().toString(),valueHDL.getText().toString());
                e = new Examination(null,examName.getText().toString(),date.getText().toString(), Examination.examType.CHOLESTEROL);

                DBHandler.addExamination(p,e);

                DBHandler.addMedicalData(e,cholesterol);
                DBHandler.addMedicalData(e,triglycerides);
                DBHandler.addMedicalData(e,ldl);
                DBHandler.addMedicalData(e,hdl);

                finish();
            }
        });

    }
}
