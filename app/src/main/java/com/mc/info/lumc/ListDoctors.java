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

import java.util.ArrayList;
import java.util.HashMap;

public class ListDoctors extends AppCompatActivity {

    private MyDatabaseHandler dbHandler = new MyDatabaseHandler(this, null, null, 1);
    private ListView lv;
    private ArrayList<Doctor> doctors;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_doctors);
        SearchView sv = (SearchView) findViewById(R.id.activity_list_doctors_search);

        doctors = dbHandler.getAllDoctors();

        for (int i = 0; i < doctors.size(); i++) {
            data.add(doctors.get(i).toHashMap());
        }


        String[] hash = {MyDatabaseHandler.COLUMN_FIRST_NAME, MyDatabaseHandler.COLUMN_LAST_NAME , MyDatabaseHandler.COLUMN_SPECIALTY};
        int[] toViewIds = {R.id.list_doctor_item_txtFname, R.id.list_doctor_item_txtLname , R.id.list_doctor_item_specialty};
        adapter = new SimpleAdapter(this, data, R.layout.list_doctor_item, hash, toViewIds);
        lv = (ListView) findViewById(R.id.activity_list_doctors_doctorList);
        lv.setAdapter(adapter);


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
                String str = adapter.getItem(i).toString();
                Doctor d;
                int index=str.lastIndexOf(MyDatabaseHandler.COLUMN_ID+"=");
                int last=str.indexOf(",",index);
                if (last == -1){
                    last=str.indexOf("}",index);
                }
                String idString = str.substring(index+MyDatabaseHandler.COLUMN_ID.length()+1,last);
                d = dbHandler.getDoctorById(Integer.parseInt(idString));
                if(idString.equals(String.valueOf( d.getId()))){
                    Intent j = new Intent(ListDoctors.this, DoctorInfo.class);
                    j.putExtra("take",d.getId());
                    startActivity(j);
                }
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
        data = new ArrayList<>();
        for (Doctor d1 : doctors) {
            data.add(d1.toHashMap());
        }

        String[] hash = {MyDatabaseHandler.COLUMN_FIRST_NAME, MyDatabaseHandler.COLUMN_LAST_NAME , MyDatabaseHandler.COLUMN_SPECIALTY};
        int[] toViewIds = {R.id.list_doctor_item_txtFname, R.id.list_doctor_item_txtLname , R.id.list_doctor_item_specialty};
        adapter = new SimpleAdapter(this, data, R.layout.list_doctor_item, hash, toViewIds);
        lv = (ListView) findViewById(R.id.activity_list_doctors_doctorList);
        lv.setAdapter(adapter);
        return true;
    }

}
