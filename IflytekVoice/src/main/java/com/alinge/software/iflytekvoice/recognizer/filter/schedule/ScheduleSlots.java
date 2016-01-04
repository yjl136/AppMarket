package com.alinge.software.iflytekvoice.recognizer.filter.schedule;

import android.text.TextUtils;

import com.alinge.software.iflytekvoice.recognizer.filter.ISlots;
import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class ScheduleSlots extends ISlots {
    private String name;
    private String datetime;
    private String repeat;
    private String content;
    private String location;

    public ScheduleSlots() {
    }

    public ScheduleSlots(String name, String datetime, String repeat, String content, String location) {
        this.name = name;
        this.datetime = datetime;
        this.repeat = repeat;
        this.content = content;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    public  DateTime getDataTimes()throws  JSONException{
        DateTime dt=new DateTime();
        if(!TextUtils.isEmpty(datetime)){
            JSONObject obj=new JSONObject();
            dt.fromJson(obj);
        }
        return dt;
    }
    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.datetime)) {
            datetime = obj.getString(PropertyList.datetime);
        }
        if (obj.has(PropertyList.location)) {
            location = obj.getString(PropertyList.location);
        }
        if (obj.has(PropertyList.content)) {
            content = obj.getString(PropertyList.content);
        }
        if (obj.has(PropertyList.name)) {
            name = obj.getString(PropertyList.name);
        }
        if (obj.has(PropertyList.repeat)) {
            repeat = obj.getString(PropertyList.repeat);
        }
    }
}
