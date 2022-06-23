package com.example.mymovies.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieItem movieItem);

    @Query("SELECT * FROM movie_table")
    List<MovieItem> getAll();

    @Query("SELECT * FROM movie_table WHERE bookmark = 1")
    List<MovieItem> getBookmarks();
}
