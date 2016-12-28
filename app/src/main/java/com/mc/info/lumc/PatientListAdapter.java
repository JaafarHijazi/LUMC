package com.mc.info.lumc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mahdi saab on 12/18/2016.
 */

public class PatientListAdapter extends BaseAdapter {

    private final ArrayList<Patient> AllPatientsArrayList;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public PatientListAdapter(Context context,ArrayList<Patient> AllPatientsArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.AllPatientsArrayList=AllPatientsArrayList;
    }

    public void add(Patient p) {
        AllPatientsArrayList.add(p);
        notifyDataSetChanged();
    }

    public void remove(Patient p) {
        AllPatientsArrayList.remove(p);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return AllPatientsArrayList.size();
    }

    @Override
    public Patient getItem(int position) {
        return AllPatientsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.list_patients_item ,parent,false);
        TextView firstName = (TextView) view.findViewById(R.id.list_item_patient_Name);
        ImageView patientImage = (ImageView) view.findViewById(R.id.list_item_patient_image);

        Patient p = getItem(position);
        firstName.setText(p.getFirstName() + " " + p.getLastName());
        patientImage.setImageResource(R.drawable.profile);
        return view;
    }


}






