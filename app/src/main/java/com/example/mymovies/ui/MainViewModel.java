package com.example.mymovies.ui;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mymovies.CallbackFromRepo;
import com.example.mymovies.data.MovieRepository;
import com.example.mymovies.data.local.MovieItem;

import java.util.List;

public class MainViewModel extends AndroidViewModel implements CallbackFromRepo {
    private static final String TAG = "MainViewModel";

    private final MovieRepository movieRepository;
    private final MutableLiveData<List<MovieItem>> _movieItems = new MutableLiveData<>();
    public LiveData<List<MovieItem>> movieItems = _movieItems;

    public MainViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        movieRepository.fetchList(false, true, this);
    }

    public void refreshUIFromNetwork(boolean onlyBookmarked) {
        movieRepository.fetchList(onlyBookmarked, true, this);
    }

    @Override
    public void receiveData(List<MovieItem> items, boolean isMainThread) {
        if (isMainThread) {
            _movieItems.setValue(items);
            movieRepository.insertAll(items);
        } else {
            _movieItems.postValue(items);
        }
    }

    @Override
    public void onNoDataFetched() {
        movieRepository.fetchList(false, false, this);
    }

}
