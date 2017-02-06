package com.abelsuviri.movieapp.mvp.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.mvp.home.presenter.HomePresenter;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;
import com.abelsuviri.movieapp.mvp.search.activity.SearchActivity;
import com.abelsuviri.movieapp.utils.MovieListScrollListener;
import com.abelsuviri.movieapp.utils.adapter.MovieListAdapter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    @BindView(R.id.movieList)
    RecyclerView mMovieList;

    @BindView(R.id.progressLayer)
    RelativeLayout mProgressLayer;

    private HomePresenter mHomePresenter;
    private MovieListScrollListener mScrollListener;
    private boolean isFirstTime = true;
    private boolean isFromError = false;
    private MovieListAdapter mAdapter;
    private Snackbar mLoadingSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mHomePresenter = new HomePresenter(this);

        mLoadingSnackbar = Snackbar.make(mMovieList, getString(R.string.loading), Snackbar.LENGTH_INDEFINITE);

        makeRequest();
        setupList();
    }

    @Override
    public void showProgress() {
        if (isFirstTime) {
            mProgressLayer.setVisibility(View.VISIBLE);
        } else {
            mLoadingSnackbar.show();
        }
    }

    @Override
    public void dismissProgress() {
        if (isFirstTime) {
            mProgressLayer.setVisibility(View.GONE);
        } else {
            mLoadingSnackbar.dismiss();
        }
    }

    @Override
    public void showMovies(MovieListAdapter adapter) {
        isFromError = false;

        if (isFirstTime) {
            mAdapter = adapter;
            mMovieList.setAdapter(adapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String error) {
        Snackbar.make(mMovieList, String.format(Locale.getDefault(),
            getString(R.string.request_error), error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry), OnClick -> {
                isFromError = true;
                makeRequest();
            })
            .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                startActivity(new Intent(this, SearchActivity.class));
                overridePendingTransition(R.anim.appear_from_bottom, R.anim.hold);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void makeRequest() {
        if (!isFromError) {
            mHomePresenter.nextPage();
        }

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