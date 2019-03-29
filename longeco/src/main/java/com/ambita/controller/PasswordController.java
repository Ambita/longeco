package com.ambita.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ambita.gui_model.RequestPasswordEmail;
import com.ambita.gui_model.Passwords;
import com.ambita.model.PasswordReset;
import com.ambita.service.PasswordService;

@Controller
public class PasswordController {

  private PasswordService passwordService;

  @GetMapping("/resetpassword/{uid}")
  public String resetPasswordForm(Model model, @PathVariable("uid") String uid) {
    Optional<PasswordReset> passwordResetOptional = passwordService.findPasswordResetByUid(uid);

    if (passwordResetOptional.isPresent()) {
      Passwords passwords = new Passwords(uid);
      model.addAttribute("passwords", passwords);
      return "resetpassword";
    }
    else {
      model.addAttribute("message", "Not valid link for resetting password");
      return "error";
    }
  }

  @PostMapping("/resetpassword")
  public String resetPassword(@ModelAttribute Passwords passwords) {
    passwordService.resetPassword(passwords);
    return "redirect:/login";
  }

  @GetMapping("/requestpassword")
  public String requestPasswordForm(Model model) {
    model.addAttribute("email", new RequestPasswordEmail());
    return "requestpassword";
  }

  @PostMapping(value = {"/requestpassword"})
  public String requestPassword(@ModelAttribute RequestPasswordEmail email) {
    passwordService.requestToSetNewPassword(email.getEmail());
    return "redirect:/login";
  }

  @Autowired
  public void setPasswordService(PasswordService passwordService) {
    this.passwordService = passwordService;
  }
}