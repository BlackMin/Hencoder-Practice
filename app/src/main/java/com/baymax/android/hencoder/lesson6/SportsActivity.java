package com.baymax.android.hencoder.lesson6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baymax.android.hencoder.R;

public class SportsActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, SportsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        final SportsView sportsView = findViewById(R.id.sports_view);
        sportsView.postDelayed(new Runnable() {
            @Override
            public void run() {
                sportsView.setProgress(60);
            }
        },300);
        sportsView.postDelayed(new Runnable() {
            @Override
            public void run() {
                sportsView.setProgress(80);
            }
        },800);

//        sportsView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                sportsView.setProgress(65);
//            }
//        },300);


    }
}
