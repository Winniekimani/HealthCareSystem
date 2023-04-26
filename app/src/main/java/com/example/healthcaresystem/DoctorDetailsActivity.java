package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DoctorDetailsActivity extends AppCompatActivity {

    //setting dummy hardcoded array of  doctors
    private String[][] doctorDetails1={
            {"DoctorName:Maryann Njeri","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","700"},
            {"DoctorName:Jackline Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","1000"},
            {"DoctorName:Winnie Kimani","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","1500"},
            {"DoctorName:Peter Njenga","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","2000"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"},
    };
    private String[][] doctorDetails2={
            {"DoctorName:Maryann Njeri","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","700"},
            {"DoctorName:Jackline Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","1000"},
            {"DoctorName:Winnie Kimani","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","1500"},
            {"DoctorName:Peter Njenga","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","2000"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"},
    };
    private String[][] doctorDetails3={
            {"DoctorName:Maryann Njeri","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","700"},
            {"DoctorName:Jackline Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","1000"},
            {"DoctorName:Winnie Kimani","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","1500"},
            {"DoctorName:Peter Njenga","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","2000"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"},
    };
    private String[][] doctorDetails4={
            {"DoctorName:Maryann Njeri","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","700"},
            {"DoctorName:Jackline Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","1000"},
            {"DoctorName:Winnie Kimani","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","1500"},
            {"DoctorName:Peter Njenga","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","2000"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"},
    };
    private String[][] doctorDetails5={
            {"DoctorName:Maryann Njeri","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","700"},
            {"DoctorName:Jackline Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","1000"},
            {"DoctorName:Winnie Kimani","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","1500"},
            {"DoctorName:Peter Njenga","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","2000"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"},
    };


    TextView tvDDTitle;
    Button btnBack;
    String[][]doctorDetails={};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tvDDTitle= findViewById(R.id.tvDDTitle);
        btnBack = findViewById(R.id.btnBack);


        //Bundle bundle = getIntent().getExtras();
        // getting the string back
        //String title = bundle.getString("title", "");
        //tvDDTitle.setText(title);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tvDDTitle.setText(title);

        if (title.compareTo("Family Physician")==0){
            doctorDetails=doctorDetails1;
        } else if (title.compareTo("Dietician")==0) {
            doctorDetails=doctorDetails2;
        } else if (title.compareTo("Dentist")==0) {
            doctorDetails=doctorDetails3;
        }else if (title.compareTo("Surgeon")==0) {
            doctorDetails = doctorDetails4;
        }else {
            doctorDetails = doctorDetails5;
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class);
                startActivity(intent);
            }
        });
    }
}