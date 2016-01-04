package com.alinge.software.iflytekvoice.recognizer.filter.website;

import com.alinge.software.iflytekvoice.recognizer.filter.ISlots;
import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class WebsiteSlot extends ISlots {
    private String name;
    private String url;

    public WebsiteSlot() {
    }

    public WebsiteSlot(String name, String url) {
        this.name = name;
        this.url = url;
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

    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.url)){
            url=obj.getString(PropertyList.url);
        }
        if(obj.has(PropertyList.name)){
            name=obj.getString(PropertyList.name);
        }
    }
}
