package com.alinge.software.iflytekvoice.application;

import android.app.Application;

import com.alinge.software.iflytekvoice.utils.AppUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * 作者： yejianlin
 * 日期：2015/12/25
 * 作用：
 */
public class VoiceApplication  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "="+AppUtils.APPID);
    }
}
