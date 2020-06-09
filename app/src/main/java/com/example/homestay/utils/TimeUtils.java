package com.example.homestay.utils;


import java.time.LocalDate;
import java.util.Calendar;

public class TimeUtils {

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
}
