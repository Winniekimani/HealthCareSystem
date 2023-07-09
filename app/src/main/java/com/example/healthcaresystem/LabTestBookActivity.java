package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {
    EditText editTextLTBFullName,editTextLTBAddress,editTextLTBPinCode,editTextLTBContactNumber;
    Button btnLTBBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        editTextLTBFullName=findViewById(R.id.editTextLTBFullName);
        editTextLTBAddress=findViewById(R.id.editTextLTBAddress);
        editTextLTBPinCode=findViewById(R.id.editTextLTBPinCode);
        editTextLTBContactNumber=findViewById(R.id.editTextLTBContactNumber);
        btnLTBBooking=findViewById(R.id.btnLTBBooking);

        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");

        btnLTBBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first get the username using sharedpreferences below
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthcaresystem",null,1);
                db.addOrder(username,editTextLTBFullName.getText().toString(),
                        editTextLTBAddress.getText().toString(),
                        editTextLTBContactNumber.getText().toString(),
                        Integer.parseInt( editTextLTBPinCode.getText().toString()),
                        date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(LabTestBookActivity.this, "Your booking is successfully done", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));


            }
        });



    }
}