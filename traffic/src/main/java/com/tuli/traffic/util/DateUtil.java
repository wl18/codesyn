package com.tuli.traffic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 1 * @Author: cl
 * 2 * @DateTime: 2019-06-26 13:01
 * 3
 */
public class DateUtil {

        private static SimpleDateFormat sdf = null;

        /**
         * 将Date格式转换为字符串格式
         *
         * @param date
         * @param dateStrCfg 例如：yyyy-MM-dd HH:mm:ss
         * @return
         */
        public static String dateToStr(Date date, String dateStrCfg) {

                sdf = new SimpleDateFormat(dateStrCfg);
                String dateStr = sdf.format(date);
                return dateStr;

        }

        /**
         * @param time
         * @param dateStrCfg 例如：yyyy-MM-dd HH:mm:ss
         * @return
         */
        public static Date strToDate(String time, String dateStrCfg) {

                Date date = null;
                sdf = new SimpleDateFormat(dateStrCfg);
                try {
                        date = sdf.parse(time);
                } catch (ParseException e) {
                        e.printStackTrace();
                }
                return date;
        }

        /**
         * 获取两个日期之间的天数
         *
         * @param before
         * @param after
         * @return
         */
        public static long getDistanceOfTwoDay(Date before, Date after) {

                Long beforeTime = before.getTime();
                Long afterTime = after.getTime();

                return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
        }

        /**
         * 获取当前时间
         *
         * @return
         */
        public static Date getCurrentDate() {
                return new Date(System.currentTimeMillis());
        }

        /**
         * 返回某个日期下几天的日期
         *
         * @param date
         * @param i
         * @return
         */
        public static Date getNextDay(Date date, int i) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
                return cal.getTime();
        }

        /**
         * 返回某个日期前几天的日期
         *
         * @param date
         * @param i
         * @return
         */
        public static Date getFrontDay(Date date, int i) {
                Calendar cal = new GregorianCalendar();
                cal.setTime(date);
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
                return cal.getTime();
        }

        /**
         * 获得一个日期所在的周的星期几的日期，如要找出2019年6月26日所在周的星期一是几号
         *
         * @param time：日期
         * @param dateStrCfg：例如：yyyy-MM-dd HH:mm:ss
         * @param num：星期几（星期天是一周的第一天）
         * @return
         */
        public static String getWeek(String time, String dateStrCfg, String num) {
                // 再转换为时间
                Date dd = DateUtil.strToDate(time, dateStrCfg);
                Calendar c = Calendar.getInstance();
                c.setTime(dd);
                if (num.equals("1")) {// 返回星期一所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                } else if (num.equals("2")) { // 返回星期二所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                } else if (num.equals("3")) {// 返回星期三所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                } else if (num.equals("4")) {// 返回星期四所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                } else if (num.equals("5")) {// 返回星期五所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                } else if (num.equals("6")) {// 返回星期六所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                } else if (num.equals("0")) {// 返回星期日所在的日期
                        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                }
                return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        }

        /**
         * 根据一个日期，返回是星期几的字符串
         *
         * @param time
         * @param dateStrCfg
         * @return
         */
        public static String getWeek(String time, String dateStrCfg) {
                // 再转换为时间
                Date date = strToDate(time, dateStrCfg);
                Calendar c = Calendar.getInstance();
                c.setTime(date);
                // int hour=c.get(Calendar.DAY_OF_WEEK);
                // hour中存的就是星期几了，其范围 1~7
                // 1=星期日 7=星期六，其他类推
                return new SimpleDateFormat("EEEE").format(c.getTime());
        }

        /**
         * 日期增长
         *
         * @param time
         * @param dayCount 增长天数
         * @return
         */
        public static String dateGrow(String time, int dayCount) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String str = "";
                try {
                        Date date = sdf.parse(time);
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        calendar.add(Calendar.DATE, dayCount);//
                        //把日期往后增加一天.整数往后推,负数往前移动
                        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
                        str = sdf.format(date);
                } catch (ParseException e) {
                        e.printStackTrace();
                }
                return str;
        }

        /**
         * 获取制定时间的日
         *
         * @param date
         * @return
         */
        public static int getDay(Date date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                return calendar.get(Calendar.DAY_OF_MONTH);
        }

//        publicInterface static void main(String[] args) {
//                String beforeStr = "2019-06-25 13:22:34";
//
//                String week = DateUtil.getWeek(beforeStr, "yyyy-MM-dd HH:mm:ss");
//
//                System.out.println(week);
//        }

}
