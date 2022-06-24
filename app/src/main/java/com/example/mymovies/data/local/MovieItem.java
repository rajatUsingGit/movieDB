package com.example.mymovies.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class MovieItem {

    @ColumnInfo
    @PrimaryKey
    @SerializedName("id")
    public int id;

    @ColumnInfo
    @SerializedName("title")
    public String title;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    public String posterPath;

    @ColumnInfo(name = "bookmark")
    public boolean isBookmarked;

    public MovieItem(int id, String title, String posterPath, boolean isBookmarked) {
        this.id = id;
        this.title = title;
        this.posterPath = posterPath;
        this.isBookmarked = isBookmarked;
    }

}
