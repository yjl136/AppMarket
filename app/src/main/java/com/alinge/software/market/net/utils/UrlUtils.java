package com.alinge.software.market.net.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.lang.reflect.Method;

/**
 * 作者： yejianlin
 * 日期：2016/1/12
 * 作用：
 */
public class UrlUtils {
    public  final static String PRODUCT_ID="3";
    /**
     *  http://market.kyd2002.cn/API/Market/MarketIndex?machineName=kimi%20i8&bannerCount=6&count=6&token=RLNE52N3VH-d69128f09f6ee5e7127ad3b07e95a636&productId=3
     */
    public  final static String HOST="http://market.kyd2002.cn/";
    public final static String QUALITY_MAIN_URI="API/Market/MarketIndex";

    /**
     * 得到机器的特征码，先获取telephoneId，mac，序列号，设置id
     * @param context
     * @return
     */
    public static String getDeviceInfo(Context context){

        String device = getTelephoneId(context);
        if(TextUtils.isEmpty(device)){
            //获取Android的序列号
            device = getSerivalNo();
        }
        if(TextUtils.isEmpty(device)){
            //获取Android的的mac
            device = getWifiMac(context);
        }
        return  device;
    }

    /**
     * 得到wifi mac地址
     * @param context
     * @return
     */
    private static String getWifiMac(Context context) {
        String device;WifiManager wifi=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        device= wifi.getConnectionInfo().getMacAddress();
        return device;
    }

    /**
     *
     * @param context
     * @return
     */
    private static String getTelephoneId(Context context) {
        TelephonyManager tm=(TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    /**
     *
     *获取机器的序列号
     * @return
     */
    private static String getSerivalNo() {
        String device=null;
        try{
            Class c= Class.forName("android.os.SystemProperties");
            Method method=c.getMethod("get",String.class,String.class);
           device=(String) method.invoke(c,"ro.serialno","unknow");
        }catch (Exception e){
        }
        return device;
    }

}
