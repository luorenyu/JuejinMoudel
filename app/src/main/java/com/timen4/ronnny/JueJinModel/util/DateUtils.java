package com.timen4.ronnny.JueJinModel.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luore on 2016/8/21.
 */
public class DateUtils {

    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";








    /**
     * 获取当前时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    /**
     * 获取日期年份
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        SimpleDateFormat sf =new SimpleDateFormat(FORMAT_LONG);
        return sf.format(date).substring(0, 4);
    }


    /**
     * 功能描述：返回月
     *
     * @param date
     *            Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar  calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日
     *
     * @param date
     *            Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar  calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar  calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date
     *            日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 用于获取item创建时间距离当下时间的差值
     * @return 返回单位可以是 年 月 日 时 分
     */
    public static String getGapTimeFromNow(long itemTime){
        Date itemDate=new Date(itemTime);
        Date curDate=new Date();

        if(!getYear(curDate).equals(getYear(itemDate))){
            String curYear = getYear(curDate);
            String itemYear = getYear(itemDate);
            int i = Integer.parseInt(curYear) - Integer.parseInt(itemYear);
            return i+"年前";
        }

        if(getMonth(curDate)!=getMonth(itemDate)){
            return getMonth(curDate)-getMonth(itemDate)+"月前";
        }

        if(getDay(curDate)!=getDay(itemDate)){
            return getDay(curDate)-getDay(itemDate)+"天前";
        }

        if(getHour(curDate)!=getHour(itemDate)){
            return getHour(curDate)-getHour(itemDate)+"小时前";
        }

        if (getMinute(curDate)!=getMinute(itemDate)){
            return getMinute(curDate)-getMinute(itemDate)+"分钟前";
        }

        return "刚刚";
    }

}
