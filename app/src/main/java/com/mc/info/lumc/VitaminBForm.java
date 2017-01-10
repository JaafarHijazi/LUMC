package com.mc.info.lumc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class VitaminBForm extends AppCompatActivity {

    /*Button ok = (Button) findViewById(R.id.vitamin_b_form_vitamin_b_btnInsert);
    TextView value = (TextView) findViewById(R.id.vitamin_b_form_vitamin_b_value) ;
    TextView date = (TextView) findViewById(R.id.vitamin_b_form_test_date) ;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamin_b_form);
        /*ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VitaminB test = new VitaminB();
                HashMap<String,Double> vitaminAHashMap=new HashMap<String, Double>();
                vitaminAHashMap.put("vitamin B",Double.parseDouble(value.getText().toString()));
                Toast toast =   Toast.makeText(VitaminBForm.this,"Cannot Connect ",Toast.LENGTH_LONG);
                toast.show();
            }
        });*/
    }

}
