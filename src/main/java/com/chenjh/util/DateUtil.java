
package com.chenjh.util;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author chenjh
 * @version V1.0
 * @since 2018年12月27日
 */
public abstract class DateUtil
{
    
    /**
     * 一天的毫秒数
     */
    public static final long ONE_DATE_TIME_MILLIS = 3600 * 24 * 1000;
    
    /** 时间短格式 */
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    
    /** 时间短格式 */
    public static final String PATTERN_YYYY_MM = "yyyy-MM";
    
    /** 时间短格式 */
    public static final String PATTERN_YYYYMM = "yyyyMM";
    
    /** 月份位数 */
    public static final int NUM2 = 2;
    
    /** 月份位数 */
    public static final int NUM5 = 5;
    
    /** 月份位数 */
    public static final int NUM10 = 10;
    
    /** 月份位数 */
    public static final int NUM11 = 11;
    
    /** 月份位数 */
    public static final int NUM12 = 12;
    
    /** 月份位数 */
    public static final int NUM31 = 31;
    
    /** 时间格式无间隔 */
    public static final String PATTERN_YMDHMS = "yyyyMMddHHmmss";
    
    /** 默认时间格式 */
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /** 最大月份 */
    private static final int MAX_MONTH = 12;    
    
    /**
     * 日志
     */
    private static final Logger LOG  = Logger.getLogger(DateUtil.class);
    
    /**
     * 格式化日期
     * @param date date
     * @return 格式化之后的日期字符串
     * @author chenjh
     */
    public static String format(Date date)
    {
        if (null == date)
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
        return sdf.format(date);
    }
    
    /**
     * 短格式化日期
     * @param date date
     * @return 格式化之后的日期字符串
     * @author chenjh
     */
    public static String shortFormat(Date date)
    {
        if (null == date)
        {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYY_MM_DD);
        return sdf.format(date);
    }
    
    /**
     * 格式化日期
     * @param date Date
     * @param locale Locale
     * @return  格式化后日期
     * @author chenjh
     */
    public static String format(Date date, Locale locale)
    {
        return format(date, locale, DEFAULT_DATE_PATTERN);
    }
    
    /**
     * 格式化日期
     * @param date Date
     * @param locale Locale
     * @param formatPattern 格式化模式
     * @return  格式化后日期
     * @author chenjh
     */
    public static String format(Date date, Locale locale, String formatPattern)
    {
        String dateStr;
        if (null == date)
        {
            dateStr = null;
        }
        else
        {
            String pattern;
            if (null == formatPattern)
            {
                pattern = DEFAULT_DATE_PATTERN;
            }
            else
            {
                pattern = formatPattern;
            }
            SimpleDateFormat dateFormat = null;
            if (null == locale)
            {
                dateFormat = new SimpleDateFormat(pattern);
            }
            else
            {
                dateFormat = new SimpleDateFormat(pattern, locale);
            }
            dateStr = dateFormat.format(date);
        }
        return dateStr;
    }
    
    /**
     * 获取当前时间
     * @return 当前时间
     * @author chenjh
     */
    public static Timestamp getTimeStamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * 获取当前时间
     * @return java.sql.date当前时间
     * @author chenjh
     */
    public static java.sql.Date getCurrentDate()
    {
        return new java.sql.Date(System.currentTimeMillis());
    }
    
    public static Date getCurrentUtilDate()
    {
        return new Date();
    }
    
    /**
     * 复制
     * @param input 输入日期
     * @return 输出日期
     * @author chenjh
     */
    public static Date cloneDate(Date input)
    {
        return input == null ? null : new Date(input.getTime());
    }
    
    /**
     * 字符串转换为日期型
     * @param strDate 字符串日期值
     * @return 日期
     * @author chenjh
     */
    public static Date parse(String strDate)
    {
        return parse(strDate, DEFAULT_DATE_PATTERN);
    }
    
