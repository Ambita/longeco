package com.ambita.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static java.time.ZoneId.systemDefault;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MILLISECOND;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONDAY;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.WEEK_OF_YEAR;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;
import static java.util.stream.Collectors.toList;

public class DateUtil {

  public static List<LocalDate> getDatesFromWeek(int week, int year) {
    Calendar calendar = getInstance();
    calendar.setWeekDate(year, week, MONDAY);
    calendar.set(HOUR, 0);
    calendar.set(MINUTE, 0);
    calendar.set(SECOND, 0);
    calendar.set(MILLISECOND, 0);

    return IntStream.rangeClosed(0, 6).mapToObj(i -> {
      Calendar orgCalendar = (Calendar) calendar.clone();
      orgCalendar.add(DAY_OF_MONTH, i);
      return orgCalendar.getTime().toInstant().atZone(systemDefault()).toLocalDate();
    }).collect(toList());
  }

  public static int getCurrentWeekNumber() {
    Calendar cal = getInstance();
    cal.setTime(new Date());
    return cal.get(WEEK_OF_YEAR);
  }

  public static Integer getCurrentYear() {
    Calendar cal = getInstance();
    cal.setTime(new Date());
    return cal.get(YEAR);
  }

  public static boolean isWeekYearValid(int week, int year) {
    Calendar calendar = getInstance();
    calendar.setLenient(false);
    try {
      calendar.setWeekDate(year, week, MONDAY);
    }
    catch (Exception ignore) {
      return false;
    }
    return calendar.get(WEEK_OF_YEAR) == week && calendar.get(YEAR) == year;
  }

  public static int[] calculateNextAndPreviousWeek(int week, int year) {
    Calendar cal = getInstance();
    cal.set(YEAR, year);
    cal.set(WEEK_OF_YEAR, week);
    cal.add(WEEK_OF_YEAR, 1);
    int nextWeek = cal.get(WEEK_OF_YEAR);
    int nextYear = cal.get(YEAR);

    cal.add(WEEK_OF_YEAR, -2);
    int previousWeek = cal.get(WEEK_OF_YEAR);
    int previousYear = cal.get(YEAR);

    return new int[] {previousWeek, previousYear, nextWeek, nextYear};
  }


  public static String formatForPresentation(LocalDate localDate) {
    return format("dd.MM.yyyy", localDate);
  }

  public static String format(String format, LocalDate localeDate) {
    return localeDate.format(DateTimeFormatter.ofPattern(format));
  }
}