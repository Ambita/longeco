package com.ambita.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ambita.interceptor.AddLoggedInUserInterceptor;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  private AddLoggedInUserInterceptor addLoggedInUserInterceptor;

  @Autowired
  public void setAddLoggedInUserInterceptor(AddLoggedInUserInterceptor addLoggedInUserInterceptor) {
    this.addLoggedInUserInterceptor = addLoggedInUserInterceptor;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(addLoggedInUserInterceptor);
  }
}