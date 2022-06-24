package com.example.mymovies.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mymovies.R;
import com.example.mymovies.callbacks.CallbackFromRepo;
import com.example.mymovies.data.MovieRepository;
import com.example.mymovies.data.local.MovieItem;

import java.util.List;

public class MainViewModel extends AndroidViewModel implements CallbackFromRepo {

    private int mCurrentMenu = R.id.navigation_home;
    private final MovieRepository mMovieRepository;
    private final MutableLiveData<List<MovieItem>> _movieItems = new MutableLiveData<>();
    public LiveData<List<MovieItem>> movieItems = _movieItems;

    public MainViewModel(Application application) {
        super(application);
        mMovieRepository = new MovieRepository(application);
        mMovieRepository.fetchList(false, true, this);
    }

    public int getCurrentMenu() {
        return mCurrentMenu;
    }

    public void setCurrentMenu(int currentMenu) {
         mCurrentMenu = currentMenu;
    }

    public void refreshUIFromNetwork() {
        mMovieRepository.fetchList(mCurrentMenu == R.id.navigation_bookmarks, true, this);
    }

    @Override
    public void onDataFetched(List<MovieItem> items, boolean isMainThread) {
        if (isMainThread) {
            _movieItems.setValue(items);
            mMovieRepository.insertAll(items);
        } else {
            _movieItems.postValue(items);
        }
    }

    @Override
    public void onNoDataFetched() {
        mMovieRepository.fetchList(false, false, this);
    }

}
