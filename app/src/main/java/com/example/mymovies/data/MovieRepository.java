package com.example.mymovies.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.mymovies.CallbackFromRepo;
import com.example.mymovies.data.local.MovieDatabase;
import com.example.mymovies.data.local.MovieItem;
import com.example.mymovies.data.remote.WebApi;
import com.example.mymovies.data.remote.WebResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private static final String TAG = "MovieRepository";

    private static final ExecutorService databaseWriteExecutor =
            Executors.newSingleThreadExecutor();

    private final MovieDatabase mLocalDatabase;

    private final WebApi.ApiService mRemoteApiService;

    public MovieRepository(Application application) {
        mLocalDatabase = MovieDatabase.getDatabase(application);
        mRemoteApiService = WebApi.getInstance();
    }

    public void insertAll(List<MovieItem> movieItems) {
        databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mLocalDatabase.getDao().insertAll(movieItems);
            }
        });
    }

    public void insert(MovieItem movieItem) {
        databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mLocalDatabase.getDao().insert(movieItem);
            }
        });
    }

    public void update(MovieItem movieItem) {
        databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mLocalDatabase.getDao().update(movieItem);
            }
        });
    }

    public MovieItem getMovieById(int id) {
        Future<MovieItem> movieItemFuture = databaseWriteExecutor.submit(new Callable<MovieItem>() {
            @Override
            public MovieItem call() throws Exception {
                return mLocalDatabase.getDao().getMovieById(id);
            }
        });
        try {
            return movieItemFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public MovieItem getMovieByTitle(String title) {
        Future<MovieItem> movieItemFuture = databaseWriteExecutor.submit(new Callable<MovieItem>() {
            @Override
            public MovieItem call() throws Exception {
                return mLocalDatabase.getDao().getMovieByTitle(title);
            }
        });
        try {
            return movieItemFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void fetchList(boolean onlyBookmarked, boolean tryOnline, CallbackFromRepo callback) {
        if (onlyBookmarked) {
            fetchMoviesLocally(true, callback);
        } else if (tryOnline) {
            fetchMoviesRemotely(callback);
        } else {
            fetchMoviesLocally(false, callback);
        }
    }

    public void fetchMoviesLocally(boolean onlyBookmarked, CallbackFromRepo callback) {
        databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (onlyBookmarked) {
                    callback.receiveData(mLocalDatabase.getDao().getBookmarks(), false);
                } else {
                    callback.receiveData(mLocalDatabase.getDao().getAll(), false);
                }
            }
        });
    }

    public void fetchMoviesRemotely(CallbackFromRepo callback) {
        mRemoteApiService.getTrendingMovies().enqueue(new Callback<WebResponse>() {
            @Override
            public void onResponse(@NonNull Call<WebResponse> call,
                                   @NonNull Response<WebResponse> response) {
                WebResponse webResponse = response.body();
                if (webResponse == null) {
                    callback.onNoDataFetched();
                    return;
                }
                List<MovieItem> movieItems = webResponse.movies;
                if (movieItems == null || movieItems.isEmpty()) {
                    callback.onNoDataFetched();
                } else {
                    callback.receiveData(movieItems, true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<WebResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                callback.onNoDataFetched();
            }
        });
    }

}


