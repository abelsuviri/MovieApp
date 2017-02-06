package com.abelsuviri.movieapp.utils.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.utils.adapter.holder.Movie;
import com.abelsuviri.movieapp.utils.adapter.holder.MovieHolder;

import java.util.ArrayList;

/**
 * @author Abel Suviri
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieHolder> {

    private ArrayList<Movie> mMovies;
    private Context mContext;

    public MovieListAdapter(ArrayList<Movie> movies, Context context) {
        mMovies = movies;
        mContext = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item,
            viewGroup, false);
        MovieHolder holder = new MovieHolder(itemView, mContext);

        return holder;
    }

    @Override
    public void onBindViewHolder(MovieHolder viewHolder, int position) {
        Movie item = mMovies.get(position);
        viewHolder.bindMovies(item);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
