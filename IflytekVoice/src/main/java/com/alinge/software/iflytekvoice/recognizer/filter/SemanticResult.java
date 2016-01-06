package com.alinge.software.iflytekvoice.recognizer.filter;

import android.text.TextUtils;

import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class SemanticResult implements ResultParser {
    //获取到数据时的导语文字，可为空
    private String normalPrompt;
    //获取到数据时的导语播报内容，若字段不存在，则取值与normalPrompt相同
    private String normalPromptTts;
    //获取不到数据时的导语文字,可以为空,如当slots就返回一个url的时候
    private String noDataPrompt;
    //获取不到数据时的导语播报内容，若字段不存在，则取值与noDataPrompt相同
    private String noDataPromptTts;
    //参照后续不同服务的定义
    private String slots;

    public SemanticResult() {
    }

    public SemanticResult(String normalPrompt, String normalPromptTts, String noDataPrompt, String noDataPromptTts, String slots) {
        this.normalPrompt = normalPrompt;
        this.normalPromptTts = normalPromptTts;
        this.noDataPrompt = noDataPrompt;
        this.noDataPromptTts = noDataPromptTts;
        this.slots = slots;
    }

    public ISlots getSlots(String type) throws  JSONException{
        if(TextUtils.isEmpty(slots)){
            return null;
        }
        JSONObject obj=new JSONObject(slots);
        return  ISlotsFactory.createISlots(type,obj);
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.slots)) {
            slots = obj.getString(PropertyList.slots);
        }
        if (obj.has(PropertyList.noDataPrompt)) {
            noDataPrompt = obj.getString(PropertyList.noDataPrompt);
        }
        if (obj.has(PropertyList.noDataPromptTts)) {
            noDataPromptTts = obj.getString(PropertyList.noDataPromptTts);
        }
        if (obj.has(PropertyList.normalPrompt)) {
            normalPrompt = obj.getString(PropertyList.normalPrompt);
        }
        if (obj.has(PropertyList.normalPromptTts)) {
            normalPromptTts = obj.getString(PropertyList.normalPromptTts);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("slots");
        buffer.append(slots);
        return buffer.toString();
    }
}
