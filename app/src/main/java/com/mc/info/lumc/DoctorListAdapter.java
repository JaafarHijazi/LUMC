package com.mc.info.lumc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mahdi saab on 12/26/2016.
 */

public class DoctorListAdapter extends BaseAdapter {

    private final ArrayList<Doctor> AllDoctorsArrayList;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public DoctorListAdapter(Context context,ArrayList<Doctor> AllDoctorsArrayList) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.AllDoctorsArrayList=AllDoctorsArrayList;
    }

    public void add(Doctor p) {
        AllDoctorsArrayList.add(p);
        notifyDataSetChanged();
    }

    public void remove(Doctor p) {
        AllDoctorsArrayList.remove(p);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return AllDoctorsArrayList.size();
    }

    @Override
    public Doctor getItem(int position) {
        return AllDoctorsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.list_doctor_item ,parent,false);
        TextView Name = (TextView) view.findViewById(R.id.list_doctor_item_Name);

        TextView specialty = (TextView) view.findViewById(R.id.list_doctor_item_specialty);
        ImageView DoctorImage = (ImageView) view.findViewById(R.id.list_item_doctor_image);

        Doctor p = getItem(position);
        Name.setText(p.getFirstName()+ " " + p.getLastName());
        specialty.setText(p.getSpecialty());
        DoctorImage.setImageResource(R.drawable.profile);
        return view;
    }
}
