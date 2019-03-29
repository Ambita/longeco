package com.ambita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import lombok.Data;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
public class Event {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String uid;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private LocalDate begin;

  @Column(name = "\"end\"", nullable = false)
  private LocalDate end;

  @Column(nullable = false)
  private boolean active = false;
}