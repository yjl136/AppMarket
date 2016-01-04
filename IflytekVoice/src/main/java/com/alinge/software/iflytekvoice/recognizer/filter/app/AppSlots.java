package com.alinge.software.iflytekvoice.recognizer.filter.app;

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
public class AppSlots  extends ISlots{
    private String  name;
    private String  category;
    private String  keyword;
    private String  price;

    public AppSlots() {
    }

    public AppSlots(String name, String category, String keyword, String price) {
        this.name = name;
        this.category = category;
        this.keyword = keyword;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.price)){
            price=obj.getString(PropertyList.price);
        }
        if(obj.has(PropertyList.keyword)){
            keyword=obj.getString(PropertyList.keyword);
        }
        if(obj.has(PropertyList.name)){
            name=obj.getString(PropertyList.name);
        }
        if(obj.has(PropertyList.category)){
            category=obj.getString(PropertyList.category);
        }
    }
}
