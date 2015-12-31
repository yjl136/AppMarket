package com.alinge.software.iflytekvoice.recognizer.filter.result;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class ChatResult extends Result {
    private String answer;

    public ChatResult() {
        super();
    }
    public ChatResult(String rc, String operation, String service, String rawText, String answer,String moreResults) {
        super(rc, operation, service, rawText,moreResults);
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        super.fromJson(obj);
        if (obj.has(PropertyList.answer)) {
            answer = obj.getString(PropertyList.answer);
        }
    }
}
