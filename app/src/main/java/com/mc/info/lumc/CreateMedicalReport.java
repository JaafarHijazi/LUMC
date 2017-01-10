package com.mc.info.lumc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class CreateMedicalReport extends AppCompatActivity implements Serializable {

    private Button btnAdd,
                   btnSave;
    private Spinner spTypesList;
    private EditText txtPrescribed, txtNotes;
    private ListView lvPrescriptions;
    private ArrayAdapter<String> adapter;
    private Bundle extras;
    private MedicalReport mr;
    private Patient p;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medical_report);
        spTypesList = (Spinner)findViewById(R.id.activity_create_prescription_typeList);
        txtPrescribed = (EditText) findViewById(R.id.activity_create_prescription_prescribed);
        txtNotes = (EditText) findViewById(R.id.activity_create_prescription_notes);
        btnSave = (Button) findViewById(R.id.activity_create_prescription_saveItem);
        lvPrescriptions = (ListView) findViewById(R.id.activity_create_prescription_listOfPrescriptions);
        adapter = new ArrayAdapter<>(this,R.layout.prescription_item,R.id.prescription_item_prescribe);
        lvPrescriptions.setAdapter(adapter);
        extras = getIntent().getExtras();
        if(extras!=null){
            mr = (MedicalReport) extras.getSerializable("take");
            p = (Patient) extras.getSerializable("patient");
        }
    }

    public void insertToPrescription(View view) {
        adapter.add(spTypesList.getSelectedItem().toString() + ": " + txtPrescribed.getText().toString());
        Toast.makeText(this,spTypesList.getSelectedItem().toString() + ":" + txtPrescribed.getText().toString(),Toast.LENGTH_LONG).show();
        txtPrescribed.setText("");
    }


    public void removePrescription(View v)
    {
        final int position = lvPrescriptions.getPositionForView((View) v.getParent());
        adapter.remove(adapter.getItem(position));
        adapter.notifyDataSetChanged();
    }

    public void insertToDatabase(View view) {
        String s;
        mr.setNotes(txtNotes.getText().toString());
        for(int i=0 ; i<adapter.getCount() ; i++){
            s = adapter.getItem(i).substring(adapter.getItem(i).indexOf(":")+2);
            if(adapter.getItem(i).contains("Medication")){
                Medication m = new Medication("",s);
                mr.addMedication(m);
                p.addMedication(m);
                DBHandler.addMedication(m , p);
            }
            else if(adapter.getItem(i).contains("Precaution")){
                Precaution pr = new Precaution("",s);
                mr.addPrecaution(pr);
                DBHandler.addPrecaution(pr);
            }
            /*else if(adapter.getItem(i).contains("Examination")){
                Examination e = new Examination();
                mr.addExamination(e.getId());
            }*/
        }
        p.addMedicalReport(mr);
        DBHandler.addMedicalReport(mr , p);
        Toast.makeText(this,"added to Database",Toast.LENGTH_SHORT).show();
        /*adapter.clear();
        startActivity(new Intent(this,MedicalReport.class));*/
    }
}

