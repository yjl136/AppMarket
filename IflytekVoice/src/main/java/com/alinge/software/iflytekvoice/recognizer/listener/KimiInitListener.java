package com.alinge.software.iflytekvoice.recognizer.listener;

import android.content.Context;

import com.alinge.software.iflytekvoice.utils.LogUtils;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;

/**
 * 作者： yejianlin
 * 日期：2015/12/30
 * 作用：
 */
public class KimiInitListener implements InitListener {
    private String tagName;

    public KimiInitListener(String tagName){
        this.tagName=tagName;
    }
    @Override
    public void onInit(int code) {
        if (code == ErrorCode.SUCCESS) {
            LogUtils.info(null, tagName+"  init success");
        } else {
            LogUtils.info(null, tagName+"   init fail");
        }
    }
}
