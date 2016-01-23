package com.alinge.software.market.adapter.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/**
 * 作者： yejianlin
 * 日期：2016/1/22
 * 作用：
 */
public class AppItemDecoration extends RecyclerView.ItemDecoration {
    private int orientation;
    private Drawable mDivider;
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public AppItemDecoration(Context context,int orientation) {
         TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        this.orientation=orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.set(0,0,0,10);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        super.onDraw(c, parent);
        if(orientation== LinearLayoutManager.VERTICAL){
            //drawVercal(c,parent);
        }else if(orientation==LinearLayoutManager.HORIZONTAL){
            //drawHorizontal(c,parent);
        }else{
            throw new  RuntimeException();
        }

    }

    /**
     *当orientation== LinearLayoutManager.VERTICAL时
     * @param c
     * @param parent
     */
    private void drawVercal(Canvas c, RecyclerView parent){
        int left=parent.getPaddingLeft();
        int right=parent.getWidth()-parent.getPaddingRight();
        int childCount=parent.getChildCount();
        for(int index=0;index<childCount;index++){
            View child=parent.getChildAt(index);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top=child.getBottom()+lp.bottomMargin;
            int bottmo=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottmo);
            mDivider.draw(c);
        }
    }
    /**
     *当orientation== LinearLayoutManager.HORIZONTAL
     * @param c
     * @param parent
     */
    private void drawHorizontal(Canvas c, RecyclerView parent){
        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        int childCount=parent.getChildCount();
        for(int index=0;index<childCount;index++){
            View child=parent.getChildAt(index);
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left=child.getRight()+lp.rightMargin;
            int right=left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,right,top,bottom);
            mDivider.draw(c);
        }
    }
}
