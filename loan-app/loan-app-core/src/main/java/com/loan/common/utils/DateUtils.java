package com.loan.common.utils;

import org.apache.commons.httpclient.util.DateParseException;
import org.springframework.util.StringUtils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FileName: com.loan.common.utils.DateUtils.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/29 15:08
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:08    1.0          Create
 */
public class DateUtils {

    /**
     * 获取文件名 当前时间戳+随机数
     *
     * @return
     */
    public static String getTimestampForFileId() {
        return System.currentTimeMillis() + "" + new Random().nextInt(10000);
    }

    /**
     * 将指定字符串转换成日期
     *
     * @param date        String 日期字符串
     * @param datePattern String 日期格式
     * @return Date
     */
    public static Date getFormatDate(String date, String datePattern) {
        SimpleDateFormat sd = new SimpleDateFormat(datePattern);
        return sd.parse(date, new java.text.ParsePosition(0));
    }

    /**
     * 将日期截断到月
     *
     * @param date
     * @return
     */
    public static Long formatDateForMonth(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
        return Long.parseLong(sd.format(date));
    }

    /**
     * 获取指定日期0点时间戳
     *
     * @param date
     * @return
     */
    public static long getDateSeconds(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime().getTime() / 1000;
    }


    /**
     * 将指定字符串转换成日期,８位
     *
     * @param date String 日期字符串
     * @return Date
     */
    public static Date getFormatDate8(String date) throws DateParseException {
        if (date == null || date.length() == 0) return null;
        date = date.replaceAll("[-\\s/:]+", "");

        if (date.length() < 8) throw new DateParseException("日期格式错误");
        if (date.length() > 8) date = date.substring(0, 8);

        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        return sd.parse(date, new java.text.ParsePosition(0));
    }

    /**
     * 将指定字符串转换成日期,14位
     *
     * @param date String 日期字符串
     * @return Date
     */
    public static Date getFormatDate14(String date) throws DateParseException {
        if (date == null || date.length() == 0) return null;

        date = date.replaceAll("[-\\s/:]+", "");
        if (date.length() < 8) throw new DateParseException("日期格式错误");

        int i = 14 - date.length();
        if (i > 0) {
            StringBuffer sb = new StringBuffer(date);
            while (i-- > 0) {
                sb.append("0");
            }
            date = sb.toString();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.parse(date, new java.text.ParsePosition(0));
    }

    /**
     * 将指定日期对象转换成格式化字符串
     *
     * @param date        Date XML日期对象
     * @param datePattern String 日期格式
     * @return String
     */
    public static String getFormattedString(Date date, String datePattern) {
        if (date == null) return null;
        SimpleDateFormat sd = new SimpleDateFormat(datePattern);

        return sd.format(date);
    }

    /**
     * 将指定XML日期对象转换成格式化字符串
     *
     * @param xmlDate     Date XML日期对象
     * @param datePattern String 日期格式
     * @return String
     */
    public static String getFormattedString(XMLGregorianCalendar xmlDate,
                                            String datePattern) {
        SimpleDateFormat sd = new SimpleDateFormat(datePattern);

        Calendar calendar = xmlDate.toGregorianCalendar();

        return sd.format(calendar.getTime());
    }

    /**
     * 将指定XML日期对象转换成日期对象
     *
     * @param xmlDate Date XML日期对象
     * @return Date
     */
    public static Date xmlGregorianCalendar2Date(XMLGregorianCalendar xmlDate) {
        return xmlDate.toGregorianCalendar().getTime();
    }

    public static String getThisYear() {
        // 获得当前日期
        Calendar cldCurrent = Calendar.getInstance();
        // 获得年月日
        String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
        return strYear;
    }

    public static XMLGregorianCalendar convert2XMLCalendar(Calendar calendar) {
        try {
            DatatypeFactory dtf = DatatypeFactory.newInstance();
            return dtf.newXMLGregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.HOUR),
                    calendar.get(Calendar.MINUTE),
                    calendar.get(Calendar.SECOND),
                    calendar.get(Calendar.MILLISECOND),
                    calendar.get(Calendar.ZONE_OFFSET) / (1000 * 60));

        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 获取当天时间
    public static java.sql.Timestamp getNowTime(String dateformat) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
        String dateString = dateFormat.format(now);
        SimpleDateFormat sd = new SimpleDateFormat(dateformat);
        Date dateFormt = sd.parse(dateString, new java.text.ParsePosition(0));
        java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt
                .getTime());

