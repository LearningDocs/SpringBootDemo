package com.keepgulp.springbootsocketio.common.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * @description: DateFormatThreadLocal
 * @author: guodongqing
 * @create: 2018-06-27 11:10
 **/
public class DateFormatThreadLocal extends ThreadLocal<DateFormat>{
    /**
     * 日期时间格式
     */
    private String pattern;

    /**
     * @param pattern 日期时间格式
     */
    public DateFormatThreadLocal(String pattern) {
        this.pattern = pattern;
    }

    @Override
    protected DateFormat initialValue() {
        return new SimpleDateFormat(pattern);
    }

}
