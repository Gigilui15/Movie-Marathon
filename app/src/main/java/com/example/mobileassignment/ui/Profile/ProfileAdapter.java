package com.example.mobileassignment.ui.Profile;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.Database.backend.MovieDbHelper;
import com.example.mobileassignment.Database.backend.UserDbHelper;
import com.example.mobileassignment.MovieDetails;
import com.example.mobileassignment.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.transform.RoundedCornersTransformation;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private ArrayList<MovieResults.ResultsBean> marathon;

    public ProfileAdapter(ArrayList<MovieResults.ResultsBean> marathon){
        this.marathon = marathon;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View profileView = inflater.inflate(R.layout.layout_discovercard, parent, false);
        return new ViewHolder(profileView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        MovieResults.ResultsBean movie = marathon.get(position);
        TextView primaryTextView = holder.primaryTextView;
        primaryTextView.setText(movie.getTitle());
        TextView rating = holder.rating;
        double voteAverage = movie.getVote_average();
        rating.setText(String.valueOf(voteAverage));
        TextView date = holder.date ;
        date.setText(movie.getRelease_date());
        holder.removeButton.setVisibility(View.VISIBLE);

        ImageRequest request = new ImageRequest.Builder(holder.itemView.getContext())
                .data(ApiInterface.POSTER_BASE_URL + movie.getPoster_path())
                .placeholder(R.drawable.poster_placeholder) // add a placeholder image if needed
                .error(R.drawable.poster_placeholder)
                .transformations(new RoundedCornersTransformation(25))
                .target(holder.posterImage)
                .build();
        holder.imageLoader.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return marathon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView primaryTextView;
        public TextView rating;
        public TextView date;
        public ImageView posterImage;
        public ImageLoader imageLoader;
        public Button removeButton;

        public ViewHolder(@NonNull View profileView) {
            super(profileView);
            primaryTextView = profileView.findViewById(R.id.movie_title);
            rating = profileView.findViewById(R.id.movie_rate);
            date = profileView.findViewById(R.id.movie_release);
            posterImage = profileView.findViewById(R.id.movie_poster);
            imageLoader = Coil.imageLoader(profileView.getContext());
            removeButton = profileView.findViewById(R.id.remove_button);

            profileView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the selected movie
                    MovieResults.ResultsBean selectedMovie = marathon.get(getAdapterPosition());

                    // Create an Intent to launch the MovieDetails activity
                    Intent intent = new Intent(v.getContext(), MovieDetails.class);
                    intent.putExtra("movie", (Serializable) selectedMovie);
                    // Launch the activity
                    v.getContext().startActivity(intent);
                }
            });
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Logic to remove selected movie's ID from user's list
                }
            });
        }
    }


}