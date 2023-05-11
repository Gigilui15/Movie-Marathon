package com.example.mobileassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        // Initialize the views
        user = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        btn = findViewById(R.id.loginButton);
        header = findViewById(R.id.loginText);
    }

    public void loginButton(View view) {
        // Get the username and password from the input fields
        String username = user.getText().toString();
        String password = pwd.getText().toString();

        // Perform user authentication
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            // Create an instance of UserDbHelper to access the database
            UserDbHelper dbHelper = new UserDbHelper(this);
            // Retrieve the user from the database based on the entered username and password
            User user = dbHelper.getUser(username, password);

            if (user == null) {
                // User not found, display an error message
                Toast.makeText(this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            } else {
                // User found, display a success message and navigate to the MainActivity
                Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
    }

    public void goToRegister(View view) {
        // Navigate to the RegisterScreen activity
        startActivity(new Intent(this, RegisterScreen.class));
    }
}