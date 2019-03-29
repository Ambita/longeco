package com.ambita.gui_model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ambita.util.DateUtil.calculateNextAndPreviousWeek;

@Data
@NoArgsConstructor
public class Week {

  private int weekNumber;
  private int year;

  private int previousWeekNumber;
  private int previousYear;

  private int nextWeekNumber;
  private int nextYear;

  private List<Day> days = new ArrayList<>();

  public Week(int weekNumber, int year, List<Day> days) {
    this.weekNumber = weekNumber;
    this.year = year;
    int[] nextAndPreviousWeek = calculateNextAndPreviousWeek(weekNumber, year);
    this.previousWeekNumber = nextAndPreviousWeek[0];
    this.previousYear = nextAndPreviousWeek[1];
    this.nextWeekNumber = nextAndPreviousWeek[2];
    this.nextYear = nextAndPreviousWeek[3];
    this.days = new ArrayList<>(days);
  }
}