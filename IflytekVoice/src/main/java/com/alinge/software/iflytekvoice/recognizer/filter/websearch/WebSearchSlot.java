package com.alinge.software.iflytekvoice.recognizer.filter.websearch;

import com.alinge.software.iflytekvoice.recognizer.filter.ISlots;
import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class WebSearchSlot extends ISlots {
    private  String channel;
    private  String keywords;

    public WebSearchSlot() {
    }

    public WebSearchSlot(String keywords, String channel) {
        this.keywords = keywords;
        this.channel = channel;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.keywords)){
            keywords=obj.getString(PropertyList.keywords);
        }
        if(obj.has(PropertyList.channel)){
            channel=obj.getString(PropertyList.channel);
        }
    }
}
