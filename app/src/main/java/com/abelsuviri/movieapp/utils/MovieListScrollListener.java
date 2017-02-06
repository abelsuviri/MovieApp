package com.abelsuviri.movieapp.utils;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Abel Suviri
 */

public abstract class MovieListScrollListener extends RecyclerView.OnScrollListener {

    private int mVisibleThreshold = 5;
    private int mPreviousTotalItemCount = 0;
    private boolean mLoading = true;
    private RecyclerView.LayoutManager mLayoutManager;

    public MovieListScrollListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int totalItemCount = mLayoutManager.getItemCount();
        int lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();

        if (totalItemCount < mPreviousTotalItemCount) {
            this.mPreviousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.mLoading = true;
            }
        }

        if (mLoading && (totalItemCount > mPreviousTotalItemCount)) {
            mLoading = false;
            mPreviousTotalItemCount = totalItemCount;
        }

        if (!mLoading && (lastVisibleItemPosition + mVisibleThreshold) > totalItemCount) {
            onLoadMore();
            mLoading = true;
        }
    }

    public abstract void onLoadMore();
}
