package com.example.mobileassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mobileassignment.API.MovieResults;

public class MovieDetails extends AppCompatActivity {

    MovieResults.ResultsBean movie;
    private TextView movie_title;
    private TextView movie_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movie_title = findViewById(R.id.title_movie);
        movie_description = findViewById(R.id.description);

        //get the Intent
        Intent intent = getIntent();
        movie = (MovieResults.ResultsBean) intent.getSerializableExtra("movie");
        movie_title.setText(movie.getTitle());
        movie_description.setText(movie.getOverview());
        //get the Serializable object Movie(id) which has been clicked on
        //populate the Views with the appropriate Data
    }
}