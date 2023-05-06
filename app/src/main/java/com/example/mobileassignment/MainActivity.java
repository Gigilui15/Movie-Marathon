package com.example.mobileassignment;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.Database.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mobileassignment.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        user = (User) bundle.getSerializable("user");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_profile, R.id.navigation_dashboard).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_profile); // navigate to profile fragment
        NavigationUI.setupWithNavController(binding.navView, navController);
        // listen to bottom navigation item selection
        navView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    navController.navigate(R.id.navigation_profile);
                    break;
                case R.id.navigation_dashboard:
                    navController.navigate(R.id.navigation_dashboard);
                    break;
            }
            return true;
        });

    }

    public User getUser() {
        Bundle bundle = this.getIntent().getExtras();
        user = (User) bundle.getSerializable("user");
        return user;
    }
}