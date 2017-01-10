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

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MedicalCenter.db";
    public static final String TABLE_PATIENT ="patient";
    public static final String TABLE_DOCTOR ="doctor";
    public static final String TABLE_EXAMINATION ="examination";
    public static final String TABLE_MEDICAL_REPORT ="medicalReport";
    public static final String TABLE_MEDICATION ="medication";
    public static final String TABLE_PRECAUTION ="precaution";
    public static final String TABLE_MEDICAL_DATA ="medicalData";
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
    private List<MedicalData> medicalDatas;

    private boolean dataReady =false;
    public DBHandler(final Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        database = FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  // get data from database to your arraylist
                patients = new ArrayList<Patient>();
                for (DataSnapshot patient : dataSnapshot.child(TABLE_PATIENT).getChildren()) {

                    try {
                        Patient p = patient.getValue(Patient.class);

                        p.setId(patient.getKey());
                        patients.add(p);
                    } catch (DatabaseException e) {
                        System.out.print(e.getMessage());
                    }
                }

                doctors = new ArrayList<Doctor>();
                Doctor d;
                for (DataSnapshot doctor : dataSnapshot.child(TABLE_DOCTOR).getChildren()) {
                    try {
                        d = doctor.getValue(Doctor.class);
                        d.setId(doctor.getKey());
                        doctors.add(d);
                    } catch (DatabaseException e) {
                        System.out.print(e.getMessage());
                    }

                }

                /*medicalResults = new ArrayList<>();
                Examination exam;
                for (DataSnapshot exams : dataSnapshot.child(TABLE_EXAMINATION).getChildren()) {
                //for (DataSnapshot exams : dataSnapshot.child(TABLE_EXAMINATION+"/"+id).getChildren()) {
                        try {
                            exam = exams.getValue(Examination.class);
                            exam.setId(exams.getKey());
                            medicalResults.add(exam);
                        } catch (DatabaseException ex) {
                            System.out.print(ex.getMessage());
                        }

                    }

                medicalDatas = new ArrayList<>();
                MedicalData data;
                for (DataSnapshot exams : dataSnapshot.child(TABLE_MEDICAL_DATA).getChildren()) {
                    try {
                        data = exams.getValue(MedicalData.class);
                        data.setId(exams.getKey());
                        medicalDatas.add(data);
                    } catch (DatabaseException ex) {
                        System.out.print(ex.getMessage());


                    }

                }*/
                dataReady = true;
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
    public List<Examination> getMedicalResult(final String Pid) {
        dataReady=false;
        database.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                medicalResults = new ArrayList<>();
                Examination exam;
                for (DataSnapshot exams : dataSnapshot.child(TABLE_EXAMINATION+"/"+Pid).getChildren()) {
                    try {
                        exam = exams.getValue(Examination.class);
                        exam.setId(exams.getKey());
                        medicalResults.add(exam);
                    } catch (DatabaseException ex) {
                        System.out.print(ex.getMessage());
                    }

                }

                medicalDatas = new ArrayList<>();
                MedicalData data;
                for (DataSnapshot exams : dataSnapshot.child(TABLE_MEDICAL_DATA).getChildren()) {
                    try {
                        data = exams.getValue(MedicalData.class);
                        data.setId(exams.getKey());
                        medicalDatas.add(data);
                    } catch (DatabaseException ex) {
                        System.out.print(ex.getMessage());


                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dataReady=true;
        return medicalResults;
    }
    public List<MedicalData> getMedicalData(){ return medicalDatas; }
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
        //DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT + "/" + p.getId()).child(COLUMN_MEDICAL_REPORTS);
        String key = myRef.push().getKey();
        mr.setId(key);
        myRef.child(key).setValue(mr);
        //myRef1.push().setValue(mr);
    }

    public static void addMedication(Medication m , Patient p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_MEDICATION);
        DatabaseReference myRef1 = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT + "/" + p.getId()).child(COLUMN_MEDICATION);
        String key = myRef.push().getKey();
        m.setId(key);
        myRef.child(key).setValue(m);
        myRef1.push().setValue(m);
    }

    public static void addPrecaution(Precaution p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_PRECAUTION);
        String key = myRef.push().getKey();
        p.setId(key);
        myRef.child(key).setValue(p);
    }

    public static void addPatientMedicalReport(MedicalReport m , Patient p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT).child("medicalReport");
        String key = myRef.push().getKey();
        m.setId(key);
        myRef.child(key).setValue(m);
    }

    public static void addExamination(Patient p, Examination e){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_EXAMINATION).child(p.getId());
        String key = myRef.push().getKey();
        e.setId(key);
        myRef.child(key).setValue(e);
    }

    public static void addMedicalData(Examination e, MedicalData m){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_MEDICAL_DATA).child(e.getId());
        String key = myRef.push().getKey();
        m.setId(key);
        myRef.child(key).setValue(m);
    }

    public static void add(Examination e, MedicalData m){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_MEDICAL_DATA).child(e.getId());
        String key = myRef.push().getKey();
        m.setId(key);
        myRef.child(key).setValue(m);
    }
}