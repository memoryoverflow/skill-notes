package cn.yj.notes.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * <br>
 *
 * @author 永健
 * @since 2019/2/23 16:35
 */
public class DateTimeUtils extends DateUtils
{
    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),


    public static final String PATTERM = "yyyy-MM-dd";
    public static final String PATTERM2 = "yyyy-MM-dd HH:mm";
    public static final String PATTERM3 = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     *
     * @return
     */
    public static LocalDateTime convertDateToLDT(Date date)
    {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param time
     *
     * @return
     */
    public static Date convertLDTToDate(LocalDateTime time)
    {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取指定日期的毫秒
     *
     * @param time
     *
     * @return
     */
    public static Long getMilliByTime(LocalDateTime time)
    {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     *
     * @param time
     *
     * @return
     */
    public static Long getSecondsByTime(LocalDateTime time)
    {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取指定时间的指定格式
     *
     * @param time
     * @param pattern
     *
     * @return
     */
    public static String formatTime(LocalDateTime time, String pattern)
    {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param pattern
     *
     * @return
     */
    public static String formatNow(String pattern)
    {
        return formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取当前时间的指定格式
     *
     * @return
     */
    public static String formatNow()
    {
        return formatTime(LocalDateTime.now(), PATTERM2);
    }

    /**
     * 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     *
     * @return
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field)
    {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     *
     * @param time
     * @param number
     * @param field
     *
     * @return
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field)
    {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field
     *         单位(年月日时分秒)
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field)
    {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS)
        {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS)
        {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 获取一天的开始时间，2017,7,22 00:00
     *
     * @param time
     *
     * @return
     */
    public static LocalDateTime getDayStart(LocalDateTime time)
    {
        return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * 获取一天的结束时间，2017,7,22 23:59:59.999999999
     *
     * @param time
     *
     * @return
     */
    public static LocalDateTime getDayEnd(LocalDateTime time)
    {
        return time.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    /**
     * <br>
     * 获取多少天前或者后的日期
     */
    public static Date getDateDayAgoOrAfter(Integer day)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * <br>
     * 获取多少小时前或者后的日期
     */
    public static Date getDateHoursAgoOrAfter(Integer hours)
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    public static String getTimeByCalendar()
    {
        Calendar cal = Calendar.getInstance();
        //获取年份
        int year = cal.get(Calendar.YEAR);
        //获取月份
        int month = cal.get(Calendar.MONTH) + 1;
        //获取日
        int day = cal.get(Calendar.DATE);
        //小时
        int hour = cal.get(Calendar.HOUR);
        //分
        int minute = cal.get(Calendar.MINUTE);
        //秒
        int second = cal.get(Calendar.SECOND);
        //一周的第几天
        int weekOfYear = cal.get(Calendar.DAY_OF_WEEK) - 1;

        return year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒 星期" + transWeekOfYear(weekOfYear);
    }

    private static String transWeekOfYear(int weekOfYear){
        String str="";
        switch (weekOfYear){
            case 1:
                str="一";
                break;
            case 2:
                str="二";
                break;
            case 3:
                str="三";
                break;
            case 4:
                str="四";
                break;
            case 5:
                str="五";
                break;
            case 6:
                str="六";
                break;
            case 7:
                str="日";
                break;

        }
        return str;
    }

    public static void main(String[] args)
    {
        System.out.println(getTimeByCalendar());
        ;
    }

}
