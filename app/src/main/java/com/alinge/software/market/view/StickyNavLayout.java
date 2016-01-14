package com.alinge.software.market.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private ViewGroup scrollView;
    private int titleBarHeight;
    private  View titleBar;



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
            case MotionEvent.ACTION_CANCEL:
                float scrollY = getScrollY();
                if (Math.abs(scrollY) >= (mTopViewHeight -titleBarHeight)/ 2) {
                    //显示
                    mScroller.startScroll(getScrollX(), getScrollY(), 0, (mTopViewHeight -titleBarHeight)/ 2);
                } else {
                    //隐藏
                    mScroller.startScroll(getScrollX(), getScrollY(), 0, -(mTopViewHeight -titleBarHeight)/ 2);
                }
                isDraging = false;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.info(null,"onInterceptTouchEvent");
        int action = ev.getAction();
        float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - lastY;
                LogUtils.info(null,"dy11:"+dy);
                getCurrentScrollView();
                if (Math.abs(dy) > mTouchSlop) {
                    LogUtils.info(null,"dy22:"+dy  +"  ishide:"+isTopViewHiden+ "    scrollview scrolly:"+scrollView.getScrollY());
                    if (!isTopViewHiden || (scrollView.getScrollY() == 0 && isTopViewHiden && dy > 0)) {
                        return true;
                    }
                    lastY=y;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isDraging=false;
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
    private void getCurrentScrollView() {

        int currentItem = mViewpager.getCurrentItem();
        PagerAdapter a = mViewpager.getAdapter();
        if (a instanceof FragmentPagerAdapter) {
            FragmentPagerAdapter fadapter = (FragmentPagerAdapter) a;
            Fragment item = (Fragment) fadapter.instantiateItem(mViewpager,
                    currentItem);
            scrollView = (ViewGroup) (item.getView()
                    .findViewById(R.id.sv));
        } else if (a instanceof FragmentStatePagerAdapter) {
            FragmentStatePagerAdapter fsAdapter = (FragmentStatePagerAdapter) a;
            Fragment item = (Fragment) fsAdapter.instantiateItem(mViewpager,
                    currentItem);
            scrollView = (ViewGroup) (item.getView()
                    .findViewById(R.id.sv));
        }

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
        if (y > mTopViewHeight-titleBarHeight) {
            y = mTopViewHeight-titleBarHeight;
        }
        if(y!=getScrollY()){
            super.scrollTo(x, y);
        }
        float percent=getScrollY()*1.0f/(mTopViewHeight-titleBarHeight);
        setTitleBarAlpha(percent);
        LogUtils.info(null,"percent:"+percent);
        isTopViewHiden = getScrollY()>= mTopViewHeight-titleBarHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.info(null, "onMeasure");
        //获取到titlbar的高度
        calTitleBarHeight();
        LogUtils.info(null, "titleBarHeight:" + titleBarHeight);
        ViewGroup.LayoutParams params = mViewpager.getLayoutParams();
        params.height = getMeasuredHeight()-mPagerIndicator.getMeasuredHeight()-titleBarHeight;
        LogUtils.info(null,"height:"+getMeasuredHeight()+"  PagerIndicator height:"+mPagerIndicator.getMeasuredHeight());
    }

    private void calTitleBarHeight() {
        ViewParent viewParent=getParent();
        RelativeLayout layout=(RelativeLayout)viewParent;
        titleBar=layout.findViewById(R.id.tilteBar);
        titleBar.measure(0, 0);
        titleBarHeight= titleBar.getMeasuredHeight();
        //设置titlbar默认的alpha
        if(!isTopViewHiden){
            titleBar.setAlpha(0.0f);
        }


    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTopView.getMeasuredHeight();
    }

    /**
     * 更具移动的距离百分比来设置titlebar的alpha
     * @param percent
     */
    private void setTitleBarAlpha(float percent){
        titleBar.setAlpha(percent);
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = (RelativeLayout) findViewById(R.id.topView);
        mPagerIndicator = (PagerIndicatorView) findViewById(R.id.pagerIndicator);
        mViewpager = (ViewPager) findViewById(R.id.viewPager);

    }
}
