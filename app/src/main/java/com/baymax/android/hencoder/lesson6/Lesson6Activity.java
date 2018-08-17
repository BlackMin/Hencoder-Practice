package com.baymax.android.hencoder.lesson6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.baymax.android.hencoder.R;

public class Lesson6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6);
    }

    public void goDashboard(View view) {
        DashboardActivity.start(this);
    }

    public void goPie(View view) {
        PieViewActivity.start(this);
    }

    public void goSportsView(View view) {
        SportsActivity.start(this);
    }

    public void goTipsView(View view) {
        TipsViewActivity.start(this);
    }

    public void goTextLayout(View view) {
        TextLayoutActivity.start(this);
    }

    public void goTestView(View view) {
        TestActivity.start(this);
    }
}
