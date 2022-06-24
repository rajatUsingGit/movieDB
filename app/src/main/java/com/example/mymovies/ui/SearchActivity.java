package com.example.mymovies.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.example.mymovies.data.MovieRepository;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ListItemBinding;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        if (!Intent.ACTION_SEARCH.equals(intent.getAction())) {
            return;
        }

        String query = intent.getStringExtra(SearchManager.QUERY);
        MovieRepository mMovieRepository = new MovieRepository(getApplication());
        MovieItem movieItem = mMovieRepository.getMovieByTitle(query);

        if (movieItem != null) {
            intent = new Intent(this, DetailActivity.class);
            intent.putExtra("id", movieItem.id);
            startActivity(intent);
            finish();
        }

        setContentView(ListItemBinding.inflate(getLayoutInflater()).getRoot());
    }

}