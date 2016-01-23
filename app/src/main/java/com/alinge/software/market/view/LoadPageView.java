package com.alinge.software.market.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alinge.software.market.R;
import com.alinge.software.market.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：
 */
public abstract class LoadPageView extends FrameLayout {
    public final static int STATE_INIT = 0;
    public final static int STATE_LOADING = 1;
    public final static int STATE_SUCCESS = 2;
    public final static int STATE_EMPTY = 3;
    public final static int STATE_ERROR = 4;
    private int state;
    private View loadingView;
    private View successView;
    private View errorView;
    private View emptyView;
    public LoadPageView(Context context) {
        this(context, null);
    }

    public LoadPageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        state = STATE_INIT;
        initView(context);
    }

    private void initView(Context context) {
        loadingView = createLoadingView();
        if (loadingView != null) {
            addContentView(loadingView);
        }
        errorView = createErrorView();
        if (errorView != null) {
            addContentView(errorView);
        }
        emptyView = createEmptyView();
        if (emptyView != null) {
            addContentView(emptyView);
        }
        successView = createSuccessView();
        if (successView != null) {
            addContentView(successView);
        }
        showView();
        //加载网络数据
        loadData();
    }

    /**
     * 创建加载中的界面
     *
     * @return
     */
    private View createLoadingView() {
        View view=View.inflate(getContext(), R.layout.loading_layout, null);
        return view;
    }

    /**
     * 创建加载错误界面
     *
     * @return
     */
    private View createErrorView() {
        TextView tv=new TextView(getContext());
        tv.setText("加载错误");
        tv.setTextSize(25);
        return tv;
    }

    /**
     * 创建加载成功界面
     *
     * @return
     */
   public abstract View createSuccessView();
    /**
     * 加载网络数据
     */
    public abstract  void loadData();
    /**
     * 创建空数据界面
     *
     * @return
     */
    private View createEmptyView() {
        TextView tv=new TextView(getContext());
        tv.setText("数据为空");
        tv.setTextSize(25);
        return tv;
    }

    /**
     * 将view添加到父布局中
     * @param view
     */
    private void addContentView(View view) {
        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity= Gravity.CENTER;
        addView(view,lp );
    }
    /**
     * 根据不同状态显示不同页面
     */
    private void showView() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_INIT || state == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        }
        if(errorView!=null){
            errorView.setVisibility(state == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if(successView!=null){
          successView.setVisibility(state==STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
        }
    }
    /**
     * 状态改变
     * @param state
     */
    public void stateChange(int state){
        LogUtils.info(null,"state:"+state );
        this.state=state;
        showView();
    }
}
