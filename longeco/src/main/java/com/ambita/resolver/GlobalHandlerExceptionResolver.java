package com.ambita.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class GlobalHandlerExceptionResolver implements HandlerExceptionResolver {

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("error");
    modelAndView.getModel().put("message", exception.getClass() + ": " +  exception.getMessage() + ": " + exception.getCause());

    return modelAndView;
  }
}