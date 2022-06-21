package cn.lvhaosir.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(dateDate);
    }

    public static Date dateIncrease(Date dateDate, int day) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateDate);
        instance.add(Calendar.DATE, day);
        return formatter.parse(formatter.format(instance.getTime()));
    }

    public static Date dateDecrease(Date dateDate, int day) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar instance = Calendar.getInstance();
        instance.setTime(dateDate);
        instance.add(Calendar.DATE, -day);
        return formatter.parse(formatter.format(instance.getTime()));
    }

    public static void main(String[] args) {
//        System.out.println(getLastYearMonth());
//        System.out.println(getCurrentMonthDay());
        System.out.println(getDayByYearMonth("2022-04"));
    }

    public static List<String> getLastYearMonth() {
        List<String> list = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            int k = c.get(Calendar.YEAR);
            int j = c.get(Calendar.MONTH) + 1 - i;
            String date = "";
            if (j >= 1) {
                date = k + "-" + (j >= 10 ? "" : "0") + j;
            } else {
                int p = 11 - i;//剩余循环次数
                int m = c.get(Calendar.YEAR) - 1;
                int n = c.get(Calendar.MONTH) + 2 + p;
                date = m + "-" + (n >= 10 ? "" : "0") + n;
            }
            list.add(date);
        }
        return list;
    }

    public static List<String> getCurrentMonthDay() {
        Calendar cd = Calendar.getInstance();
        int year = cd.get(Calendar.YEAR);
        int month = cd.get(Calendar.MONTH);
        int day = 0;

        int[] monDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ( ( (year) % 4 == 0 && (year) % 100 != 0) ||(year) % 400 == 0) {
            day = monDays[month]++;
        } else {
            day = monDays[month];
        }

        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= day; i++) {
            list.add(year+"-"+(month+1)+"-"+i);
        }
        return list;
    }

    public static List<String> getDayByYearMonth(String yearMonth) {
        String[] split = yearMonth.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]) - 1;
        int day = 0;

        int[] monDays = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if ( ( (year) % 4 == 0 && (year) % 100 != 0) ||(year) % 400 == 0) {
            day = monDays[month]++;
        } else {
            day = monDays[month];
        }
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= day; i++) {
            list.add(year+"-"+(month+1)+"-"+i);
        }
        return list;
    }
}
