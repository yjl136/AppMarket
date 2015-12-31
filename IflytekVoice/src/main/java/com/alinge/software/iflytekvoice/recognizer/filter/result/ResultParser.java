package com.alinge.software.iflytekvoice.recognizer.filter.result;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public interface ResultParser {
    public void fromJson(JSONObject obj)throws JSONException;
}
