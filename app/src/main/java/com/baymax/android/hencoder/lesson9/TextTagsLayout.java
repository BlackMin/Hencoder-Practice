package com.baymax.android.hencoder.lesson9;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baymax.android.hencoder.R;
import com.baymax.android.hencoder.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

public class TextTagsLayout extends ViewGroup {

    private Rect tagPadding = new Rect(UIUtils.dip2Px(4), UIUtils.dip2Px(2), UIUtils.dip2Px(4), UIUtils.dip2Px(2));

    private int horizontalTagsDivider = UIUtils.dip2Px(8);

    private int verticalTagsDivider = UIUtils.dip2Px(8);

    private int showHorizontalTagsDivider = TagsDividerMode.DIVIDER_NONE;

    private int showVerticalTagsDivider = TagsDividerMode.DIVIDER_NONE;

    private List<String> tags = new ArrayList<>();

    private int tagBackgroundColor = Color.parseColor("#c15d3e");

    private int tagTextColor = Color.parseColor("#ffffff");

    private int tagTextSize = 14;

    public TextTagsLayout(Context context) {
        super(context);
    }

    public TextTagsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextTagsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextTagsLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context) {

    }

    @Override
    public void addView(View child) {

    }

    @Override
    public void addView(View child, int index) {

    }

    @Override
    public void addView(View child, int width, int height) {

    }

    @Override
    public void addView(View child, LayoutParams params) {

    }

    @Override
    public void addView(View child, int index, LayoutParams params) {

    }

    public void setTags(List<String> tags) {
        this.tags = (tags == null ? new ArrayList<>() : tags);
        removeAllViews();
        if (tags != null && tags.size() > 0) {
            for (String tag : tags) {
                View tagItem = generateTag(tag);
                super.addView(tagItem, -1, tagItem.getLayoutParams());
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int tagLayoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        int tagLayoutWidthSpec = MeasureSpec.getMode(widthMeasureSpec);
        int tagLayoutHeight = MeasureSpec.getSize(heightMeasureSpec);
        int tagLayoutHeightSpec = MeasureSpec.getMode(heightMeasureSpec);

        int childCount = getChildCount();

        int usedWidthInLine = 0;

        int usedHeight = 0;

        int maxHeightInLine = 0;

        int maxWidth = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin
                            + usedWidthInLine, lp.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin
                            + usedHeight, lp.height);
            if (MeasureSpec.getMode(childWidthMeasureSpec) == MeasureSpec.AT_MOST
                    || MeasureSpec.getMode(childWidthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
                child.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), childHeightMeasureSpec);
            } else {
                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
            int widthAndState = resolveSizeAndState(child.getMeasuredWidth()
                    , MeasureSpec.makeMeasureSpec(Math.max(0,tagLayoutWidth-usedWidthInLine-(getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin)),MeasureSpec.AT_MOST)
                    , 0);
            if ((widthAndState & MEASURED_STATE_MASK) == MEASURED_STATE_TOO_SMALL) {
                //需要换行了
                usedHeight += maxHeightInLine;
                usedWidthInLine = 0;
                maxHeightInLine = 0;
                if(child.getMeasuredWidth() >= tagLayoutWidth-(getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin)) {
                    //重新测量
                    childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                            getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin
                                    + usedWidthInLine, lp.width);
                    childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                            getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin
                                    + usedHeight, lp.height);
                    child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                }
                usedWidthInLine += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                maxHeightInLine = Math.max(maxHeightInLine, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                maxWidth = Math.max(maxWidth, usedWidthInLine + getPaddingLeft() + getPaddingRight());
            }else {
                usedWidthInLine += child.getMeasuredWidth()+ lp.leftMargin + lp.rightMargin;;
                maxHeightInLine = Math.max(maxHeightInLine, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                maxWidth = Math.max(maxWidth, usedWidthInLine + getPaddingLeft() + getPaddingRight());
            }
        }

        int width = maxWidth;
        int height = usedHeight + maxHeightInLine + getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(height, heightMeasureSpec));
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutChildren(l, t, r, b);
    }

    private void layoutChildren(int left, int top, int right, int bottom) {
        int layoutLeft = left + getPaddingLeft();
        int layoutTop = top + getPaddingTop();
        int layoutRight = right - getPaddingRight();
        int layoutBottom = bottom - getPaddingBottom();
        int width = layoutRight - layoutLeft;
        int height = layoutBottom - layoutTop;
        int childCount = getChildCount();
        int usedWidthInLine = 0;
        int usedHeight = 0;
        int maxHeightInLine = 0;
        for (int i = 0; i < childCount; i++) {
            int remainWidth = width - usedWidthInLine;
            View child = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            if(remainWidth < (child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin)) {
                usedHeight += maxHeightInLine;
                usedWidthInLine = 0;
                child.layout(layoutLeft + lp.leftMargin
                        , layoutTop + usedHeight + lp.topMargin
                        , layoutLeft + lp.leftMargin + child.getMeasuredWidth()
                        , layoutTop + usedHeight + lp.topMargin + child.getMeasuredHeight());
                usedWidthInLine += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                maxHeightInLine = Math.max(maxHeightInLine, lp.topMargin + lp.bottomMargin + child.getMeasuredHeight());
            }else {
                child.layout(layoutLeft + usedWidthInLine + lp.leftMargin
                        , layoutTop + usedHeight + lp.topMargin
                        , layoutLeft + usedWidthInLine + lp.leftMargin + child.getMeasuredWidth()
                        , layoutTop + usedHeight + lp.topMargin + child.getMeasuredHeight());
                usedWidthInLine += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                maxHeightInLine = Math.max(maxHeightInLine, lp.topMargin + lp.bottomMargin + child.getMeasuredHeight());
            }
        }
    }

    private View generateTag(@NonNull String tag) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_tag_item, this, false);
        textView.setPadding(tagPadding.left, tagPadding.top, tagPadding.right, tagPadding.bottom);
        textView.setBackgroundColor(tagBackgroundColor);
        textView.setTextColor(tagTextColor);
        textView.setTextSize(tagTextSize);
        textView.setText(tag);
        return textView;
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        if (p instanceof MarginLayoutParams) {
            return p;
        } else {
            if (p != null) {
                MarginLayoutParams params = new MarginLayoutParams(p.width, p.height);
                params.layoutAnimationParameters = p.layoutAnimationParameters;
                return params;
            } else {
                return generateDefaultLayoutParams();
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    public static class TagsDividerMode {
        public static final int DIVIDER_NONE = 0;

        public static final int DIVIDER_START = 1;

        public static final int DIVIDER_MIDDLE = 2;

        public static final int DIVIDER_END = 4;
    }

}
