package com.alinge.software.iflytekvoice.recognizer.filter.translation;

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
public class TranslationSlots extends ISlots {
    private String  content;
    private String  source;
    private String  target;

    public TranslationSlots() {
    }

    public TranslationSlots(String content, String source, String target) {
        this.content = content;
        this.source = source;
        this.target = target;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public ISlots getSlots() {
        return this;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.content)) {
            content = obj.getString(PropertyList.content);
        }
        if (obj.has(PropertyList.source)) {
            source = obj.getString(PropertyList.source);
        }
        if (obj.has(PropertyList.target)) {
            target = obj.getString(PropertyList.target);
        }
    }
}
