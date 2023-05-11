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
import java.util.ArrayList;

public class RegisterScreen extends AppCompatActivity {
    EditText user, pwd, name;
    Button register;
    TextView header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // Initialize views
        name = findViewById(R.id.Rfullname);
        user = findViewById(R.id.Rusername);
        pwd = findViewById(R.id.Rpassword);
        register = findViewById(R.id.registerButton);
        header = findViewById(R.id.Rregister);
    }

    public void registerClick(View view) {
        // Create an instance of the UserDbHelper class
        UserDbHelper dbHelper = new UserDbHelper(this);

        // Get the values entered by the user
        String fullNameValue = name.getText().toString();
        String usernameValue = user.getText().toString();
        String passwordValue = pwd.getText().toString();

        // Check if all fields are filled
        if (user.length() == 0 || pwd.length() == 0 || name.length() == 0) {
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            // Create a new User object
            ArrayList<Integer> fave = new ArrayList<>();
            User user = new User(fullNameValue, usernameValue, passwordValue);

            // Add the user to the database
            long id = dbHelper.addUser(user);

            // Display a toast message to indicate successful account creation
            Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show();

            // Start the MainActivity with the registered user
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}