package com.alinge.software.iflytekvoice.recognizer.filter.result;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class Answer  implements ResultParser{
    private  String type;
    private String text;

    public Answer() {
    }

    public Answer(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if(obj.has(PropertyList.text)){
            text=obj.getString(PropertyList.text);
        }
        if(obj.has(PropertyList.type)){
            type=obj.getString(PropertyList.type);
        }
    }
}
