package com.example.mymovies.callbacks;

import com.example.mymovies.data.local.MovieItem;

import java.util.List;

public interface CallbackFromRepo {
    void onDataFetched(List<MovieItem> movieItems, boolean isMainThread);
    void onNoDataFetched();
}
