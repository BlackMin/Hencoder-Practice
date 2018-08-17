package com.baymax.android.hencoder.lesson6;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.baymax.android.hencoder.utils.UIUtils;

public class SportsView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final int PADDING = UIUtils.dip2Px(10);

    private int radius;

    private int centerX;

    private int centerY;

    private static final int STOKE_WIDTH = UIUtils.dip2Px(16);

    private float startAngle = -90;

    private float preStartAngle = startAngle;

    private int progress = 0;

    private float fraction;

    private RectF mRectF = new RectF();

    private ObjectAnimator progressAnimator;

    public SportsView(Context context) {
        super(context);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SportsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initPaint() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(STOKE_WIDTH);
        mPaint.setColor(Color.parseColor("#999999"));
    }

    public void setProgress(int progress) {
        this.progress = progress;
        getProgressAnimator().setDuration(500).start();
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.reset();
        initPaint();
        float sweepAngle = 360 * progress * 1.0f / 100;
        radius = Math.min(getWidth() / 2 - PADDING, getHeight() / 2 - PADDING);
        radius = Math.max(0, getWidth() / 2 - PADDING);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        //画空心圆
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        //画进度条
        mPaint.setColor(Color.parseColor("#c15d3e"));
        mRectF.set(getWidth()/2-radius
                , getHeight()/2-radius
                ,getWidth()/2+radius
                ,getHeight()/2+radius);
        Log.e("jhy","ondraw");
        canvas.drawArc(mRectF
                ,startAngle
                ,preStartAngle-startAngle
                ,false
                ,mPaint);
        Log.e("jhy","startAngle:"+startAngle+",sweepAngle:"+(preStartAngle-startAngle));
        canvas.drawArc(mRectF
                ,preStartAngle
                ,(sweepAngle-preStartAngle+startAngle) * fraction
                ,false
                ,mPaint);
        Log.e("jhy","preStartAngle:"+preStartAngle+",sweepAngle:"+((sweepAngle-preStartAngle+startAngle) * fraction));
        preStartAngle = preStartAngle + (sweepAngle-preStartAngle+startAngle) * fraction;
        Log.e("jhy","preStartAngle:"+preStartAngle);
        //画数字进度
        mPaint.setTextSize(UIUtils.dip2Px(30));
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText((int)(progress * fraction)+"%",centerX,centerY,mPaint);
    }

    private ObjectAnimator getProgressAnimator() {
        if(progressAnimator == null) {
            progressAnimator = ObjectAnimator.ofFloat(this,"fraction",0,1);
        }
        return progressAnimator;
    }
}
