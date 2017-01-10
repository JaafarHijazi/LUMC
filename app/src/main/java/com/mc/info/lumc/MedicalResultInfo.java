package com.mc.info.lumc;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MedicalResultInfo extends AppCompatActivity {

    private DBHandler dbHandler = new DBHandler(this, null, null, 1);
    private RecyclerView recyclerView;
    private List exams;
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private MedicalResultInfoRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medical_result_info);
        recyclerView= (RecyclerView) findViewById(R.id.activity_medical_result_info_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new AsyncTask<Void,Void,List<MedicalData>>(){
            @Override
            protected List<MedicalData> doInBackground(Void... params) {
                while (!dbHandler.isDataReady()) try {
                    Thread.sleep(100); }
                catch (InterruptedException e) {
                    e.printStackTrace(); }
                return dbHandler.getMedicalData();
            }
            @Override
            protected void onPostExecute(List<MedicalData> exams) {
                MedicalResultInfo.this.exams=exams;
                adapter = new MedicalResultInfoRecyclerAdapter(exams);
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }
}
