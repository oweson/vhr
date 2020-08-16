/**
 * <p>Title: DateUtil.java<／p>
 * <p>Description: <／p>
 * <p>Copyright: Copyright (c) AKE 2019<／p>
 * <p>Company: AKE<／p>
 *
 * @author GuoJM
 * @date 2019年1月25日
 * @version 1.0
 */
package org.javaboy.vhr.utils.sea521;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author GuoJM
 */
public final class DateUtil {

    public static final String BASETIME = "1970-01-01 00:00:00";

    /**
     * Private Constructor
     **/
    private DateUtil() {

    }

    /**
     * 日期格式
     **/
    public interface DATE_PATTERN {
        String HHMMSS = "HHmmss";
        String HH_MM_SS = "HH:mm:ss";
        String YYYYMMDD = "yyyyMMdd";
        String YYYY_MM_DD = "yyyy-MM-dd";
        String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
        String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
        String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 1 将Date类型转换成String类型
     *
     * @param date Date对象
     * @return 形如:"yyyy-MM-dd HH:mm:ss"
     */
    public static String date2String(Date date) {
        return date2String(date, DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        //date-pattern
    }

    /**
     * 2 将Date按格式转化成自定义的String模式
     *
     * @param date,Date对象
     * @param pattern,日期类型
     * @return String
     */
    public static String date2String(Date date, String pattern) {
        if (date == null || pattern == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 3 将String类型转换成Date类型
     *
     * @param date,Date对象
     * @return
     */
    public static Date string2Date(String date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param lastDealTime,上次处理的时间
     * @param intervalTime,时间间隔(毫秒)
     * @return 是否大于时间间隔, 大于true, 小于false
     * @throws Exception
     */
    public static boolean dealTimeInterval(Object lastDealTime, int intervalTime) throws Exception {
        Date now = new Date();
        Date lastDealTimeDate = stringToDate(lastDealTime.toString());
        // 时间间隔大于一分钟则进入队列
        if (now.getTime() - lastDealTimeDate.getTime() > intervalTime) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 5 String的日期类型转Date
     */
    public static Date stringToDate(String date) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date dateString = formatter.parse(date);
        return dateString;
    }

    /**
     * 6 将Date类型转换为字符串
     *
     * @param date日期类型
     * @return 日期字符串
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 7 将Date类型转换为字符串
     *
     * @param date日期类型
     * @param pattern字符串格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "null";
        }

        if (null == pattern || "".equals(pattern) || "null".equals(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 8 将字符串转换为Date类型
     *
     * @param date字符串类型
     * @return 日期类型
     */
    public static Date format(String date) {
        return format(date, null);
    }

    /**
     * 9 将字符串转换为Date类型
     *
     * @param date字符串类型
     * @param pattern格式
     * @return 日期类型
     */
    public static Date format(String date, String pattern) {
        if (null == pattern || "".equals(pattern) || "null".equals(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (null == date || "".equals(date) || "null".equals(date)) {
            return new Date();
        }
        Date d = null;
        try {
            d = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException pe) {

        }
        return d;
    }

    /**
     * 10 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 11 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 12 当前时间的指定多少分钟前的时间
     */
    public static String beforeMinuteToNowDate(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -minute);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    /**
     * 13 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        // Saturday
        return calendar.getTime();
    }

    /**
     * 14 得到某年某周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);
        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 15 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }

    /**
     * 16 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        // Sunday
        return calendar.getTime();
    }

    /**
     * 17 返回指定日期的上个月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) - 1, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 18 返回指定日期的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 19 获取当前日期X天后的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 20 得到两个时间段之间所有的天数的list集合
     *
     * @param beginday
     * @param endday
     * @return
     * @throws ParseException
     */
    public static List<String> calculateDayIn(String begin, String end) throws ParseException {
        List<String> outstr = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = sdf.parse(begin);
        Date endDate = sdf.parse(end);
        double between = (endDate.getTime() - beginDate.getTime()) / 1000;
        // 除以1000是为了转换成秒
        double day = between / (24 * 3600);
        for (int i = 0; i <= day; i++) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(beginDate);
            cd.add(Calendar.DATE, i);
            // 增加一天
            outstr.add(sdf.format(cd.getTime()));
        }
        return outstr;
    }

    /**
     * 21 判断time是否在from,to之内
     *
     * @param time,指定日期
     * @param from,开始日期
     * @param to,结束日期
     * @return
     */
    public static boolean belongCalendar(Date time, Date from, Date to) {
        Calendar date = Calendar.getInstance();
        date.setTime(time);
        Calendar after = Calendar.getInstance();
        after.setTime(from);
        Calendar before = Calendar.getInstance();
        before.setTime(to);
        if (date.after(after) | date.equals(after) && date.before(before) | date.equals(before)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 22 时间转换，保留小时，分钟，秒！
     */
    public static String formatToTime(Date date) {
        return format(date, "HH:mm:ss");
    }

    /**
     * 23 日期转换
     */
    public static String formatStr(Date date) {
        return formatStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 24 日期转换，保留天数
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date formatDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateString = formatter.parse(date);
        return dateString;
    }

    /**
     * 25 获取指定日期是星期几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 26 获取指定日期是星期几用数字表示
     *
     * @param date
     * @return
     */
    public static String getWeekOfDateNumber(Date date) {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 27 获取某年某月的天数
     *
     * @param year 指定年，例18
     * @param from 指定月，例 3、03
     * @return
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 28 获取当前日期的前一天相同时间
     *
     * @param date 指定日期
     * @return
     */
    public static String getBeforeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * 29 日期截掉串后返回年月日
     *
     * @param 8位的日期 yyyyMMdd
     * @return 10位的日期 yyyy-MM-dd
     */
    public static String date8To10(String date_8) {
        String date_10 = "";
        if (date_8 == null || "".equals(date_8) || date_8.length() != 8) {
            date_10 = "";
        } else {
            String y = date_8.substring(0, 4);
            String m = date_8.substring(4, 6);
            String d = date_8.substring(6, 8);
            date_10 = y + "-" + m + "-" + d;
        }
        return date_10;
    }

    /**
     * 30 日期转字符串按照自定义的格式，没有就按照默认的转换
     */
    public static String formatStr(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (null == pattern || "".equals(pattern) || "null".equals(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 32 计算两个时间段之间的月数
     *
     * @throws ParseException
     */
    public static List<String> calculateMonthIn(String enddate, String begindate) throws ParseException {
        List<String> outstr = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date endDate = sdf.parse(enddate);
        Date beginDate = sdf.parse(begindate);
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(endDate);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(beginDate);
        int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + cal1.get(Calendar.MONTH)
                - cal2.get(Calendar.MONTH);
        for (int i = 0; i < c + 1; i++) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(beginDate);
            cd.add(Calendar.MONTH, i);
            // 增加一月
            outstr.add(sdf.format(cd.getTime()));
        }
        return outstr;
    }

    /**
     * 33 计算两个时间段之间的年数
     *
     * @throws ParseException
     */
    public static List<String> calculateYearIn(String enddate, String begindate) throws ParseException {
        List<String> outstr = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date endDate = sdf.parse(enddate);
        Date beginDate = sdf.parse(begindate);
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(endDate);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(beginDate);
        int c = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        for (int i = 0; i < c + 1; i++) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(beginDate);
            cd.add(Calendar.YEAR, i);
            // 增加一年
            outstr.add(sdf.format(cd.getTime()));
        }
        return outstr;
    }

    public static String beforeHourToNowDate(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String afterMinuteToNowDate(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static long startTiming() {
        Date date = new Date();
        return date.getTime();
    }

    public static long getSpendTime(long startTime) {
        Date date = new Date();
        return date.getTime() - startTime;
    }

    public static String getStrByLongTime(long time) {
        Date date = new Date(time);
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDayStart() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return format(start);
    }

    public static String getDayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date end = calendar.getTime();
        return format(end);
    }

    /**
     * 获取网关时间
     *
     * @param millTime
     * @return
     */
    public static String getCtrlTime(Double millTime) {
        String result = "";
        if (millTime == null) {
            result = null;
        } else {
            Date baseDate = DateUtil.format(BASETIME);
            if (baseDate != null) {
                long time = baseDate.getTime() + Math.round(millTime) + (long) 8 * 3600 * 1000;
                Date date = new Date(time);
                result = DateUtil.format(date);
            } else {
                result = null;
            }
        }
        return result;
    }

    public static Double save2Persion(Double param) {
        if (param == null) {
            return 0.0;
        } else {
            BigDecimal b = new BigDecimal(param);
            param = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return param;
    }

    /**
     * 两个时间差
     *
     * @param start
     * @param end
     * @return
     */
    public static Double getDistanceTime(String start, String end) {
        Date startDt = format(start);
        Date endDt = format(end);
        long time1 = startDt.getTime();
        long time2 = endDt.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        return save2Persion((diff / (double) (60 * 60 * 1000)));
    }

    /**
     * 获取某年某月的第一天
     *
     * @return
     */
    public static String getMonthFirstDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date firstDay = calendar.getTime();
        return format(firstDay);
    }

    /**
     * 获取某年某月的最后一天
     *
     * @return
     */
    public static String getMonthLastDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        Date lastDay = calendar.getTime();
        return format(lastDay);
    }

    /**
     * 获得指定日期的下月
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedMonthAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param d1 较大的日期
     * @param d2 较小的日期
     * @return 如果d1>d2,返回月数差,否则返回0
     * @throws ParseException
     */
    public static BigDecimal getMonthDiff(Date d1, Date d2) throws ParseException {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return new BigDecimal("0");
        }
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        String enddate = new SimpleDateFormat("yyyy-MM-dd").format(c1.getTime());
        String begindate = new SimpleDateFormat("yyyy-MM-dd").format(c2.getTime());
        List<String> months = calculateMonthIn(enddate, begindate);
        int size = months.size();
        if (size == 1) {
            BigDecimal between = new BigDecimal((day1 - day2 + 1) + "");
            BigDecimal day1Max = new BigDecimal((c1.getActualMaximum(Calendar.DAY_OF_MONTH)) + "");
            return between.divide(day1Max, 2, BigDecimal.ROUND_HALF_UP);
        } else {
            BigDecimal day1Max = new BigDecimal((c1.getActualMaximum(Calendar.DAY_OF_MONTH)) + "");
            BigDecimal between1 = new BigDecimal(day1 + "");
            BigDecimal temp1 = between1.divide(day1Max, 2, BigDecimal.ROUND_HALF_UP);
            int day2Max = c2.getActualMaximum(Calendar.DAY_OF_MONTH);
            BigDecimal between2 = new BigDecimal((day2Max - day2 + 1) + "");
            BigDecimal temp2 = between2.divide(new BigDecimal(day2Max + ""), 2, BigDecimal.ROUND_HALF_UP);
            size = size - 2;
            BigDecimal month = temp1.add(temp2);
            month = month.add(new BigDecimal(size + ""));
            return month;
        }
    }

    /**
     * 得到某年某周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        week = week - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }

    /**
     * 返回指定年月的月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定年月的月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(Integer year, Integer month) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        calendar.set(year, month, 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 返回指定日期的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的第一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getFirstDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 1 - 1;
        } else if (quarter == 2) {
            month = 4 - 1;
        } else if (quarter == 3) {
            month = 7 - 1;
        } else if (quarter == 4) {
            month = 10 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getFirstDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 3 - 1;
        } else if (quarter == 2) {
            month = 6 - 1;
        } else if (quarter == 3) {
            month = 9 - 1;
        } else if (quarter == 4) {
            month = 12 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfLastQuarter(calendar.get(Calendar.YEAR), getQuarterOfYear(date));
    }

    /**
     * 返回指定年季的上一季的最后一天
     *
     * @param year
     * @param quarter
     * @return
     */
    public static Date getLastDayOfLastQuarter(Integer year, Integer quarter) {
        Calendar calendar = Calendar.getInstance();
        Integer month = new Integer(0);
        if (quarter == 1) {
            month = 12 - 1;
        } else if (quarter == 2) {
            month = 3 - 1;
        } else if (quarter == 3) {
            month = 6 - 1;
        } else if (quarter == 4) {
            month = 9 - 1;
        } else {
            month = calendar.get(Calendar.MONTH);
        }
        return getLastDayOfMonth(year, month);
    }

    /**
     * 返回指定日期的季度
     *
     * @param date
     * @return
     */
    public static int getQuarterOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) / 3 + 1;
    }

    /**
     * 获得指定之间点的前后几个小时的时间
     */
    public static Date getOtherHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTime();
    }

    /**
     * 判断选择的日期是否是本周
     */
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Date from = DateUtil.string2Date("2018-11-01 00:00:00");
        Date to = DateUtil.string2Date("2019-03-31 23:59:59");
        Date totay = DateUtil.string2Date("2019-01-31 23:59:59");
        boolean flag = belongCalendar(totay, from, to);
        System.err.println(flag);
    }
}
