package com.abelsuviri.movieapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Abel Suviri
 */

public class MoviesModel {

    @SerializedName("results")
    List<Movies> mMovies;

    public MoviesModel(List<Movies> movies) {
        this.mMovies = movies;
    }

    public List<Movies> getMovies() {
        return mMovies;
    }
}
