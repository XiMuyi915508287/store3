/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author chenjingjun
 */
public class DateUtil {
// SimpleDateFormat线程不安全, so ThreadLocal
    private static final ThreadLocal<DateFormats> dateFormats = new ThreadLocal<DateFormats>() {
        @Override
        protected DateFormats initialValue() {
                    return new DateFormats();			
            }
    };

    private static final int DAY_MI_SECOND = 24 * 60 * 60 * 1000;


    public static String formatYMD(Date date) {
        return dateFormats.get().ymd.format(date);
    }

    public static String formatHMS(Date date) {
        return dateFormats.get().hms.format(date);
    }

    public static String formatMDHM(Date date) {
        return dateFormats.get().mdhm.format(date);
    }

    public static String formatHM(Date date) {
        return dateFormats.get().hm.format(date);
    }

    public static String formatYMDHM(Date date) {
        return dateFormats.get().ymdhm.format(date);
    }

    public static String formatYMDHMS(Date date) {
        return dateFormats.get().ymdhms.format(date);
    }

    public static String formatYMDChinese(Date date) {
        return dateFormats.get().ymdChinese.format(date);
    }
    
    public static String formatYMDHMSChinese(Date date) {
        return dateFormats.get().ymdhmsChinese.format(date);
    }

    public static String formatYMDSlash(Date date) {
        return dateFormats.get().ymdSlash.format(date);
    }

    public static Date parseYMD(String dateStr) {
        return parse(dateFormats.get().ymd, dateStr);
    }

    public static Date parseYMDHMS(String dateStr) {
        return parse(dateFormats.get().ymdhms, dateStr);
    }

    public static Date parseHMS(String dateStr) {
        return parse(dateFormats.get().hms, dateStr);
    }
    
    public static Date parseYMDHMSChinese(String dateStr) {
        return parse(dateFormats.get().ymdhmsChinese, dateStr);
    }

    /**
     * 判断当前时间是否在某段时间内 参数不区分先后顺序
     */
    public static boolean isDuringTwoDate(Date date, Date another) {
        long dateTime = date.getTime();
        long anotherTime = another.getTime();
        long currentTime = new Date().getTime();

        if (currentTime > dateTime && currentTime < anotherTime) {
                return true;
        } else if (currentTime > anotherTime && currentTime < dateTime) {
                return true;
        } else {
                return false;
        }
    }

    /**是否为同一天**/
    public static boolean isSameDate(Date date, Date another) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(another);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                        && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                        && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 
     * @param date
     * @param history
     * @return
     */
    public static boolean isYesterday(Date date , Date history){
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(history);
        calendar1.add(Calendar.DATE, -1);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                        && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
                        && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }
    
