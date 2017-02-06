package com.abelsuviri.movieapp.utils.adapter.holder;

/**
 * @author Abel Suviri
 */

public class Movie {
    private String title;
    private String year;
    private String picture;

    public Movie(String title, String year, String picture) {
        this.title = title;
        this.year = year;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
