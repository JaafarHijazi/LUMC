package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class VitaminDForm extends AppCompatActivity {

    /*Button ok = (Button) findViewById(R.id.vitamin_d_form_vitamin_d_btnInsert);
    TextView value = (TextView) findViewById(R.id.vitamin_d_form_vitamin_d_value) ;*/
   // TextView date = (TextView) findViewById(R.id.vitamin_d_form_test_date) ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin_d_form);
        /*ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VitaminD test = new VitaminD();
                HashMap<String,Double> vitaminAHashMap=new HashMap<String, Double>();
                vitaminAHashMap.put("vitamin D",Double.parseDouble(value.getText().toString()));
            }
        });*/
    }
}
