package com.example.homestay;

import com.example.homestay.utils.CommonUtils;
import com.example.homestay.utils.DateTimeUtils;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println(CommonUtils.getTimeStamp());
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        System.out.println(calendar.getTimeInMillis());

    }
}