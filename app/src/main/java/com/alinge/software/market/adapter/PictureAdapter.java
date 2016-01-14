package com.alinge.software.market.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 作者： yejianlin
 * 日期：2016/1/11
 * 作用：
 */
public class PictureAdapter extends PagerAdapter {
    private  Context context;
    private  int[] picId;
    public PictureAdapter(int[] picId,Context context){
        this.context=context;
        this.picId=picId;
    }

    @Override
    public int getCount() {
        return picId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv=new ImageView(context);
        iv.setImageResource(picId[position]);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setTag(position);
        container.addView(iv,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view=container.findViewWithTag(position);
         container.removeView(view);
    }

}
