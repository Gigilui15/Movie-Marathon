package com.example.mobileassignment.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobileassignment.API.MovieResults;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String fullName;
    private String username;
    private String password;
    private ArrayList<Integer> marathon;

    public User(String fullName, String username, String password) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.marathon = new ArrayList<Integer>();
    }

    public User(String fullName, String username, String password, ArrayList<Integer> marathon) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.marathon = marathon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<Integer> getMarathon() {
        return marathon;
    }

    public void setMarathon(ArrayList<Integer> marathon) {
        this.marathon = marathon;
    }
}
