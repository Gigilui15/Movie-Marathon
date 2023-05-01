package com.example.mobileassignment.ui.Profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.R;

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
        View profileView = inflater.inflate(R.layout.profilecard, parent, false);
        return new ViewHolder(profileView);

    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        MovieResults.ResultsBean movie = marathon.get(position);

        ImageRequest request = new ImageRequest.Builder(holder.itemView.getContext())
                .data(ApiInterface.POSTER_BASE_URL + movie.getPoster_path())
                .placeholder(R.drawable.poster_placeholder) // add a placeholder image if needed
                .error(R.drawable.poster_placeholder)
                .target(holder.profile_poster_movie)
                .build();
        holder.imageLoader.enqueue(request);
    }

    @Override
    public int getItemCount() {
        return marathon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageLoader imageLoader;
        public ImageView profile_poster_movie;

        public ViewHolder(@NonNull View profileView) {
            super(profileView);
            imageLoader = Coil.imageLoader(profileView.getContext());
            profile_poster_movie = (ImageView) profileView.findViewById(R.id.profile_poster_movie);
        }
    }


}