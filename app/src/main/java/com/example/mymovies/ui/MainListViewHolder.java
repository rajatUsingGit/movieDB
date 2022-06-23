package com.example.mymovies.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ListItemBinding;

public class MainListViewHolder extends RecyclerView.ViewHolder {

    public final ListItemBinding binding;

    public MainListViewHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(MovieItem movieItem) {
        binding.textBox.setText(movieItem.title);
    }
}
