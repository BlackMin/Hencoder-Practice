package com.baymax.android.hencoder.lesson9;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.baymax.android.hencoder.R;
import com.baymax.android.hencoder.utils.UIUtils;

public class SquareImageView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int imageSize = UIUtils.dip2Px(200);

    private Bitmap mBitmap;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        imageSize = Math.min(width, height);
        setMeasuredDimension(imageSize, imageSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(UIUtils.resizeBitmap(getResources(),R.mipmap.ic_launcher,imageSize),getLeft(), getTop(), mPaint);
    }
}