    /**
     * 基础时间上添加或减去指定的时间量
     * @param date  基础时间
     * @param amount  天数
     * @return  返回当前时间往前或往后移动天数的时间
     * @author chenjh
     */
    public static Date addDay(Date date, int amount)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, amount);
        return calendar.getTime();
    }
    
    /**
     * 基础时间上添加或减去指定的时间量
     * @param date  基础时间
     * @param amount  月份数量，可以为负数
     * @return  返回当前时间往前或往后移动月数的时间
     * @author chenjh
     */
    public static Date addMonth(Date date, int amount)
    {
        if (null == date)
        {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, amount);
        return calendar.getTime();
    }
    
    /**
     * 字符串转换成日期
     * @param strDate 字符型日期
     * @param patter 日期格式
     * @return 日期型日期
     * @author chenjh
     */
    public static Date parse(String strDate, String patter)
    {
        if (StringUtils.isEmpty(strDate))
        {
            return null;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat(patter);//小写的mm表示的是分钟
        
        try
        {
            return sdf.parse(strDate);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        
        return null;
    }
    
    /**
     * 比较日期
     * @param d1 前者
     * @param d2 后者
     * @return 前者大于等于后者返回true 反之false 
     * @author chenjh
     */
    public static boolean compareDate(Date d1, Date d2)
    {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        
        int result = c1.compareTo(c2);
        return result >= 0;
    }
    
    /**
     * 根据给定的日期，获取日期的当前月的第一天
     * @param date date
     * @return date
     * @author chenjh
     */
    public static Date getFirstDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        String ym = year + StringUtils.leftPad(month + "", NUM2, "0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的日期，获取本次周期开始时间（每年11月1日为周期开始时间，10月31日为周期结束时间）
     * @param date date
     * @return date
     * @author chenjh
     */
    public static Date getCycleStartTime(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        if (month < NUM11)
        {
            year = year - 1;
        }
        String ym = year + StringUtils.leftPad(NUM11 + "", NUM2, "0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的日期，获取本次周期结束时间（每年11月1日为周期开始时间，10月31日为周期结束时间）
     * @param date date
     * @return date
     * @author chenjh
     */
    public static Date getCycleEndTime(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        if (month > NUM10)
        {
            year = year + 1;
        }
        String ym = year + StringUtils.leftPad(NUM10 + "", NUM2, "0") + StringUtils.leftPad(NUM31 + "", NUM2, "0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的日期，获取自然年开始时间（开始时间为1月1日，结束时间为12.31日）
     * @param date date
     * @return date
     * @author chenjh
     */
    public static Date getNaturalStartTime(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        String ym = String.valueOf(year);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的日期，获取自然年结束时间（开始时间为1月1日，结束时间为12.31日）
     * @param date date
     * @return date
     * @author chenjh
     */
    public static Date getNaturalEndTime(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        String ym = year + StringUtils.leftPad(NUM12 + "", NUM2, "0") + StringUtils.leftPad(NUM31 + "", NUM2, "0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的日期，获取日期的上月的第一天
     * @param date date
     * @return date
     * @author chenjh   
     */
    public static Date getLastMonthFirstDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, -1);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        String ym = year + StringUtils.leftPad(month + "", NUM2, "0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的日期，获取日期的下月的第一天
     * @param date date
     * @return date
     * @author chenjh
     */
    public static Date getNextMonthFirstDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, 1);
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH) + 1;
        String ym = year + StringUtils.leftPad(month + "", NUM2, "0");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try
        {
            return sdf.parse(ym);
        }
        catch (ParseException e)
        {
            LOG.error("date conv error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的时间，获取日期所在半年度，上半年度和下半年度
     * 如：2016年3月19日,得到2016年01月01日 0:00:00
     * 如：2016年9月19日,得到2016年07月01日 0:00:00
     * @param date 日期
     * @return 日期
     * @author chenjh
     */
    public static Date getHalfYearDate(Date date)
    {
        if (null == date)
        {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        //获取月份 0 ， 1， 2，，，，11
        int month = cal.get(Calendar.MONTH);
        if (month <= Calendar.JUNE)
        {
            month = Calendar.JANUARY;
        }
        if (month >= Calendar.JULY)
        {
            month = Calendar.JULY;
        }
        month++;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try
        {
            return sdf.parse(year + StringUtils.leftPad(month + "", NUM2, "0"));
        }
        catch (ParseException e)
        {
            LOG.error("Date Parse Error", e);
        }
        return null;
    }
    /**
     * 根据给定的时间，获取日期所在半年度的结束时间
     * 如：2016年3月19日,得到2016年06月31日 0:00:00
     * 如：2016年9月19日,得到2016年12月31日 0:00:00
     * @param date 日期
     * @return 日期
     * @author chenjh
     */
    public static Date getHalfYearEndDate(Date date)
    {
        if (null == date)
        {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        //获取月份 0 ， 1， 2，，，，11
        int month = cal.get(Calendar.MONTH);
        if (month <= Calendar.JUNE)
        {
            month = Calendar.JUNE;
        }
        if (month >= Calendar.JULY)
        {
            month = Calendar.DECEMBER;
        }
        month++;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try
        {
            return sdf.parse(year + StringUtils.leftPad(month + "", NUM2, "0") 
                    + StringUtils.leftPad(NUM31 + "", NUM2, "0"));
        }
        catch (ParseException e)
        {
            LOG.error("Date Parse Error", e);
        }
        return null;
    }
    
    /**
     * 根据给定的月份获取从1月开始到给定月份的月集合
     * @param date 给定日期
     * @return 月集合
     */
    public static List<Date> getMonthList(Date date)
    {
        List<Date> months = new ArrayList<Date>();
        Calendar ca = Calendar.getInstance();
        months.add(date);
        ca.setTime(date);
        for (int i = 1; i <= MAX_MONTH; i++)
        {
            ca.add(Calendar.MONTH, -1);
            months.add(0, ca.getTime());
        }
        
        return months;
    }
        
    /**
     * 判断当天是否为当月第一天
     * @param date 当天
     * @return true、false
     * @author chenjh
     */
    public static boolean isFisrtDayOfMonth(Date date)
    {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int day = ca.get(Calendar.DAY_OF_MONTH);
        
        return day == 1;
    }
    
    /**
     * 获取上月字符串
     * @param date date
     * @return yyyyMM
     * @author y00305001
     * @date 2016年4月1日
     */
    public static String getLastMonthStr(Date date)
    {
        Date d = getLastMonthFirstDay(date);
        
        return format(d, null, PATTERN_YYYYMM);
    }
    
    /**
     * 获取昨天日期
     * @return yesterday
     * @author chenjh
     */
    public static Date getYesterday()
    {
        Date result = null;
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, -1);
        result = ca.getTime();
        
        return result;
    }
    
    /**
     * 获取给定月最后一天
     * @param date date
     * @return 最后一天date
     * @author chenjh
     */
    public static Date getLastDayOfMonth(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, 1);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.DATE, -1);
        return ca.getTime();
    }
    
    /**
     * 获取下月第一天
     * @param date date
     * @return 第一天date
     * @author chenjh
     */
    public static Date getNextMonthDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, 1);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        return ca.getTime();
    }
    
    /**
     * 去年
     * @param date 输入时间
     * @return 去年
     * @author chenjh
     */
    public static Date getLastYearDay(Date date)
    {
        if (date == null)
        {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR, -1);
        
        return ca.getTime();
    }
    
    /**
     * 转换日期格式
     * @param dateVal 日志字符串值
     * @param oldPattern 旧格式
     * @param newPattern 新格式
     * @return 转换日期格式
     * @author chenjh
     */
    public static String getPatterTime(String dateVal, String oldPattern, String newPattern)
    {
        Date date = parse(dateVal, oldPattern);
        SimpleDateFormat sdf = new SimpleDateFormat(newPattern);
        return sdf.format(date);
    }
    
    /**
     * 获取当前日时间(时分秒置0)
     * @param patter 日期格式
     * @return 获取当前日时间
     * @author chenjh
     */
    public static Date getCurrentDay(String patter)
    {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(patter);
        String time = df.format(date);
        return DateUtil.parse(time, patter);
    }
    
    /**
     * 根据指定结束时间,返回12个月份 yyyyMM
     * @param endDate endDate
     * @return list
     * @author chenjh
     */
    public static List<String> getLastTwelveYearMonthList(Date endDate)
    {
        if (endDate == null)
        {
            throw new InvalidParameterException();
        }
        List<String> monthList = new ArrayList<String>(NUM12);
        Calendar ca = Calendar.getInstance();
        ca.setTime(endDate);
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYYMM);
        for (int i = 0; i < NUM12; i++)
        {
            Date d = ca.getTime();
            monthList.add(0, sdf.format(d));
            ca.add(Calendar.MONTH, -1);
        }
        return monthList;
    }
    /**
     * 根据指定开始时间和结束时间返回开始到结束时间的月份(不带横杠，CODEX使用)
     * @param beginDate 开始时间  时间格式为短日期格式
     * @param endDate 结束时间 时间格式为短日期格式
     * @return 获取时间段的年和月份
     * @author chenjh
     */
    public static List<String> getByBeginAndEndDate(Date beginDate, Date endDate)
    {
        if (null == beginDate || null == endDate)
        {
            return new ArrayList<String>(0);
        }
        
        List<String> dateList = new ArrayList<String>();
        Date startDate = beginDate;
        while (compareDate(endDate, startDate))
        {
            Calendar ca = Calendar.getInstance();
            ca.setTime(startDate);
            int year = ca.get(Calendar.YEAR);
            int month = ca.get(Calendar.MONTH) + 1;
            StringBuilder dateString = new StringBuilder();
            dateString.append(year);
            dateString.append(StringUtils.leftPad(month + "", NUM2, "0"));
            dateList.add(dateString.toString());
            startDate = addMonth(startDate, 1);
        }
        
        return dateList;
    }
    
    /**
     * 根据指定开始时间和结束时间返回开始到结束时间的月份
     * @param beginDate 开始时间  时间格式为短日期格式
     * @param endDate 结束时间 时间格式为短日期格式
     * @return 获取时间段的年和月份
     * @author chenjh
     */
    public static List<String> getYearMonthByBeginAndEndDate(Date beginDate, Date endDate)
    {
        if (null == beginDate || null == endDate)
        {
            return new ArrayList<String>(0);
        }
        
        List<String> dateList = new ArrayList<String>();
        Date startDate = beginDate;
        while (compareDate(endDate, startDate))
        {
            Calendar ca = Calendar.getInstance();
            ca.setTime(startDate);
            int year = ca.get(Calendar.YEAR);
            int month = ca.get(Calendar.MONTH) + 1;
            StringBuilder dateStr = new StringBuilder();
            dateStr.append(year);
            dateStr.append('-');
            dateStr.append(StringUtils.leftPad(month + "", NUM2, "0"));
            dateList.add(dateStr.toString());
            startDate = addMonth(startDate, 1);
        }
        
        return dateList;
    }
    
    /**
     * 获取当前日期上月yyyyMM
     * @return 获取当前日期上月yyyyMM
     * @author chenjh
     */
    public static String getLastMonth()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YYYYMM);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return sdf.format(cal.getTime());
    }
    
    /**
     * 获取年份集合
     * @param yearStr yearStr 
     * @return 年份集合
     * @author chenjh
     */
    public static List<String> getYearListAfter(String yearStr)
    {
        List<String> yearList = new ArrayList<String>();
        
        Calendar ca = Calendar.getInstance();
        int endYear = ca.get(Calendar.YEAR);
        int beginYear;
        try
        {
            beginYear = Integer.parseInt(yearStr);
        }
        catch (NumberFormatException e)
        {
            beginYear = endYear;
        }
        
        int param = beginYear;
        while (param <= endYear)
        {
            yearList.add(String.valueOf(param));
            param = param + 1;
        }
        
        return yearList;
    }
    
}
