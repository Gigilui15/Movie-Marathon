package com.example.mobileassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginScreen extends AppCompatActivity {

    EditText user, pwd;
    Button btn;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        user = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        btn = findViewById(R.id.loginButton);
        header = findViewById(R.id.loginText);
    }

    public void loginButton(View view){
        if(user.length() == 0 || pwd.length() == 0 ){
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        startActivity(new Intent(this, MainActivity.class));
    }
}