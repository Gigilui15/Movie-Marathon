package com.example.mobileassignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String API_KEY = "1bde390d4ef9fe09e434ab3569042f63";
    String BASE_URL = "https://api.themoviedb.org";
    String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/";
    String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    int PAGE = 1;
    String LANGUAGE = "en-US";
    String CATEGORY = "popular";

    static void setPage(int i) {
        i = PAGE;
    }

    //https://api.themoviedb.org/3/trending/all/day?api_key=1bde390d4ef9fe09e434ab3569042f63&language=en&page=1

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
