package com.mc.info.lumc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hphh4 on 1/7/2017.
 */

public class PrescriptionAdapter extends BaseAdapter implements Serializable {
    private final ArrayList<String> prescribe;
    private final Context context;
    private Spinner spTypesList;
    private EditText  txtPrescribed;
    private final LayoutInflater layoutInflater;

    public PrescriptionAdapter(Context context,ArrayList<String> prescribe) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.prescribe = prescribe;
    }

    public void add(String p) {
        prescribe.add(p);
        notifyDataSetChanged();
    }

    public void remove(String p) {
        prescribe.remove(p);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return prescribe.size();
    }

    @Override
    public String getItem(int position) {
        return prescribe.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.prescription_item ,parent,false);
        View view1 = layoutInflater.inflate(R.layout.activity_create_prescription,parent,false);

        spTypesList = (Spinner) view1.findViewById(R.id.activity_create_prescription_typeList);
        txtPrescribed = (EditText) view1.findViewById(R.id.activity_create_prescription_prescribed);

        TextView type = (TextView) view.findViewById(R.id.prescription_item_type);
        TextView prescribe = (TextView) view.findViewById(R.id.prescription_item_prescribe);
         String p = getItem(position);

//        type.setText(spTypesList.getSelectedItem().toString());
        prescribe.setText(p.toString());
        return view;
    }

}
