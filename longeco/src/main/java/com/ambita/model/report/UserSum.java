package com.ambita.model.report;

import java.time.LocalDate;

import com.ambita.util.DateUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSum {

  public String name;
  public Integer distance;
  public Integer count;
  public Integer avgDistancePerDay;
  public LocalDate lastDate;

  public String lastDateText() {
    if (lastDate == null) {
      return "";
    }
    return DateUtil.formatForPresentation(lastDate);
  }
}