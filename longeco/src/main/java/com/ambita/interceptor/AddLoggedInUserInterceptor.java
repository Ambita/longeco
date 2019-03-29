package com.ambita.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ambita.gui_model.LoggedInUser;
import com.ambita.model.User;
import com.ambita.repository.UserRepository;

import static com.ambita.util.MappingUtil.mapUserToLoggedInUser;

@Component
public class AddLoggedInUserInterceptor extends HandlerInterceptorAdapter{

  private UserRepository userRepository;

  @Autowired
  public AddLoggedInUserInterceptor(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (request.getUserPrincipal() != null && request.getAttribute("loggedInUser") == null) {
      LoggedInUser loggedInUser = createLoggedInUser(request.getUserPrincipal());
      request.setAttribute("loggedInUser", loggedInUser);
    }
    return super.preHandle(request, response, handler);
  }

  private LoggedInUser createLoggedInUser(Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    return mapUserToLoggedInUser(user);
  }
}