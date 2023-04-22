package com.example.mobileassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.Database.backend.UserDbHelper;


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
        String username = user.getText().toString();
        String password = pwd.getText().toString();
        //include user Authentication here
        if(username.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            UserDbHelper dbHelper = new UserDbHelper(this);
            User user_profile = dbHelper.getUser(username, password);
            if (user_profile == null) {
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", user_profile);
                startActivity(intent);
            }
        }
    }


    public void goToRegister(View view){
        startActivity(new Intent(this, RegisterScreen.class));
    }

}