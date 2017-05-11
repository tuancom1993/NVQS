package com.nghiavuquansu.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class AgeUtils {
    
    private static final String STRING_FORMAT_DATE_DD_MM_YYYY = "dd/MM/yyyy";
    private static final String STRING_FORMAT_DATETIME_DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
    
    private static Date dateCalculateAge = new Date();

	public static Date getDateCalculateAge() {
        return dateCalculateAge;
    }

    public static void setDateCalculateAge(Date dateCalculateAge) {
        AgeUtils.dateCalculateAge = dateCalculateAge;
    }

    public static int getAge(Date birdate) {
        Date now = dateCalculateAge;
        Calendar cNow = Calendar.getInstance();
        cNow.setTime(now);
        Calendar cBirthday = Calendar.getInstance();
        cBirthday.setTime(birdate);
        int year = cNow.get(Calendar.YEAR) - cBirthday.get(Calendar.YEAR);
        int m = cNow.get(Calendar.MONTH) - cBirthday.get(Calendar.MONTH);
        int d = cNow.get(Calendar.DAY_OF_MONTH) - cBirthday.get(Calendar.DAY_OF_MONTH);
        if (m < 0 || (m == 0 && d < 0)) {
            year--;
        }
        return year;
    }
	
	public static int getAge(Date dateFrom, Date birdate) {
        Calendar cNow = Calendar.getInstance();
        cNow.setTime(dateFrom);
        Calendar cBirthday = Calendar.getInstance();
        cBirthday.setTime(birdate);
        int year = cNow.get(Calendar.YEAR) - cBirthday.get(Calendar.YEAR);
        int m = cNow.get(Calendar.MONTH) - cBirthday.get(Calendar.MONTH);
        int d = cNow.get(Calendar.DAY_OF_MONTH) - cBirthday.get(Calendar.DAY_OF_MONTH);
        if (m < 0 || (m == 0 && d < 0)) {
            year--;
        }
        return year;
    }

    public static boolean isBetween(Date birdate, int from, int to) {
        boolean result = true;
        int age = getAge(birdate);
        if (age >= from && age <= to)
            result = true;
        else
            result = false;
        return result;
    }
    
    public static String getStringFromDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
    public static Date getDateFromString(String str){
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }
    
    public static String getStringFromDate(Date date, String strFormat){
        try {
            DateFormat dateFormat = new SimpleDateFormat(strFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public static Date getDateFromString(String strDate, String strFormat){
        try {
            DateFormat dateFormat = new SimpleDateFormat(strFormat);
            return dateFormat.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }
    
    public static Date getCurrentDateInVN(){
        try {
            Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

            DateFormat formatter = new SimpleDateFormat(STRING_FORMAT_DATETIME_DD_MM_YYYY_HH_MM_SS);
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));  
            String newVNTime = formatter.format(calendar.getTime());
            return getDateFromString(newVNTime, STRING_FORMAT_DATETIME_DD_MM_YYYY_HH_MM_SS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
