package com.ambita.controller.api.report;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class PersonWithData {

  private String name;
  private List<List<Long>> data;


  public PersonWithData(String name) {
    this.name = name;
    data = new ArrayList<>();
  }

  public void add(LocalDate localDate, Long value) {
    ZoneId zoneId = ZoneId.of("Europe/Oslo");
    long epoch = localDate.atStartOfDay(zoneId).toEpochSecond();
    add(epoch * 1000, value);
  }

  private void add(Long timestampInMs, Long value) {
    List<Long> list = Arrays.asList(timestampInMs, value);
    this.data.add(list);
  }

  public Long findLatestValue() {
    Optional<List<Long>> latestElement = findLatestElement();
    if (!latestElement.isPresent()) {
      return 0L;
    }


    if (latestElement.get().size() < 1) {
      return 0L;
    }

    return latestElement.get().get(1);
  }

  private Optional<List<Long>> findLatestElement() {
    if (data.isEmpty()) {
      return Optional.empty();
    }

    List<Long> lastElement = data.get(data.size() - 1);
    return Optional.of(lastElement);
  }
}
