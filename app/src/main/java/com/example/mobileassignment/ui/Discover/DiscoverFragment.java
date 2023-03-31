package com.example.mobileassignment.ui.Discover;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mobileassignment.API.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobileassignment.API.ApiInterface;
import com.example.mobileassignment.R;
import com.example.mobileassignment.databinding.FragmentDiscoverBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding binding;
    private TextView movieTextView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DiscoverViewModel discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);

        View root = binding.getRoot();
        movieTextView = root.findViewById(R.id.movies_discover);

        discoverViewModel.getMovieTitle().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String title) {
                movieTextView.setText(title);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}