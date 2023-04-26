package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    CardView cardLogout,cardFindDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();
        Toast.makeText(this, "welcome" +username, Toast.LENGTH_SHORT).show();


        cardLogout=findViewById(R.id.cardLogout);
        cardFindDoctor=findViewById(R.id.cardFindDoctor);


        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //erasing the details of the user when she/he clicks logout
                SharedPreferences.Editor editor =sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        cardFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,FindDoctorActivity.class);
                startActivity(intent);
            }
        });

    }
}