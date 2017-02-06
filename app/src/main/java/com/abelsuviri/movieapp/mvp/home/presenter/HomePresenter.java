package com.abelsuviri.movieapp.mvp.home.presenter;

import android.content.Context;
import android.util.Log;

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

    public HomePresenter(HomeView homeView) {
        this.mHomeView = homeView;
    }

    public void nextPage() {
        mPage++;
    }

    public void getMovies(Context context) {
        MovieRequest movieRequest = MovieApi.getApiClient().create(MovieRequest.class);
        Observable<MoviesModel> movies = movieRequest.getMovies(BuildConfig.API_KEY, mPage);
        movies.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(moviesModel -> {
                for (Movies movie : moviesModel.getMovies()) {
                    String[] splitDate = movie.getDate().split("-");
                    String year = splitDate[0];
                    mMovies.add(new Movie(movie.getTitle(), year, movie.getPicture()));
                }

                if (mHomeView != null) {
                     mHomeView.showMovies(new MovieListAdapter(mMovies, context));
                }
            }, error -> {
                mHomeView.showError(error.getMessage());
            });
    }
}
