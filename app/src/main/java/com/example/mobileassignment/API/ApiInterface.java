package com.example.mobileassignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://api.themoviedb.org/3/trending/all/day?api_key=1bde390d4ef9fe09e434ab3569042f63&language=en&page=1

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

}
