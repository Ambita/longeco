package com.ambita.gui_model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ambita.gui_model.Day;
import com.ambita.gui_model.Week;

import static java.util.stream.IntStream.range;

public class WeekValidator implements Validator {

  private static final int MIN_DISTANCE = 0;
  private static final int MAX_DISTANCE = 999;

  @Override
  public boolean supports(Class<?> clazz) {
    return Week.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Week week = (Week) target;
    range(0, week.getDays().size())
        .forEach(idx -> {
          Day day = week.getDays().get(idx);
          if (day.getDistance() == null) {
            errors.rejectValue("days[" + idx + "].distance", "distance", "The distance must be an positive integer");
          }
          if (!day.distanceBetween(MIN_DISTANCE,MAX_DISTANCE)) {
            errors.rejectValue("days[" + idx + "].distance", "distance", "The distance should be between " + MIN_DISTANCE + " and " + MAX_DISTANCE);
          }
        });
  }
}