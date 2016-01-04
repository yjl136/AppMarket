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
public class DataTime implements ResultParser {
    private String date;
    private String type;
    private String dateOrig;

    public DataTime() {
    }

    public DataTime(String type, String dateOrig, String date) {
        this.type = type;
        this.dateOrig = dateOrig;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateOrig() {
        return dateOrig;
    }

    public void setDateOrig(String dateOrig) {
        this.dateOrig = dateOrig;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.dateOrig)){
            dateOrig=obj.getString(PropertyList.dateOrig);
        }
        if(obj.has(PropertyList.date)){
            date=obj.getString(PropertyList.date);
        }
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
    }
}
