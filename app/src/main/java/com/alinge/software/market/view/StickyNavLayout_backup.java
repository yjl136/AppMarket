package com.alinge.software.market.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.alinge.software.market.R;
import com.alinge.software.market.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class StickyNavLayout_backup extends LinearLayout {
    private ViewDragHelper mDragHelper;
    private RelativeLayout mTopView;
    private PagerIndicatorView mPagerIndicator;
    private ViewPager mViewpager;
    private int topViewHeight;
    private int height;
    private int width;


    public StickyNavLayout_backup(Context context) {
        this(context, null);
    }

    public StickyNavLayout_backup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyNavLayout_backup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, 1.0f, mCallback);
    }

    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
         //   LogUtils.info(null,"tryCaptureView chilid:"+child);
            return true;
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
           // LogUtils.info(null, "onViewCaptured capturedChild:" + capturedChild);
            //onViewCaptured(StickyNavLayout.this,activePointerId);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
           // LogUtils.info(null, " top:" + top + "  dy:" + dy + "   getTop:" + child.getTop());
            int fixTop=0;
            if(mTopView==child){
                //如果当前的是topview
                if(-topViewHeight<top && top<0){
                    fixTop=top;
                }else if(-topViewHeight>top){
                    fixTop=-topViewHeight;
                }else if(top>0){
                    fixTop=0;
                }
            }
            return fixTop;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            LogUtils.info(null, " top:" + top + "  dy:" + dy + "   getTop:" + changedView.getTop());
            scrollBy(dx, -dy);
            //mTopView.layout(0,dy,width,mTopView.getHeight()+dy);
         /*   if(dy<0){

                mTopView.offsetTopAndBottom(-dy);
            }else {

                mTopView.offsetTopAndBottom(-dy);
            }*/

        }
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.info(null,"onMeasure");
        //应为vp中里面包含有scrolView，vp默认测量规则是如果为mode为at_most,exactly,直接从parent中读取
        int fixHeightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE / 2, MeasureSpec.AT_MOST);
        mViewpager.measure(widthMeasureSpec,fixHeightMeasureSpec);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.info(null,"onInterceptTouchEvent");
        boolean isIntercept= false;
        //什么时候应该讲scrview的move时间拦截呢 1：topview可以看见时，必须拦截move事件
        //2:topview不可以看见时，scrolvie.getscrolY=0
        int bottom = mTopView.getBottom();
            if(bottom>0){
                isIntercept=true;
            }
        LogUtils.info(null,"onInterceptTouchEvent isIntercept:"+isIntercept);
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        LogUtils.info(null,"top:"+mTopView.getTop()+" bottom:"+mTopView.getBottom());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
         height = getMeasuredHeight();
         topViewHeight = mTopView.getMeasuredHeight();
        width=getMeasuredWidth();
        int indicatorHeight = mPagerIndicator.getMeasuredHeight();
        int vpHeight = mViewpager.getMeasuredHeight();
        LogUtils.info(null, "height:" + height +"  width"+width+ "  topViewHeight:" + topViewHeight + " indicatorHeight:" + indicatorHeight + "  vpHeight:" + vpHeight);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = (RelativeLayout) findViewById(R.id.topView);
        mPagerIndicator = (PagerIndicatorView) findViewById(R.id.pagerIndicator);
        mViewpager = (ViewPager) findViewById(R.id.viewPager);
    }
}
