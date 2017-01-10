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
    private ArrayAdapter<MedicalReport> adapter;
    Bundle extras;
    Patient p;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reports);
        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("take");
        lvReports = (ListView) findViewById(R.id.activity_list_reports_all_reports);
        adapter = new ArrayAdapter<>(this,R.layout.list_report_item,R.id.list_report_item_details);
        lvReports.setAdapter(adapter);
        lvReports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(ListReports.this, ReportDetails.class);
                j.putExtra("take", p);
                startActivity(j);

            }
        });
    }


    public void newReport(View view) {
        Intent i = new Intent(this,CreateMedicalReport.class);
        i.putExtra("patient", p);
        startActivity(i);
    }
}
