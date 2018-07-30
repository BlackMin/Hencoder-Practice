package com.baymax.android.hencoder.lesson6;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.baymax.android.hencoder.utils.UIUtils;

public class PieView extends View {

    private static final int PADDING = UIUtils.dip2Px(16);

    private static final float[] SWEEP_ANGELS = new float[] {60,160,140};

    private static final int[] ARC_COLORS = new int[] {Color.RED, Color.GREEN, Color.BLUE};

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mRectF = new RectF();

    private float usedAngel = 0;

    public PieView(Context context) {
        super(context);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        usedAngel = 0;
        float radius = (getWidth() - PADDING * 2) * 1.0f / 2;
        //Math.sin Math.cos 传入的角度必须是Math.PI / 多少
        float offsetY = (radius / 12) * (float) Math.sin(Math.PI / 6);
        float offsetX = (radius / 12) * (float) Math.cos(Math.PI / 6);
        mRectF.set(PADDING, PADDING, getWidth() - PADDING, getHeight()-PADDING);
        for (int i = 0 ;i<SWEEP_ANGELS.length;i++) {
            mPaint.setColor(ARC_COLORS[i]);

            if(i == 0) {
                mRectF.offset(offsetX, offsetY);
                canvas.drawArc(mRectF,usedAngel,SWEEP_ANGELS[i],true,mPaint);
                mRectF.offset(-offsetX, -offsetY);
            }else {
                canvas.drawArc(mRectF,usedAngel,SWEEP_ANGELS[i],true,mPaint);
            }


            usedAngel = usedAngel + SWEEP_ANGELS[i];
        }
    }
}
