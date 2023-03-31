package com.example.mobileassignment.ui.Watchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileassignment.R;
import com.example.mobileassignment.databinding.FragmentWatchlistBinding;

import java.util.ArrayList;
import java.util.List;

public class WatchlistFragment extends Fragment {
    private WatchlistViewModel notificationsViewModel;
    private ItemsAdapter adapter;
    private @NonNull FragmentWatchlistBinding binding;
    private RecyclerView itemsView;
    private List<String> items = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        binding = FragmentWatchlistBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        itemsView = root.findViewById(R.id.items_list);
        setUpRecyclerView();
        fetchItems();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void fetchItems() {
        notificationsViewModel.getItems().observe(getViewLifecycleOwner(), this::updateItemsList);
    }
    private void setUpRecyclerView() {
        adapter = new ItemsAdapter(items);
        itemsView.setAdapter(adapter);
        itemsView.setLayoutManager(new LinearLayoutManager(itemsView.getContext()));
    }
    private void updateItemsList(List<String> newItems) {
        items.addAll(newItems);
        adapter.notifyDataSetChanged();
    }
}