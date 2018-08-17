package com.baymax.android.hencoder.lesson6;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.baymax.android.hencoder.utils.UIUtils;

public class TipsView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final Rect PADDING = new Rect(UIUtils.dip2Px(15),UIUtils.dip2Px(10),UIUtils.dip2Px(15),UIUtils.dip2Px(10));

    private int maxTextWidth=0;

    private TextPaint mTextPaint = new TextPaint();
    Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    //"患者端不展示治疗服务费，治疗服务费直接计入药费总额。默认金额：按照实际药费的固定比例收取。"
    private String text = "患者端不展示治疗服务费，治疗服务费直接计入药费总额。默认金额：按照实际药费的固定比例收取。";

    public TipsView(Context context) {
        super(context);
    }

    public TipsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TipsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TipsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        maxTextWidth = getMeasuredWidth() - PADDING.left - PADDING.right;
        //StaticLayout staticLayout = new StaticLayout(text, mTextPaint,maxTextWidth, Layout.Alignment.ALIGN_NORMAL,1,0,false);
        //int maxLines = staticLayout.getLineCount();
//        if(staticLayout.getHeight() > (getMeasuredHeight() - PADDING.top - PADDING.bottom)) {
//            maxLines = (int)Math.floor((getMeasuredHeight() - PADDING.top - PADDING.bottom) * 1.0f / staticLayout.getHeight() * staticLayout.getLineCount());
//        }
//        setMeasuredDimension(MeasureSpec.makeMeasureSpec(getMeasuredWidth(),MeasureSpec.EXACTLY)
//                ,MeasureSpec.makeMeasureSpec(staticLayout.getLineTop(maxLines)+ PADDING.top + PADDING.bottom,MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画文字
        initTextPaint();
        StaticLayout staticLayout = new StaticLayout(text, mTextPaint,maxTextWidth, Layout.Alignment.ALIGN_NORMAL,1,0,false);
        staticLayout.draw(canvas);

        //矩形框

        //画三角形
    }

    public void initTextPaint() {
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(UIUtils.dip2Px(30));
        mTextPaint.setColor(Color.RED);
    }
}
