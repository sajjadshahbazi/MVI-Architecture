package com.sharifin.calendar;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by mehrdadsml@gmail.com on 2017-06-07.
 */

public class CalendarUtils {

    private static String[] persianMonths = new String[]{"فروردین",
            "اردیبهشت",
            "خرداد",
            "تیر",
            "مرداد",
            "شهریور",
            "مهر",
            "آبان",
            "آذر",
            "دی",
            "بهمن",
            "اسفند"};
    private static String[] islamicMonths = new String[]{"محرم",
            "صفر",
            "ربيع الأول",
            "ربیع الثاني",
            "جمادى الأولى",
            "جمادی الثاني",
            "رجب",
            "شعبان",
            "رمضان",
            "شوال",
            "ذو القعده",
            "ذو الحجه"};
    private static String[] gregorianMonths = new String[]{"ژانویه",
            "فوریه",
            "مارس",
            "آوریل",
            "مه",
            "ژوئن",
            "ژوئیه",
            "اوت",
            "سپتامبر",
            "اکتبر",
            "نوامبر",
            "دسامبر"};
    private static String[] weekDays = new String[]{"شنبه",
            "یک‌شنبه",
            "دوشنبه",
            "سه‌شنبه",
            "چهارشنبه",
            "پنج‌شنبه",
            "جمعه"};

    public static String timeToString(long datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetime);
        return String.format(Locale.ENGLISH, "%d:%02d", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

    public static String clockToString(int hour, int minute) {
        return String.format(Locale.ENGLISH, "%d:%02d", hour, minute);
    }

    public static String datetimeToPersianString(long datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetime);
        PersianDate persianDate = new PersianDate(calendar);
        return persianDate.getDayOfMonth() + " " + getMonthName(persianDate) + " " + persianDate.getYear();
    }

    public static String dateToString(AbstractDate date) {
        return date.getDayOfMonth() + ' ' + getMonthName(date) + ' ' + date.getYear();
    }

    public static String datetimeToShortString(long datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetime);
        PersianDate persianDate = new PersianDate(calendar);
        return persianDate.getMonth() + "/" + persianDate.getDayOfMonth();
    }

    public static String datetimeToString(long datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(datetime);
        PersianDate persianDate = new PersianDate(calendar);
        return persianDate.getYear() + "/" + persianDate.getMonth() + "/" + persianDate.getDayOfMonth();
    }

    public static String[] monthsNamesOfCalendar(AbstractDate date) {
        if (date instanceof PersianDate)
            return persianMonths.clone();
        else if (date instanceof IslamicDate)
            return islamicMonths.clone();
        else
            return gregorianMonths.clone();
    }

    public static String getMonthName(AbstractDate date) {
        return monthsNamesOfCalendar(date)[date.getMonth() - 1];
    }

    public static String getWeekDayName(AbstractDate date) {
        if (date instanceof IslamicDate)
            date = DateConverter.islamicToCivil((IslamicDate) date);
        else if (date instanceof PersianDate)
            date = DateConverter.persianToCivil((PersianDate) date);

        return weekDays[date.getDayOfWeek() % 7];
    }
}
