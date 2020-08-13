package com.example.homestay.utils;

import com.aminography.primecalendar.PrimeCalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTimeUtils {

    public static Long getCurrentTimeSecond(){
        return System.currentTimeMillis()/1000;
    }

    public static String timeReview(String time){
        long timeL = Long.parseLong(time);
        long now = getCurrentTimeSecond();
        long timeDiff = now - timeL;
        if (timeDiff < 0) return "";
        if (timeDiff > 86400 * 7) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timeL*1000);
            return "Cập nhật lần cuối " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
        }
        if (timeDiff > 86400) {
            return "Cập nhật lần cuối " + timeDiff/86400 + " ngày trước";
        }
        if (timeDiff > 3600) {
            return "Cập nhật lần cuối " + timeDiff/3600 + " giờ trước";
        }
        return "Cập nhật lần cuối " + timeDiff/60 + " phút trước";
    }

    public static List<Long> calendarToHouseDate(List<Calendar> calendars){
        List<Long> houseDates = new ArrayList<>();
        for(int i = 0; i < calendars.size(); i ++){
            long sec = calendars.get(i).getTimeInMillis()/1000;
            houseDates.add(sec);
        }
        return houseDates;
    }

    public static List<Long> getListHouseDate(long start, long end){
        List<Long> houseDates = new ArrayList<>();
        while (start + 86400 != end){
            houseDates.add(start);
            start = start + 86400;
        }
        houseDates.add(end);
        return houseDates;
    }

    public static String dayOfWeekString(int day){
        if(day == 1) return "CN";
        else return "Th" + day;
    }

    public static String calendarToString(Calendar calendar){
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        return dayOfWeekString(calendar.get(Calendar.DAY_OF_WEEK)) + " " + String.format("%02d", day) + "/" + String.format("%02d", month);
    }

    public static String houseDateToString(int houseDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) houseDate * 1000);
        return calendarToString(calendar);
    }

    public static List<PrimeCalendar> getListPrimeCalendar(PrimeCalendar startDate, PrimeCalendar endDate){
        List<PrimeCalendar> list = new ArrayList<>();
        PrimeCalendar calendar = startDate.clone();
        while (!calendar.equals(endDate)){
            list.add(calendar.clone());
            calendar.add(Calendar.DATE, 1);
        }
        list.add(endDate);
        return list;
    }
}
