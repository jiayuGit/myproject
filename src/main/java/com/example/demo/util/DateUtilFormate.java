package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author dengjy
 * @version 1.0
 * @date Created in 2020年04月23日 13:12
 * @since 1.0
 */
public class DateUtilFormate {
    private static final Logger logger = LoggerFactory.getLogger(DateUtilFormate.class);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
    public static final String DATEFORMAT_1 = "yyyyMMddHHmmss";
    public static final String DATEFORMAT_2 = "MM-dd HH:mm";
    public static final String DATEFORMAT_3 = "yyyyMMdd";
    public static final String DATEFORMAT_4 = "yyyy-MM-dd";
    public static final String DATEFORMAT_5 = "yyyy-MM-dd HH:mm";
    public static final String DATEFORMAT_6 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATEFORMAT_7 = "yyyyMM";
    public static final String DATEFORMAT_8 = "yyyy年MM月dd日";
    public static final String DATEFORMAT_10 = "yyyy-MM";
    public static final String DATEFORMAT_9 = "yyyy年M月";
    public static final String DATEFORMAT_01 = "yyyy.MM.dd";

    private DateUtilFormate() {
    }

    public static Date getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date date = null;

        try {
            date = formatter.parse(dateString);
        } catch (ParseException var5) {
            logger.error("获取现在时间", var5);
        }

        return date;
    }

    public static Date getAddMinutes(Date date, int minute) {
        Date afterDate = new Date(date.getTime() + (long)(minute * 60 * 1000));
        return afterDate;
    }

    public static String formatDate(Date date) {
        return formatDateToString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatDateToString(Date time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    public static Date formatStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException var4) {
            logger.error("", var4);
        }

        return date;
    }

    public static Date formatDateStringToDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException var4) {
            logger.error("", var4);
        }

        return date;
    }

    public static Date formatStringToDate(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = sdf.parse(time);
        } catch (ParseException var5) {
            logger.error("", var5);
        }

        return date;
    }

    public static String formatChineseDate(String date) {
        Date d = formatStringToDate(date, "yyyy-MM-dd");
        return d == null ? date : formatDateToString(d, "yyyy年MM月dd日");
    }

    public static Long[] getAllMonths(Date start, Date end) {
        List<Long> months = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(start);
        c2.setTime(end);
        c2.add(2, 1);

        while(c1.compareTo(c2) < 0) {
            Date ss = c1.getTime();
            Long str = Long.valueOf(sdf.format(ss));
            months.add(str);
            c1.add(2, 1);
        }

        Long[] str = new Long[months.size()];
        int i = 0;

        for(int j = months.size(); i < j; ++i) {
            str[i] = (Long)months.get(i);
        }

        return str;
    }

    public static String formatDuring(long mss) {
        long days = mss / 86400000L;
        long hours = mss % 86400000L / 3600000L;
        long minutes = mss % 3600000L / 60000L;
        long seconds = mss % 60000L / 1000L;
        return days + " 天 " + hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒 ";
    }

    public static Date setHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, hours);
        return cal.getTime();
    }

    public static Date setTime(Date date, int hours, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, hours);
        cal.set(12, minute);
        cal.set(13, second);
        return cal.getTime();
    }

    public static Date addHours(int hours) {
        return addHours(new Date(), hours);
    }

    public static Date addHours(Date date, int hours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(11, hours);
        return cal.getTime();
    }

    public static int getHourOfDay() {
        return getHourOfDay(new Date());
    }

    public static int getHourOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(11);
    }

    public static Date getTomorrowDate() {
        return addDate(new Date(), 1);
    }

    public static Date addDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(5, days);
        return cal.getTime();
    }

    public static Date addMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, months);
        return cal.getTime();
    }

    public static Date addYear(Date date, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(1, years);
        return cal.getTime();
    }

    public static int getWeekDay() {
        return getWeekDay(new Date());
    }

    public static int getWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(7);
        return week == 1 ? 7 : week - 1;
    }

    public static String getAddWeekDay(int weekDay) {
        int newweekDay;
        if (weekDay == 7) {
            newweekDay = 1;
        } else {
            newweekDay = weekDay + 1;
        }

        return String.valueOf(newweekDay);
    }

    public static Boolean CheckTwoDateIsEqual(Date date1, Date date2) {
        Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date1String = f.format(date1);
        String date2String = f.format(date2);
        return date1String.equals(date2String);
    }

}
