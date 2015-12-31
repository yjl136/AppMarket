package com.alinge.software.iflytekvoice.recognizer.filter.result;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class Result implements ResultParser {
    //返回识别的结果，0或者是4
    private String rc;
    //默认操作类型
    private String operation;
    //默认服务场景
    private String service;
    //识别到的文本
    private String rawText;
    //可选结果更多
    private String moreResults;
    public Result() {
    }

    public Result(String rc, String operation, String service, String rawText,String moreResults) {
        this.rc = rc;
        this.operation = operation;
        this.service = service;
        this.rawText = rawText;
        this.moreResults=moreResults;
    }

    public String getRc() {
        return rc;
    }

    public String getMoreResults() {
        return moreResults;
    }

    public void setMoreResults(String moreResults) {
        this.moreResults = moreResults;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
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
        if (obj.has(PropertyList.rawText)) {
            rawText = obj.getString(PropertyList.rawText);
        }
        if (obj.has(PropertyList.moreResults)) {
            moreResults = obj.getString(PropertyList.moreResults);
        }
    }

}
