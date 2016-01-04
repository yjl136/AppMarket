package com.alinge.software.iflytekvoice.recognizer.filter;

import android.text.TextUtils;

import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class DataResult implements ResultParser {
    private String header;
    private String headerTts;
    private String result;

    public DataResult() {
    }

    public DataResult(String result, String header, String headerTts) {
        this.result = result;
        this.header = header;
        this.headerTts = headerTts;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeaderTts() {
        return headerTts;
    }

    public void setHeaderTts(String headerTts) {
        this.headerTts = headerTts;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<IResullt> getResults(String type) throws JSONException {
        List<IResullt> results = new ArrayList<IResullt>();
        if (!TextUtils.isEmpty(result)) {
            JSONArray array = new JSONArray(result);
            for (int index = 0; index < array.length(); index++) {
                JSONObject obj = array.getJSONObject(index);
                IResullt result = IResultFactory.createIResult(type, obj);
                results.add(result);
            }
        }
        return results;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.result)) {
            result = obj.getString(PropertyList.result);
        }
        if (obj.has(PropertyList.header)) {
            header = obj.getString(PropertyList.header);
        }
        if (obj.has(PropertyList.headerTts)) {
            headerTts = obj.getString(PropertyList.headerTts);
        }
    }
}
