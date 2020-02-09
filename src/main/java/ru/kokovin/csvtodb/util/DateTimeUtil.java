package ru.kokovin.csvtodb.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
    public static Date convert(String date) {
        String[] parts = date.split(" ");
        Calendar calendar = new GregorianCalendar();
        int year = Integer.parseInt(parts[0].split("-")[0]);
        int month = Integer.parseInt(parts[0].split("-")[1]);
        int day = Integer.parseInt(parts[0].split("-")[2]);
        int hour = Integer.parseInt(parts[1].split(":")[0]);
        int min = Integer.parseInt(parts[1].split(":")[1]);
        int sec = Integer.parseInt(parts[1].split(":")[2]);
        calendar.set(year, month, day, hour, min, sec);
        Date result = new Date(calendar.getTimeInMillis());
        return result;
    }
}
