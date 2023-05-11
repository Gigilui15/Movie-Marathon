/**
 * ViewModel class for the profile feature.
 * Responsible for managing the data related to the user profile and marathon movies.
 */
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
    /**
     * Constructor for the ProfileViewModel class.
     * Initializes the LiveData objects and retrieves the marathon movies.
     */
    public ProfileViewModel() {
        userLiveData = new MutableLiveData<>();
        userLiveData = getUserLiveData();
        marathonLiveData = new MutableLiveData<>();
        marathonLiveData = getMarathonLiveData();
        marathonResults = new MutableLiveData<>();
        marathonResults.setValue(getMarathonMovies());
        Log.d("Arraylist: ", marathonResults.toString());
    }

    /**
     * Sets the user data in the ViewModel.
     * @param user The user object to be set.
     */
    public void setUser(User user) {
        userLiveData.setValue(user);
        marathonLiveData.setValue(user.getMarathon());
    }

    /**
     * Retrieves the LiveData object containing the user data.
     * @return The MutableLiveData object for the user data.
     */
    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    /**
     * Retrieves the LiveData object containing the marathon data.
     * @return The MutableLiveData object for the marathon data.
     */
    public MutableLiveData<List<Integer>> getMarathonLiveData() {
        return marathonLiveData;
    }

    /**
     * Retrieves the marathon movies based on the marathon IDs.
     * @return The list of marathon movies.
     */
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
    /**
     * Retrieves the LiveData object containing the marathon movies.
     * @return The LiveData object for the marathon movies.
     */
    public LiveData<List<Integer>> getMovies() {
        return marathonLiveData;
    }
}
