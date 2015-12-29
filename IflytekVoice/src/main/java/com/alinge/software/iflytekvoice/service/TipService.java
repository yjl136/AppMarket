package com.alinge.software.iflytekvoice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;

import com.alinge.software.iflytekvoice.service.helper.ShakeCheckHelper;
import com.alinge.software.iflytekvoice.utils.LogUtils;

/**
 * 作者： yejianlin
 * 日期：2015/12/28
 * 作用：
 */
public class TipService extends Service {
    private ShakeCheckHelper shakeHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.info(null, "TipService oncreate");
        initHelper();
    }

    private void initHelper() {
        shakeHelper = ShakeCheckHelper.getHelper();
        shakeHelper.setOnShakeListener(new ShakeCheckHelper.OnShakeListener() {
            @Override
            public void onShake() {
                LogUtils.info(null, "onshake");
                try {
                    Vibrator vibrator = ServiceManager.getVibrator(TipService.this);
                    vibrator.vibrate(200);
                } catch (Exception e) {
                    LogUtils.error(null,e.getMessage().toString());
                }

            }
        });
        shakeHelper.startCheck(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (shakeHelper != null) {
            shakeHelper.stopCheck();
        }
    }
}
