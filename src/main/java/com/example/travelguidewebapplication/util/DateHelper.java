package com.example.travelguidewebapplication.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

public class DateHelper {
    public static LocalDateTime getAzerbaijanDateTime() {
        Calendar azerbaijanCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Baku"));
        int year = azerbaijanCalendar.get(Calendar.YEAR);
        int month = azerbaijanCalendar.get(Calendar.MONTH) + 1;  // Ay, 0-indeksli olduğu üçün +1 olur
        int day = azerbaijanCalendar.get(Calendar.DAY_OF_MONTH);
        int hour = azerbaijanCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = azerbaijanCalendar.get(Calendar.MINUTE);
        int second = azerbaijanCalendar.get(Calendar.SECOND);

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
