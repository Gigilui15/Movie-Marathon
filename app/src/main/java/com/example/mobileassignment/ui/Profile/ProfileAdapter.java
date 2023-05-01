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
        TextView marathon_movie = holder.marathon_movie;
        marathon_movie.setText(movie.getTitle());
    }

    @Override
    public int getItemCount() {
        return marathon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView marathon_movie;

        public ViewHolder(@NonNull View profileView) {
            super(profileView);
            marathon_movie = (TextView) profileView.findViewById(R.id.marathon_movie);
        }
    }

}