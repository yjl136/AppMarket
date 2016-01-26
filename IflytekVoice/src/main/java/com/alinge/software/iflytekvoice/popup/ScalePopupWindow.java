package com.alinge.software.iflytekvoice.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.alinge.software.iflytekvoice.R;
import com.alinge.software.iflytekvoice.utils.DensityHelper;

/**
 * 作者： yejianlin
 * 日期：2016/1/26
 * 作用：
 */
public class ScalePopupWindow extends PopupWindow {
    private Activity context;
    private View popupView;
    private View animatorView;
    private ImageView imageView;

    public ScalePopupWindow(Context context) {
        super(context);
        this.context = (Activity) context;
        init();
    }

    private void init() {
        initWindow();
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        animatorView = popupView.findViewById(R.id.animator);
        imageView = (ImageView) popupView.findViewById(R.id.helper);
    }

    /**
     * 初始化window
     */
    private void initWindow() {
        setContentView(getPopupView(context));
       // setWidth(calWidth(context));
        //setHeight(calHeight(context));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable());
        setFocusable(true);
        setAnimationStyle(0);
    }

    /**
     * showWindow
     */
    public void show() {
        showAtLocation(context.findViewById(android.R.id.content),
                Gravity.RIGHT | Gravity.CENTER_HORIZONTAL,
                0,
                0);
        animatorView.clearAnimation();
        animatorView.startAnimation(getDefaultScaleAnimation());
    }

    /**
     * 计算宽度
     *
     * @param context
     * @return
     */
    private int calWidth(Context context) {
        return DensityHelper.getScreenWidth(context) / 3;
    }

    /**
     * 计算高度
     *
     * @param context
     * @return
     */
    private int calHeight(Context context) {
        return DensityHelper.getScreenHeight(context) / 2;
    }

    /**
     * 获取popup的view
     *
     * @param context
     * @return
     */
    private View getPopupView(Context context) {
        if (popupView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            popupView = inflater.inflate(R.layout.activity_helper, null);
        }
        return popupView;
    }
    /**
     * 生成ScaleAnimation
     */
    protected Animation getScaleAnimation(float fromX, float toX, float fromY, float toY,
                                          int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        Animation scaleAnimation =
                new ScaleAnimation(fromX, toX, fromY, toY, pivotXType, pivotXValue, pivotYType,
                        pivotYValue);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }

    /**
     * 生成自定义ScaleAnimation
     */
    protected Animation getDefaultScaleAnimation() {
        Animation scaleAnimation =
                new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(300);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setFillEnabled(true);
        scaleAnimation.setFillAfter(true);
        return scaleAnimation;
    }
}
