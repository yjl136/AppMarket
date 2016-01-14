package com.alinge.software.market.net;

import android.content.Context;

import com.alinge.software.market.utils.LogUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * 作者： yejianlin
 * 日期：2016/1/12
 * 作用：创建volleyutils工具类
 */
public class VolleyUtils {
    private static VolleyUtils volleyUtils;
    private  Context context;
    private static RequestQueue queue;
    private VolleyUtils(Context context){
        this.context=context;
        queue= Volley.newRequestQueue(context);
    }
    public static VolleyUtils createVolleyUtils(Context context){
        if(volleyUtils==null){
            synchronized (VolleyUtils.class){
                if(volleyUtils==null){
                    volleyUtils=new VolleyUtils(context);
                }
            }
        }
        return  volleyUtils;
    }

    /**
     *
     * @param url 请求地址url
     * @param method  请求方式
     * @param requestJson 请求json串
     */
    public static void requestByJson(String url,int method,JSONObject requestJson){
        JsonObjectRequest request=new JsonObjectRequest(method, url,requestJson, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtils.info(null,"result:"+jsonObject.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError!=null){
                    LogUtils.info(null,"result error:"+volleyError.getMessage());
                }

            }
        });
        queue.add(request);
    }
}
