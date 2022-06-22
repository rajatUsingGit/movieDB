package com.example.mymovies.data.local;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MovieItem implements Parcelable {
    @SerializedName("title")
    public String title;

    @SerializedName("poster_path")
    public String posterPath;

    protected MovieItem(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
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
        parcel.writeString(title);
        parcel.writeString(posterPath);
    }
}
