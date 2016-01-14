package com.alinge.software.market.net.bean;

import com.alinge.software.market.net.ResultParse;
import com.alinge.software.market.net.utils.FieldUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/12
 * 作用：
 */
public class Info implements ResultParse{
    private String Result;

    public Info() {
    }

    public Info(String result) {
        Result = result;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(FieldUtils.Result)){
            Result=obj.getString(FieldUtils.Result);
        }
    }
}
