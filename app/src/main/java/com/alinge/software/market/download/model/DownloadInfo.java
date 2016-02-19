package com.alinge.software.market.download.model;

import org.litepal.crud.DataSupport;

/**
 * 作者： yejianlin
 * 日期：2016/2/4
 * 作用：
 */
public class DownloadInfo  extends DataSupport{

    private int appId;
    private float progress;
    private int state;
    private String appName;
    private String packageName;
    private String url;
    public DownloadInfo() {
    }
    public DownloadInfo(int appId, float progress, int state, String appName, String packageName, String url) {
        this.appId = appId;
        this.progress = progress;
        this.state = state;
        this.appName = appName;
        this.packageName = packageName;
        this.url = url;
    }



    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
