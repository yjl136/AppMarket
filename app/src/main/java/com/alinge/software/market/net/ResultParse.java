package com.alinge.software.market.net;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/12
 * 作用：
 */
public interface ResultParse {
    public void fromJson(JSONObject obj) throws JSONException;
}
