package com.example.mymovies.data.local;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table")
public class MovieItem implements Parcelable {

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

    public MovieItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        posterPath = in.readString();
        isBookmarked = in.readBoolean();
    }

    public static final Creator<MovieItem> CREATOR = new Creator<MovieItem>() {
        @Override
        public MovieItem createFromParcel(Parcel in) {
            return new MovieItem(in);
        }

        @Override
        public MovieItem[] newArray(int size) {
            return new MovieItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(posterPath);
        parcel.writeBoolean(isBookmarked);
    }
}
