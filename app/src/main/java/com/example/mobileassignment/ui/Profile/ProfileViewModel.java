package com.example.mobileassignment.ui.Profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.Database.backend.MovieDbHelper;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<User> userLiveData;
    private MutableLiveData<List<Integer>> marathonLiveData;
    private MutableLiveData<List<MovieResults.ResultsBean>> marathonResults;
    private MovieDbHelper dbHelper;

    public ProfileViewModel() {
        userLiveData = new MutableLiveData<>();
        userLiveData = getUserLiveData();
        marathonLiveData = new MutableLiveData<>();
        marathonLiveData = getMarathonLiveData();
        marathonResults = new MutableLiveData<>();
        marathonResults.setValue(getMarathonMovies());
        Log.d("Arraylist: ", marathonResults.toString());
    }


    public void setUser(User user) {
        userLiveData.setValue(user);
        marathonLiveData.setValue(user.getMarathon());
    }

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<List<Integer>> getMarathonLiveData() {
        return marathonLiveData;
    }

    public List<MovieResults.ResultsBean> getMarathonMovies() {
        List<MovieResults.ResultsBean> movies = new ArrayList<>();
        List<Integer> marathonIds = marathonLiveData.getValue();
        if (marathonIds != null) {
            for (int id : marathonIds) {
                MovieResults.ResultsBean movie = dbHelper.getMovie(id); // use dbHelper to call getMovie
                if (movie != null) {
                    movies.add(movie);
                }
            }
        }
        return movies;
    }

    public LiveData<List<Integer>> getMovies() {
        return marathonLiveData;
    }
}
