package com.alinge.software.iflytekvoice.recognizer.filter.telephone;

import com.alinge.software.iflytekvoice.recognizer.filter.ISlots;
import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class TelephoneSlots extends ISlots {
    private String name;
    private String code;
    private String category;
    private String teleOperator;
    private String location;
    private String headNum;
    private String tailNum;

    public TelephoneSlots() {
    }

    public TelephoneSlots(String name, String code, String category, String teleOperator, String location, String headNum, String tailNum) {
        this.name = name;
        this.code = code;
        this.category = category;
        this.teleOperator = teleOperator;
        this.location = location;
        this.headNum = headNum;
        this.tailNum = tailNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeleOperator() {
        return teleOperator;
    }

    public void setTeleOperator(String teleOperator) {
        this.teleOperator = teleOperator;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeadNum() {
        return headNum;
    }

    public void setHeadNum(String headNum) {
        this.headNum = headNum;
    }

    public String getTailNum() {
        return tailNum;
    }

    public void setTailNum(String tailNum) {
        this.tailNum = tailNum;
    }

    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.tailNum)) {
            tailNum = obj.getString(PropertyList.tailNum);
        }
        if (obj.has(PropertyList.headNum)) {
            headNum = obj.getString(PropertyList.headNum);
        }
        if (obj.has(PropertyList.location)) {
            location = obj.getString(PropertyList.location);
        }
        if (obj.has(PropertyList.name)) {
            name = obj.getString(PropertyList.name);
        }
        if (obj.has(PropertyList.teleOperator)) {
            teleOperator = obj.getString(PropertyList.teleOperator);
        }
        if (obj.has(PropertyList.category)) {
            category = obj.getString(PropertyList.category);
        }
        if (obj.has(PropertyList.code)) {
            code = obj.getString(PropertyList.code);
        }
    }
}
