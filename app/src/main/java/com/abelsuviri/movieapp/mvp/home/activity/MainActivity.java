package com.abelsuviri.movieapp.mvp.home.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.mvp.home.presenter.HomePresenter;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;
import com.abelsuviri.movieapp.utils.MovieListScrollListener;
import com.abelsuviri.movieapp.utils.adapter.MovieListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.movieList)
    RecyclerView mMovieList;

    private HomePresenter mHomePresenter;
    private MovieListScrollListener mScrollListener;
    private boolean isFirstTime = true;
    private MovieListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mHomePresenter = new HomePresenter(this);

        makeRequest();
        setupList();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showMovies(MovieListAdapter adapter) {
        if (isFirstTime) {
            mAdapter = adapter;
            mMovieList.setAdapter(adapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String error) {

    }

    private void makeRequest() {
        mHomePresenter.nextPage();
        mHomePresenter.getMovies(this);
    }

    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieList.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mMovieList.getContext(),
            LinearLayoutManager.VERTICAL);
        mMovieList.addItemDecoration(dividerItemDecoration);

        mScrollListener = new MovieListScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                isFirstTime = false;
                makeRequest();
            }
        };

        mMovieList.addOnScrollListener(mScrollListener);
    }
}