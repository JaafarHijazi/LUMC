package com.mc.info.lumc;

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
import java.util.Map;

/**
 * Created by BurgerMan on 12/9/2016.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MedicalCenter.db";
    public static final String TABLE_PATIENT ="patient";
    public static final String TABLE_DOCTOR ="doctor";
    public static final String TABLE_MEDICAL_EXAMINATION ="medicalExamination";
    public static final String TABLE_MEDICAL_REPORT ="medicalReport";
    public static final String TABLE_MEDICATION ="medication";
    public static final String TABLE_PRECAUTION ="precaution";
    public static final String COULMN_DATE ="date";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_FIRST_NAME ="firstname";
    public static final String COLUMN_LAST_NAME ="lastname";
    public static final String COLUMN_CITY ="city";
    public static final String COLUMN_STREET ="street";
    public static final String COLUMN_BUILDING ="building";
    public static final String COLUMN_PHONE ="phone";
    public static final String COLUMN_EMAIL ="email";
    public static final String COLUMN_SPECIALTY ="specialty";
    public static final String COLUMN_EXPERIENCE_YEARS ="experienceYears";
    public static final String COLUMN_MEDICATION ="medication";
    public static final String COLUMN_MEDICAL_REPORTS ="medicalReports";

    public FirebaseDatabase database;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Examination> medicalResults;
    private boolean dataReady =false;
    public DBHandler(final Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  // get data from database to your arraylist
                patients=new ArrayList<Patient>();
                for (DataSnapshot patient : dataSnapshot.child(TABLE_PATIENT).getChildren()) {
                    try {
                        Patient p = patient.getValue(Patient.class);
                        p.setId(patient.getKey());
                        patients.add(p);
                    }
                    catch( DatabaseException e){
                        System.out.print(e.getMessage());
                    }
                }

                doctors=new ArrayList<Doctor>();
                Doctor d;
                for (DataSnapshot doctor : dataSnapshot.child(TABLE_DOCTOR).getChildren()) {
                    try{
                        d=doctor.getValue(Doctor.class);
                        d.setId(doctor.getKey());
                        doctors.add(d);
                    }
                    catch( DatabaseException e){
                        System.out.print(e.getMessage());
                    }
                }
                dataReady =true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());

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
    public List<Examination> getMedicalResult() {
        return medicalResults;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PATIENT);
        onCreate(db);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

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

    }

    public void addPatient(Patient p){

    }


    public void addDoctor(Doctor d){

    }

    public List<Patient> getPatients(){
        return patients;
    }

    public Patient getPatientById(final int id){
       // Patient p=patients.
        return null;
    }



    public Doctor getDoctorById(String id) {
        for (Doctor d : doctors){
            if (d.getId().equals(id))
                return d;
        }
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

    public static void addMedicalReport(MedicalReport mr , Patient p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_MEDICAL_REPORT).child(p.getId());
        String key = myRef.push().getKey();
        mr.setId(key);
        myRef.child(key).setValue(mr);
    }

    public static void addMedication(Medication m , MedicalReport mr){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_MEDICATION).child(mr.getId());
        String key = myRef.push().getKey();
        m.setId(key);
        myRef.child(key).setValue(m);
    }

    public static void addPrecaution(Precaution p , MedicalReport mr){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_PRECAUTION).child(mr.getId());
        String key = myRef.push().getKey();
        p.setId(key);
        myRef.child(key).setValue(p);
    }

    public static List<MedicalReport> getMedicalReports (Patient p){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference();
        List<MedicalReport>  mr1;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  // get data from database to your arraylist
                List<MedicalReport> mr = new ArrayList<>();
                for (DataSnapshot medR : dataSnapshot.child(TABLE_MEDICAL_REPORT).getChildren()) {
                    try {
                        MedicalReport p = medR.getValue(MedicalReport.class);
                        p.setId(medR.getKey());
                        mr.add(p);
                    }
                    catch( DatabaseException e){
                        System.out.print(e.getMessage());
                    }
                }
                getMedicalReports(p).mr1 = mr;

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return mr;
    }
}