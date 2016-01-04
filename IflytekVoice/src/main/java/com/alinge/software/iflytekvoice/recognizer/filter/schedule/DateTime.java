package com.alinge.software.iflytekvoice.recognizer.filter.schedule;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class DateTime implements ResultParser {
    private String dateOrig;
    private String type;
    private String time;
    private String timeOrig;
    private String date;

    public DateTime() {
    }

    public DateTime(String dateOrig, String type, String time, String timeOrig, String date) {
        this.dateOrig = dateOrig;
        this.type = type;
        this.time = time;
        this.timeOrig = timeOrig;
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getTimeOrig() {
        return timeOrig;
    }

    public void setTimeOrig(String timeOrig) {
        this.timeOrig = timeOrig;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.dateOrig)){
            dateOrig=obj.getString(PropertyList.dateOrig);
        }
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
        if(obj.has(PropertyList.time)){
            time=obj.getString(PropertyList.time);
        }
        if(obj.has(PropertyList.timeOrig)){
            timeOrig=obj.getString(PropertyList.timeOrig);
        }
        if(obj.has(PropertyList.date)){
            date=obj.getString(PropertyList.date);
        }
    }
}
