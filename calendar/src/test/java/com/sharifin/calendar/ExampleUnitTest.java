package com.sharifin.calendar;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testTimeStampTimeZoned() {
        String actual = CalendarUtils.datetimeToPersianString(1558248557000L);
        DateConverter dateConverter = new DateConverter();
        Calendar a = Calendar.getInstance();
        a.setTimeInMillis(1558248557000L);
        a.get(Calendar.HOUR_OF_DAY);
        assertEquals(4, 1558248557);
    }
}