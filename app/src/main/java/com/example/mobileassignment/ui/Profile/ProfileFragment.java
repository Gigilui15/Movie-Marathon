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
import androidx.recyclerview.widget.GridLayoutManager;
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

    private @NonNull
    FragmentProfileBinding binding;
    private ProfileAdapter pAdapter;
    private RecyclerView profileView;
    private ProfileViewModel profileViewModel;
    private TextView name_holder;
    private User user;
    private List<Integer> faveIDs;
    MovieDbHelper dbHelper;
    UserDbHelper userHelper;
    private ArrayList<MovieResults.ResultsBean> marathon;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        profileView = root.findViewById(R.id.grid_list);
        name_holder = root.findViewById(R.id.full_name);
        //getting the user from Main Activity
        user = getUser();
        //getting the list of movie ID's of the user
        this.faveIDs = user.getMarathon();
        //Method to convert movieIDs into Movie(ResultsBean) objects
        makeMovies(faveIDs);

        if (user != null) {
            name_holder.setText(user.getFullName());
        }else {
            name_holder.setText("__Profile Name__");
        }
        userHelper = new UserDbHelper(getContext());

        fetchItems();
        setUpRecyclerView();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Update user data from the database
        User updatedUser = userHelper.getUser(user.getUsername(),user.getPassword()); // Replace user.getId() with your implementation
        if (updatedUser != null) {
            user = updatedUser;
            // Update UI with new user data
            name_holder.setText(user.getFullName());
            // Get the updated list of movie IDs and create movies
            faveIDs = user.getMarathon();
            makeMovies(faveIDs);
            // Update the adapter with the new movie list
            updateMovieList((ArrayList)marathon);
        }
    }


    private void makeMovies(List<Integer> ids) {
        dbHelper = new MovieDbHelper(getContext());
        userHelper = new UserDbHelper(getContext());
        marathon = new ArrayList<MovieResults.ResultsBean>();
        if(ids != null){
            for (int id : ids) {
                MovieResults.ResultsBean movie = dbHelper.getMovie(id); // use dbHelper to call getMovie
                if (movie != null) {
                    Log.d("Movie Made", "ResultsBean movie successfully created");
                    marathon.add(movie);
                    Log.d("Movie Added", marathon.toString());
                }
                else{
                    marathon.add(new MovieResults.ResultsBean("Null"));
                    Log.d("Movie not Made", "getMovie returned null or an error occurred");
                }
            }
        }else{
            Log.d("Empty marathon IDS", "no IDs in user's list");
        }
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }

    private void fetchItems () {
        profileViewModel.getMovies().observe(getViewLifecycleOwner(), this::updateMovieList);
        }

        private void updateMovieList (List <Integer> newMovieID) {
            faveIDs.addAll(newMovieID);
            pAdapter.notifyDataSetChanged();
        }

        private void setUpRecyclerView () {
            pAdapter = new ProfileAdapter(marathon);
            profileView.setAdapter(pAdapter);
            profileView.setLayoutManager(new LinearLayoutManager(profileView.getContext()));
        }

        //method to get the logged in user
        public User getUser () {
            return ((MainActivity) requireActivity()).getUser();
        }
}
