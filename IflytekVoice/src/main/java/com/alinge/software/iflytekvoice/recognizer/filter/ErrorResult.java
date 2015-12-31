package com.alinge.software.iflytekvoice.recognizer.filter;

import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class ErrorResult implements ResultParser {
    private String code;
    private String message;

    public ErrorResult() {
    }

    public ErrorResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.code)) {
            code = obj.getString(PropertyList.code);
        }
        if (obj.has(PropertyList.message)) {
            message = obj.getString(PropertyList.message);
        }
    }
}
