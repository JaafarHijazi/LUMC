
package com.mc.info.lumc;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientMedicines extends AppCompatActivity {


    private ListView lv;
    private SimpleAdapter adapter;
    private SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medicines);
        sv= (SearchView) findViewById(R.id.activity_patient_medicines_search);
        lv = (ListView) findViewById(R.id.activity_patient_medicines_lvmedicines);

        new AsyncTask<Void, Void, List<Medication>>() {
            @Override
            protected List<Medication> doInBackground(Void... params) {
               /*while (!dbHandler.isDataReady())
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                List<Medication> myMedicines = new ArrayList<>();

                Bundle extras = getIntent().getExtras();
                Patient p = (Patient) extras.getSerializable("take");
                myMedicines = DBHandler.getInstance().getPatientMedicines(p);
                return myMedicines;
            }

            @Override
            protected void onPostExecute(List<Medication> myMedicines) {
                ArrayList<HashMap<String, String>> data = new ArrayList<>();
                for (int i = 0; i < myMedicines.size(); i++) {
                    data.add(myMedicines.get(i).toHashMap());
                }

                String[] hash = {DBHandler.COLUMN_MEDICINE_NAME};
                int[] toViewIDs = {R.id.activity_item_medicine_name};
                adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.medicine_item, hash, toViewIDs);
                lv.setAdapter(adapter);            }
        }.execute();




        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter!=null)
                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }


}
