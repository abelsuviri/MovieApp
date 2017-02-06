package com.abelsuviri.movieapp.mvp.home.view;

import com.abelsuviri.movieapp.utils.adapter.MovieListAdapter;

/**
 * @author Abel Suviri
 */

public interface HomeView {
    void showProgress();

    void dismissProgress();

    void showMovies(MovieListAdapter adapter);

    void showError(String error);
}
