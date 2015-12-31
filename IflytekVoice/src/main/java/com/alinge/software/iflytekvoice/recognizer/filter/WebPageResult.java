package com.alinge.software.iflytekvoice.recognizer.filter;

import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class WebPageResult implements ResultParser {
    private String url;
    private String headerTts;
    private String header;

    public WebPageResult() {
    }

    public WebPageResult(String headerTts, String header, String url) {
        this.headerTts = headerTts;
        this.header = header;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeaderTts() {
        return headerTts;
    }

    public void setHeaderTts(String headerTts) {
        this.headerTts = headerTts;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.header)) {
            header = obj.getString(PropertyList.header);
        }
        if (obj.has(PropertyList.headerTts)) {
            headerTts = obj.getString(PropertyList.headerTts);
        }
        if (obj.has(PropertyList.url)) {
            url = obj.getString(PropertyList.url);
        }
    }
}
