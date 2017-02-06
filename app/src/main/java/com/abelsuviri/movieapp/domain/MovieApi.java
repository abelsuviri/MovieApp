package com.abelsuviri.movieapp.domain;

import com.abelsuviri.movieapp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
