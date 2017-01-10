package com.mc.info.lumc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;

public class ListReports extends AppCompatActivity implements Serializable {


    private ListView lvReports;
    private ArrayAdapter<String> adapter;
    Bundle extras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reports);
        extras = getIntent().getExtras();
        lvReports = (ListView) findViewById(R.id.activity_list_reports_all_reports);
        adapter = new ArrayAdapter<>(this,R.layout.list_report_item,R.id.list_report_item_details);
        lvReports.setAdapter(adapter);
        adapter.add("batee5");
        lvReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(ListReports.this, ReportDetails.class);
                startActivity(j);

            }
        });
    }


    public void newReport(View view) {
        Intent i = new Intent(this,CreateMedicalReport.class);
        MedicalReport mr = new MedicalReport();
        i.putExtra("take",mr);
        i.putExtra("patient",(Patient)extras.getSerializable("take"));
        startActivity(i);
    }
}
