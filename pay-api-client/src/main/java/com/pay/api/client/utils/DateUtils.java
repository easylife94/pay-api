package com.pay.api.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期帮助类
 *
 * @author chenwei
 * @date 2019/1/30 11:21
 */
public class DateUtils {

    /**
     * 格式：yyyy-MM-dd hh:mm:ss
     */
    public static final String FORMAT_YYYYMMDDHHMMSS_1 = "yyyy-MM-dd hh:mm:ss";

    /**
     * 格式：yyyy/MM/dd hh:mm:ss
     */
    public static final String FORMAT_YYYYMMDDHHMMSS_2 = "yyyy/MM/dd hh:mm:ss";


    /**
     * 当前时间默认格式字符串
     *
     * @return 返回当前时间字符串
     * @see #time2Str(Date)
     */
    public static String nowTime2Str() {
        return time2Str(new Date());
    }

    /**
     * 返回指定日期字符串
     *
     * @param date 指定时间
     * @return 时间字字符串
     * @see #time2StrFormat(Date, String)
     * @see #FORMAT_YYYYMMDDHHMMSS_1
     */
    public static String time2Str(Date date) {
        return time2StrFormat(date, FORMAT_YYYYMMDDHHMMSS_1);
    }

    /**
     * 返回指定时间字指定格式字符串
     *
     * @param date   指定时间
     * @param format 指定格式
     * @return 时间字符串
     */
    public static String time2StrFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 字符串转为date
     *
     * @param dateStr 日期字符串
     * @param format  日期格式
     * @return
     * @throws ParseException
     */
    public static Date parse(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr);
    }

}
