package com.example.mobileassignment.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    // Class representing a user

    private String fullName; // User's full name
    private String username; // User's username
    private String password; // User's password
    private ArrayList<Integer> marathon; // List of movie IDs for the user's marathon

    // Constructor for creating a new user without marathon list (Register)
    public User(String fullName, String username, String password) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.marathon = new ArrayList<Integer>();
    }

    // Constructor for creating a new user with marathon list
    public User(String fullName, String username, String password, ArrayList<Integer> marathon) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.marathon = marathon;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for full name
    public String getFullName() {
        return fullName;
    }

    // Setter for full name
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Getter for marathon list
    public ArrayList<Integer> getMarathon() {
        return marathon;
    }

    // Setter for marathon list
    public void setMarathon(ArrayList<Integer> marathon) {
        this.marathon = marathon;
    }
}
