package com.alinge.software.market.net;
import android.content.Context;

import com.alinge.software.market.R;
import com.alinge.software.market.fragment.base.VolleyResponseFragment;
import com.alinge.software.market.net.cache.BitMapCache;
import com.alinge.software.market.net.utils.UrlUtils;
import com.alinge.software.market.utils.LogUtils;
import com.alinge.software.market.view.AsycImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
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
     * @param method  请求方式 0为get 1为post
     * @param requestJson 请求json串
     */
    public static void request(String url,int method,JSONObject requestJson){
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

    /**
     * get请求
     * @param url
     * @param params
     * @param response
     */
    public static  void requestGet(String url,Map<String,String> params, final VolleyResponseFragment response){
        String requestUrl=getUrl(url,params);
        LogUtils.info(null,"requestUrl:"+requestUrl);
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject object) {
                LogUtils.info(null,"result:"+object.toString());
                response.onResponse(object);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                response.onErrorResponse(volleyError);
                if(volleyError!=null){
                    LogUtils.info(null,"result error:"+volleyError.getMessage());
                }
            }
        });
        queue.add(request);
    }

    /**
     *
     * @param url 不包含host的url
     * @param params get请求参数
     * @return 拼接后的url
     */
    public static String getUrl(String url,Map<String,String> params){
        StringBuffer buffer=new StringBuffer(UrlUtils.HOST);
        buffer.append(url);
        if(params!=null && params.size()>0){
            buffer.append("?");
            for(String key:params.keySet()){
                buffer.append(key);
                buffer.append("=");
                buffer.append(params.get(key));
                buffer.append("&");
            }
            int lastIndex=buffer.lastIndexOf("&");
            buffer.deleteCharAt(lastIndex);
        }
        return buffer.toString();
    }

    /**
     * 异步加载显示图片
     * @param imageView 控件
     * @param url 图片地址
     */
    public static void display(AsycImageView imageView,String url){
        BitMapCache cache=BitMapCache.getInstance();
        ImageLoader loader=new ImageLoader(queue,cache);
        imageView.setImageUrl(url, loader);
    }

    /**
     *
     * @param imageView 显示图片的控件
     * @param url  图片地址
     * @param errorImage 加载失败是显示的图片
     * @param defaultImage 加载中显示的图片
     */
    public static void display(AsycImageView imageView,String url,int errorImage,int defaultImage){
        if(errorImage != 0){
            imageView.setErrorImageResId(errorImage);
        }
        if(defaultImage != 0){
            imageView.setDefaultImageResId(R.mipmap.icon_empty);
        }
        display(imageView,url);
    }
}
