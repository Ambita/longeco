package com.ambita.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id", "event_id"})})
@NoArgsConstructor
@AllArgsConstructor
public class Log {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  private String comment;

  @Column(nullable = false)
  private Integer distance;

  private Integer minutes;

  @ManyToOne(optional = false)
  private User user;

  @ManyToOne(optional = false)
  private Event event;

  public Log(LocalDate date, Event event, User user) {
    this.event = event;
    this.user = user;
    this.date = date;
    this.distance = 0;
    this.minutes = 0;
  }
}