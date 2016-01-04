package com.alinge.software.iflytekvoice.recognizer.filter.weather;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class Location implements ResultParser {
    private String cityAddr;
    private String city;
    private String type;

    public Location() {
    }

    public Location(String cityAddr, String city, String type) {
        this.cityAddr = cityAddr;
        this.city = city;
        this.type = type;
    }

    public String getCityAddr() {
        return cityAddr;
    }

    public void setCityAddr(String cityAddr) {
        this.cityAddr = cityAddr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
        if(obj.has(PropertyList.cityAddr)){
            cityAddr=obj.getString(PropertyList.cityAddr);
        }
        if(obj.has(PropertyList.city)){
            city=obj.getString(PropertyList.city);
        }
    }
}
