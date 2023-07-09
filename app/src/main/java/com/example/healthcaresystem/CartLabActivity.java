package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {

    ListView listviewCart;
    TextView tvTotalPrice;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    Button btnCartDatePicker,btnCartTimePicker,btnCartBack,btnCartCheckout;
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String, String>item;
    private String[][] packages={};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);

        listviewCart=findViewById(R.id.listviewCart);
        tvTotalPrice=findViewById(R.id.tvTotalPrice);
        btnCartDatePicker=findViewById(R.id.btnCartDatePicker);
        btnCartTimePicker=findViewById(R.id.btnCartTimePicker);
        btnCartBack=findViewById(R.id.btnCartBack);
        btnCartCheckout=findViewById(R.id.btnCartCheckout);

        //get the user stored in the db
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(),"healthcaresystem",null,1);

     float totalAmount = 0;
     ArrayList dbData = db.getCartData(username,"lab");
        Toast.makeText(getApplicationContext(), ""+dbData, Toast.LENGTH_SHORT).show();

        //passing number into the packages
        //here we find all the number of five packages
        packages=new String[dbData.size()][];
        for (int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }
        //here we are breaking the loop
        for (int i=0;i<dbData.size();i++){
           String arrData=dbData.get(i).toString();
           String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
           packages[i][0]=strData[0];
           packages[i][4]="Cost:"+strData[1]+"/-";
           totalAmount=totalAmount+Float.parseFloat(strData[1]);
        }
        //set the total in a textview
        tvTotalPrice.setText("Total cost: "+totalAmount);
        //storing all the data into the arraylist
        list= new ArrayList<>();
        for (int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
                //then display into the listview
                listviewCart.setAdapter(sa);

        btnCartBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLabActivity.this,LabTestActivity.class);
                startActivity(intent);
            }
        });
        btnCartCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartLabActivity.this,LabTestBookActivity.class);
                intent.putExtra("price",tvTotalPrice.getText());
                intent.putExtra("date",btnCartDatePicker.getText());
                intent.putExtra("time",btnCartTimePicker.getText());
                startActivity(intent);
            }
        });

        initDatePicker();
        btnCartDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        initTimePicker();
        btnCartTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }

    public void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                i1=i1+1;
                btnCartDatePicker.setText(i2+"/"+i1+"/"+i);
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

    public void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                btnCartDatePicker.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog= new TimePickerDialog(this,timeSetListener,hour,minutes,true);
    }
}