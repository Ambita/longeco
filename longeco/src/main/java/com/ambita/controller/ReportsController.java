package com.ambita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {

  public ReportsController() {

  }

  @GetMapping({"/reports"})
  public String home() {
    return "reports";
  }
}