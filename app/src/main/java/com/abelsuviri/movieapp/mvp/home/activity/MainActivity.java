package com.abelsuviri.movieapp.mvp.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.model.Movie;
import com.abelsuviri.movieapp.model.MoviesModel;
import com.abelsuviri.movieapp.mvp.home.presenter.HomePresenter;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    private HomePresenter mHomePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mHomePresenter = new HomePresenter(this);

        makeRequest();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showMovies(MoviesModel moviesModel) {
        for (Movie movie : moviesModel.getMovies()) {
        }
    }

    @Override
    public void showError(String error) {
    }

    private void makeRequest() {
        mHomePresenter.nextPage();
        mHomePresenter.getMovies();
    }
}