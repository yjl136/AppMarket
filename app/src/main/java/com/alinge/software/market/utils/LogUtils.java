package com.alinge.software.market.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * 作者： yejianlin
 * 日期：2015/12/23
 * 作用：
 */
public class LogUtils {
    private static boolean isDebug = true;
    private static final String default_tag="12/23";
    private LogUtils(){
    }
    public static void info(String tag,String message){
        if(isDebug){
            if(TextUtils.isEmpty(tag)){
                Log.i(default_tag,message);
                return;
            }
            Log.i(tag,message);
        }
    }
    public static void error(String tag,String message){
        if(isDebug){
            if(TextUtils.isEmpty(tag)){
                Log.e(default_tag, message);
                return;
            }
            Log.e(tag, message);
        }
    }
    public static void warn(String tag,String message){
        if(isDebug){
            if(TextUtils.isEmpty(tag)){
                Log.w(default_tag,message);
                return;
            }
            Log.w(tag,message);
        }
    }
}
