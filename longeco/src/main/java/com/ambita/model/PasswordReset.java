package com.ambita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import lombok.Data;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class PasswordReset {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String uid;

  @Column(nullable = false)
  private LocalDateTime created;

  @ManyToOne(optional = false)
  private User user;
}