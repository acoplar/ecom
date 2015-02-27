/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecom.ejb.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.faces.context.FacesContext;
import org.joda.time.DateTime;
import org.joda.time.Days;

public class DateTimeUtil {

    public final static String pattern = "dd-MMM-yyyy";
    public final static String PATTERN_DB = "yyyy-MM-dd";
    public final static String SELECT_FROM_TIME = " 00:00:00";
    public final static String SELECT_TO_TIME = " 23:59:59";
    public final static long day = 1000 * 60 * 60 * 24;
    public final static int dayOfYear = 356;
    public final static int dayOfMonth = 30;
    public final static int dayOfWeek = 7;

    public static Date currentDate() {
        Calendar c = new GregorianCalendar(Locale.US);
        c.setTimeInMillis(System.currentTimeMillis());
        return c.getTime();
    }

    public static Date currentDate(int d) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Locale locale = facesContext.getViewRoot().getLocale();
        Calendar c = new GregorianCalendar(locale);
        c.setTimeInMillis(System.currentTimeMillis() - (d * day));
        return c.getTime();
    }

    public static Calendar calendarDate() {
        Calendar c = new GregorianCalendar(Locale.US);
        c.setTimeInMillis(System.currentTimeMillis());
        return c;
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static String dateToString(Date d, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
        return sdf.format(d);
    }

    public static String dateToString(Date d, String pattern, Locale l) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, l);
        return sdf.format(d);
    }

    public static String dateToStringDB(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return sdf.format(d);
    }

    public static TimeZone getTimeZone() {
        TimeZone t = TimeZone.getTimeZone("Asia/Bangkok");
        return t;
    }

    public static long calculateDays(Date dateEarly, Date dateLater) {
        return (dateLater.getTime() - dateEarly.getTime()) / (24 * 60 * 60 * 1000);
    }

    public static void main(String[] args) {
        Calendar c = new GregorianCalendar(Locale.US);
        c.setTime(DateTimeUtil.currentDate());
        Date d1 = c.getTime();
//
//        System.out.println("" + d1);
        c.set(Calendar.DATE, 11);
        Date d2 = c.getTime();

        int days = Days.daysBetween(new DateTime(d1), new DateTime(d2)).getDays();

        System.out.println("days = " + days);
    }
}
