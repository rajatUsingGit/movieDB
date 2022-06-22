package com.example.mymovies.ui.home.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ListItemBinding;

public class HomeListViewHolder extends RecyclerView.ViewHolder {

    private final ListItemBinding binding;

    public HomeListViewHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(MovieItem movieItem) {
        binding.textBox.setText(movieItem.title);
    }
}
