package com.baymax.android.hencoder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.baymax.android.hencoder.lesson6.DashboardActivity;
import com.baymax.android.hencoder.lesson6.PieView;
import com.baymax.android.hencoder.lesson6.PieViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goDashboard(View view) {
        DashboardActivity.start(this);
    }

    public void goPie(View view) {
        PieViewActivity.start(this);
    }
}
