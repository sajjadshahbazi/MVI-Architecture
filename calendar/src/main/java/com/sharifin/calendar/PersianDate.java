package com.sharifin.calendar;

import java.util.Calendar;
import java.util.Locale;

/**
 * @author Amir
 * @author ebraminio (implementing isLeapYear)
 */

public class PersianDate extends AbstractDate {
    private int year;
    private int month;
    private int day;

    public static final int[] daysInMonth = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};

    public static final String[] monthNames = {"", "فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند"};

    public PersianDate(int year, int month, int day) {
        setYear(year);
        // Initialize day, so that we get no exceptions when setting month
        this.day = 1;
        setMonth(month);
        setDayOfMonth(day);
    }

    public PersianDate clone() {
        return new PersianDate(getYear(), getMonth(), getDayOfMonth());
    }

    public int getDayOfMonth() {
        return day;
    }

    public void setDayOfMonth(int day) {
        if (day < 1)
            throw new DayOutOfRangeException(
                    CalendarConstants.DAY + " " + day + " " + CalendarConstants.IS_OUT_OF_RANGE);

        if (month <= 6 && day > 31)
            throw new DayOutOfRangeException(
                    CalendarConstants.DAY + " " + day + " " + CalendarConstants.IS_OUT_OF_RANGE);

        if (month > 6 && month <= 12 && day > 30)
            throw new DayOutOfRangeException(
                    CalendarConstants.DAY + " " + day + " " + CalendarConstants.IS_OUT_OF_RANGE);

        if (isLeapYear() && month == 12 && day > 30)
            throw new DayOutOfRangeException(
                    CalendarConstants.DAY + " " + day + " " + CalendarConstants.IS_OUT_OF_RANGE);

        if ((!isLeapYear()) && month == 12 && day > 29)
            throw new DayOutOfRangeException(
                    CalendarConstants.DAY + " " + day + " " + CalendarConstants.IS_OUT_OF_RANGE);

        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public String getMonthName() {
        return monthNames[month];
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12)
            throw new MonthOutOfRangeException(
                    CalendarConstants.MONTH + " " + month + " " + CalendarConstants.IS_OUT_OF_RANGE);

        // Set the day again, so that exceptions are thrown if the
        // day is out of range
        setDayOfMonth(day);

        this.month = month;
    }

    public int getWeekOfYear() {
        //throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
        int days;
        if (this.month <= 7) {
            days = (this.month - 1) * 31;
        } else {
            //days = 0 + 186;
            days = ((this.month - 7) * 30) + 186;
        }
        return ((days + this.day) / 7) + 1;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year == 0)
            throw new YearOutOfRangeException(CalendarConstants.YEAR_0_IS_INVALID);

        this.year = year;
    }

    public void rollDay(int amount, boolean up) {
        throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
    }

    public void rollMonth(int amount, boolean up) {
        throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
    }

    public void rollYear(int amount, boolean up) {
        throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
    }

    public String getEvent() {
        throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
    }

    public int getDayOfWeek() {
        int dayOfWeek = DateConverter.persianToCivil(clone()).getDayOfWeek() % 7;
        return dayOfWeek + 1;
    }

    public int getDayOfYear() {
        //throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
        int days;
        if (this.month <= 7) {
            days = (this.month - 1) * 31;
        } else {
            days = ((this.month - 7) * 30) + 186;
        }
        return days + this.day;
    }

    public int getWeekOfMonth() {
        //throw new RuntimeException(CalendarConstants.NOT_IMPLEMENTED_YET);
        return ((this.day) / 7) + 1;
    }

    public boolean isLeapYear() {
        int y;
        if (year > 0)
            y = year - 474;
        else
            y = 473;
        return (((((y % 2820) + 474) + 38) * 682) % 2816) < 682;
    }

    public String toString() {
        return String.format(Locale.US, "%04d/%02d/%02d", getYear(), getMonth(), getDayOfMonth());
    }

    public boolean equals(PersianDate persianDate) {
        return this.getDayOfMonth() == persianDate.getDayOfMonth()
                && this.getMonth() == persianDate.getMonth()
                && (this.getYear() == persianDate.getYear() || this.getYear() == -1);
    }

    public void addDay() {
        if (day == daysInMonth[month - 1]) {
            if (month == 12) {
                if (isLeapYear()) {
                    day++;
                } else {
                    day = 1;
                    month = 1;
                    year++;
                }
            } else {
                day = 1;
                month++;
            }
        } else {
            if (isLeapYear() && day == 30 && month == 12) {
                day = 1;
                month = 1;
                year++;
            } else {
                day++;
            }
        }
    }

    public void minusDay() {
        if (day == 1) {
            if (month == 1) {
                PersianDate pDate = new PersianDate(year - 1, 1, 1);
                if (pDate.isLeapYear()) {
                    day = 30;
                    month = 12;
                    year--;
                } else {
                    day = 29;
                    month = 12;
                    year--;
                }
            } else {
                day = daysInMonth[month - 2];
                month--;
            }
        } else {
            day--;
        }
    }

    public void changeDay(int offset) {
        int count = (offset > 0) ? offset : offset * -1;
        for (int i = 0; i < count; i++) {
            if (offset < 0)
                minusDay();
            else if (offset > 0)
                addDay();
        }
    }

    public long diffDays(PersianDate pDate) {
        CivilDate cDate = DateConverter.persianToCivil(pDate);
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.MILLISECOND, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.DAY_OF_MONTH, cDate.getDayOfMonth());
        calDate.set(Calendar.MONTH, cDate.getMonth() - 1);
        calDate.set(Calendar.YEAR, cDate.getYear());

        CivilDate thisDate = DateConverter.persianToCivil(clone());
        Calendar calThisDate = Calendar.getInstance();
        calThisDate.set(Calendar.MILLISECOND, 0);
        calThisDate.set(Calendar.SECOND, 0);
        calThisDate.set(Calendar.MINUTE, 0);
        calThisDate.set(Calendar.HOUR_OF_DAY, 0);
        calThisDate.set(Calendar.DAY_OF_MONTH, thisDate.getDayOfMonth());
        calThisDate.set(Calendar.MONTH, thisDate.getMonth() - 1);
        calThisDate.set(Calendar.YEAR, thisDate.getYear());

        long diff = calThisDate.getTimeInMillis() - calDate.getTimeInMillis();
        long days = Math.round((double) diff / (24 * 60 * 60 * 1000));
        days = (days < 0) ? days * -1 : days;
        return days;

        //if(pDate.isLargerThan(clone())){

        //}
    }

    public void addWeek() {
        for (int i = 0; i < 7; i++) {
            addDay();
        }
    }

    public void minusWeek() {
        for (int i = 0; i < 7; i++) {
            minusDay();
        }
    }

    public void changeWeek(int offset) {
        int count = (offset > 0) ? offset : offset * -1;
        for (int i = 0; i < count; i++) {
            if (offset < 0)
                minusWeek();
            else if (offset > 0)
                addWeek();
        }
    }

    public void addMonth() {
        if (day == daysInMonth[month - 1]) {
            if (month == 6) {
                day = 30;
            } else if (month == 11) {
                if (!isLeapYear()) {
                    day = 29;
                }
            } else if (month == 12) {
                day = 31;
            }
        }
        if (month == 12) {
            if (day == 30) {
                day = 31;
            }
            month = 1;
            year++;
        } else {
            month++;
        }
    }

    public void minusMonth() {
        if (day == daysInMonth[month - 1]) {
            if (month == 7) {
                day = 31;
            } else if (month == 12) {
                if (!isLeapYear()) {
                    day = 30;
                }
            } else if (month == 1) {
                PersianDate pDate = new PersianDate(year - 1, 1, 1);
                if (pDate.isLeapYear()) {
                    day = 30;
                } else {
                    day = 29;
                }
            }
        }
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
    }

    public void changeMonth(int offset) {
        int count = (offset > 0) ? offset : offset * -1;
        for (int i = 0; i < count; i++) {
            if (offset < 0)
                minusMonth();
            else if (offset > 0)
                addMonth();
        }
    }

    public void addYear() {
        if (month == 12) {
            if (day == 30) {
                day = 29;
            }
        }
        year++;
    }

    public void minusYear() {
        if (month == 12) {
            if (day == 30) {
                day = 29;
            }
        }
        year--;
    }

    public Boolean isLargerThan(PersianDate pDate) {
        CivilDate cDate = DateConverter.persianToCivil(pDate);
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.MILLISECOND, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.DAY_OF_MONTH, cDate.getDayOfMonth());
        calDate.set(Calendar.MONTH, cDate.getMonth() - 1);
        calDate.set(Calendar.YEAR, cDate.getYear());

        CivilDate thisDate = DateConverter.persianToCivil(clone());
        Calendar calThisDate = Calendar.getInstance();
        calThisDate.set(Calendar.MILLISECOND, 0);
        calThisDate.set(Calendar.SECOND, 0);
        calThisDate.set(Calendar.MINUTE, 0);
        calThisDate.set(Calendar.HOUR_OF_DAY, 0);
        calThisDate.set(Calendar.DAY_OF_MONTH, thisDate.getDayOfMonth());
        calThisDate.set(Calendar.MONTH, thisDate.getMonth() - 1);
        calThisDate.set(Calendar.YEAR, thisDate.getYear());

        return (calThisDate.getTimeInMillis() > calDate.getTimeInMillis());
    }

    public Boolean isSmallerThan(PersianDate pDate) {
        CivilDate cDate = DateConverter.persianToCivil(pDate);
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.MILLISECOND, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.DAY_OF_MONTH, cDate.getDayOfMonth());
        calDate.set(Calendar.MONTH, cDate.getMonth() - 1);
        calDate.set(Calendar.YEAR, cDate.getYear());

        CivilDate thisDate = DateConverter.persianToCivil(clone());
        Calendar calThisDate = Calendar.getInstance();
        calThisDate.set(Calendar.MILLISECOND, 0);
        calThisDate.set(Calendar.SECOND, 0);
        calThisDate.set(Calendar.MINUTE, 0);
        calThisDate.set(Calendar.HOUR_OF_DAY, 0);
        calThisDate.set(Calendar.DAY_OF_MONTH, thisDate.getDayOfMonth());
        calThisDate.set(Calendar.MONTH, thisDate.getMonth() - 1);
        calThisDate.set(Calendar.YEAR, thisDate.getYear());

        return (calThisDate.getTimeInMillis() < calDate.getTimeInMillis());
    }

    public long getTimeInMillis() {
        CivilDate cDate = DateConverter.persianToCivil(clone());
        Calendar calDate = Calendar.getInstance();
        calDate.set(Calendar.MILLISECOND, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        calDate.set(Calendar.DAY_OF_MONTH, cDate.getDayOfMonth());
        calDate.set(Calendar.MONTH, cDate.getMonth() - 1);
        calDate.set(Calendar.YEAR, cDate.getYear());
        return calDate.getTimeInMillis();
    }

    public void setTimeInMillis(long timeInMillis) {
        Calendar calDate = Calendar.getInstance();
        calDate.setTimeInMillis(timeInMillis);
        calDate.set(Calendar.MILLISECOND, 0);
        calDate.set(Calendar.SECOND, 0);
        calDate.set(Calendar.MINUTE, 0);
        calDate.set(Calendar.HOUR_OF_DAY, 0);
        CivilDate cDate = new CivilDate(calDate);
        PersianDate pDate = DateConverter.civilToPersian(cDate);
        this.year = pDate.getYear();
        this.month = pDate.getMonth();
        this.day = pDate.getDayOfMonth();
    }

    public PersianDate(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        CivilDate cDate = new CivilDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        PersianDate pDate = DateConverter.civilToPersian(cDate);
        this.year = pDate.getYear();
        this.month = pDate.getMonth();
        this.day = pDate.getDayOfMonth();
    }

    public PersianDate(Calendar cal) {
        CivilDate cDate = new CivilDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        PersianDate pDate = DateConverter.civilToPersian(cDate);
        this.year = pDate.getYear();
        this.month = pDate.getMonth();
        this.day = pDate.getDayOfMonth();
    }
}
