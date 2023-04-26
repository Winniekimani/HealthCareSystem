package com.example.healthcaresystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText loginName,loginPassword;
    Button btnLogin;
    TextView registerNewUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginName = findViewById(R.id.loginName);
        loginPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        registerNewUser=findViewById(R.id.registerNewUser);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginName.getText().toString();
                String password = loginPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcaresystem",null,1);

                if (username.length()==0||password.length()==0){
                    Toast.makeText(LoginActivity.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    if (db.login(username,password)==1){
                        Toast.makeText(LoginActivity.this, "login successful", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",username);
                        //to save data with key and value
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "invalid username and password", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        registerNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}