package com.example.mymovies.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.databinding.ListItemBinding;

public class MainListViewHolder extends RecyclerView.ViewHolder {

    private final ListItemBinding mBinding;

    public MainListViewHolder(ListItemBinding binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public ListItemBinding getBinding() {
        return mBinding;
    }

}
