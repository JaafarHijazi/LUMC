package com.mc.info.lumc;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ListMedicalResult extends AppCompatActivity {
    private DBHandler dbHandler = DBHandler.getInstance();
    private RecyclerView recyclerView; private List exams;
    private ArrayList<MedicalData> data = new ArrayList<>();
    private MedicalResultRecyclerAdapter adapter;
    private Bundle extras;
    private Patient p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medical_result);

        extras = getIntent().getExtras();
        p = (Patient) extras.getSerializable("patient");

        recyclerView= (RecyclerView) findViewById(R.id.activity_list_medical_result_medicalResultList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                exams=dbHandler.getMedicalExaminations(p.getId());
                adapter=new MedicalResultRecyclerAdapter(exams);
                recyclerView.setAdapter(adapter);
    }
}

