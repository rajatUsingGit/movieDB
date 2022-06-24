package com.example.mymovies.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.mymovies.R;
import com.example.mymovies.data.MovieRepository;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.databinding.ActivityDetailBinding;
import com.example.mymovies.util.Constants;

public class DetailActivity extends AppCompatActivity {

    private MovieItem mMovieItem;
    private ActivityDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            initView(intent.getIntExtra("id", 0));
        }
    }

    private void initView(int id) {
        MovieRepository movieRepository = new MovieRepository(getApplication());
        mMovieItem = movieRepository.getMovieById(id);

        mBinding.title.setText(mMovieItem.title);

        Glide.with(this)
                .load(Constants.IMAGE_BASE_URL + mMovieItem.posterPath)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.image_not_found)
                .into(mBinding.image);

        mBinding.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMovieItem.isBookmarked = !mMovieItem.isBookmarked;
                movieRepository.update(mMovieItem);
                setTextForBookmarkButton();
            }
        });

        setTextForBookmarkButton();
    }

    private void setTextForBookmarkButton() {
        if (mMovieItem.isBookmarked) {
            mBinding.bookmarkButton.setText(getString(R.string.remove_bookmark));
        } else {
            mBinding.bookmarkButton.setText(getString(R.string.add_bookmark));
        }
    }

}