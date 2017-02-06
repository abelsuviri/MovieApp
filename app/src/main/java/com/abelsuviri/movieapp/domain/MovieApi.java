package com.abelsuviri.movieapp.domain;

import android.util.Log;

import com.abelsuviri.movieapp.BuildConfig;
import com.abelsuviri.movieapp.model.MoviesModel;
import com.abelsuviri.movieapp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Abel Suviri
 */

public class MovieApi {

    public static Retrofit getApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build();

        return retrofit;
    }
}
