package com.ambita.controller.api.report;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ProgressReport {

  private List<PersonWithData> series;

  public ProgressReport() {
    series = new ArrayList<>();
  }

  public void add(PersonWithData personWithData) {
    this.series.add(personWithData);
  }
}

