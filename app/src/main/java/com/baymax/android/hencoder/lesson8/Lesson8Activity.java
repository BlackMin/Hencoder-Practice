package com.baymax.android.hencoder.lesson8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baymax.android.hencoder.R;

public class Lesson8Activity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, Lesson8Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson8);
    }
}
