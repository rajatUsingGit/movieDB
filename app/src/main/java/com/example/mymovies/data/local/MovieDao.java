package com.example.mymovies.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<MovieItem> movieItems);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MovieItem movieItem);

    @Update
    void update(MovieItem movieItem);

    @Query("SELECT * FROM movie_table")
    List<MovieItem> getAll();

    @Query("SELECT * FROM movie_table WHERE bookmark = 1")
    List<MovieItem> getBookmarks();

    @Query("SELECT * FROM movie_table WHERE id=:id")
    MovieItem getMovieById(int id);

    @Query("SELECT * FROM movie_table WHERE UPPER(title)=UPPER(:title)")
    MovieItem getMovieByTitle(String title);

}
