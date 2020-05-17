package com.example.homestay;

import com.example.homestay.utils.CalendarUtil;
import com.example.homestay.utils.CommonUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.junit.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneOffset;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        System.out.println(CommonUtils.getRate("1999000", 11));
    }
}