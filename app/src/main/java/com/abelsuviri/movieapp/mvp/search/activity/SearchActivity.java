package com.abelsuviri.movieapp.mvp.search.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.abelsuviri.movieapp.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.hold, R.anim.disappear_to_bottom);
    }
}
