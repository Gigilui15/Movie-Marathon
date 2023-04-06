package com.example.mobileassignment.ui.Discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.R;
import com.example.mobileassignment.ui.Watchlist.ItemsAdapter;

import java.util.List;

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
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView primaryTextView;
        public ViewHolder(final View discoverView) {
            super(discoverView);
            primaryTextView = (TextView) discoverView.findViewById(R.id.movie_title);
        }
    }

    public void updateMovies (List<MovieResults.ResultsBean> newMovies){
        movies.clear();
        movies.addAll(newMovies);
        notifyDataSetChanged();
    }
}
