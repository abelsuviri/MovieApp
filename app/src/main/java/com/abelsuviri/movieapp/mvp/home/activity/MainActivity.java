package com.abelsuviri.movieapp.mvp.home.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.mvp.home.presenter.HomePresenter;
import com.abelsuviri.movieapp.mvp.home.view.HomeView;
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
    private boolean isFromSearch = false;
    private MovieListAdapter mAdapter;
    private Snackbar mLoadingSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mHomePresenter = new HomePresenter(this);

        mLoadingSnackbar = Snackbar.make(mMovieList, getString(R.string.loading), Snackbar.LENGTH_INDEFINITE);

        makeRequest(false);
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

        if (isFirstTime | isFromSearch) {
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
                makeRequest(false);
            })
            .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                makeSearchRequest(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (TextUtils.isEmpty(query)) {
                    if (!isFirstTime) {
                        makeRequest(true);
                    }
                } else {
                    makeSearchRequest(query);
                }

                return false;
            }
        });

        return true;
    }

    private void makeRequest(boolean shouldClear) {
        if (!isFromError) {
            isFromSearch = false;
            mHomePresenter.nextPage();
        }

        mHomePresenter.getMovies(this, shouldClear);
    }

    private void makeSearchRequest(String search) {
        isFirstTime = false;
        isFromSearch = true;
        setupList();
        mHomePresenter.getSearchedMovies(search, this);
    }

    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMovieList.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mMovieList.getContext(),
            LinearLayoutManager.VERTICAL);
        mMovieList.addItemDecoration(dividerItemDecoration);

        if (!isFromSearch) {
            mScrollListener = new MovieListScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore() {
                    isFirstTime = false;
                    makeRequest(false);
                }
            };

            mMovieList.addOnScrollListener(mScrollListener);
        }
    }
}