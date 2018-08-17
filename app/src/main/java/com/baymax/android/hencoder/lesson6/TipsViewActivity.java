package com.baymax.android.hencoder.lesson6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baymax.android.hencoder.R;

public class TipsViewActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, TipsViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view);
        TextView textView = findViewById(R.id.badge);
        TriangleTipsView triangleTipsView = (TriangleTipsView) LayoutInflater.from(this).inflate(R.layout.activity_tips_view, null);
        TriangleTipsPopupHelper.PopupWindowBuilder builder = new TriangleTipsPopupHelper.PopupWindowBuilder(this);
        PopupWindow popupWindow = builder.setAnchorView(textView)
                .setContentView(triangleTipsView)
                .setDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                }).show();
    }
}
