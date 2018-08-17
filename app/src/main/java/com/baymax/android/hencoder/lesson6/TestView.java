package com.baymax.android.hencoder.lesson6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.baymax.android.hencoder.utils.UIUtils;

public class TestView extends FrameLayout {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestView(@NonNull Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public TestView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        //canvas.drawColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        RectF rectF = new RectF(UIUtils.dip2Px(16),UIUtils.dip2Px(16),UIUtils.dip2Px(160),UIUtils.dip2Px(160));
        canvas.drawRect(rectF, mPaint);
        //canvas.drawColor(Color.RED);
    }
}
