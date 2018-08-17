package com.baymax.android.hencoder.lesson7;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.baymax.android.hencoder.R;
import com.baymax.android.hencoder.utils.UIUtils;

public class FancyFlipView extends View {
    private static final float IMAGE_SIZE = UIUtils.dip2Px(300);
    private static final float FLIP_ANGLE = 45;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Camera camera = new Camera();

    private float leftFlip;
    private float rightFlip;
    private float flipRotation;

    private int imageWidth;

    private int imageHeight;

    ObjectAnimator animator1;

    ObjectAnimator animator2;

    AnimatorSet animatorSet = new AnimatorSet();


    public float getLeftFlip() {
        return leftFlip;
    }

    public void setLeftFlip(float leftFlip) {
        this.leftFlip = leftFlip;
        invalidate();
    }

    public float getRightFlip() {
        return rightFlip;
    }

    public void setRightFlip(float rightFlip) {
        this.rightFlip = rightFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public FancyFlipView(Context context) {
        super(context);
    }

    public FancyFlipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FancyFlipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap = UIUtils.resizeBitmap(getResources(), R.mipmap.bc, (int) IMAGE_SIZE);
        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();

        animator1 = ObjectAnimator.ofFloat(this, "rightFlip", 1);
        animator1.setDuration(500);
        animator2 = ObjectAnimator.ofFloat(this, "flipRotation", 270);
        animator2.setDuration(1000);
        animator2.setStartDelay(300);

        ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "leftFlip", 1);
        animator3.setDuration(500);
        animator3.setStartDelay(300);

        animatorSet.playSequentially(animator1,animator2,animator3);
        animatorSet.setStartDelay(600);

        camera.setLocation(0, 0, UIUtils.getCameraZ(-6));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animatorSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorSet.end();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int paddingLeft = (getWidth() - imageWidth)/2;
        int paddingTop = (getHeight() - imageHeight)/2;
        float offsetX = imageWidth / 2 + paddingLeft;
        float offsetY = imageHeight / 2 + paddingTop;

        // 左半边
        camera.save();
        camera.rotateY(leftFlip * FLIP_ANGLE);

        canvas.save();
        canvas.translate(offsetX, offsetY);
        canvas.rotate(-flipRotation);
        camera.applyToCanvas(canvas);
        canvas.clipRect(0,-imageHeight,-imageWidth,imageHeight);
        canvas.rotate(flipRotation);
        canvas.translate(-offsetX, -offsetY);
        canvas.drawBitmap(bitmap,paddingLeft,paddingTop, paint);
        canvas.restore();

        camera.restore();


        // 右半边
        camera.save();
        camera.rotateY(-rightFlip * FLIP_ANGLE);
        canvas.save();
        canvas.translate(offsetX, offsetY);
        canvas.rotate(-flipRotation );
        camera.applyToCanvas(canvas);
        canvas.clipRect(0,-imageHeight,imageWidth,imageHeight);
        canvas.rotate(flipRotation );
        canvas.translate(-offsetX, -offsetY);
        canvas.drawBitmap(bitmap,paddingLeft,paddingTop, paint);
        canvas.restore();
        camera.restore();


    }
}
