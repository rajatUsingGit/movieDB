package com.example.mymovies.data.remote;

import com.example.mymovies.data.local.MovieItem;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebResponse {

    @SerializedName("results")
    public List<MovieItem> movies;

    public WebResponse(List<MovieItem> movies) {
        this.movies = movies;
    }

}
