package com.alinge.software.iflytekvoice.recognizer.filter.map;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class Loc implements ResultParser {
    private String keyword;
    private String poi;
    private String city;
    private String type;

    public Loc() {
    }

    public Loc(String keyword, String poi, String city, String type) {
        this.keyword = keyword;
        this.poi = poi;
        this.city = city;
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPoi() {
        return poi;
    }

    public void setPoi(String poi) {
        this.poi = poi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.poi)){
            poi=obj.getString(PropertyList.poi);
        }
        if(obj.has(PropertyList.city)){
            city=obj.getString(PropertyList.city);
        }
        if(obj.has(PropertyList.keyword)){
            keyword=obj.getString(PropertyList.keyword);
        }
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
    }
}
