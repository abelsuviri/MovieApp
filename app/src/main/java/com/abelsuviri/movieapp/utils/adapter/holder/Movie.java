package com.abelsuviri.movieapp.utils.adapter.holder;

/**
 * @author Abel Suviri
 */

public class Movie {
    private String mTitle;
    private String mYear;
    private String mPicture;
    private String mOverview;

    public Movie(String title, String year, String picture, String overview) {
        this.mTitle = title;
        this.mYear = year;
        this.mPicture = picture;
        this.mOverview = overview;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getYear() {
        return mYear;
    }

    public String getPicture() {
        return mPicture;
    }

    public String getOverview() {
        return mOverview;
    }
}