        return dateTime;
        // return hehe;
    }

    // 获取指定时间
    public static java.sql.Timestamp getNowNewTime(String date, String dateformat) {
        //Date   now   =   new   Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);//可以方便地修改日期格式
        dateFormat.parse(date, new java.text.ParsePosition(0));

        //  String  dateString= dateFormat.format(date);
        Date dateFormt = dateFormat.parse(date, new java.text.ParsePosition(0));
        java.sql.Timestamp dateTime = new java.sql.Timestamp(dateFormt.getTime());


        return dateTime;
    }

    /**
     * @param tdate 含有yyyy-MM-dd'T'hh:mm:ss.SSS格式的时间转换.
     * @return
     */
    public static String getTFormatString(String tdate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String str = "";
        try {
            Date date = format1.parse(tdate);
            SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = format2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    //获取当前时间前2个小时的时间。
    public static String getBefore2HourDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -2); // 目前時間加3小時
        return df.format(c.getTime());

    }

    /**
     * @param time1 当前时间
     * @param time2 比较时间
     * @return 如果time1比time2大gap分钟，则返回true;
     */
    public static boolean compareDateTime(Date time1, Date time2, int gap) {
        return time1.getTime() - time2.getTime() > gap * 60 * 1000;
    }


    /**
     * 获取当前时间的字符串
     *
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateformat.format(new Date());
        System.out.println(time + "-------------------------------");
        return time;
    }

    public static long getNowTimestamp13() {
        return System.currentTimeMillis();
    }

    public static long getNowTimestamp10() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取定时间的字符串
     *
     * @return
     */
    public static String getStrTime(Date date) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateformat.format(date);
        System.out.println(time + "-------------------------------");
        return time;
    }

    public static String LdateStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
        String str = format.format(date);
        return str;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 昨天的开始 时间戳
     *
     * @return
     */
    public static long yesterdayBeginTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime().getTime() / 1000);
        return calendar.getTime().getTime() / 1000;
    }

    /**
     * 昨天的结束 时间戳
     *
     * @return
     */
    public static long yesterdayEndTime() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -1);
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 59);
        System.out.println(calendar1.getTime().getTime() / 1000);
        return calendar1.getTime().getTime() / 1000;
    }

    public static long dataTime() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DATE, -1);
        calendar1.set(Calendar.HOUR_OF_DAY, 12);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        System.out.println(calendar1.getTime().getTime() / 1000);
        return calendar1.getTime().getTime() / 1000;
    }

    public static long beforeSevenDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime().getTime() / 1000);
        return calendar.getTime().getTime() / 1000;
    }

    public static long before30Day() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime().getTime() / 1000);
        return calendar.getTime().getTime() / 1000;
    }

    public static long formartTime(String time) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(time);
        System.out.println(date.getTime() / 1000);
        return date.getTime() / 1000;
    }

    public static long formartTimeToHms(String time) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd天HH时mm分");
        Date date = format.parse(time);
        System.out.println(date.getTime() / 1000);
        return date.getTime() / 1000;
    }

    /**
     * 根据时间戳 计算 时间的最大日期
     *
     * @param time
     * @return
     */
    public static long getMonthDays(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date(time * 1000));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time * 1000));
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String longToDate(String str) {
        try {
            if (!StringUtils.isEmpty(str)) {
                long longStr = Long.parseLong(str);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Long time = new Long(longStr);
                String d = format.format(new Date(time * 1000));
                // Date date=format.parse(d);
                return d;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //锁定期限起始日期限
    public static String ProtocolLongToTime(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date(time*1000));
        calendar.add(calendar.DATE,1);
        Date date=calendar.getTime();
        return sdf.format( date.getTime());
    }

    //协议日期转换
    public static String ProtocolLongToDate(String str) {
        try {
            if (!StringUtils.isEmpty(str)) {
                long longStr = Long.parseLong(str);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                Long time = new Long(longStr);
                String d = format.format(new Date(time * 1000));
                // Date date=format.parse(d);
                return d;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String longToDate(long timeOfSeconds, String formate) {
        if (timeOfSeconds <= 0) return "";

        try {
            SimpleDateFormat format = new SimpleDateFormat(formate);
            String d = format.format(new Date(timeOfSeconds * 1000));
            return d;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getTimeStandard(String format) {
        long t = 0;
        if (StringUtils.isEmpty(format)) {
            return t;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(format);
            t = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static long getTodayBeginTime() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        System.out.println(calendar1.getTime().getTime() / 1000);
        return calendar1.getTime().getTime() / 1000;
    }

    /**
     * 获取当前时间之后的多少天  日期加天数
     *
     * @param time
     * @return
     */
    public static long getafterTime(int time) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, time);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        System.out.println(calendar1.getTime().getTime() / 1000);
        return calendar1.getTime().getTime() / 1000;
    }

    /**
     * 获取当前时间之后的多少天 格式（y-m-d : 23:59:59）
     *
     * @param time
     * @return
     */
    public static long getafterDayTime(int time) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, time);
        calendar1.set(Calendar.HOUR_OF_DAY, 23);
        calendar1.set(Calendar.MINUTE, 59);
        calendar1.set(Calendar.SECOND, 59);
        System.out.println(calendar1.getTime().getTime() / 1000);
        return calendar1.getTime().getTime() / 1000-86400;
    }

    public static String fromLongToDate(String longStr) {
        if (cn.jpush.api.utils.StringUtils.isNotEmpty(longStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sd = sdf.format(new Date(Long.parseLong(longStr) * 1000));
            return sd;
        }else {
            return "";
        }
    }

    public static String fromLongToSimpleDate(String longStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sd = sdf.format(new Date(Long.parseLong(longStr) * 1000));
        return sd;
    }

    public static long fromLongToLong(String longStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sd = sdf.format(new Date(Long.parseLong(longStr) * 1000));
        Date date = sdf.parse(sd);
        System.out.println(date.getTime() / 1000);
        return date.getTime() / 1000;
    }


    /**
     * 获取指定日期的天
     *
     * @param d
     * @return
     */
    public static int getDay(long d) {
        Date date = new Date(d * 1000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static String secToTime(int time, String vtime) {
        int num = 0;
        if (vtime.equals("280")) {
            num = 1;
        }
        if (vtime.equals("281")) {
            num = 2;
        }
        if (vtime.equals("282")) {
            num = 3;
        }

        time = Integer.valueOf(time) + num * 86400;
        if (time < 0) {
            time = 0;
        }
        String timeStr = null;
        int day = 0;
        int hour = 0;
        int minute = 0;
//        if (time <= 0)
//            return "00:00";
//        else {
//            minute = time / 60;
//            if (minute < 60) {
//                second = time % 60;
//                timeStr = unitFormat(minute) + ":" + unitFormat(second);
//            } else {
//                hour = minute / 60;
//                if (hour > 99)
//                    return "99:59:59";
//                minute = minute % 60;
//                second = time - hour * 3600 - minute * 60;
//                day = hour / 24;
//                if (day < 0) {
//                    day = 0;
//                }

        day = time / 86400;
        hour = time % 86400 / 3600;
        minute = time % 86400 % 3600 / 60;
        timeStr = unitFormat(day) + "天" + unitFormat(hour) + "时" + unitFormat(minute) + "分";
        return timeStr;
    }

    public static long secToLong(int time, String vtime) {
        int num = 0;
        if (vtime.equals("280")) {
            num = 1;
        }
        if (vtime.equals("281")) {
            num = 2;
        }
        if (vtime.equals("282")) {
            num = 3;
        }

        time = Integer.valueOf(time) + num * 86400;
        return time;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 获取指定月份开始时间
     *
     * @param month
     * @return miaoyus
     */
    public static long getTheMonthTimeStart(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        long t = date.getTime();
        t = t / 1000;
        return t;
    }

    /**
     * 获得指定日期提前一个月的开始时间
     *
     * @param month
     * @return
     */
    public static long getTheAssignMonthTimeStart(int month, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        long t = date.getTime();
        t = t / 1000;
        return t;
    }

    /**
     * 返回当前时间字符串
     *
     * @param date
     * @return
     */
    public static String dateStrForArticle(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = format.format(date);
        return str;
    }

    public static boolean isDate(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 是否是指定格式的时间
     *
     * @param strDate 时间
     * @param formart 时间格式
     * @return
     */
    public static boolean isDate(String strDate, String formart) {
        boolean convertSuccess = true;
        try {
            SimpleDateFormat format = new SimpleDateFormat(formart);
            format.parse(strDate);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * @param time    时间字符串
     * @param formart 日期格式 yyyy-MM-dd/yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Long dateToLong(String time, String formart) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formart);
            Date date = format.parse(time);
            return date.getTime() / 1000;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 是否是 简单格式 2015-03-01 格式的时间字符串
     * @param dateStr 2015-01-01
     * @return
     */
    public static boolean isSimpleDateStr(String dateStr){
        if (StringUtils.isEmpty(dateStr)){
            return false;
        }
        String eL="^[1-9]\\d{3}-((0[1-9])|(1[0-2]))-(([0][1-9])|([1-2][0-9])|(3[0-1]))$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(dateStr);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            System.out.println("格式错误");
            return false;
        }
        System.out.println("格式正确");
        return true;
    }

    /**
     *是否是复杂格式时间字符串
     * @param dateStr 2015-03-01 13:23:28
     * @return
     */
    public static boolean isDateStr(String dateStr){
        if (StringUtils.isEmpty(dateStr)){
            return false;
        }
        String eL="^[1-9]\\d{3}-((0[1-9])|(1[0-2]))-(([0][1-9])|([1-2][0-9])|(3[0-1]))\\s+[0-2][0-9]:[0-9][0-9]:[0-9][0-9]$";
        Pattern p = Pattern.compile(eL);
        Matcher m = p.matcher(dateStr);
        boolean dateFlag = m.matches();
        if (!dateFlag) {
            System.out.println("格式错误");
            return false;
        }
        System.out.println("格式正确");
        return true;
    }

    /**
     * 2个月前
     * @return
     */
    public static long beforeTwoMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -2);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        System.out.println(calendar.getTime().getTime() / 1000);
        return calendar.getTime().getTime() / 1000;
    }
    public static String getNowYmd(){
        // 获得当前日期
        Calendar calendar = Calendar.getInstance();
        // 获得年月日
        String date=  String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.get(Calendar.MONTH)+1)
                + "-" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return date;
    }



    public static void main(String[] args)throws Exception{
        System.out.println(getNowYmd());
    }

}
