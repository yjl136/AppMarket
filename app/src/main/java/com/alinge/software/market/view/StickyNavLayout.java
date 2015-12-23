package com.alinge.software.market.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class StickyNavLayout extends LinearLayout {
    private RelativeLayout mTopView;
    private PagerIndicatorView mPagerIndicator;
    private ViewPager mViewpager;
    public StickyNavLayout(Context context) {
        this(context, null);
    }

    public StickyNavLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}
