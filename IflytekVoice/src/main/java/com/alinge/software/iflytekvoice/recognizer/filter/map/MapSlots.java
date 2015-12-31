package com.alinge.software.iflytekvoice.recognizer.filter.map;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class MapSlots implements ResultParser {
    private String location;
    private String startLoc;
    private String endLoc;
    private String type;

    public MapSlots() {
    }

    public MapSlots(String location, String startLoc, String endLoc, String type) {
        this.location = location;
        this.startLoc = startLoc;
        this.endLoc = endLoc;
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartLoc() {
        return startLoc;
    }

    public void setStartLoc(String startLoc) {
        this.startLoc = startLoc;
    }

    public String getEndLoc() {
        return endLoc;
    }

    public void setEndLoc(String endLoc) {
        this.endLoc = endLoc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.endLoc)){
            endLoc=obj.getString(PropertyList.endLoc);
        }
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
        if(obj.has(PropertyList.startLoc)){
            startLoc=obj.getString(PropertyList.startLoc);
        }
        if(obj.has(PropertyList.location)){
            location=obj.getString(PropertyList.location);
        }
    }
}