    /**
     *
     * @param date
     * @param another
     * @return
     */
    public static boolean isSameWeely(Date date, Date another) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(another);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                        && calendar1.get(Calendar.WEEK_OF_YEAR) == calendar2.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     *
     * @param date
     * @param another
     * @return
     */
    public static boolean isSameMonth(Date date, Date another) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(another);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                        && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }
    
    /**
     *
     * @param date
     * @param another
     * @return
     */
    public static boolean isSameYear(Date date, Date another) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(another);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }
    
    /**
     *
     * @param date
     * @param history
     * @return
     */
    public static int differMonth(Date date, Date history) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(history);
        int yearCount = calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
        int monthCount = calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
        return (yearCount * 12) + monthCount;
    }

    public static Date parse(SimpleDateFormat format, String dateStr) {
        try {
                Date d = format.parse(dateStr);
                // check range for mysql
                // mysql date range : '1000-01-01' to '9999-12-31'
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                int year = c.get(Calendar.YEAR);
                if (year >= 1000 && year <= 9999) {
                        return d;
                } else {
                        return null;
                }
        } catch (ParseException ex) {
                return null;
        }
    }

    /**
     * 间隔的天数
     *  @return
     * **/
    public static long daysOffset(Date date1, Date date2) {
        date1 = DateUtil.parseYMD(DateUtil.formatYMD(date1));
        date2 = DateUtil.parseYMD(DateUtil.formatYMD(date2));

        return (date1.getTime() - date2.getTime()) / DAY_MI_SECOND;
    }

    /**
     * 今天是星期几 , 7表示星期日
     * @return
     */
    public static int getTodayDayOfWeek() {
        Calendar now = Calendar.getInstance();
        int dayOfweek = now.get(Calendar.DAY_OF_WEEK);
        dayOfweek--;
        if (dayOfweek == 0) {
                dayOfweek = 7;
        }
        return dayOfweek;
    }

    /**
     * 这个时间是星期几 , 7表示星期日
     * @return
     */
    public static int getDayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int dayOfweek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfweek--;
        if (dayOfweek == 0) {
                dayOfweek = 7;
        }
        return dayOfweek;
    }

    /**
     * 这个时间是星期几 , 7表示星期日
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfweek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfweek--;
        if (dayOfweek == 0) {
                dayOfweek = 7;
        }
        return dayOfweek;
    }

    /**
     * 是否是今天
     * @return
     * **/
    public static boolean isCurrentDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar todayCalendar = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) != todayCalendar.get(Calendar.YEAR)) {
                return false;
        } else if (calendar.get(Calendar.MONTH) != todayCalendar.get(Calendar.MONTH)) {
                return false;
        } else if (calendar.get(Calendar.DAY_OF_MONTH) != todayCalendar.get(Calendar.DAY_OF_MONTH)) {
                return false;
        }
        return true;
    }

    /**
     * @return 最近的周五
     */
    public static Date getLatestMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.MONDAY) {
                return calendar.getTime();
        } else if (dayOfWeek > Calendar.MONDAY) {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                return calendar.getTime();
        } else {
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 7);
                return calendar.getTime();
        }
    }

    /**
     * @return 是否同一个星期
     */
    public static boolean isCurrentWeelyDay(Date date) {
        Date another = DateUtil.getLatestMonday(date);
        Date current = DateUtil.getLatestMonday(new Date());
        return DateUtil.isSameDate(another, current);
    }

    /**获取今天的过期时间**/
    public static Date getExpiryDate4CurrentDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**获取今天的过期时间**/
    public static Date getExpiryDate4weeklyDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek >= Calendar.MONDAY) {
                calendar.add(Calendar.DAY_OF_WEEK, 7 - dayOfWeek + Calendar.MONDAY);
        } else {
                calendar.add(Calendar.DAY_OF_WEEK, Calendar.MONDAY - dayOfWeek);
        }
        return calendar.getTime();
    }


    /**
     * 获取两个时间差
     * @param startTime 开始时间点 毫秒
     * @param endTime 结束时间点 毫秒
     * @return 时间差
     */
    public static long getBetweenTime(long startTime, long endTime){
        if(startTime<=0 || startTime>endTime)
                return 0;
        return endTime - startTime;
    }

     /**
      * 根据传入的Date换算成秒数
      * @return 秒数返回
      */
     public static int getTimeSeconds(Date date){
        return (int)(date.getTime()/1000);
     }


    /**
     * 传入时间是否为整点（误差前后一分钟）
     * @param millisSeconds
     * @return
     */
    public static boolean isHourPoint(long millisSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millisSeconds);
        int minute = calendar.get(Calendar.MINUTE);
        if((minute >= 0 && minute <=1) || minute ==59)
            return true;
        return false;
    }


    /**
     * 获取某一天的零点时间戳
     * @return 秒
     */
    public static int getDayZeroTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (int)(calendar.getTimeInMillis()/1000);
    }

    static class DateFormats {
        public final SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");
        public final SimpleDateFormat mdhm = new SimpleDateFormat("MM-dd HH:mm");
        public final SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
        public final SimpleDateFormat ymdhm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        public final SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        public final SimpleDateFormat ymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public final SimpleDateFormat ymdChinese = new SimpleDateFormat("yyyy年MM月dd");
        public final SimpleDateFormat ymdhmsChinese = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        public final SimpleDateFormat ymdSlash = new SimpleDateFormat("yyyy/MM/dd");
    }
}
