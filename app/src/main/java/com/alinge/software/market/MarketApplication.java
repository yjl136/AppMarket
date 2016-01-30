package com.alinge.software.market;

import android.app.Application;

import com.alinge.software.market.net.VolleyUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * 作者： yejianlin
 * 日期：2016/1/14
 * 作用：
 */
public class MarketApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        VolleyUtils.createVolleyUtils(this);

    }
}
