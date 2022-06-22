package com.example.mymovies.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.data.remote.WebApi;
import com.example.mymovies.data.remote.WebResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "HomeViewModel";

    private final MutableLiveData<List<MovieItem>> _movieItem = new MutableLiveData<>();

    public LiveData<List<MovieItem>> movieItem = _movieItem;

    public HomeViewModel() {
        fetchMovies();
    }

    private void fetchMovies() {
        Call<WebResponse> responseCall = WebApi.getInstance().getTrendingMovies();
        responseCall.enqueue(new Callback<WebResponse>() {
            @Override
            public void onResponse(@NonNull Call<WebResponse> call,
                                   @NonNull Response<WebResponse> response) {
                WebResponse webResponse = response.body();
                if (webResponse == null) {
                    return;
                }
                _movieItem.setValue(webResponse.movies);
                Log.d(TAG, String.valueOf(_movieItem.getValue()));
            }

            @Override
            public void onFailure(@NonNull Call<WebResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

}