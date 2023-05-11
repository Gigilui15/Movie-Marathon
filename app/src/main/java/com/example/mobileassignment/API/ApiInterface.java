package com.example.mobileassignment.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    // API key for accessing the Movie Database API
    String API_KEY = "1bde390d4ef9fe09e434ab3569042f63";
    // Base URL for the Movie Database API
    String BASE_URL = "https://api.themoviedb.org";
    // Base URL for retrieving movie posters
    String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/";
    // Base URL for retrieving movie backdrops
    String BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w500/";
    // Default page number for movie results
    int PAGE = 1;
    // Default language for movie results
    String LANGUAGE = "en-US";
    // Default category for movie results
    String CATEGORY = "popular";

    /**
     * Retrieves a list of movies based on the specified category.
     *
     * @param category The category of movies to retrieve.
     * @param apiKey   The API key for accessing the Movie Database API.
     * @param language The language for the movie results.
     * @param page     The page number for the movie results.
     * @return A Call object with MovieResults as the response type.
     */

    @GET("/3/movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
