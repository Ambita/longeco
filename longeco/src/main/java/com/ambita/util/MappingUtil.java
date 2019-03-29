package com.ambita.util;

import java.util.List;

import com.ambita.gui_model.Day;
import com.ambita.gui_model.LoggedInUser;
import com.ambita.model.Log;
import com.ambita.model.User;

import static java.time.ZoneId.systemDefault;
import static java.util.Date.from;
import static java.util.stream.Collectors.toList;

public class MappingUtil {

  public static List<Log> mapDaysToLogs(List<Day> days) {
    return days.stream().map(MappingUtil::mapDayToLog).collect(toList());
  }

  public static List<Day> mapLogsToDays(List<Log> logs) {
    return logs.stream().map(MappingUtil::mapLogToDay).collect(toList());
  }

  private static Log mapDayToLog(Day day) {
    return Log.builder()
        .date(day.getDate().toInstant().atZone(systemDefault()).toLocalDate())
        .distance(day.getDistance())
        .build();
  }

  private static Day mapLogToDay(Log log) {
    return Day.builder()
        .date(from(log.getDate().atStartOfDay(systemDefault()).toInstant()))
        .distance(log.getDistance())
        .build();
  }

  public static LoggedInUser mapUserToLoggedInUser(User user) {
    return LoggedInUser.builder()
        .name(user.name)
        .build();
  }
}