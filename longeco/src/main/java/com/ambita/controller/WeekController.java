package com.ambita.controller;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ambita.gui_model.Week;
import com.ambita.gui_model.WeekYear;
import com.ambita.gui_model.validator.WeekValidator;
import com.ambita.model.Log;
import com.ambita.service.LogService;

import static com.ambita.gui_model.WeekYear.today;
import static com.ambita.util.MappingUtil.mapDaysToLogs;
import static com.ambita.util.MappingUtil.mapLogsToDays;

@Controller
public class WeekController {

  private LogService logService;

  @Autowired
  public WeekController(LogService logService) {
    this.logService = logService;
  }

  @GetMapping("/week")
  public String week(Model model, @RequestParam(required = false) WeekYear weekYear, Principal principal) {

    weekYear = (weekYear == null) ? today() : weekYear;
    List<Log> logs = logService.getWeek(weekYear.getWeek(), weekYear.getYear(), principal.getName());
    Week week = new Week(weekYear.getWeek(), weekYear.getYear(), mapLogsToDays(logs));
    model.addAttribute("week", week);

    return "week";
  }

  @PostMapping("/week")
  public String saveWeek(@Valid @ModelAttribute Week week, BindingResult bindingResult, Principal principal) {

    if (bindingResult.hasErrors()) {
      System.out.println("has errors");
      return "week";
    }

    List<Log> logs = mapDaysToLogs(week.getDays());
    logService.saveWeek(week.getWeekNumber(), week.getYear(), logs, principal.getName());
    return "redirect:/week?weekYear=" + week.getWeekNumber() + "." + week.getYear();
  }

  @InitBinder("week")
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(new WeekValidator());
  }
}