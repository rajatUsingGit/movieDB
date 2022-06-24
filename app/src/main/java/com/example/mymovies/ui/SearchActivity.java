package com.example.mymovies.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.mymovies.data.MovieRepository;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ListItemBinding;

public class SearchActivity extends AppCompatActivity {

    private MovieItem movieItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MovieRepository mMovieRepository = new MovieRepository(getApplication());
        ListItemBinding mBinding = ListItemBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            movieItem = mMovieRepository.getMovieByTitle(query);
            if (movieItem != null) {
                openDetails();
            }
        }
        setContentView(mBinding.getRoot());
    }

    void openDetails() {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", movieItem.id);
        startActivity(intent);
        finish();
    }

}