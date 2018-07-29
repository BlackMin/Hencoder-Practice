package com.baymax.android.hencoder.lesson6;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.baymax.android.hencoder.utils.UIUtils;

public class Dashboard extends View {

    private static final int DASHBOARD_STROKE_WIDTH = UIUtils.dip2Px(2);

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Path dashboardPath = new Path();

    private RectF dashboardRect = new RectF();

    private Path effectPath = new Path();

    private RectF effectRect = new RectF();

    private static final int PADDING = UIUtils.dip2Px(15);

    //开口角度  y轴对称
    private static final int OPEN_ANGLE = 120;

    //起始角度
    private static final int START_ANGLE = 90 + OPEN_ANGLE / 2;

    //扫描角度
    private static final int SWEEP_ANGLE = 360 - OPEN_ANGLE;

    //需要绘制刻度的数量
    private static final int SCALE_COUNT = 20;

    //刻度的宽度
    private static final int SCALE_WIDTH = UIUtils.dip2Px(1);

    //刻度的高度
    private static final int SCALE_HEIGHT = UIUtils.dip2Px(5);

    private Path pointPath = new Path();

    private PathMeasure dashboardPathMeasure = new PathMeasure();

    private int scalePosition;

    private float fraction = 0;

    public Dashboard(Context context) {
        super(context);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Dashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Dashboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public float getFraction() {
        return fraction;
    }

    public void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    public void setScalePosition(int scalePosition) {
        this.scalePosition = Math.max(scalePosition,0);
        this.scalePosition = Math.min(SCALE_COUNT, scalePosition);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "fraction",0,1);
        objectAnimator.start();
    }

    {

        effectRect.set(0, 0, SCALE_WIDTH, SCALE_HEIGHT);
        effectPath.addRect(effectRect, Path.Direction.CW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制dashboard
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DASHBOARD_STROKE_WIDTH);
        dashboardRect.set(PADDING, PADDING, getWidth() - PADDING, getHeight() - PADDING);
        dashboardPath.addArc(dashboardRect, START_ANGLE, SWEEP_ANGLE);
        canvas.drawPath(dashboardPath, paint);

        //绘制刻度
        dashboardPathMeasure.setPath(dashboardPath, false);
        PathEffect pathEffect = new PathDashPathEffect(effectPath, (dashboardPathMeasure.getLength() - SCALE_WIDTH) / (SCALE_COUNT), 0, PathDashPathEffect.Style.ROTATE);
        paint.setPathEffect(pathEffect);
        canvas.drawPath(dashboardPath, paint);
        paint.setPathEffect(null);

        //画指针
        int radius = (getWidth() - PADDING * 2) / 2;
        int pointRadius = radius - SCALE_HEIGHT * 3;
        pointPath.moveTo(getWidth() / 2, getHeight() / 2);
        pointPath.rLineTo(pointRadius, 0);


        //canvas.save();
        //int saveLayer = canvas.saveLayer(0,0,getWidth(),getHeight(),paint, Canvas.ALL_SAVE_FLAG);
        //canvas.translate(-getWidth()/2, -getHeight()/2);
        //canvas.rotate(START_ANGLE);

        //paint.setStyle(Paint.Style.FILL);
        canvas.save();
        paint.setColor(Color.RED);
        paint.setTextSize(UIUtils.dip2Px(16));
        canvas.rotate(START_ANGLE +( SWEEP_ANGLE  * scalePosition * 1.0f / SCALE_COUNT) * fraction, getWidth()/2, getHeight() /2);
        //canvas.drawRect(0,0,UIUtils.dip2Px(100),UIUtils.dip2Px(100),paint);
        //canvas.rotate(-90);

        canvas.drawPath(pointPath, paint);
        canvas.restore();
        //canvas.rotate(-START_ANGLE);
        //canvas.translate(getWidth()/2, getHeight()/2);
        //canvas.restore();
    }
}
