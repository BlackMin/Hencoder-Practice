package com.baymax.android.hencoder.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.util.DisplayMetrics;

public class UIUtils {

    public static int dip2Px(int dp) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return (int) (displayMetrics.density * dp);
    }

    public static Bitmap resizeBitmap(Resources resources, @DrawableRes int drawableRes, int targetImageWidth) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, drawableRes, options);
        options.inDensity = options.outWidth;
        options.inTargetDensity = targetImageWidth;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, drawableRes, options);
    }

    public static float getCameraZ(int locationZ) {
        return locationZ * Resources.getSystem().getDisplayMetrics().density;
    }
}
