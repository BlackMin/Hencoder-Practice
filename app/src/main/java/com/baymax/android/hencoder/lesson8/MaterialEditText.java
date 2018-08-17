package com.baymax.android.hencoder.lesson8;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;

import com.baymax.android.hencoder.utils.UIUtils;

public class MaterialEditText extends AppCompatEditText {

    private Paint mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Paint.FontMetrics mLabelFontMetrics = new Paint.FontMetrics();

    private TextPaint mETPaint;

    private Paint.FontMetrics mETFontMetrics = new Paint.FontMetrics();

    private float labelFraction = 0f;

    private ObjectAnimator labelAnimator = ObjectAnimator.ofFloat(this,"labelFraction",1);

    private OnLabelShowChangedListener mOnLabelShowChangedListener = new OnLabelShowChangedListener() {
        @Override
        public void onShow() {
            labelAnimator.start();
        }

        @Override
        public void onHide() {
            labelAnimator.reverse();
        }
    };

    /**
     * 用户设置的showLabel状态
     */
    private boolean showLabel = true;

    /**
     * 根据showLabel以及label文字是否为空 得出的实际showLabel状态
     */
    private boolean realShowLabel = false;

    private int labelTextSize = UIUtils.dip2Px(16);

    private String mLabel;

    private Rect initPadding = new Rect();

    public MaterialEditText(Context context) {
        super(context);
        init(context, null);
    }

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        //1.first init padding
        initPadding.set(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        mLabelPaint.setTextSize(labelTextSize);
        mLabelPaint.setColor(Color.parseColor("#ffffff"));
        mLabelPaint.getFontMetrics(mLabelFontMetrics);
        mETPaint = getPaint();
        mETPaint.getFontMetrics(mETFontMetrics);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(s)) {
                    setShowLabel(false);
                }else {
                    setLabel(getHint().toString());
                    setShowLabel(true);
                }
            }
        });
    }

    public void setLabelFraction(float labelFraction) {
        this.labelFraction = labelFraction;
        invalidate();
    }

    public float getLabelFraction() {
        return labelFraction;
    }

    public void setShowLabel(boolean showLabel) {
        this.showLabel = showLabel;
        setRealShowLabel(this.showLabel && !TextUtils.isEmpty(mLabel));
    }

    public void setLabel(String label) {
        this.mLabel = label;
        setRealShowLabel(this.showLabel && !TextUtils.isEmpty(mLabel));
    }

    private void setRealShowLabel(boolean realShowLabel) {
        if (this.realShowLabel != realShowLabel) {
            if (this.realShowLabel == true) {
                this.realShowLabel = false;
                setPadding(getPaddingLeft(), (int) ((getPaddingTop() - mLabelPaint.getFontSpacing()) / 2), getPaddingRight(), getPaddingBottom());
                if(mOnLabelShowChangedListener != null) {
                    mOnLabelShowChangedListener.onHide();
                }
            } else {
                this.realShowLabel = true;
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());if(mOnLabelShowChangedListener != null) {
                    mOnLabelShowChangedListener.onShow();
                }
            }
        }
    }

    private void setLabelTextSize(int labelTextSize) {
        if(this.labelTextSize != labelTextSize) {
            this.labelTextSize = labelTextSize;
            if(this.realShowLabel) {
                float fontSpacing = mLabelPaint.getFontSpacing();
                mLabelPaint.setTextSize(this.labelTextSize);
                setPadding(getPaddingLeft(), (int) ((getPaddingTop() - fontSpacing) / 2), getPaddingRight(), getPaddingBottom());
            }else {
                mLabelPaint.setTextSize(this.labelTextSize);
            }
        }
    }

    /**
     * EditText原本的 left, top , right, bottom padding
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if(realShowLabel) {
            super.setPadding(left, top * 2 + (int) mLabelPaint.getFontSpacing(), right, bottom);
        }else {
            super.setPadding(left, top, right, bottom);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(!TextUtils.isEmpty(mLabel)) {
            mLabelPaint.setAlpha((int) (labelFraction * 0xff));
            int labelOffSetY = (int)((getPaddingTop() - mLabelPaint.getFontSpacing()) / 2 + mLabelPaint.getFontSpacing()) + (int)((1-labelFraction) * mETPaint.getFontSpacing());
            canvas.drawText(mLabel,getPaddingLeft(), labelOffSetY, mLabelPaint);
        }
        super.onDraw(canvas);
    }

    private interface OnLabelShowChangedListener {

        public void onShow();

        public void onHide();
    }
}
