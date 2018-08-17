package com.baymax.android.hencoder.lesson6;

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

public class TextLayout extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint.FontMetrics mFontMetrics = new Paint.FontMetrics();

    private String text = "Contrary to popular belief, " +
            "Lorem Ipsum is not simply random text. " +
            "It has roots in a piece of classical Latin literature " +
            "from 45 BC, making it over 2000 years old. " +
            "Richard McClintock, a Latin professor at " +
            "Hampden-Sydney College in Virginia, looked up " +
            "one of the more obscure Latin words, consectetur," +
            " from a Lorem Ipsum passage, and going through the" +
            " cites of the word in classical literature, " +
            "discovered the undoubtable source. Lorem Ipsum " +
            "comes from sections 1.10.32 and 1.10.33 of \"de Finibus " +
            "Bonorum et Malorum\" (The Extremes of Good and Evil) " +
            "by Cicero, written in 45 BC. This book is a treatise " +
            "on the theory of ethics, very popular during the Renaissance. " +
            "The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\"," +
            " comes from a line in section 1.10.32. " +
            "There are many variations of passages of Lorem Ipsum available, " +
            "but the majority have suffered alteration in some form, " +
            "by injected humour, or randomised words which don't " +
            "look even slightly believable. If you are going to use " +
            "a passage of Lorem Ipsum, you need to be sure there isn't " +
            "anything embarrassing hidden in the middle of text. " +
            "All the Lorem Ipsum generators on the Internet tend " +
            "to repeat predefined chunks as necessary, making " +
            "this the first true generator on the Internet. " +
            "It uses a dictionary of over 200 Latin words, " +
            "combined with a handful of model sentence structures, " +
            "to generate Lorem Ipsum which looks reasonable. " +
            "The generated Lorem Ipsum is therefore always " +
            "free from repetition, injected humour, or " +
            "non-characteristic words etc.";

    private float[] measuredWidth = new float[1];

    private int IMAGE_WIDTH = UIUtils.dip2Px(250);

    private int IMAGE_TOP_MARGIN = UIUtils.dip2Px(80);

    public TextLayout(Context context) {
        super(context);
    }

    public TextLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    {
        mPaint.setTextSize(UIUtils.dip2Px(16));
        mPaint.getFontMetrics(mFontMetrics);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bcBitmap = getBitmap(IMAGE_WIDTH);
        int imageLeft = (getWidth() - IMAGE_WIDTH)/2;
        int imageTop = (getHeight() - bcBitmap.getHeight())/2;
        int imageRight = imageLeft + IMAGE_WIDTH;
        int imageBottom = imageTop + bcBitmap.getHeight();
        int usedCount = 0;
        float usedHeight = -mFontMetrics.top;
        int leftCount = 0;
        int rightCount = 0;
        if((usedHeight >= imageTop && usedHeight <= imageBottom) || ((usedHeight - mPaint.getFontSpacing() <= imageBottom) && (usedHeight >= imageBottom))) {
            leftCount = mPaint.breakText(text,true,imageLeft,measuredWidth);
            rightCount = mPaint.breakText(text,usedCount + leftCount,text.length(),true,getWidth() - imageRight,measuredWidth);
        }else {
            leftCount = mPaint.breakText(text,true,getMeasuredWidth(),measuredWidth);
            rightCount = 0;
        }
        while (leftCount > 0 || rightCount > 0) {
            if(leftCount > 0) {
                canvas.drawText(text,usedCount, usedCount+leftCount,0,usedHeight,mPaint);
                usedCount += leftCount;
            }
            if(rightCount > 0) {
                canvas.drawText(text,usedCount, usedCount+rightCount,imageRight,usedHeight,mPaint);
                usedCount += rightCount;
            }
            usedHeight += mPaint.getFontSpacing();
            if((usedHeight >= imageTop && usedHeight <= imageBottom) || ((usedHeight - mPaint.getFontSpacing() <= imageBottom) && (usedHeight >= imageBottom))) {
                leftCount = mPaint.breakText(text,usedCount,text.length(),true,imageLeft,measuredWidth);
                rightCount = mPaint.breakText(text,usedCount + leftCount,text.length(),true,getWidth() - imageRight,measuredWidth);
            }else {
                leftCount = mPaint.breakText(text,usedCount,text.length(),true,getMeasuredWidth(),measuredWidth);
                rightCount = 0;
            }
        }

        canvas.drawBitmap(bcBitmap, (getWidth() - IMAGE_WIDTH)/2, (getHeight() - bcBitmap.getHeight())/2, mPaint);
    }

    private Bitmap getBitmap(int targetWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.bc,options);
        options.inJustDecodeBounds = false;
        options.inDensity = options.outWidth;
        options.inTargetDensity = targetWidth;
        return BitmapFactory.decodeResource(getResources(), R.mipmap.bc,options);
    }
}
