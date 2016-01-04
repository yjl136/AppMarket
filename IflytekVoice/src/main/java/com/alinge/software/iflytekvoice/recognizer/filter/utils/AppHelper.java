package com.alinge.software.iflytekvoice.recognizer.filter.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

import java.util.List;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class AppHelper {
    public static void searchApp(String appName){

    }
    public static void exitApp(String appName){

    }
    public static void installApp(String appName){

    }

    /**
     * 卸载app应用
     * @param context
     * @param appName
     */
    public static void uninstallApp(Context context,String appName){
        PackageManager pm=context.getPackageManager();
        ResolveInfo resolve=queryApp(pm,appName);
        if(resolve!=null){
            String packageName=resolve.activityInfo.packageName;
            Uri uri=Uri.parse("package:"+packageName);
            Intent intent=new Intent(Intent.ACTION_DELETE,uri);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"对不起，你要卸载的应用不存在",Toast.LENGTH_SHORT).show();
        }
    }
    public static void downloadApp(String appName){

    }
    /***
     * 启动应用
     * @param appName 应用的名字
     * @param  context
     */
    public static void launchApp(Context context,String appName){
        PackageManager pm=context.getPackageManager();
        ResolveInfo resolve=queryApp(pm,appName);
        if(resolve!=null){
            String packageName=resolve.activityInfo.packageName;
            Intent intent=pm.getLaunchIntentForPackage(packageName);
            context.startActivity(intent);
        }else{
            Toast.makeText(context,"对不起，你要打开的应用还没有安装",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 判断所给定的应用名是否可以启动
     * @param pm
     * @param queryName
     * @return
     */
    public static ResolveInfo queryApp(PackageManager pm,String queryName){
        Intent intent=new Intent();
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setAction(Intent.ACTION_MAIN);
        List<ResolveInfo> resolves=pm.queryIntentActivities(intent,0);
        for(ResolveInfo resolve:resolves){
            String appName= (String)resolve.loadLabel(pm);
           // LogUtils.info(null,"appname:"+appName);
            if(queryName.equalsIgnoreCase(appName) ){
                return resolve;
            }
        }
        return null;
    }
}
