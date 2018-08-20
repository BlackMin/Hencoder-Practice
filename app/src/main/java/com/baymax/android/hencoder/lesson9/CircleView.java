package com.baymax.android.hencoder.lesson9;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.baymax.android.hencoder.utils.UIUtils;

public class CircleView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final int PADDING = UIUtils.dip2Px(15);

    private static final int RADIUS = UIUtils.dip2Px(100);

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size = (PADDING + RADIUS) * 2;

        setMeasuredDimension(resolveSize(size, widthMeasureSpec), resolveSize(size, heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        mPaint.setColor(Color.BLACK);
        canvas.drawCircle(PADDING+RADIUS, PADDING+RADIUS, RADIUS, mPaint);
    }
}
