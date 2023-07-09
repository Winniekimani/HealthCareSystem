package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView LTDPackage, tvTotalCost;
    EditText edLabTestDetails;
    Button btnAddToCart, btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        LTDPackage = findViewById(R.id.LTDPackage);
        tvTotalCost = findViewById(R.id.tvTotalCost);
        edLabTestDetails = findViewById(R.id.edLabTestDetails);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBack = findViewById(R.id.btnBack);

        edLabTestDetails.setKeyListener(null);

        Intent intent = getIntent();
        LTDPackage.setText(intent.getStringExtra("text1"));
        edLabTestDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost:"+intent.getStringExtra("text3" )+"/=");

        //String labTestDetails = intent.getStringExtra("text2");
        //String cost = intent.getStringExtra("text3" + "/=");

        //LTDPackage.setText(labTestName);
        //edLabTestDetails.setText(labTestDetails);
        //tvTotalCost.setText(cost);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LabTestDetailsActivity.this, LabTestActivity.class);
                startActivity(intent);
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code here
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","");
                String product =LTDPackage.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcaresystem",null,1);
                if (db.checkCart(username,product)==1){
                    Toast.makeText(LabTestDetailsActivity.this, "product already added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(LabTestDetailsActivity.this, "record inserted to cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LabTestDetailsActivity.this,LabTestActivity.class);
                    startActivity(intent);
                }
            }
        });


    }
}