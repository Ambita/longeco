package com.ambita.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ambita.gui_model.Passwords;
import com.ambita.model.PasswordReset;
import com.ambita.model.User;
import com.ambita.repository.PasswordResetRepository;
import com.ambita.repository.UserRepository;

import static java.time.LocalDateTime.now;

@Service
public class PasswordServiceImpl implements PasswordService {

  private PasswordResetRepository passwordResetRepository;
  private UserRepository userRepository;
  private EmailService emailService;
  @Value("${longeco.baseUrl}")
  private String baseUrl;
  private PasswordEncoder passwordEncoder;

  @Override
  public void requestToSetNewPassword(String email) {
    userRepository
        .findByEmail(email)
        .filter(u -> u.active)
        .ifPresent(user -> sendResetEmail(user, createResetPasswordUrl(user)));
  }

  @Override
  public Optional<PasswordReset> findPasswordResetByUid(String uid) {
    return passwordResetRepository.findByUidAndCreatedBetween(uid, now().minusHours(1), now());
  }

  @Transactional
  public void resetPassword(Passwords passwords) {

    if (passwords.getPassword().equals(passwords.getRepeatPassword())) {
      this
          .findPasswordResetByUid(passwords.getUid())
          .ifPresent(passwordReset -> passwordReset
              .getUser()
              .setPassword(passwordEncoder.encode(passwords.getPassword())));
    }
  }

  private String createResetPasswordUrl(User user) {
    PasswordReset passwordReset = new PasswordReset();
    passwordReset.setCreated(now());
    passwordReset.setUser(user);
    passwordReset.setUid(UUID.randomUUID().toString());
    passwordResetRepository.save(passwordReset);
    return passwordReset.getUid();
  }

  private void sendResetEmail(User user, String resetPasswordUid) {
    emailService.setSubject("Reset/set Longeco password");
    emailService.setTo(user.email);
    emailService.setText(
        "Dear " + user.name
            + ", here is a link for you, to set/reset your password."
            + "We do not store the raw password or an encrypted password. The link is just valid for about a hour."
            + "The link to set/reset password: " + baseUrl + "resetpassword/" + resetPasswordUid);
    emailService.send();
  }

  @Autowired
  public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Autowired
  public void setPasswordResetRepository(PasswordResetRepository passwordResetRepository) {
    this.passwordResetRepository = passwordResetRepository;
  }

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setEmailService(EmailService emailService) {
    this.emailService = emailService;
  }
}