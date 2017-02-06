package com.abelsuviri.movieapp.mvp.home.view;

import com.abelsuviri.movieapp.model.MoviesModel;

/**
 * @author Abel Suviri
 */

public interface HomeView {
    void showProgress();

    void dismissProgress();

    void showMovies(MoviesModel moviesModel);

    void showError(String error);
}
