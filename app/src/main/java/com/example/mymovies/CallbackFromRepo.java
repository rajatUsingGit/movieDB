package com.example.mymovies;

import com.example.mymovies.data.local.MovieItem;

import java.util.List;

public interface CallbackFromRepo {
    void receiveData(List<MovieItem> movieItems, boolean isMainThread);
    void onNoDataFetched();
}
