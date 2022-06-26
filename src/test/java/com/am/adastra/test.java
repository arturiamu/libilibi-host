package com.am.adastra;

import com.am.adastra.util.GetIpInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Calendar calendarLogTime = Calendar.getInstance();
        calendarLogTime.setTime(new Date());
        int hourNow = calendarLogTime.get(Calendar.HOUR_OF_DAY);
//        calendarLogTime.setTime();
        int hourLog = calendarLogTime.get(Calendar.HOUR_OF_DAY);
        System.out.println(hourNow);
    }
}
