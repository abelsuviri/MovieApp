package com.abelsuviri.movieapp.utils.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abelsuviri.movieapp.R;
import com.abelsuviri.movieapp.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Abel Suviri
 */

public class MovieHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.picture)
    ImageView mPicture;

    @BindView(R.id.title)
    TextView mTitle;

    @BindView(R.id.year)
    TextView mYear;

    private Context mContext;

    public MovieHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;

        ButterKnife.bind(this, itemView);
    }

    public void bindMovies(Movie movie) {
        Picasso.with(mContext).load(Constants.PICTURE_URL + movie.getPicture())
            .resize(250, 300)
            .centerCrop()
            .into(mPicture);

        mTitle.setText(movie.getTitle());
        mYear.setText(movie.getYear());
    }
}
