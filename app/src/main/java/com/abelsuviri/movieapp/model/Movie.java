package com.abelsuviri.movieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Abel Suviri
 */

public class Movie {
    @SerializedName("title")
    String title;

    @SerializedName("overview")
    String overview;

    @SerializedName("release_date")
    String date;

    @SerializedName("poster_path")
    String picture;

    public Movie(String title, String overview, String date, String picture) {
        this.title = title;
        this.overview = overview;
        this.date = date;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getDate() {
        return date;
    }

    public String getPicture() {
        return picture;
    }
}
