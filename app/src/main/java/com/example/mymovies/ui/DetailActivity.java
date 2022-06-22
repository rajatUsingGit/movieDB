package com.example.mymovies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ActivityDetailBinding;
import com.example.mymovies.util.Constants;

public class DetailActivity extends AppCompatActivity {

    public MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            movieItem = intent.getParcelableExtra("data");
            binding.title.setText(movieItem.title);
            Glide.with(this)
                    .load(Constants.IMAGE_BASE_URL + movieItem.posterPath)
                    .into(binding.image);
        }
    }
}