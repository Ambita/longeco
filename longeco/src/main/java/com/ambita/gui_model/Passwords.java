package com.ambita.gui_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passwords {

  private String password;
  private String repeatPassword;
  private String uid;

  public Passwords(String uid) {
    this.uid = uid;
  }
}