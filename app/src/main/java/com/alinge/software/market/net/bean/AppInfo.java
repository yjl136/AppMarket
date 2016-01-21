package com.alinge.software.market.net.bean;

/**
 * 作者： yejianlin
 * 日期：2016/1/21
 * 作用：
 */
public class AppInfo {
    private String name;
    private String url;

    public AppInfo() {
    }

    public AppInfo(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
