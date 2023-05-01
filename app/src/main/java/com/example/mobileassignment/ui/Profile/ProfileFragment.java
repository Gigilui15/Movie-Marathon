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

        fetchItems();
        setUpRecyclerView();
        return root;
    }

        private void makeMovies(List<Integer> ids) {
            dbHelper = new MovieDbHelper(getContext());
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
                        marathon.add(new MovieResults.ResultsBean("FUCK"));
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

        private void updateMovieList (List < Integer > newMovieID) {
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
