package com.example.mobileassignment.ui.Discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.Database.backend.UserDbHelper;
import com.example.mobileassignment.MainActivity;
import com.example.mobileassignment.R;
import com.example.mobileassignment.databinding.FragmentDiscoverBinding;
import java.util.ArrayList;

public class DiscoverFragment extends Fragment {
    private @NonNull FragmentDiscoverBinding binding;
    private DiscoverAdapter dAdapter;
    private RecyclerView discoverView;
    private DiscoverViewModel discoverViewModel;
    private UserDbHelper userHelper;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Create an instance of DiscoverViewModel using ViewModelProvider
        discoverViewModel = new ViewModelProvider(this).get(DiscoverViewModel.class);

        // Inflate the layout for this fragment using ViewBinding
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get references to the views
        discoverView = root.findViewById(R.id.movies_list);

        // Get the logged-in user from the MainActivity
        user = ((MainActivity) requireActivity()).getUser();

        // Create an instance of UserDbHelper for database operations
        userHelper = new UserDbHelper(getContext());

        // Set up the RecyclerView
        setUpRecyclerView();

        // Fetch the items (movies) from the DiscoverViewModel
        fetchItems();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void fetchItems() {
        // Observe the movie list from the DiscoverViewModel and update the adapter
        discoverViewModel.getMovies().observe(getViewLifecycleOwner(), movieList -> {
            dAdapter.updateMovies(movieList);
        });
    }

    private void setUpRecyclerView() {
        // Create an instance of DiscoverAdapter and set it to the RecyclerView
        dAdapter = new DiscoverAdapter(new ArrayList<>(), user, userHelper);
        discoverView.setAdapter(dAdapter);
        discoverView.setLayoutManager(new LinearLayoutManager(discoverView.getContext()));
    }
}
