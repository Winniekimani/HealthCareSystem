package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    //setting dummy hardcoded array of  doctors
    private String[][] doctorDetails1={
            {"DoctorName:Maryann Njeri","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","700"},
            {"DoctorName:Jackline Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","1000"},
            {"DoctorName:Winnie Kimani","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","1500"},
            {"DoctorName:Peter Njenga","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","2000"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"}
    };
    private String[][] doctorDetails2={
            {"DoctorName:Julius Maina","HospitalAddress:St Paul's Medical","Exp:8yrs","MobileNo:0726677787","1700"},
            {"DoctorName:Robert Wainaina","HospitalAddress:Agakhan","Exp:12yrs","MobileNo:0740490890","2000"},
            {"DoctorName:Monicah Njeri","HospitalAddress:Nairobi women's hosp","Exp:3yrs","MobileNo:0720907787","3500"},
            {"DoctorName:Peter kamole","HospitalAddress: kisumu National","Exp:15yrs","MobileNo:0728677790","2500"},
            {"DoctorName:victor omondi","HospitalAdress:Murang'a Level 5","Exp:5yrs","MobileNo:0725886522","1500"}
    };
    private String[][] doctorDetails3={
            {"DoctorName:john muli","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677787","1000"},
            {"DoctorName:francis Wanjiku","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090890","700"},
            {"DoctorName:Ryan Chege","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0790907787","2500"},
            {"DoctorName:Lawrence thuku","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0728677787","600"},
            {"DoctorName:Vincent Travis","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0725876522","500"}
    };
    private String[][] doctorDetails4={
            {"DoctorName:Beatrice muthoni","HospitalAddress:Barika Medical","Exp:15yrs","MobileNo:0726677787","700"},
            {"DoctorName:catherine wanjiru","HospitalAddress:Agakhan","Exp:10yrs","MobileNo:0720090890","1000"},
            {"DoctorName:lucy wagio","HospitalAddress:Kenyatta hosp","Exp:17yrs","MobileNo:0790907787","1500"},
            {"DoctorName:lynette peter","HospitalAddress: kabuku Medical","Exp:25yrs","MobileNo:0728677787","2000"},
            {"DoctorName:timothy bayo","HospitalAdress:Thika Level 5","Exp:30yrs","MobileNo:0725876522","500"}
    };
    private String[][] doctorDetails5={
            {"DoctorName:Fredrick Gituamba","HospitalAddress:Barika Medical","Exp:5yrs","MobileNo:0726677525","500"},
            {"DoctorName:Geoffrey kamau","HospitalAddress:Agakhan","Exp:15yrs","MobileNo:0720090990","500"},
            {"DoctorName:priscilla mwihaki","HospitalAddress:Kenyatta hosp","Exp:7yrs","MobileNo:0745607787","2500"},
            {"DoctorName:joyce wangari","HospitalAddress: kabuku Medical","Exp:20yrs","MobileNo:0722577787","2500"},
            {"DoctorName:austin mwangi","HospitalAdress:Thika Level 5","Exp:3yrs","MobileNo:0729096522","1500"}
    };


    TextView tvDDTitle;
    Button btnBack;
    String[][]doctorDetails={};
    ArrayList list;
    HashMap<String,String>item;
    SimpleAdapter sa;
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

        list = new ArrayList<>();
        for (int i=0;i<doctorDetails.length;i++) {

            //get one item hashmap
            item = new HashMap<>();
            //putting the doctor details in a row
            item.put("line1", doctorDetails[i][0]);
            item.put("line2", doctorDetails[i][1]);
            item.put("line3", doctorDetails[i][2]);
            item.put("line4", doctorDetails[i][3]);
            item.put("line5", "cons fee:" + doctorDetails[i][4] + "/=");
            list.add(item);
        }
            sa= new SimpleAdapter(this,list,R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
            );

            ListView listviewDD = findViewById(R.id.listviewDD);
            listviewDD.setAdapter(sa);

            listviewDD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Intent intent = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                    intent.putExtra("text1",title);
                    intent.putExtra("text2",doctorDetails[i][0]);
                    intent.putExtra("text3",doctorDetails[i][1]);
                    //experience is not required on book appointment ,thats why we've skippped the 2nd
                    intent.putExtra("text4",doctorDetails[i][3]);
                    intent.putExtra("text5",doctorDetails[i][4]);
                    startActivity(intent);
                }
            });
        }

    }
