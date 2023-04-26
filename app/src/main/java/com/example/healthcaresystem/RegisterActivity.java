package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText RegisterUserName,registrationEmail,userPassword,confirmPassword;
    TextView existingUser;
    Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterUserName = findViewById(R.id.RegisterUserName);
        registrationEmail = findViewById(R.id.registrationEmail);
        userPassword = findViewById(R.id.userPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        existingUser = findViewById(R.id.existingUser);
        btnRegister= findViewById(R.id.btnRegister);

        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = RegisterUserName.getText().toString();
                String email = registrationEmail.getText().toString();
                String password = userPassword.getText().toString();
                String passwordConfirmation = confirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcaresystem",null,1);

                if (username.length()==0||email.length()==0||password.length()==0||passwordConfirmation.length()==0){
                    Toast.makeText(RegisterActivity.this, "please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(passwordConfirmation)==0){
                        if (isValid(password)){
                            db.register(username,email,password);
                            Toast.makeText(RegisterActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RegisterActivity.this, "password must contain atleast 8 characters,have a letter and a digit and special characters",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "password and confirm password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public static boolean isValid(String passwordAdhere){
        int f1=0,f2=0,f3=0;
        if (passwordAdhere.length()<8){
            return false;
        }else {
            for (int p=0;p<passwordAdhere.length();p++){
                if (Character.isLetter(passwordAdhere.charAt(p))){
                    f1=1;
                }
            }
            for (int r=0;r<passwordAdhere.length();r++){
                if (Character.isDigit(passwordAdhere.charAt(r))){
                    f2=1;
                }
            }
            for (int s=0;s<passwordAdhere.length();s++){
                char c= passwordAdhere.charAt(s);
                if (c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }
            if (f1==1 && f2==1 && f3==1){
                return true;
            }
            return false;
        }
    }

}