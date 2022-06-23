package com.example.mymovies.ui;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.data.local.MovieItem;

import java.util.List;

public class BindingAdapters {
    @BindingAdapter("listData")
    public static void listData(RecyclerView recyclerView, List<MovieItem> movieItems) {
        MainListAdapter adapter = (MainListAdapter) recyclerView.getAdapter();
        if (adapter != null && movieItems != null) {
            adapter.submitList(movieItems);
        }
    }
}
