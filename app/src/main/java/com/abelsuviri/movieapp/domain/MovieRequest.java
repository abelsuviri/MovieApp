package com.abelsuviri.movieapp.domain;

import com.abelsuviri.movieapp.model.MoviesModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author Abel Suviri
 */

public interface MovieRequest {
    @GET("3/discover/movie?sort_by=popularity.desc")
    Observable<MoviesModel> getMovies(
        @Query("api_key") String apiKey,
        @Query("page") int page
    );

    @GET("/3/search/movie")
    Observable<MoviesModel> getSearchedMovies(
        @Query("api_key") String apiKey,
        @Query("query") String query
    );
}
