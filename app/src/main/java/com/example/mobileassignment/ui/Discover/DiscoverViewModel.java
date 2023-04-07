package com.example.mobileassignment.ui.Discover;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;

import java.util.List;

import kotlin.annotation.MustBeDocumented;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscoverViewModel extends ViewModel {
    private MutableLiveData<List<MovieResults.ResultsBean>>movieList = new MutableLiveData<>();

    public DiscoverViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        //build URL using constants
        Call<MovieResults> call = apiInterface.getMovies(ApiInterface.CATEGORY,ApiInterface.API_KEY,ApiInterface.LANGUAGE,ApiInterface.PAGE);
        call.enqueue(new Callback<MovieResults>() {
            @Override
            public void onResponse(Call<MovieResults> call, Response<MovieResults> response) {
                MovieResults results = response.body();
                List<MovieResults.ResultsBean> ListOfMovies = results.getResults();
                movieList.setValue(ListOfMovies);

            }

            @Override
            public void onFailure(Call<MovieResults> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<List<MovieResults.ResultsBean>>getMovies(){
        return movieList;
    }

}