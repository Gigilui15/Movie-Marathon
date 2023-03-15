package com.example.mobileassignment.ui.Watchlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class WatchlistViewModel extends ViewModel {
    private MutableLiveData<List<String>> items;

    public WatchlistViewModel() {
        items = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getItems() {
        ArrayList<String> notifications = new ArrayList<>();
        for(int i =0; i < 20; i++){
            notifications.add("Movie " + (i+1));
        }
        items.setValue(notifications);
        return items;
    }
}