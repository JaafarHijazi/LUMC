package com.mc.info.lumc;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class DBHandler extends Application{

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MedicalCenter.db";
    public static final String TABLE_PATIENT ="patient";
    public static final String TABLE_DOCTOR ="doctor";
    public static  final String TABLE_CONSULTS = "consults";
    public static final String TABLE_MEDICATIONS = "medications";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_FIRST_NAME ="firstName";
    public static final String COLUMN_LAST_NAME ="lastName";
    public static final String COLUMN_CITY ="city";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_STREET ="street";
    public static final String COLUMN_BUILDING ="building";
    public static final String COLUMN_PHONE ="phone";
    public static final String COLUMN_EMAIL ="email";
    public static final String COLUMN_SPECIALTY ="specialty";
    public static final String COLUMN_EXPERIENCE_YEARS ="experienceYears";
    public static final String COLUMN_PID_FK = "pid";
    public static final String COLUMN_DID_FK ="did";
    public static final String COLUMN_DATEOFCONSULTATION ="dateOfConsultation";
    public static final String COLUMN_MEDICINE_NAME = "medicineName";


    public FirebaseDatabase database;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Patient> myPatients;
    private boolean dataReady =false;
    private static DBHandler singlton;
    @Override
    public void onCreate() {
        super.onCreate();
        singlton=this;
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myPatients = new ArrayList<>();
        DatabaseReference reference=database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                patients=new ArrayList<Patient>();
                for (DataSnapshot patient : dataSnapshot.child(TABLE_PATIENT).getChildren()) {
                    Patient p= patient.getValue(Patient.class);
                    p.setId(patient.getKey());
                    patients.add(p);
                }

                doctors=new ArrayList<Doctor>();
                for (DataSnapshot doctor : dataSnapshot.child(TABLE_DOCTOR).getChildren()) {
                    Doctor d=doctor.getValue(Doctor.class);
                    d.setId(doctor.getKey());
                    doctors.add(d);
                }
                dataReady =true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public static DBHandler getInstance()
    {
        return singlton;
    }
    public boolean isDataReady()
    {
        return dataReady;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void addPatient(Patient p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT);
        String key = myRef.push().getKey();
        p.setId(key);
        myRef.child(key).setValue(p);
/*
        Address addr = p.getAddress();
        ContentValues values = new ContentValues();
        values.put( COLUMN_FIRST_NAME , p.getFirstName() );
        values.put( COLUMN_LAST_NAME , p.getLastName() );
        values.put( COLUMN_CITY , addr.getCity() );
        values.put( COLUMN_STREET , addr.getStreet() );
        values.put( COLUMN_BUILDING ,addr.getBuilding() );
        values.put( COLUMN_PHONE ,p.getPhone() );
        values.put( COLUMN_EMAIL ,p.getEmail() );
        SQLiteDatabase db = getWritableDatabase();
        String id = new Long(db.insert(TABLE_PATIENT, null, values) ).toString();
        p.setId(id);
        db.close();
        */
    }


    public void addDoctor(Doctor d){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_DOCTOR);
        String key = myRef.push().getKey();
        d.setId(key);
        myRef.child(key).setValue(d);

        /*DatabaseReference doctorRef = database.getReference().child(TABLE_DOCTOR);
        String key = doctorRef.push().getKey();
        DatabaseReference item = doctorRef.child(key);
        item.child(COLUMN_FIRST_NAME).setValue(d.getFirstName());
        item.child(COLUMN_LAST_NAME).setValue(d.getLastName());
        item.child(COLUMN_ADDRESS).setValue(d.getAddress());
        item.child(COLUMN_EMAIL).setValue(d.getEmail());
        item.child(COLUMN_PHONE).setValue(d.getPhone());
        item.child(COLUMN_EXPERIENCE_YEARS).setValue(d.getExperienceYears());
        item.child(COLUMN_SPECIALTY).setValue(d.getSpecialty());
        item.child(COLUMN_ID).setValue(Integer.parseInt(key));*/

    }

    public void addConsult (Consults c){

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_CONSULTS);
        String key = myRef.push().getKey();
        c.setCid(key);
        myRef.child(key).setValue(c);
        /*DatabaseReference consultRef = database.getReference().child(TABLE_CONSULTS);
        String key = consultRef.push().getKey();
        DatabaseReference item = consultRef.child(key);
        item.child(COLUMN_PID_FK).setValue(c.getPid());
        item.child(COLUMN_DID_FK).setValue(c.getDid());
        item.child(COLUMN_DATEOFCONSULTATION).setValue(c.getDateOfConsultation());*/

    }
    public void signUp(Person person){

    }
    public List<Patient> getPatients(){
        return patients;
    }
//this is nice
    public List<Patient> getMyPatients(Doctor d) {
        final String did = d.getId();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(TABLE_CONSULTS);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot consult :  dataSnapshot.child(TABLE_CONSULTS).getChildren()) {
                        Consults mConsult = consult.getValue(Consults.class);
                        if((mConsult).getDid().equals(did)) {
                            Patient p = getPatientById(mConsult.getPid());
                            if (myPatients.contains(p))
                                continue;
                            else
                                myPatients.add(p);
                        }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
    return myPatients;
        }


    public List<Medication> getPatientMedicines(Patient p){
        final List<Medication> patientmedicines = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return patientmedicines;
    }

    public Patient getPatientById(final String id){
        for (Patient p : patients){
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }



    public Doctor getDoctorById(final String id) {

        for (Doctor d : doctors){
            if (d.getId().equals(id))
                return d;
        }
        return null;

    }

    public void getMedications (Patient p){
        DatabaseReference doctorRef = database.getReference().child(TABLE_DOCTOR);
        String key = doctorRef.push().getKey();
        DatabaseReference item = doctorRef.child(key);


    }




//function to get JSON Database from android device
    public JSONObject getResults(Context context)
    {

        String myPath = DATABASE_NAME;// Set path to your database

        String[] myTables =new String[]{TABLE_DOCTOR,TABLE_PATIENT};//Set name of your table

//or you can use `context.getDatabasePath("my_db_test.db")`
        JSONObject db=new JSONObject();

        SQLiteDatabase myDataBase = SQLiteDatabase.openDatabase(context.getDatabasePath(myPath).getPath(), null, SQLiteDatabase.OPEN_READONLY);
        for(String myTable:myTables){
            String searchQuery = "SELECT  * FROM " + myTable;
            Cursor cursor = myDataBase.rawQuery(searchQuery, null);

            JSONObject resultSet = new JSONObject();

            cursor.moveToFirst();
            while (cursor.isAfterLast() == false) {

                int totalColumn = cursor.getColumnCount();
                JSONObject rowObject = new JSONObject();

                for (int i = 0; i < totalColumn; i++) {
                    if (cursor.getColumnName(i) != null) {
                        if (!cursor.getColumnName(i).equals(COLUMN_ID))
                            try {
                                if (cursor.getString(i) != null) {
                                    Log.d("TAG_NAME", cursor.getString(i));
                                    rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                                } else {
                                    rowObject.put(cursor.getColumnName(i), "");
                                }
                            } catch (Exception e) {
                                Log.d("TAG_NAME", e.getMessage());
                            }
                    }
                }
                try {
                    resultSet.put(cursor.getString(cursor.getColumnIndex(COLUMN_ID)), rowObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cursor.moveToNext();
            }
            cursor.close();
            Log.d("TAG_NAME", resultSet.toString());
            try {
                db.put(myTable,resultSet);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return db;
    }
}
