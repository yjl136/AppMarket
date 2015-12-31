package com.alinge.software.iflytekvoice.recognizer.filter.result;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class Semantic implements ResultParser {
    private String slots;

    public Semantic() {
    }
    public Semantic(String slots) {
        this.slots = slots;
    }

    public String getSlots() {
        return slots;
    }

    public void setSlots(String slots) {
        this.slots = slots;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.slots)) {
            slots = obj.getString(PropertyList.slots);
        }
    }
}
