package com.alinge.software.market.adapter.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

/**
 * 作者： yejianlin
 * 日期：2016/1/22
 * 作用：
 */
public class AppItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        outRect.set(0,0,0,20);
    }
}
