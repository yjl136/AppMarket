package com.alinge.software.iflytekvoice.recognizer.filter;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class FilterResult {
    //返回识别的结果，0或者是4
    private String rc;
    //默认操作类型
    private String operation;
    //默认服务场景
    private String service;
    //识别到的文本
    private String rawText;
    //默认回答
    private String answer;
    //识别分析
    private String semantic;
    //可选结果更多
    private String moreResults;

    public FilterResult() {
    }

    public FilterResult(String rc, String operation, String service, String rawText, String answer, String semantic, String moreResults) {
        this.rc = rc;
        this.operation = operation;
        this.service = service;
        this.rawText = rawText;
        this.answer = answer;
        this.semantic = semantic;
        this.moreResults = moreResults;
    }

    public String getRc() {
        return rc;
    }

    public String getService() {
        return service;
    }

    public String getOperation() {
        return operation;
    }

    public String getAnswer() {
        return answer;
    }

    public String getRawText() {
        return rawText;
    }

    public String getSemantic() {
        return semantic;
    }

    public String getMoreResults() {
        return moreResults;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMoreResults(String moreResults) {
        this.moreResults = moreResults;
    }

    public void setSemantic(String semantic) {
        this.semantic = semantic;
    }

    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.rc)) {
            rc = obj.getString(PropertyList.rc);
        }
        if (obj.has(PropertyList.operation)) {
            operation = obj.getString(PropertyList.operation);
        }
        if (obj.has(PropertyList.service)) {
            service = obj.getString(PropertyList.service);
        }
        if (obj.has(PropertyList.semantic)) {
            semantic = obj.getString(PropertyList.semantic);
        }
        if (obj.has(PropertyList.answer)) {
            answer = obj.getString(PropertyList.answer);
        }
        if (obj.has(PropertyList.text)) {
            rawText = obj.getString(PropertyList.text);
        }
        if (obj.has(PropertyList.moreResults)) {
            moreResults = obj.getString(PropertyList.moreResults);
        }
    }

}
