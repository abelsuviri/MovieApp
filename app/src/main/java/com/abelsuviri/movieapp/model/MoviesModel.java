package com.abelsuviri.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Abel Suviri
 */

public class MoviesModel {

    @SerializedName("results")
    List<Movie> movies;

    public MoviesModel(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
