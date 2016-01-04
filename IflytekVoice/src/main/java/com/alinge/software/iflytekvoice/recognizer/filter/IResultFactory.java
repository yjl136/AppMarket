package com.alinge.software.iflytekvoice.recognizer.filter;

import com.alinge.software.iflytekvoice.recognizer.filter.weather.WeatherResultItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class IResultFactory {
    public  static IResullt createIResult(String type,JSONObject obj) throws JSONException{
        IResullt result=null;
        if(ServiceType.WEATHER.equals(type)){
            result=new WeatherResultItem();
        }
        result.fromJson(obj);
        return  result;
    }
}
