package com.wxcm.vtvi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author GZH
 */
public class DateUtil {


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

}
