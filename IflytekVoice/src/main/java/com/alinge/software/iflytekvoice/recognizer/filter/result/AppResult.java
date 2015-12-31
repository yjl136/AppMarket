package com.alinge.software.iflytekvoice.recognizer.filter.result;

import android.graphics.RectF;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class AppResult extends Result {
    private String semantic;

    public AppResult() {
        super();
    }

    public AppResult(String rc, String operation, String service, String rawText, String semantic,String moreResults) {
        super(rc, operation, service, rawText,moreResults);
        this.semantic = semantic;
    }

    public String getSemantic() {
        return semantic;
    }

    public void setSemantic(String semantic) {
        this.semantic = semantic;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        super.fromJson(obj);
        if (obj.has(PropertyList.semantic)) {
            semantic = obj.getString(PropertyList.semantic);
        }
    }
}
