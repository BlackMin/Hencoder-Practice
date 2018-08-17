package com.baymax.android.hencoder.lesson6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baymax.android.hencoder.R;

public class TestActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, TestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        final TextView badgeView = findViewById(R.id.badge);
        badgeView.post(new Runnable() {
            @Override
            public void run() {
                badgeView.offsetTopAndBottom(-badgeView.getHeight() / 2);
                badgeView.offsetLeftAndRight(badgeView.getWidth() / 2);
                badgeView.requestLayout();
            }
        });
    }
}
