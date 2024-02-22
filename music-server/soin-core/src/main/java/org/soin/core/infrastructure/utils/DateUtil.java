package org.soin.core.infrastructure.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期处理工具
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:12
 **/
public class DateUtil {

    public static final String DATE_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YEAR = "yyyy";
    public static final String DATE_YEAR_MONTH = "yyyy-MM";
    public static final String DATE_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DATE_YEAR_MONTH_DAY_H_M = "yyyy-MM-dd HH:mm";


    /**
     * 将时间字符串格式化为日期对象
     *
     * @param value      时间字符串
     * @param convention 公约
     * @return 转换后的时间
     */
    public static Date build(String value, String convention) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(convention);
            return format.parse(value);
        } catch (ParseException parseException) {
            RunTimeTool.printError(parseException);
            throw new IllegalArgumentException("系统繁忙");
        }
    }

    /**
     * 将日期对象格式化为时间字符串
     *
     * @param date       转换后的日期
     * @param convention 公约
     * @return 时间字符串
     */
    public static String reverseBuild(Date date, String convention) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(convention);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化时间字符
     *
     * @param value      编译值
     * @param convention 公约
     * @return 格式化后的时间字符
     */
    public static String format(String value, String convention) {
        Date build = build(value, convention);
        return reverseBuild(build, convention);
    }


}
