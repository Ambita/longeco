package com.ambita.service;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ambita.longeco.exception.DuplicationException;
import com.ambita.model.User;
import com.ambita.repository.UserRepository;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

  private final UserRepository userRepository = mock(UserRepository.class);
  private final PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
  private UserService userService = new UserServiceImpl(userRepository, passwordEncoder);

  @After
  public void tearDown() throws Exception {
    Mockito.reset(userRepository, passwordEncoder);
  }

  @Test
  public void createNewUser() {
    User user = new User(null, "new", "1234", "new@ambita.com", "New User", true);
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
    when(userRepository.save(user)).thenReturn(user);
    when(passwordEncoder.encode(user.password)).thenReturn("TOP SECRET");

    User persistedUser = userService.createUser(user);
    verify(userRepository, times(1)).findByEmail(user.email);
    verify(userRepository, times(1)).save(user);
    verify(passwordEncoder, times(1)).encode("1234");
  }

  @Test
  public void createDupliccatedUSer() {
    User user = new User(null, "new", "1234", "new@ambita.com", "New User", true);
    when(userRepository.findByEmail(user.email)).thenThrow(new DuplicationException("Mocked duplication"));
    try {
      User persistedUser = userService.createUser(user);
      fail("Expects exception");
    }
    catch (DuplicationException de) {
      verify(userRepository, times(1)).findByEmail(user.email);
      verify(userRepository, times(0)).save(user);
      verify(passwordEncoder, times(0)).encode(anyString());
    }
  }
}