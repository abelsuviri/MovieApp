package com.abelsuviri.movieapp.mvp.home.presenter;

import android.content.Context;

import com.abelsuviri.movieapp.BuildConfig;
import com.abelsuviri.movieapp.domain.MovieApi;
import com.abelsuviri.movieapp.domain.MovieRequest;
import com.abelsuviri.movieapp.model.Movies;
import com.abelsuviri.movieapp.model.MoviesModel;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;
import com.abelsuviri.movieapp.utils.adapter.MovieListAdapter;
import com.abelsuviri.movieapp.utils.adapter.holder.Movie;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Abel Suviri
 */

public class HomePresenter {

    private final HomeView mHomeView;
    private int mPage = 0;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private MovieRequest mMovieRequest = MovieApi.getApiClient().create(MovieRequest.class);

    public HomePresenter(HomeView homeView) {
        this.mHomeView = homeView;
    }

    public void nextPage() {
        mPage++;
    }

    private void resetPage() {
        mPage = 1;
    }

    public void getMovies(Context context, boolean shouldClear) {
        if (mHomeView != null) {
            mHomeView.showProgress();

            if (shouldClear) {
                mMovies.clear();
                resetPage();
            }

            Observable<MoviesModel> movies = mMovieRequest.getMovies(BuildConfig.API_KEY, mPage);
            movies.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesModel -> {
                    for (Movies movie : moviesModel.getMovies()) {
                        mMovies.add(new Movie(movie.getTitle(), getYear(movie.getDate()),
                            movie.getPicture(), ""));
                    }
                    mHomeView.showMovies(new MovieListAdapter(mMovies, context));
                    mHomeView.dismissProgress();
                }, error -> {
                    mHomeView.showError(error.getMessage());
                    mHomeView.dismissProgress();
                });
        }
    }

    public void getSearchedMovies(String search, Context context) {
        if (mHomeView != null) {
            mHomeView.showProgress();

            mMovies.clear();

            Observable<MoviesModel> movies = mMovieRequest.getSearchedMovies(BuildConfig.API_KEY, search);
            movies.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesModel -> {
                    for (Movies movie : moviesModel.getMovies()) {
                        mMovies.add(new Movie(movie.getTitle(), getYear(movie.getDate()),
                            movie.getPicture(), movie.getOverview()));
                    }
                    mHomeView.showMovies(new MovieListAdapter(mMovies, context));
                    mHomeView.dismissProgress();
                }, error -> {
                    mHomeView.showError(error.getMessage());
                    mHomeView.dismissProgress();
                });
        }
    }

    private String getYear(String date) {
        String[] splitDate = date.split("-");
        return splitDate[0];
    }
}
