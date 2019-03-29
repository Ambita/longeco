package com.ambita.controller;

import java.util.Optional;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ambita.controller.api.User;
import com.ambita.longeco.exception.DuplicationException;
import com.ambita.service.UserService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

  private static final String BASE_URL = "http://localhost:8080/mockedUrl";
  private static final String LOCATION_HEADER_NAME = "location";
  private User user = new User(Optional.empty(), "abc", "secret", "abc@ambita.com", "Abc", true);
  private com.ambita.model.User modelUser =
      new com.ambita.model.User(100L, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), user.getActive());
  private UserService userService = mock(UserService.class);

  @Test
  public void newUser() {
    when(userService.createUser(any())).thenReturn(modelUser);
    UserController controller = new UserController(userService, BASE_URL);
    ResponseEntity response = controller.createUser(user);
    assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
    assertTrue(response.getHeaders().containsKey(LOCATION_HEADER_NAME));
    assertTrue(response.getHeaders().get(LOCATION_HEADER_NAME).get(0).startsWith(BASE_URL));
  }

  @Test
  public void addExistingUserShouldFail() {
    when(userService.createUser(any())).thenThrow(new DuplicationException("User exists"));
    UserController controller = new UserController(userService, BASE_URL);
    ResponseEntity response = controller.createUser(user);
    assertEquals(HttpStatus.CONFLICT.value(), response.getStatusCodeValue());
  }
}