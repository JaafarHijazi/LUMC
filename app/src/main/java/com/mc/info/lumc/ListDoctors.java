package com.mc.info.lumc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListDoctors extends AppCompatActivity {

    private MyDatabaseHandler dbHandler = new MyDatabaseHandler(this, null, null, 1);
    private ListView lv;
    private ArrayList<Doctor> doctors;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private SimpleAdapter adapter;
    private DoctorListAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctors);
        SearchView sv = (SearchView) findViewById(R.id.activity_list_doctors_search);
        doctors = dbHandler.getAllDoctors();
        adapter1 = new DoctorListAdapter(this,doctors);


       /*

        for (int i = 0; i < patients.size(); i++) {
            data.add(patients.get(i).toHashMap());
        }


         String[] hash = {MyDatabaseHandler.COLUMN_FIRST_NAME, MyDatabaseHandler.COLUMN_LAST_NAME};
        int[] toViewIDs = {R.id.list_item_patient_firstName, R.id.list_item_patient_lastName};
        adapter = new SimpleAdapter(this, data, R.layout.list_patients_item, hash, toViewIDs);
        */
        lv = (ListView) findViewById(R.id.activity_list_doctors_doctorList);
        lv.setAdapter(adapter1);


        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
 /*               String str = adapter1.getItem(i).toString();
                Toast.makeText(ListDoctors.this,str,Toast.LENGTH_SHORT).show();

                Doctor d;
                int index=str.lastIndexOf(MyDatabaseHandler.COLUMN_ID+"=");
                int last=str.indexOf(",",index);
                if (last == -1){
                    last=str.indexOf("}",index);
                }
                String idString = str.substring(index+MyDatabaseHandler.COLUMN_ID.length()+1,last);
                d = dbHandler.getDoctorById(Integer.parseInt(idString));*/
                Doctor d=adapter1.getItem(i);

                Intent j = new Intent(ListDoctors.this, DoctorInfo.class);
                j.putExtra("take",d.getId());
                startActivity(j);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_doctors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_list_doctors_ByFirstName)
            doctors = dbHandler.sortDoctorsBy(MyDatabaseHandler.COLUMN_FIRST_NAME);
        else if(item.getItemId() == R.id.menu_list_doctors_ByLastName)
            doctors = dbHandler.sortDoctorsBy(MyDatabaseHandler.COLUMN_LAST_NAME);
        else if(item.getItemId() == R.id.menu_list_doctors_BySpecialty)
            doctors = dbHandler.sortDoctorsBy(MyDatabaseHandler.COLUMN_SPECIALTY);
        adapter1 = new DoctorListAdapter(this,doctors);
        lv = (ListView) findViewById(R.id.activity_list_doctors_doctorList);
        lv.setAdapter(adapter1);
        return true;
    }

}
