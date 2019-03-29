package com.ambita.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class EmailConfig {

  @Value("${email.from}")
  private String emailFrom;
  @Value("${email.host}")
  private String emailHost;
  @Value("${email.port}")
  private Integer emailPort;
}