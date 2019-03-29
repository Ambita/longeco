package com.ambita.controller.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class PingProperties {

  @Value("${spring.application.env}")
  private String env;
  @Value("${spring.application.name}")
  private String name;
}
