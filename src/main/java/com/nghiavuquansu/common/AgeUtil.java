package com.nghiavuquansu.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

public class AgeUtil {
    
    private static Date dateCalculateAge = new Date();

	public static Date getDateCalculateAge() {
        return dateCalculateAge;
    }

    public static void setDateCalculateAge(Date dateCalculateAge) {
        AgeUtil.dateCalculateAge = dateCalculateAge;
    }

    public static int getAge(Date birdate) {
        Date now = new Date();
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
}
