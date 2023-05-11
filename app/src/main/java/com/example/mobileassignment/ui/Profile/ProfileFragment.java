package com.example.mobileassignment.ui.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.API.MovieResults;
import com.example.mobileassignment.Database.User;
import com.example.mobileassignment.Database.backend.MovieDbHelper;
import com.example.mobileassignment.Database.backend.UserDbHelper;
import com.example.mobileassignment.MainActivity;
import com.example.mobileassignment.R;
import com.example.mobileassignment.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private @NonNull FragmentProfileBinding binding;
    private ProfileAdapter pAdapter;
    private RecyclerView profileView;
    private ProfileViewModel profileViewModel;
    private TextView name_holder;
    private User updatedUser;
    private MovieDbHelper dbHelper;
    private UserDbHelper userHelper;
    private ArrayList<MovieResults.ResultsBean> marathon;
    private TextView noMovies;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Get references to the views
        profileView = root.findViewById(R.id.grid_list);
        name_holder = root.findViewById(R.id.full_name);
        noMovies = root.findViewById(R.id.noMoviesText);

        // Create instances of the database helpers
        dbHelper = new MovieDbHelper(getContext());
        userHelper = new UserDbHelper(getContext());

        // Get the logged-in user from the MainActivity
        User user = getUser();
        // Retrieve the updated user from the database
        updatedUser = userHelper.getUser(user.getUsername(), user.getPassword());

        if (updatedUser != null) {
            name_holder.setText(updatedUser.getFullName());
        } else {
            name_holder.setText("__Profile Name__");
        }

        // Get the list of movie ID's of the user
        List<Integer> faveIDs = updatedUser.getMarathon();
        // Convert movie IDs into MovieResults.ResultsBean objects
        ArrayList<MovieResults.ResultsBean> live = makeMovies(faveIDs);

        fetchItems();
        // Set up the RecyclerView with the movies list
        setUpRecyclerView(live);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private ArrayList<MovieResults.ResultsBean> makeMovies(List<Integer> ids) {
        marathon = new ArrayList<>();
        if (ids != null) {
            for (int id : ids) {
                MovieResults.ResultsBean movie = dbHelper.getMovie(id);
                if (movie != null) {
                    Log.d("Movie Made", "ResultsBean movie successfully created");
                    marathon.add(movie);
                    Log.d("Movie Added", marathon.toString());
                } else {
                    marathon.add(new MovieResults.ResultsBean("Null"));
                    Log.d("Movie not Made", "getMovie returned null or an error occurred");
                }
            }
        } else {
            Log.d("Empty marathon IDS", "no IDs in user's list");
        }
        return marathon;
    }

    private void fetchItems() {
        profileViewModel.getMovies().observe(getViewLifecycleOwner(), this::updateMovieList);
    }

    private void updateMovieList(List<Integer> newMovieID) {
        // This method is called when the list of movie IDs is updated in the ProfileViewModel
        // Notify the adapter that the data has changed
        pAdapter.notifyDataSetChanged();
    }

    private void setUpRecyclerView(ArrayList<MovieResults.ResultsBean> movies) {
        // Set up the RecyclerView with the provided list of movies
        if (movies.size() == 0) {
            noMovies.setVisibility(View.VISIBLE);
        } else {
            noMovies.setVisibility(View.INVISIBLE);
        }
        pAdapter = new ProfileAdapter(marathon, updatedUser);
        profileView.setAdapter(pAdapter);
        profileView.setLayoutManager(new LinearLayoutManager(profileView.getContext()));
    }

    // Method to get the logged-in user
    public User getUser() {
        // Retrieve the user from the MainActivity using the requireActivity() method
        return ((MainActivity) requireActivity()).getUser();
    }
}
