package com.alinge.software.iflytekvoice.recognizer.filter.weather;

import android.text.TextUtils;

import com.alinge.software.iflytekvoice.recognizer.filter.ISlots;
import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class WeatherSlots extends ISlots {
    private String location;
    private String datetime;
    private String sightspot;

    public WeatherSlots() {
    }

    public WeatherSlots(String datetime, String sightspot, String location) {
        this.datetime = datetime;
        this.sightspot = sightspot;
        this.location = location;
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

    public String getSightspot() {
        return sightspot;
    }

    public void setSightspot(String sightspot) {
        this.sightspot = sightspot;
    }

    public  DataTime getDateTimes() throws  JSONException{
        DataTime dt=new DataTime();
        if(!TextUtils.isEmpty(datetime)){
            JSONObject obj=new JSONObject(datetime);
            dt.fromJson(obj);
        }
        return dt;
    }
    public Location getLocations() throws  JSONException{
        Location loc=new Location();
        if(!TextUtils.isEmpty(location)){
            JSONObject obj=new JSONObject(location);
            loc.fromJson(obj);
        }
        return  loc;
    }
    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
    if(obj.has(PropertyList.location)){
        location=obj.getString(PropertyList.location);
    }
    if(obj.has(PropertyList.datetime)){
        datetime=obj.getString(PropertyList.datetime);
    }
    if(obj.has(PropertyList.sightspot)){
        sightspot=obj.getString(PropertyList.sightspot);
    }
    }
}
