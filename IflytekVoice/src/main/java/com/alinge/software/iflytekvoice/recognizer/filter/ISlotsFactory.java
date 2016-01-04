package com.alinge.software.iflytekvoice.recognizer.filter;

import com.alinge.software.iflytekvoice.recognizer.filter.app.AppSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.map.MapSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.message.MessageSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.schedule.ScheduleSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.telephone.TelephoneSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.translation.TranslationSlots;
import com.alinge.software.iflytekvoice.recognizer.filter.websearch.WebSearchSlot;
import com.alinge.software.iflytekvoice.recognizer.filter.website.WebsiteSlot;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： yejianlin
 * 日期：2016/1/4
 * 作用：
 */
public class ISlotsFactory {
    //创建Islot
    public  static ISlots createISlots(String type,JSONObject obj) throws JSONException{
        ISlots slots=null;
        if(ServiceType.APP.equals(type)){
            //应用
            slots=new AppSlots();
        }else if (ServiceType.MESSAGE.equals(type)){
            //电话
            slots=new MessageSlots();
        }else if(ServiceType.TRANSLATION.equals(type)){
            //翻译
            slots=new TranslationSlots();
        }else if(ServiceType.TELEPHONE.equals(type)){
            //打电话
            slots=new TelephoneSlots();
        }else if(ServiceType.SCHEDULE.equals(type)){
            //提醒
            slots=new ScheduleSlots();
        }else if(ServiceType.MAP.equals(type)){
            //地图
            slots=new MapSlots();
        }else if(ServiceType.WEBSEARCH.equals(type)){
            //网页搜
            slots=new WebSearchSlot();
        }else if(ServiceType.WEBSITE.equals(type)){
            //打开网站
            slots=new WebsiteSlot();
        }
        slots.fromJson(obj);
        return slots;

    }
}
