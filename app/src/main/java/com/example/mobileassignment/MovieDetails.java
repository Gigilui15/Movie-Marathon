package com.example.mobileassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.transform.RoundedCornersTransformation;

public class MovieDetails extends AppCompatActivity {
    MovieResults.ResultsBean movie;
    public ImageLoader imageLoader;
    private ImageView movie_poster;
    private ImageView movie_backdrop;
    private TextView movie_title;
    private TextView movie_description;
    private TextView language;
    private TextView popularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // Initialize views
        movie_poster = findViewById(R.id.imgMovie);
        movie_backdrop = findViewById(R.id.backdrop);
        movie_title = findViewById(R.id.title_movie);
        movie_description = findViewById(R.id.description);
        language = findViewById(R.id.lan);
        popularity = findViewById(R.id.pop);

        // Get the Intent
        Intent intent = getIntent();
        movie = (MovieResults.ResultsBean) intent.getSerializableExtra("movie");

        // Set the movie details to the respective views
        movie_title.setText(movie.getTitle());
        movie_description.setText(movie.getOverview());
        language.setText(movie.getOriginal_language());
        popularity.setText(String.valueOf(movie.getPopularity()));
        Log.d("MovieDetails", "Media type: " + movie.getPopularity());

        // Load the images using Coil image loading library
        imageLoader = Coil.imageLoader(MovieDetails.this);

        // Load the poster image
        ImageRequest poster = new ImageRequest.Builder(this)
                .data(ApiInterface.POSTER_BASE_URL + movie.getPoster_path())
                .placeholder(R.drawable.poster_placeholder) // Add a placeholder image if needed
                .error(R.drawable.poster_placeholder) // Add an error image if needed
                .transformations(new RoundedCornersTransformation(25))
                .target(movie_poster)
                .build();

        // Load the backdrop image
        ImageRequest backdrop = new ImageRequest.Builder(this)
                .data(ApiInterface.BACKDROP_BASE_URL + movie.getBackdrop_path())
                .placeholder(R.drawable.poster_placeholder) // Add a placeholder image if needed
                .error(R.drawable.poster_placeholder) // Add an error image if needed
                .target(movie_backdrop)
                .build();

        // Enqueue the image requests for loading
        imageLoader.enqueue(backdrop);
        imageLoader.enqueue(poster);
    }
}
