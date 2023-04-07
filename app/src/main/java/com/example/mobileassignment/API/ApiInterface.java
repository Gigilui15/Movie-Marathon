package com.example.mobileassignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String API_KEY = "9f514cf61fa17e951146f7c97600bd4b";
    String BASE_URL = "https://api.themoviedb.org";
    String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/";

    int PAGE = 1;
    String LANGUAGE = "en-US";
    String CATEGORY = "popular";

    //https://api.themoviedb.org/3/trending/all/day?api_key=9f514cf61fa17e951146f7c97600bd4b&language=en&page=1

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
