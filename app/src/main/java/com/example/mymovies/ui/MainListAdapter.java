package com.example.mymovies.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ListItemBinding;

public class MainListAdapter extends ListAdapter<MovieItem, MainListViewHolder> {

    private final Context mContext;

    public MainListAdapter(Context context) {
        super(DIFF_CALLBACK);
        mContext = context;
    }

    public static final DiffUtil.ItemCallback<MovieItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieItem oldItem, @NonNull MovieItem newItem) {
                    return oldItem.id == newItem.id;
                }

                @Override
                public boolean areContentsTheSame(@NonNull MovieItem oldItem, @NonNull MovieItem newItem) {
                    return oldItem.title.equals(newItem.title)
                            && oldItem.posterPath.equals(newItem.posterPath);
                }
            };


    @NonNull
    @Override
    public MainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding listItemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listItemBinding.getRoot().setLayoutParams(lp);
        return new MainListViewHolder(listItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainListViewHolder holder, int position) {
        MovieItem movieItem = getItem(position);
        holder.getBinding().textBox.setText(movieItem.title);
        holder.getBinding().textBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id", movieItem.id);
                mContext.startActivity(intent);
            }
        });
    }

}
