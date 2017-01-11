package com.mc.info.lumc;

import android.content.Intent;
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
        mr = new MedicalReport();
        mr.setNotes(txtNotes.getText().toString());
        DBHandler.addMedicalReport(mr , p);
        for(int i=0 ; i<adapter.getCount() ; i++){
            s = adapter.getItem(i).substring(adapter.getItem(i).indexOf(":")+2);
            if(adapter.getItem(i).contains("Medication")){
                Medication m = new Medication("",s);
                DBHandler.addMedication(m , mr);
            }
            else if(adapter.getItem(i).contains("Precaution")){
                Precaution pr = new Precaution("",s);
                DBHandler.addPrecaution(pr , mr);
            }
        }
        Toast.makeText(this,"added to Database",Toast.LENGTH_SHORT).show();
        adapter.clear();
        Intent intent = new Intent(this,ListReports.class);
        intent.putExtra("take",p);
        startActivity(intent);
    }
}

