package com.example.mobileassignment.ui.Profile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.Database.backend.UserDbHelper;
import com.example.mobileassignment.MovieDetails;
import com.example.mobileassignment.R;

import java.io.Serializable;
import java.util.ArrayList;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.transform.RoundedCornersTransformation;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private ArrayList<MovieResults.ResultsBean> marathon; // List of movies to display
    private User user; // User information

    public ProfileAdapter(ArrayList<MovieResults.ResultsBean> marathon, User user){
        this.marathon = marathon;
        this.user = user;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create and return a new ViewHolder
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View profileView = inflater.inflate(R.layout.layout_discovercard, parent, false);
        return new ViewHolder(profileView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        // Bind data to the ViewHolder
        MovieResults.ResultsBean movie = marathon.get(position);
        TextView primaryTextView = holder.primaryTextView;
        primaryTextView.setText(movie.getTitle());
        TextView rating = holder.rating;
        double voteAverage = movie.getVote_average();
        rating.setText(String.valueOf(voteAverage));
        TextView date = holder.date ;
        date.setText(movie.getRelease_date());
        holder.removeButton.setVisibility(View.VISIBLE);

        // Load and display the movie poster image using Coil library
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
        return marathon.size(); // Return the number of movies in the list
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView primaryTextView;
        public TextView rating;
        public TextView date;
        public ImageView posterImage;
        public ImageLoader imageLoader;
        public Button removeButton;
        public Button addButton;

        public ViewHolder(@NonNull View profileView) {
            super(profileView);
            // Initialize view elements from the layout
            primaryTextView = profileView.findViewById(R.id.movie_title);
            rating = profileView.findViewById(R.id.movie_rate);
            date = profileView.findViewById(R.id.movie_release);
            posterImage = profileView.findViewById(R.id.movie_poster);
            imageLoader = Coil.imageLoader(profileView.getContext());
            removeButton = profileView.findViewById(R.id.remove_button);
            addButton = profileView.findViewById(R.id.add_button);

            // Set click listeners for profileView and removeButton
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
                    UserDbHelper userDB = new UserDbHelper(profileView.getContext());
                    MovieResults.ResultsBean selectedMovie = marathon.get(getAdapterPosition());
                    ArrayList<Integer> userList = user.getMarathon();

                    // Remove the movie ID from User Marathon List
                    int index = userList.indexOf(selectedMovie.getId());
                    if (index != -1) {
                        userList.remove(index);
                        removeButton.setVisibility(View.INVISIBLE);
                        addButton.setVisibility(View.INVISIBLE);
                    }

                    user.setMarathon(userList);
                    userDB.updateUser(user);

                    // Remove the item from the list and notify the adapter
                    marathon.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }
}