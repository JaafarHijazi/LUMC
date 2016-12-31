package com.mc.info.lumc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListPatients extends AppCompatActivity {
    private DBHandler dbHandler = new DBHandler(this, null, null, 1);
    private ListView lv;
    private ArrayList<Patient> patients;
    private ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private SimpleAdapter adapter;
    private SearchView sv;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patients);
        sv= (SearchView) findViewById(R.id.activity_list_patients_search);




        patients = dbHandler.getAllPatients();

        for (int i = 0; i < patients.size(); i++) {
            data.add(patients.get(i).toHashMap());
        }


        String[] hash = {DBHandler.COLUMN_FIRST_NAME, DBHandler.COLUMN_LAST_NAME};
        int[] toViewIDs = {R.id.list_item_patient_firstName, R.id.list_item_patient_lastName};
        adapter = new SimpleAdapter(this, data, R.layout.list_patients_item, hash, toViewIDs);
        lv = (ListView) findViewById(R.id.activity_list_patients_patientList);
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
                Patient p;
                int index=str.lastIndexOf(DBHandler.COLUMN_ID+"=");
                int last=str.indexOf(",",index);
                if (last == -1){
                    last=str.indexOf("}",index);
                }
                String idString = str.substring(index+ DBHandler.COLUMN_ID.length()+1,last);
                p = dbHandler.getPatientById(Integer.parseInt(idString));
                if(idString.equals(String.valueOf( p.getId()))){
                    Intent j = new Intent(ListPatients.this, PatientInfo.class);
                    j.putExtra("take",p.getId());
                    startActivity(j);
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.list_patients_drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.main_nav) ;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                unCheckAllMenuItems(navigationView);
                item.setChecked(true);
                if(item.getItemId()==R.id.drwrViewDoctors)
                    startActivity(new Intent(ListPatients.this, ListDoctors.class));
                else if(item.getItemId()==R.id.drwrViewPatients)
                    startActivity(new Intent(ListPatients.this, ListPatients.class));
                return true;
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void unCheckAllMenuItems(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    subMenuItem.setChecked(false);
                }
            } else {
                item.setChecked(false);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_patients, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_list_patients_ByFirstName)
            patients = dbHandler.sortPatientsBy(DBHandler.COLUMN_FIRST_NAME);
        else if(item.getItemId() == R.id.menu_list_patients_ByLastName)
            patients = dbHandler.sortPatientsBy(DBHandler.COLUMN_LAST_NAME);
        data = new ArrayList<>();
        for (Patient p1 : patients) {
            data.add(p1.toHashMap());
        }

        String[] hash = {DBHandler.COLUMN_FIRST_NAME, DBHandler.COLUMN_LAST_NAME};
        int[] toViewIds = {R.id.list_item_patient_firstName, R.id.list_item_patient_lastName};
        adapter = new SimpleAdapter(this, data, R.layout.list_patients_item, hash, toViewIds);
        lv = (ListView) findViewById(R.id.activity_list_patients_patientList);
        lv.setAdapter(adapter);

        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

}
