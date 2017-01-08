package com.mc.info.lumc;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MedicalCenter.db";
    public static final String TABLE_PATIENT ="patient";
    public static final String TABLE_DOCTOR ="doctor";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_FIRST_NAME ="firstName";
    public static final String COLUMN_LAST_NAME ="lastName";
    public static final String COLUMN_CITY ="city";
    public static final String COLUMN_STREET ="street";
    public static final String COLUMN_BUILDING ="building";
    public static final String COLUMN_PHONE ="phone";
    public static final String COLUMN_EMAIL ="email";
    public static final String COLUMN_SPECIALTY ="specialty";
    public static final String COLUMN_EXPERIENCE_YEARS ="experienceYears";
    public FirebaseDatabase database;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private boolean dataReady =false;


    public DBHandler(final Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        database = FirebaseDatabase.getInstance();
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
    public boolean isDataReady()
    {
        return dataReady;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PATIENT);
        onCreate(db);*/

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
/*

        String queryPatientTable ="CREATE TABLE " + TABLE_PATIENT + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " VARCHAR[50], " +
                COLUMN_LAST_NAME + " VARCHAR[50], " +
                COLUMN_CITY + " VARCHAR[50], " +
                COLUMN_STREET + " VARCHAR[50], " +
                COLUMN_BUILDING + " VARCHAR[100], " +
                COLUMN_PHONE + " VARCHAR[50], " +
                COLUMN_EMAIL + " VARCHAR[50] " +
                ");";

        db.execSQL(queryPatientTable);

        String queryDoctorTable ="CREATE TABLE " + TABLE_DOCTOR + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " VARCHAR[50], " +
                COLUMN_LAST_NAME + " VARCHAR[50], " +
                COLUMN_CITY + " VARCHAR[50], " +
                COLUMN_STREET + " VARCHAR[50], " +
                COLUMN_BUILDING + " VARCHAR[100], " +
                COLUMN_PHONE + " VARCHAR[50], " +
                COLUMN_EMAIL + " VARCHAR[50], " +
                COLUMN_SPECIALTY + " VARCHAR[50], " +
                COLUMN_EXPERIENCE_YEARS + " INTEGER " +
                ");";

        db.execSQL(queryDoctorTable);
*/

    }

    public static void addPatient(Patient p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT);
        p.setId(myRef.push().getKey());
        myRef.child(p.getId()).setValue(p);
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
        /*
        Address addr = d.getAddress();
        ContentValues values = new ContentValues();
        values.put( COLUMN_FIRST_NAME , d.getFirstName() );
        values.put( COLUMN_LAST_NAME , d.getLastName() );
        values.put( COLUMN_CITY , addr.getCity() );
        values.put( COLUMN_STREET , addr.getStreet() );
        values.put( COLUMN_BUILDING , addr.getBuilding() );
        values.put( COLUMN_PHONE , d.getPhone() );
        values.put( COLUMN_EMAIL , d.getEmail() );
        values.put( COLUMN_SPECIALTY , d.getSpecialty() );
        values.put( COLUMN_EXPERIENCE_YEARS , d.getExperienceYears() );
        SQLiteDatabase db = getWritableDatabase();
        String id = new Long(db.insert(TABLE_DOCTOR, null, values)).toString();
        d.setId(id);
        db.close();
        */
    }

    public List<Patient> getPatients(){
        return patients;
    }

    public Patient getPatientById(final int id){
       // Patient p=patients.
        return null;
    }



    public Doctor getDoctorById(String id) {
        /*
        SQLiteDatabase db = this.getReadableDatabase();
        String getQuery = " SELECT * FROM " + TABLE_DOCTOR + " WHERE " + COLUMN_ID + " = " + id;
        Cursor c = db.rawQuery(getQuery, null);
        c.moveToFirst();
        String fName = c.getString(c.getColumnIndex(COLUMN_FIRST_NAME));
        String lName = c.getString(c.getColumnIndex(COLUMN_LAST_NAME));
        String city = c.getString(c.getColumnIndex(COLUMN_CITY));
        String street = c.getString(c.getColumnIndex(COLUMN_STREET));
        String building = c.getString(c.getColumnIndex(COLUMN_BUILDING));
        String phone = c.getString(c.getColumnIndex(COLUMN_PHONE));
        String email = c.getString(c.getColumnIndex(COLUMN_EMAIL));
        String specialty = c.getString(c.getColumnIndex(COLUMN_SPECIALTY));
        int experienceYears = c.getInt(c.getColumnIndex(COLUMN_EXPERIENCE_YEARS));
        Doctor d = new Doctor(id, fName, lName, phone, email, new Address(city, street, building),specialty,experienceYears);
        db.close();
        return d;
        */
        return null;
    }

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
