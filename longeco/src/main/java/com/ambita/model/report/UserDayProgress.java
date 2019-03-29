package com.ambita.model.report;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDayProgress {

  public Integer userId;
  public String userName;
  public LocalDate date;
  public Integer distanceDay;
  public Integer distanceSoFar;
}