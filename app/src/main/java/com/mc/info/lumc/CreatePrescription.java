package com.mc.info.lumc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hphh4 on 1/6/2017.
 */

public class CreatePrescription extends AppCompatActivity implements Serializable {

    private Button btnAdd,
                   btnSave;
    private Spinner spTypesList;
    private EditText txtPrescribed;
    private ListView lvPrescribtions;
    private ArrayList<String> data;
    private PrescriptionAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prescription);
        spTypesList = (Spinner)findViewById(R.id.activity_create_prescription_typeList);
        spTypesList.setVisibility(View.INVISIBLE);
        txtPrescribed = (EditText) findViewById(R.id.activity_create_prescription_prescribed);
        txtPrescribed.setVisibility(View.INVISIBLE);
        btnSave = (Button) findViewById(R.id.activity_create_prescription_saveItem);
        btnSave.setVisibility(View.INVISIBLE);
        btnAdd =(Button)findViewById(R.id.activity_create_prescription_newItem);
        lvPrescribtions = (ListView) findViewById(R.id.activity_create_prescription_listOfPrescriptions);
    }

    public void viewTypesList(View view){
        spTypesList.setVisibility(view.VISIBLE);
        txtPrescribed.setVisibility(view.VISIBLE);
        btnSave.setVisibility(View.VISIBLE);
    }

    public void insertPrescription(View view) {
        adapter = new PrescriptionAdapter(this,data);
        data.add(spTypesList.getSelectedItem().toString() + ":" + txtPrescribed.getText().toString());
        lvPrescribtions.setAdapter(adapter);
        Toast.makeText(this,spTypesList.getSelectedItem().toString() + ":" + txtPrescribed.getText().toString(),Toast.LENGTH_LONG).show();
    }
}
