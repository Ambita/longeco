package com.ambita;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ambita.service.PasswordService;

import static junit.framework.TestCase.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class LongecoApplicationTests {

  @Autowired
  private
  PasswordService passwordService;

  @Test
  public void contextLoads() {
    passwordService.requestToSetNewPassword("test@gmail.com");
  }

  @Test
  public void encryptPassword(){
    String pw = "test";
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    String encodedPw = bCryptPasswordEncoder.encode(pw);
    assertEquals(pw, encodedPw);
  }
}