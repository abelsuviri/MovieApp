package com.abelsuviri.movieapp.mvp.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.mvp.home.presenter.HomePresenter;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;
import com.abelsuviri.movieapp.utils.adapter.MovieListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.movieList)
    RecyclerView mMovieList;

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
    public void showMovies(MovieListAdapter adapter) {
        mMovieList.setAdapter(adapter);
        mMovieList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mMovieList.getContext(),
            LinearLayoutManager.VERTICAL);
        mMovieList.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showError(String error) {

    }

    private void makeRequest() {
        mHomePresenter.nextPage();
        mHomePresenter.getMovies(this);
    }
}