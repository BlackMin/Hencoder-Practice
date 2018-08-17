package com.baymax.android.hencoder.lesson6;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.baymax.android.hencoder.utils.UIUtils;

public class StaticTextLayout extends View {

    private TextPaint mPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    private String text = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32.";

    private StaticLayout mStaticLayout;

    public StaticTextLayout(Context context) {
        super(context);
    }

    public StaticTextLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticTextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StaticTextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        mPaint.setTextSize(UIUtils.dip2Px(16));
        mPaint.getFontMetrics(mFontMetrics);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mStaticLayout = new StaticLayout(text,mPaint,getMeasuredWidth(), Layout.Alignment.ALIGN_NORMAL,1,0,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mStaticLayout.draw(canvas);
    }
}
