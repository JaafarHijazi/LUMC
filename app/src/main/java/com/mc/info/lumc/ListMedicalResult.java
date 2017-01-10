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
    private DBHandler dbHandler = new DBHandler(this, null, null, 1);
    private RecyclerView recyclerView; private List exams;
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private MedicalResultRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_medical_result);

        recyclerView= (RecyclerView) findViewById(R.id.activity_list_medical_result_medicalResultList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new AsyncTask<Void,Void,List<Examination>>(){
            @Override
            protected List<Examination> doInBackground(Void... params) {
                while (!dbHandler.isDataReady()) try {
                    Thread.sleep(100); }
                catch (InterruptedException e) {
                    e.printStackTrace(); }
                return dbHandler.getMedicalResult();
            }
            @Override protected void onPostExecute(List<Examination> exams) {
                ListMedicalResult.this.exams=exams;
                adapter=new MedicalResultRecyclerAdapter(exams);
                recyclerView.setAdapter(adapter);
            }
        }.execute();
    }
}

