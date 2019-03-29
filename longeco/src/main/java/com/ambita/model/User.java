package com.ambita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "\"user\"")
public class User {

  public User() {
  }

  @Id
  @GeneratedValue(strategy = IDENTITY)
  public Long id;

  @Column(nullable = false, unique = true)
  @NotNull
  public String username;

  @Column(nullable = false)
  public String password;

  @Column(nullable = false, unique = true)
  public String email;

  @Column(nullable = false)
  public String name;

  @Column(nullable = false, columnDefinition = "boolean")
  public boolean active;

  public User(Long id, String username, String password, String email, String name, boolean active) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.name = name;
    this.active = active;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}