package com.example.mobileassignment.ui.Discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscoverViewModel extends ViewModel {
    private MutableLiveData<List<MovieResults.ResultsBean>> movieList = new MutableLiveData<>();
    public DiscoverViewModel() {
        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the API interface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        // Build the URL using constants and make the API call
        Call<MovieResults> call = apiInterface.getMovies(ApiInterface.CATEGORY, ApiInterface.API_KEY, ApiInterface.LANGUAGE, ApiInterface.PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                // Handle the API response
                MovieResults results = response.body();
                List<MovieResults.ResultsBean> listOfMovies = results.getResults();
                movieList.setValue(listOfMovies);
            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                // Handle API call failure
                t.printStackTrace();
            }
        });
    }
    public LiveData<List<MovieResults.ResultsBean>> getMovies() {
        return movieList;
    }
}
