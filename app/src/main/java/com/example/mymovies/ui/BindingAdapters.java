package com.example.mymovies.ui;

import androidx.databinding.BindingAdapter;
import androidx.databinding.BindingMethods;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.ui.home.adapter.HomeListAdapter;

import java.util.List;

public class BindingAdapters {
    @BindingAdapter("listData")
    public static void listData(RecyclerView recyclerView, List<MovieItem> movieItems) {
        HomeListAdapter adapter = (HomeListAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(movieItems);
        }
    }
}
