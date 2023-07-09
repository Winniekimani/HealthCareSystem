package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String packages[][]=
            {
                    //we are putting blanks because we are using the item(multi_lines)which has 5 rows but here we need only two
                    {"package 1: Full Body Checkup","","","","999"},
                    {"package 2: Blood Glucose Fasting","","","","299"},
                    {"package 3: COVID-19 Antibody","","","","899"},
                    {"package 4: Thyroid Check","","","","499"},
                    {"package 5: Immunity Check","","","","699"}

           };

    private String packageDetails[]={

            "Blood Glucose Fasting\n" +
                "Complete Hemogram\n" +
                    "hbA1c\n" +
                    "iron studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase,serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody",
            "Thyroid Profile Total(T3,T4 &TSH Ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP(C Reactive Protein)Quantitative,serum\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };

    HashMap<String,String>item;
    ArrayList list;
    ListView listviewLabTest;
    Button gOToCart,btnBack;
    SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        gOToCart = findViewById(R.id.gOToCart);
        btnBack = findViewById(R.id.btnBack);
        listviewLabTest = findViewById(R.id.listviewLabTest);


        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/=");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listviewLabTest.setAdapter(sa);

        listviewLabTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",packageDetails[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);
            }
        });

        gOToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this,CartLabActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}