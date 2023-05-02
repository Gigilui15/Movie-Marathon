package com.example.mobileassignment.ui.Discover;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.Database.backend.MovieDbHelper;
import com.example.mobileassignment.Database.backend.UserDbHelper;
import com.example.mobileassignment.MainActivity;
import com.example.mobileassignment.MovieDetails;
import com.example.mobileassignment.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.transform.RoundedCornersTransformation;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {

    private List<MovieResults.ResultsBean> movies;
    private User user;
    ArrayList<Integer> marathon;

    public DiscoverAdapter(List<MovieResults.ResultsBean> movies, User user) {
        this.movies = movies;
        this.user = user;
        this.marathon = user.getMarathon();
    }

    @NonNull
    @Override
    public DiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View discoverView = inflater.inflate(R.layout.layout_discovercard, parent, false);
        return new DiscoverAdapter.ViewHolder(discoverView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverAdapter.ViewHolder holder, int position) {
        MovieResults.ResultsBean mv = movies.get(position);
        TextView primaryTextView = holder.primaryTextView;
        primaryTextView.setText(mv.getTitle());
        TextView rating = holder.rating;
        double voteAverage = mv.getVote_average();
        rating.setText(String.valueOf(voteAverage));
        TextView date = holder.date ;
        date.setText(mv.getRelease_date());

        //checking if movie is in list
        if (marathon.contains(mv.getId())) {
            holder.addButton.setVisibility(View.INVISIBLE);
            holder.removeButton.setVisibility(View.VISIBLE);
        } else {
            holder.addButton.setVisibility(View.VISIBLE);
            holder.removeButton.setVisibility(View.INVISIBLE);
        }

        ImageRequest request = new ImageRequest.Builder(holder.itemView.getContext())
                .data(ApiInterface.POSTER_BASE_URL + mv.getPoster_path())
                .placeholder(R.drawable.poster_placeholder) // add a placeholder image if needed
                .error(R.drawable.poster_placeholder)
                .transformations(new RoundedCornersTransformation(25))// add an error image if needed
                .target(holder.posterImage)
                .build();
        holder.imageLoader.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView primaryTextView;
        public TextView rating;
        public TextView date;
        public ImageView posterImage;
        public ImageLoader imageLoader;
        public Button addButton;
        public Button removeButton;

        public ViewHolder(final View discoverView) {
            super(discoverView);
            primaryTextView = discoverView.findViewById(R.id.movie_title);
            rating = discoverView.findViewById(R.id.movie_rate);
            date = discoverView.findViewById(R.id.movie_release);
            posterImage = discoverView.findViewById(R.id.movie_poster);
            imageLoader = Coil.imageLoader(discoverView.getContext());
            addButton = discoverView.findViewById(R.id.add_button);
            removeButton = discoverView.findViewById(R.id.remove_button);

            //Movie Card opening Movie Details
            discoverView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the selected movie
                    MovieResults.ResultsBean selectedMovie = movies.get(getAdapterPosition());

                    // Create an Intent to launch the MovieDetails activity
                    Intent intent = new Intent(v.getContext(), MovieDetails.class);
                    intent.putExtra("movie", (Serializable) selectedMovie);
                    // Launch the activity
                    v.getContext().startActivity(intent);
                }
            });
            //Add to List Click Function
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MovieDbHelper movieDB = new MovieDbHelper(discoverView.getContext());
                    UserDbHelper userDB = new UserDbHelper(discoverView.getContext());
                    MovieResults.ResultsBean selectedMovie = movies.get(getAdapterPosition());
                    //add the movie to the movie database
                    movieDB.addMovie(selectedMovie);
                    //add the movie ID to the User Marathon List
                    Log.d("marathon",marathon.toString());
                    marathon.add(selectedMovie.getId());
                    user.setMarathon(marathon);
                    userDB.updateUser(user);
                    Log.d("marathon",marathon.toString());
                    addButton.setVisibility(View.INVISIBLE);
                    removeButton.setVisibility(View.VISIBLE);
                }
            });
            removeButton.setOnClickListener(new View.OnClickListener() {
                //This works
                @Override
                public void onClick(View v) {
                    UserDbHelper userDB = new UserDbHelper(discoverView.getContext());
                    MovieResults.ResultsBean selectedMovie = movies.get(getAdapterPosition());

                    // remove the movie ID from User Marathon List
                    int index = marathon.indexOf(selectedMovie.getId());
                    if (index != -1) {
                        marathon.remove(index);
                    }
                    user.setMarathon(marathon);
                    userDB.updateUser(user);
                    Log.d("marathon", marathon.toString());
                    addButton.setVisibility(View.VISIBLE);
                    removeButton.setVisibility(View.INVISIBLE);
                }
            });

        }
    }

    public void updateMovies (List<MovieResults.ResultsBean> newMovies){
        movies.clear();
        movies.addAll(newMovies);
        notifyDataSetChanged();
    }
}
