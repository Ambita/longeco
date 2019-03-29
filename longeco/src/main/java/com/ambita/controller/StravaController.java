package com.ambita.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StravaController {

  public StravaController() {

  }

  @GetMapping({"/strava"})
  public String home() {
    return "strava";
  }
}