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
import com.example.mobileassignment.MovieDetails;
import com.example.mobileassignment.R;
import com.example.mobileassignment.ui.Watchlist.ItemsAdapter;

import java.io.Serializable;
import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import coil.transform.RoundedCornersTransformation;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.ViewHolder> {

    private List<MovieResults.ResultsBean> movies;

    public DiscoverAdapter(List<MovieResults.ResultsBean> movies) {
        this.movies = movies;
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

        public ViewHolder(final View discoverView) {
            super(discoverView);
            primaryTextView = discoverView.findViewById(R.id.movie_title);
            rating = discoverView.findViewById(R.id.movie_rate);
            date = discoverView.findViewById(R.id.movie_release);
            posterImage = discoverView.findViewById(R.id.movie_poster);
            imageLoader = Coil.imageLoader(discoverView.getContext());
            addButton = discoverView.findViewById(R.id.add_button);

            //Movie Card's Click Function
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
                    MovieResults.ResultsBean selectedMovie = movies.get(getAdapterPosition());
                    //Checking the correct Movie ID is logged when clicked
                    String id = String.valueOf(+selectedMovie.getId());
                    Log.d("Movie ID:", id);
                    String message;
                    if(!selectedMovie.getIsInList()){
                        //Add to List + Change to Orange
                        addButton.setText("Remove");
                        addButton.setBackgroundColor(Color.RED);
                        message = ("Added " +selectedMovie.getTitle());
                        selectedMovie.setInList(true);
                    }
                    else{
                        addButton.setText("Add To list");
                        addButton.setBackgroundColor(0xFF388E3C);
                        message = ("Removed " +selectedMovie.getTitle());
                        selectedMovie.setInList(false);
                    }
                    Toast.makeText(v.getContext(), message, Toast.LENGTH_SHORT).show();
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
