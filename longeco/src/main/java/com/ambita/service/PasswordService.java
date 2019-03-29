package com.ambita.service;

import java.util.Optional;

import com.ambita.gui_model.Passwords;
import com.ambita.model.PasswordReset;

public interface PasswordService {

  void requestToSetNewPassword(String email);

  Optional<PasswordReset> findPasswordResetByUid(String uid);

  void resetPassword(Passwords passwords);
}