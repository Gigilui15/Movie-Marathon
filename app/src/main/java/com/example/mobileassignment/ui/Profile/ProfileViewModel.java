package com.example.mobileassignment.ui.Profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<List<String>> test;

    public ProfileViewModel() {
        test = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getItems() {
        ArrayList<String> movies = new ArrayList<>();
        for(int i =0; i < 9; i++){
            movies.add("Movie " + (i+1));
        }
        test.setValue(movies);
        return test;
    }
}