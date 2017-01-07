package com.mc.info.lumc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prescription);
        spTypesList = (Spinner)findViewById(R.id.activity_create_prescription_typeList);
        txtPrescribed = (EditText) findViewById(R.id.activity_create_prescription_prescribed);
        btnSave = (Button) findViewById(R.id.activity_create_prescription_saveItem);
        lvPrescribtions = (ListView) findViewById(R.id.activity_create_prescription_listOfPrescriptions);
        adapter = new ArrayAdapter<String>(this,R.layout.prescription_item,R.id.prescription_item_prescribe);
        lvPrescribtions.setAdapter(adapter);
    }

    public void insertPrescription(View view) {
        adapter.add(spTypesList.getSelectedItem().toString() + ": " + txtPrescribed.getText().toString());
        Toast.makeText(this,spTypesList.getSelectedItem().toString() + ":" + txtPrescribed.getText().toString(),Toast.LENGTH_LONG).show();
        txtPrescribed.setText("");
    }


    public void removePrescription(View v)
    {
        final int position = lvPrescribtions.getPositionForView((View) v.getParent());
        adapter.remove(adapter.getItem(position));
        adapter.notifyDataSetChanged();


    }

    public void insertToDatabase(View view) {
        //insert to database
        adapter.clear();
        startActivity(new Intent(this,PatientInfo.class));
    }
}
