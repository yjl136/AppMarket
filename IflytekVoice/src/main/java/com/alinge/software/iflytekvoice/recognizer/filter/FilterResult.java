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
public class FilterResult implements ResultParser{
    //应答码
    private int rc;
    //服务的细分操作编码，各业务服务自定义
    private String operation;
    //服务的全局唯一名称
    private String service;
    //用户的输入，可能和请求中的原始text不完全一致，因服务器可能会对text进行语言纠错
    private String rawText;
    //对结果内容的最简化文本/图片描述，各服务自定义
    private String answer;
    //语义结构化表示，各服务自定义
    private String semantic;
    //在存在多个候选结果时，用于提供更多的结果描述
    private String moreResults;
    //错误信息
    private String error;
    //上下文信息，客户端需要将该字段结果传给下一次请求的history字段
    private String history;
    //数据结构化表示，各服务自定义
    private String data;
    //该字段提供了结果内容的HTML5页面，客户端可以无需解析处理直接展现
    private String webPage;
    //结果内容的关联信息，作为用户后续交互的引导展现
    private String tips;

    public FilterResult() {
    }
    public FilterResult(int rc, String operation, String service, String rawText, String semantic, String answer, String error, String moreResults, String history, String data, String tips, String webPage) {
        this.rc = rc;
        this.operation = operation;
        this.service = service;
        this.rawText = rawText;
        this.semantic = semantic;
        this.answer = answer;
        this.error = error;
        this.moreResults = moreResults;
        this.history = history;
        this.data = data;
        this.tips = tips;
        this.webPage = webPage;
    }

    public int getRc() {
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

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
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
    public AnswerResult getAnswerResult() throws  JSONException{
        AnswerResult answerResult=new AnswerResult();
        if(!TextUtils.isEmpty(answer)){
            JSONObject obj=new JSONObject(answer);
            answerResult.fromJson(obj);
        }
        return answerResult;
    }
    public ErrorResult getErrorResult()throws  JSONException{
        ErrorResult errorResult=new ErrorResult();
        if(!TextUtils.isEmpty(error)){
            JSONObject obj=new JSONObject(error);
            errorResult.fromJson(obj);
        }
        return errorResult;
    }
    public DataResult getDataResult()throws  JSONException{
        DataResult dataResult=new DataResult();
        if(!TextUtils.isEmpty(data)){
            JSONObject obj=new JSONObject(data);
            dataResult.fromJson(obj);
        }
        return dataResult;
    }
    public WebPageResult getWebPageResult()throws  JSONException{
        WebPageResult webPageResult=new WebPageResult();
        if(!TextUtils.isEmpty(webPage)){
            JSONObject obj=new JSONObject(webPage);
            webPageResult.fromJson(obj);
        }
        return webPageResult;
    }
    public SemanticResult getSemanticResult()throws  JSONException{
        SemanticResult semanticResult=new SemanticResult();
        if(!TextUtils.isEmpty(semantic)){
            JSONObject obj=new JSONObject(semantic);
            semanticResult.fromJson(obj);
        }
        return semanticResult;
    }
    public void fromJson(JSONObject obj) throws JSONException {
        if (obj.has(PropertyList.rc)) {
            rc = obj.getInt(PropertyList.rc);
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
        if (obj.has(PropertyList.error)) {
            error = obj.getString(PropertyList.error);
        }
        if (obj.has(PropertyList.history)) {
            history = obj.getString(PropertyList.history);
        }
        if (obj.has(PropertyList.tips)) {
            tips = obj.getString(PropertyList.tips);
        }
        if (obj.has(PropertyList.webPage)) {
            webPage = obj.getString(PropertyList.webPage);
        }
        if (obj.has(PropertyList.data)) {
            data = obj.getString(PropertyList.data);
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer=new StringBuffer();
        buffer.append("rc:"+rc);
        buffer.append("   rawText:"+rawText);
        buffer.append("   operation:"+operation);
        buffer.append("   service:"+service);
        buffer.append("   data:"+data);
        buffer.append("   webPage:"+webPage);
        buffer.append("   answer:"+answer);
        buffer.append("   semantic:"+semantic);
        buffer.append("   moreResults:"+moreResults);
        return buffer.toString();

    }
}
