package com.ambita.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ambita.model.Event;
import com.ambita.model.Log;
import com.ambita.model.User;
import com.ambita.repository.EventRepository;
import com.ambita.repository.LogRepository;
import com.ambita.repository.UserRepository;

import static com.ambita.util.DateUtil.getDatesFromWeek;
import static java.util.stream.Collectors.toList;

@Service
public class LogServiceImpl implements LogService {

  private LogRepository logRepository;
  private UserRepository userRepository;
  private Event event;

  @Autowired
  public LogServiceImpl(LogRepository logRepository, EventRepository eventRepository, UserRepository userRepository) {
    this.logRepository = logRepository;
    this.userRepository = userRepository;
    this.event = eventRepository.findByActive(true);
  }

  @Override
  public List<Log> getWeek(int week, int year, String username) {

    User user = userRepository.findByUsername(username);

    List<LocalDate> dates = getDatesFromWeek(week, year);

    List<LocalDate> eventDates = removeDatesOutsideEvent(dates, event);

    if (eventDates.size() < 1) {
      return Collections.emptyList();
    }

    List<Log> persistedLogs = logRepository.findByDateBetweenAndUserUsernameAndEventUid(eventDates.get(0), eventDates.get(eventDates.size() - 1), username, event.getUid());

    return eventDates
        .stream()
        .map(date ->
            persistedLogs
                .stream()
                .filter(persistedLog -> date.equals(persistedLog.getDate()))
                .findFirst()
                .orElse(new Log(date, event, user)))
        .collect(toList());
  }

  private List<LocalDate> removeDatesOutsideEvent(List<LocalDate> dates, Event event) {
    if (event == null) return new ArrayList<>();
    return dates.stream().filter(date -> (date.isAfter(event.getBegin()) && date.isBefore(event.getEnd())) || (date.isEqual(event.getBegin()) || date.isEqual(event.getEnd()))).collect(Collectors.toList());
  }

  @Override
  public void saveWeek(int week, int year, List<Log> logs, String username) {

    User user = userRepository.findByUsername(username);

    logs.forEach(log -> {
      log.setEvent(event);
      log.setUser(user);
      Log newLog = logRepository.findByDateAndUserUsernameAndEventUid(log.getDate(), username, event.getUid()).orElse(log);
      newLog.setDistance(log.getDistance());
      persistOrDelete(newLog);
    });
  }

  private void persistOrDelete(Log newLog) {
    if (newLog.getDistance() > 0) {
      logRepository.save(newLog);
      return;
    }

    if (newLog.getId() != null) {
      logRepository.delete(newLog);
    }
  }
}