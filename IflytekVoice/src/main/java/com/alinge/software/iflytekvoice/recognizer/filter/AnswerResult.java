package com.alinge.software.iflytekvoice.recognizer.filter;

import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class AnswerResult implements ResultParser {
    private  String type;
    private String text;
    private String imgUrl;
    private String imgDesc;
    private String urlDesc;
    private String url;
    public AnswerResult() {
    }

    public AnswerResult(String type, String text, String imgUrl, String imgDesc, String urlDesc, String url) {
        this.type = type;
        this.text = text;
        this.imgUrl = imgUrl;
        this.imgDesc = imgDesc;
        this.urlDesc = urlDesc;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getUrlDesc() {
        return urlDesc;
    }

    public void setUrlDesc(String urlDesc) {
        this.urlDesc = urlDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.text)){
            text=obj.getString(PropertyList.text);
        }
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
        if(obj.has(PropertyList.urlDesc)){
            urlDesc=obj.getString(PropertyList.urlDesc);
        }
        if(obj.has(PropertyList.imgDesc)){
            imgDesc=obj.getString(PropertyList.imgDesc);
        }
        if(obj.has(PropertyList.url)){
            url=obj.getString(PropertyList.url);
        }
        if(obj.has(PropertyList.imgUrl)){
            imgUrl=obj.getString(PropertyList.imgUrl);
        }
    }
}
