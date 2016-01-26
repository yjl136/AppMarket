package com.alinge.software.iflytekvoice.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 作者： yejianlin
 * 日期：2015/12/7
 * 作用：
 */
public class DensityHelper {
    private DensityHelper(){

    }
    public static int getScreenHeight(Context context){
        DisplayMetrics dm= context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
    public static int getScreenWidth(Context context){
        DisplayMetrics dm= context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
}
