package com.ambita.mapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ambita.controller.api.report.PersonWithData;
import com.ambita.controller.api.report.ProgressReport;
import com.ambita.model.report.UserDayProgress;

public class ProgressReportMapper {

  public static ProgressReport map(List<UserDayProgress> progresses) {
    Map<String, List<UserDayProgress>> groupedByUser = progresses.stream().collect(
        Collectors.groupingBy(UserDayProgress::getUserName,
            Collectors.toList()));

    ProgressReport report = new ProgressReport();

    groupedByUser.forEach((key, value) -> {
      PersonWithData personWithData = new PersonWithData(key);
      value.forEach(day -> personWithData.add(day.date, day.distanceSoFar.longValue()));
      report.add(personWithData);
    });

    report.getSeries().sort(Comparator.comparing(PersonWithData::findLatestValue).reversed());

    int i=1;
    for (PersonWithData personWithData : report.getSeries()) {
      personWithData.setName(personWithData.getName() + " (" + i++ + ")");
    }
    return report;
  }
}
