package com.alinge.software.iflytekvoice.recognizer.filter.message;

import com.alinge.software.iflytekvoice.recognizer.filter.PropertyList;
import com.alinge.software.iflytekvoice.recognizer.filter.result.ResultParser;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2015/12/31
 * 作用：
 */
public class MessageSlots implements ResultParser {
    private String location;
    private String name;
    private String code;
    private String category;
    private String teleOperator;
    private String headNum;
    private String tailNum;
    private String content;
    private String messageType;
    private String contentType;
    private String nameType;

    public MessageSlots() {
    }

    public MessageSlots(String location, String name, String code, String category, String teleOperator, String tailNum, String headNum, String content, String messageType, String contentType, String nameType) {
        this.location = location;
        this.name = name;
        this.code = code;
        this.category = category;
        this.teleOperator = teleOperator;
        this.tailNum = tailNum;
        this.headNum = headNum;
        this.content = content;
        this.messageType = messageType;
        this.contentType = contentType;
        this.nameType = nameType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTeleOperator() {
        return teleOperator;
    }

    public void setTeleOperator(String teleOperator) {
        this.teleOperator = teleOperator;
    }

    public String getHeadNum() {
        return headNum;
    }

    public void setHeadNum(String headNum) {
        this.headNum = headNum;
    }

    public String getTailNum() {
        return tailNum;
    }

    public void setTailNum(String tailNum) {
        this.tailNum = tailNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.tailNum)) {
            tailNum = obj.getString(PropertyList.tailNum);
        }
        if (obj.has(PropertyList.headNum)) {
            headNum = obj.getString(PropertyList.headNum);
        }
        if (obj.has(PropertyList.location)) {
            location = obj.getString(PropertyList.location);
        }
        if (obj.has(PropertyList.name)) {
            name = obj.getString(PropertyList.name);
        }
        if (obj.has(PropertyList.teleOperator)) {
            teleOperator = obj.getString(PropertyList.teleOperator);
        }
        if (obj.has(PropertyList.category)) {
            category = obj.getString(PropertyList.category);
        }
        if (obj.has(PropertyList.code)) {
            code = obj.getString(PropertyList.code);
        }
        if (obj.has(PropertyList.messageType)) {
            messageType = obj.getString(PropertyList.messageType);
        }
        if (obj.has(PropertyList.nameType)) {
            nameType = obj.getString(PropertyList.nameType);
        }
        if (obj.has(PropertyList.contentType)) {
            contentType = obj.getString(PropertyList.contentType);
        }
        if (obj.has(PropertyList.content)) {
            content = obj.getString(PropertyList.content);
        }
    }
}
