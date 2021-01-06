package com.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtils {

    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss格式
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * @return boolean
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() > fomatDate(e).getTime();
    }

    /**
     * @return boolean
     * @Title: compareDate
     * @Description: TODO(日期比较，如果s>e 返回true 否则返回false)
     * @author luguosui
     */
    public static boolean compareDateStrict(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() > fomatDate(e).getTime();
    }

    public static String formatDateToString(Date date, String format) {
        SimpleDateFormat fmt = new SimpleDateFormat(format);
        return fmt.format(date);
    }

    public static Date fomatDate(String date) {
        return fomatDate(date, "yyyy-MM-dd");
    }

    /**
     * 格式化日期
     */
    public static Date fomatDate(String date, String format) {
        DateFormat fmt = new SimpleDateFormat(format);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            logger.error("fomatDate:", e);
            return null;
        }
    }

    /**
     * 格式化日期
     */
    public static String fomatMSELDate(String date) {
        if (date.contains(".")) {
            String[] strs = date.split("\\.");
            return strs[0];
        } else {
            return date;
        }
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            logger.error("isValidDate:", e);
            return false;
        }
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long aa = 0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime())
                    / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            logger.error("getDiffYear:", e);
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = null;
            Date endDate = null;
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
            logger.error("getDaySub:", e);
        }
        return day;
    }

    /**
     * 得到周期单位为月/季的日期
     */
    public static String getAfterDayCycleByMonth(Date date, String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(Calendar.MONTH, daysInt);
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(canlendar.getTime());
        return dateStr;
    }

    /**
     * 得到周期单位为年的日期
     */
    public static String getAfterDayCycleByYear(Date date, String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(Calendar.YEAR, daysInt);
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(canlendar.getTime());
        return dateStr;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayCycleByDay(Date date, String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(Calendar.DATE, daysInt);
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(canlendar.getTime());
        return dateStr;
    }

    /**
     * 得到n小时之后的日期
     */
    public static String getAfterHourCycleByHour(Date date, String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.setTime(date);
        canlendar.add(Calendar.HOUR, daysInt);
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(canlendar.getTime());
        return dateStr;
    }


    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        return dateStr;
    }

    /**
     * 得到n天之后是周几
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance();
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 根据出生日期计算年龄
     *
     * @return 未来日期返回0
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            return 0;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }

        return age;
    }

    /**
     * 根据出生日期计算年龄
     *
     * @param strBirthDay 字符串型日期
     * @param format 日期格式
     * @return 未来日期返回0
     */
    public static int getAge(String strBirthDay, String format) throws Exception {
        DateFormat df = new SimpleDateFormat(format);
        Date birthDay = df.parse(strBirthDay);
        return getAge(birthDay);
    }

    public static String getCurrentTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }

    public static long getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.getTimeInMillis();
        return calendar.getTimeInMillis();
    }

    public static String getCurrentStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentStartTime(String formatValue, String datetime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentEndTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentEndTime(String formatValue, String datetime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }


    public static String getCurrentMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        return format.format(calendar.getTime());
    }

    public static String getCurrentMonth(String datetime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeek() {
        SimpleDateFormat format = new SimpleDateFormat("MM-W");
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeek(String datetime) throws Exception {
        SimpleDateFormat currentFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date tempDatetime = currentFormat.parse(datetime);
        SimpleDateFormat format = new SimpleDateFormat("MM-W");
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(tempDatetime);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return format.format(calendar.getTime());
    }

    public static String getCurrentLastYearStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentLastMonthStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentLastWeekStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentMonthStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentMonthStartTime(String formatValue, String datetime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentMonthEndTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentMonthEndTime(String formatValue, String datetime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentYearStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentYearStartTime(String formatValue, String datetime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        calendar.add(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentYearEndTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentYearEndTime(String formatValue, String datetime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tempDatetime);
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.DAY_OF_YEAR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeekStartTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeekStartTime(String formatValue, String datetime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(tempDatetime);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeekEndTime(String formatValue) {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeekEndTime(String formatValue, String datetime)
            throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Date tempDatetime = format.parse(datetime);
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(tempDatetime);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeekStartTimePlusWeek(String formatValue, int week) {
        int day = week * 7;
        System.out.println(day);
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return format.format(calendar.getTime());
    }

    public static String getCurrentWeekEndTimePlusWeek(String formatValue, int week) {
        int day = week * 7;
        SimpleDateFormat format = new SimpleDateFormat(formatValue);
        Calendar calendar = new GregorianCalendar();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        calendar.add(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        return format.format(calendar.getTime());
    }

    // 获取本周的开始时间
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取本月的开始时间
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }


    // 获取本月的结束时间
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取上月的开始时间
    public static Date getBeginDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        return getDayStartTime(calendar.getTime());
    }

    // 获取上月的结束时间
    public static Date getEndDayOfLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 2, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 2, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取本年的开始时间
    public static Date getBeginDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DATE, 1);
        return getDayStartTime(cal.getTime());
    }

    // 获取本年的结束时间
    public static Date getEndDayOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getNowYear());
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 31);
        return getDayEndTime(cal.getTime());
    }

    // 获取今年是哪一年
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    // 获取本月是哪一月
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;

    }

}
