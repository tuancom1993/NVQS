package com.nghiavuquansu.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

public class AgeUtil {
	
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
	
	public static int getAge(Date dateFrom ,Date birdate) {
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
}
