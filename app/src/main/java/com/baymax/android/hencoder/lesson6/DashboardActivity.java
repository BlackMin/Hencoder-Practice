package com.baymax.android.hencoder.lesson6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.baymax.android.hencoder.R;

public class DashboardActivity extends AppCompatActivity {

    EditText editText;

    Dashboard dashboard;

    private TextWatcher mTextWatcher;

    public static void start(Context context) {
        Intent intent = new Intent(context, DashboardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        editText = findViewById(R.id.et_scale);
        editText.addTextChangedListener(mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int scale = Integer.valueOf(TextUtils.isEmpty(s)?"0":s.toString());
                dashboard.setScalePosition(scale);
            }
        });
        dashboard = findViewById(R.id.dash_board);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editText.removeTextChangedListener(mTextWatcher);
    }
}
