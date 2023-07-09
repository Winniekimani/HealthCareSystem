package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    TextView bookAppointmentTitle;
    EditText appointmentName,appointmentAddress,appointmentNumber,appointmentFees;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
     Button btnAppDate,btnAppTime,btnBook,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        bookAppointmentTitle=findViewById(R.id.bookAppointmentTitle);
        appointmentName= findViewById(R.id.appointmentName);
        appointmentAddress= findViewById(R.id.appointmentAddress);
        appointmentNumber= findViewById(R.id.appointmentNumber);
        appointmentFees= findViewById(R.id.appointmentFees);
        btnAppDate = findViewById(R.id.btnAppDate);
        btnAppTime = findViewById(R.id.btnAppTime);
        btnBack = findViewById(R.id.btnBack);
        btnBook = findViewById(R.id.btnBook);

        //set all editTexts to not editable
        appointmentName.setKeyListener(null);
        appointmentAddress.setKeyListener(null);
        appointmentNumber.setKeyListener(null);
        appointmentFees.setKeyListener(null);

        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fullName = intent.getStringExtra("text2");
        String address = intent.getStringExtra("text3");
        String contact = intent.getStringExtra("text4");
        String fees = intent.getStringExtra("text5");

        bookAppointmentTitle.setText(title);
        appointmentName.setText(fullName);
        appointmentAddress.setText(address);
        appointmentNumber.setText(contact);
        appointmentFees.setText("con fee:"+fees +"/=");

        //initiate datePicker
        initDatePicker();
        btnAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        btnAppTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

       btnBook.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //code here
           }
       });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookAppointmentActivity.this,FindDoctorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            //int view,int year=i, int month=i1, int dayOfMonth=i2
            @Override
            public void onDateSet(DatePicker datePicker, int i,int i1,int i2 ) {
                i1=i1+1;
                btnAppDate.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

           //TimePicker view=timepicker, int hour of the day=int i,int minute=int i1
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                btnAppTime.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,timeSetListener,hour,minutes,true);
    }


}