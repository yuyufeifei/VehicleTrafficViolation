package com.wxcm.vtvq.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GZH
 * @date 2020-12-22
 */
public class DateUtil {

    private static final Logger logger = LogManager.getLogger(DateUtil.class);

    /**
     * Date格式日期转成String
     * @param date  Date日期
     * @param format    格式
     */
    public static String format(Date date, String format) throws Exception {
        String result = "";
        try {
            if (date != null) {
                DateFormat df = new SimpleDateFormat(format);
                result = df.format(date);
            }
        } catch (Exception e) {
            throw new Exception("时间转换错误");
        }
        return result;
    }

    /**
     * String格式日期转成Date
     * @param dateStr   string日期
     * @param format    格式
     */
    public static Date parseDate(String dateStr, String format) throws Exception {
        Date date;
        try {
            DateFormat df = new SimpleDateFormat(format);
            date = df.parse(dateStr);
        } catch (Exception e) {
            throw new Exception("时间解析错误");
        }
        return date;
    }

    /**
     * 判断日期是否在今天零点之前
     * @param date  需判断的日期
     * @return  true or false
     */
    public static boolean beforeTodayZero(Date date) throws Exception {
        boolean result;
        try {
            Date now = new Date();
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date zero = df.parse(df.format(now));
            result = date.before(zero);
        } catch (ParseException e) {
            throw new Exception("时间比较发生错误");
        }
        return result;
    }

}
