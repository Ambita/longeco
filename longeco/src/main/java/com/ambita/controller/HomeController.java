package com.ambita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ambita.service.ReportService;

@Controller
public class HomeController {

  private ReportService reportService;

  public HomeController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping({"/home", "/"})
  public String home(Model model) {

    model.addAttribute("usersSummary", reportService.getUsersSummary());

    model.addAttribute("eventName", reportService.getEventName());

    model.addAttribute("rangeText", reportService.getRangeText());

    return "home";
  }
}