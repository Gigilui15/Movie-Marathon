package com.example.mobileassignment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the user object from the intent's extras
        Bundle bundle = this.getIntent().getExtras();
        user = (User) bundle.getSerializable("user");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Set up the navigation configuration for the app bar
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_dashboard, R.id.navigation_profile).build();

        // Set up the navigation controller with the app bar and binding
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public User getUser() {
        // Retrieve the user object from the intent's extras
        Bundle bundle = this.getIntent().getExtras();
        user = (User) bundle.getSerializable("user");

        return user;
    }
}
