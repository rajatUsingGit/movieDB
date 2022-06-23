package com.example.mymovies.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.mymovies.data.MovieRepository;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ActivityDetailBinding;
import com.example.mymovies.util.Constants;

public class DetailActivity extends AppCompatActivity {

    public MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieRepository movieRepository = new MovieRepository(getApplication());
        ActivityDetailBinding binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", 0);
            movieItem = movieRepository.getMovieById(id);
            binding.title.setText(movieItem.title);
            Glide.with(this)
                    .load(Constants.IMAGE_BASE_URL + movieItem.posterPath)
                    .into(binding.image);
        }
        setTextForBookmarkButton(binding.bookmarkButton);
        binding.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieItem.isBookmarked = !movieItem.isBookmarked;
                movieRepository.update(movieItem);
                setTextForBookmarkButton(binding.bookmarkButton);
            }
        });
    }

    private void setTextForBookmarkButton(AppCompatButton button) {
        if (movieItem.isBookmarked) {
            button.setText("remove bookmark");
        } else {
            button.setText("add bookmark");
        }
    }

}