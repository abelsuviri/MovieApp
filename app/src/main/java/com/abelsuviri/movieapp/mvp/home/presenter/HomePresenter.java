package com.abelsuviri.movieapp.mvp.home.presenter;

import com.abelsuviri.movieapp.BuildConfig;
import com.abelsuviri.movieapp.domain.MovieApi;
import com.abelsuviri.movieapp.domain.MovieRequest;
import com.abelsuviri.movieapp.model.MoviesModel;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Abel Suviri
 */

public class HomePresenter {

    private final HomeView mHomeView;
    private int mPage = 0;

    public HomePresenter(HomeView homeView) {
        this.mHomeView = homeView;
    }

    public void nextPage() {
        mPage++;
    }

    public void getMovies() {
        MovieRequest movieRequest = MovieApi.getApiClient().create(MovieRequest.class);
        Observable<MoviesModel> movies = movieRequest.getMovies(BuildConfig.API_KEY, mPage);
        movies.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(moviesModel -> {
                if (mHomeView != null) {
                    mHomeView.showMovies(moviesModel);
                }
            }, error -> {
                mHomeView.showError(error.getMessage());
            });
    }
}
