package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    CardView cardGoBack,cardFDFamilyPhysician,cardFDFamilyDietician,
            cardFDDentist,cardFDSurgeon,cardFDCardiologist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);


        cardGoBack= findViewById(R.id.cardGoBack);
        cardFDFamilyPhysician= findViewById(R.id.cardFDFamilyPhysician);
        cardFDFamilyDietician=findViewById(R.id.cardFDFamilyDietician);
        cardFDDentist=findViewById(R.id.cardFDDentist);
        cardFDSurgeon=findViewById(R.id.cardFDSurgeon);
        cardFDCardiologist=findViewById(R.id.cardFDCardiologist);

        cardGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        cardFDFamilyPhysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                Bundle bundle= new Bundle();
                // storing the string value in the bundle
                // which is mapped to key
                bundle.putString("title","Family Physicians");
                // passing the bundle into the intent
                intent.putExtras(bundle);
                startActivity(intent);

                //Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                //intent.putExtra("title","Family Physicians");
                //startActivity(intent);
            }
        });
        cardFDFamilyDietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Dietician");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cardFDDentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Dentist");
                startActivity(intent);
            }
        });
        cardFDSurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                intent.putExtra("title","Surgeon");
                startActivity(intent);
            }
        });
        cardFDCardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title","Cardiologist");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}