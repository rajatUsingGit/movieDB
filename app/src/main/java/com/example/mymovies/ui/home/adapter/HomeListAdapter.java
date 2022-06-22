package com.example.mymovies.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ListItemBinding;

public class HomeListAdapter extends ListAdapter<MovieItem, HomeListViewHolder> {

    public HomeListAdapter() {
        super(DIFF_CALLBACK);
    }

    public static final DiffUtil.ItemCallback<MovieItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieItem oldItem, @NonNull MovieItem newItem) {
                    return oldItem.title.equals(newItem.title);
                }

                @Override
                public boolean areContentsTheSame(@NonNull MovieItem oldItem, @NonNull MovieItem newItem) {
                    return oldItem.title.equals(newItem.title);
                }
            };


    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listItemBinding.getRoot().setLayoutParams(lp);
        return new HomeListViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
