package com.alinge.software.iflytekvoice.recognizer.filter.utils;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者： yejianlin
 * 日期：2016/1/19
 * 作用：
 */
public class TimeHelper {
    private TimeHelper(){

    }

    /**
     * 得到今天的是第几小时
     * @return
     */
    public static int getHours(){
        Calendar calendar=Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
}
