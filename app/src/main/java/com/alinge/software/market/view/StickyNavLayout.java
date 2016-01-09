package com.alinge.software.market.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alinge.software.market.R;
import com.alinge.software.market.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class StickyNavLayout extends LinearLayout {
    private final static int default_duration = 100;
    private RelativeLayout mTopView;
    private PagerIndicatorView mPagerIndicator;
    private ViewPager mViewpager;
    private int mTopViewHeight;
    private float lastY;
    private OverScroller mScroller;
    private VelocityTracker mVelocityTracker;
    private boolean isDraging;
    private int mTouchSlop;
    private int mMaxVelocity;
    private int mMinVeloctiy;
    private boolean isTopViewHiden;


    public StickyNavLayout(Context context) {
        this(context, null);
    }

    public StickyNavLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyNavLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new OverScroller(context);
        mVelocityTracker = VelocityTracker.obtain();
        ViewConfiguration vc = ViewConfiguration.get(context);
        //可以认为用户在滚动的最小距离
        mTouchSlop = vc.getScaledTouchSlop();
        mMaxVelocity = vc.getScaledMaximumFlingVelocity();
        mMinVeloctiy = vc.getScaledMinimumFlingVelocity();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
              if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                lastY = y;
               break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - lastY;
                if (!isDraging && Math.abs(dy) > mTouchSlop) {
                    isDraging = true;
                }
                if (isDraging) {
                    scrollBy(0, (int) -dy);
                    lastY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
                float scrollY = getScrollY();
                if (Math.abs(scrollY) >= mTopViewHeight / 2) {
                    //显示
                    mScroller.startScroll(getScrollX(), getScrollY(), 0, mTopViewHeight / 2);
                } else {
                    //隐藏
                    mScroller.startScroll(getScrollX(), getScrollY(), 0, -mTopViewHeight / 2);
                }
                isDraging = false;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - lastY;
                ScrollView scrollView = (ScrollView) mViewpager.findViewById(R.id.sv);
                if (Math.abs(dy) > mTouchSlop) {
                    if (!isTopViewHiden || (scrollView.getScrollY() == 0 && isTopViewHiden && dy > 0)) {
                        return true;
                    }
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        super.scrollTo(x, y);
        isTopViewHiden = getScrollY() == mTopViewHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.info(null, "onMeasure");
        //应为vp中里面包含有scrolView，vp默认测量规则是如果为mode为at_most,exactly,直接从parent中读取
       /*int fixHeightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE / 2, MeasureSpec.AT_MOST);
        mViewpager.measure(widthMeasureSpec, fixHeightMeasureSpec);*/
        ViewGroup.LayoutParams params = mViewpager.getLayoutParams();
        params.height = getMeasuredHeight()-mPagerIndicator.getMeasuredHeight();
        mViewpager.setLayoutParams(params);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTopView.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = (RelativeLayout) findViewById(R.id.topView);
        mPagerIndicator = (PagerIndicatorView) findViewById(R.id.pagerIndicator);
        mViewpager = (ViewPager) findViewById(R.id.viewPager);
    }
}
