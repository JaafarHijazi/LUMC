package com.mc.info.lumc;

import android.content.ContentValues;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class DBHandler extends Application{

    private static final String DATABASE_NAME="MedicalCenter.db";
    public static final String TABLE_PATIENT ="patient";
    public static final String TABLE_DOCTOR ="doctor";
    public static  final String TABLE_CONSULTS = "consults";
    public static final String TABLE_MEDICAL_EXAMINATION ="medicalExamination";
    public static final String TABLE_MEDICAL_REPORT ="medicalReport";
    public static final String TABLE_MEDICATION ="medication";
    public static final String TABLE_PRECAUTION ="precaution";
    public static final String COULMN_DATE ="date";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_FIRST_NAME ="firstName";
    public static final String COLUMN_LAST_NAME ="lastName";
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
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_STREET ="street";
    public static final String COLUMN_BUILDING ="building";
    public static final String COLUMN_PHONE ="phone";
    public static final String COLUMN_EMAIL ="email";
    public static final String COLUMN_SPECIALTY ="specialty";
    public static final String COLUMN_EXPERIENCE_YEARS ="experienceYears";
    public static final String COLUMN_MEDICATION ="medication";
    public static final String COLUMN_MEDICAL_REPORTS ="medicalReports";
    public static final String COLUMN_PID_FK = "pid";
    public static final String COLUMN_DID_FK ="did";
    public static final String COLUMN_DATEOFCONSULTATION ="dateOfConsultation";
    public static final String COLUMN_MEDICINE_NAME = "name";


    public FirebaseDatabase database;
    private List<Patient> patients;
    private List<Doctor> doctors;
    private List<Examination> medicalResults;
    private List<Patient> myPatients;
    private List<Doctor> myDoctors;
    private List<MedicalData> medicalDatas;

    private boolean dataReady =false;
    private boolean loggedIn=false;
    private static DBHandler singlton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private Patient patientMe;
    private Doctor doctorMe;
    private LoginType loginType;

    public enum LoginType {
        DOCTOR,PATIENT
    }

    public Person getActiveUser(){
        switch (loginType){
            case DOCTOR:
                return doctorMe;
            case PATIENT:
                return patientMe;
        }
        return null;
    }

    public void setActiveUser(Person p){
        switch (loginType){
            case DOCTOR:
                doctorMe = (Doctor) p;
            case PATIENT:
                patientMe = (Patient) p;
        }
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

    public void setUser(FirebaseUser user) {
        /*database.getReference().child(TABLE_PATIENT).child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Patient person= dataSnapshot.getValue(Patient.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        this.user=user;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    public FirebaseAuth.AuthStateListener getmAuthListener() {
        return mAuthListener;
    }

    public void setmAuthListener(FirebaseAuth.AuthStateListener mAuthListener) {
        this.mAuthListener = mAuthListener;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singlton=this;
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    DBHandler db = DBHandler.getInstance();
                    db.setUser(user);
                    db.database.getReference(TABLE_PATIENT).child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            DBHandler.getInstance().setActiveUser(dataSnapshot.getValue(Patient.class));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    loggedIn = true;
                    //
                    setLoginType(LoginType.PATIENT);
                } else {
                    // User is signed out
                    loggedIn=false;
                }
                // ...
            }
        };
        mAuth.addAuthStateListener(mAuthListener);

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
                    Doctor d= doctor.getValue(Doctor.class);
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
    public List<Examination> getMedicalResult() {
        return medicalResults;
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

    public void addPatient(Patient p){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT);
        String key = myRef.push().getKey();
        p.setId(key);
        myRef.child(key).setValue(p);

    }


    public void addDoctor(Doctor d){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_DOCTOR);
        String key = myRef.push().getKey();
        d.setId(key);
        myRef.child(key).setValue(d);

    }

    public void addConsult (Consults c){

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(TABLE_CONSULTS);
        String key = myRef.push().getKey();
        c.setCid(key);
        myRef.child(key).setValue(c);

    }
    public void signUp(Person person){

    }
    public List<Patient> getPatients(){
        return patients;
    }


    public List<Patient> getMyPatients(Doctor d) {
        final String did = d.getId();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(TABLE_CONSULTS);


    public Doctor getDoctorById(String id) {
        for (Doctor d : doctors){
            if (d.getId().equals(id))
                return d;
        }
        return null;
    }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot consult :  dataSnapshot.getChildren()) {
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

    public List<Doctor> getMyDoctors( Patient p) {
        final String pid = p.getId();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(TABLE_CONSULTS);
        myDoctors = new ArrayList<Doctor>();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot consult :  dataSnapshot.getChildren()) {
                    Consults mConsult = consult.getValue(Consults.class);
                    if((mConsult).getPid().equals(pid)) {
                        Doctor d = getDoctorById(mConsult.getDid());
                        if (myDoctors.contains(d))
                            continue;
                        else
                            myDoctors.add(d);
                    }
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        return myDoctors;
    }
    public List<Medication> getPatientMedicines(Patient p){
        // return p.getPatientMedicines();
        final String pid = p.getId();
        final List<Medication> patientMedicines = new ArrayList<>();
        final DatabaseReference patientRef = FirebaseDatabase.getInstance().getReference(TABLE_PATIENT);
        //patientRef.orderByChild(COLUMN_ID).equalTo(pid).
        patientRef.orderByChild(COLUMN_ID).equalTo(pid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot medication : dataSnapshot.child(TABLE_MEDICATIONS).getChildren()){
                    Medication m = medication.getValue(Medication.class);
                    patientMedicines.add(m);
                }
            }


        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    return  patientMedicines;


}

    public Patient getPatientById(final String id){
        for (Patient p : patients){
            if (p.getId().equals(id))
                return p;
        }
        return null;
    }

    public Doctor getDoctorById(String id) {
        for (Doctor d : doctors){
            if (d.getId().equals(id))
                return d;
        }
        return null;
    }


    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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