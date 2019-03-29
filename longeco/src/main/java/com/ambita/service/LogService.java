package com.ambita.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ambita.model.Log;

@Service
public interface LogService {

  List<Log> getWeek(int week, int year, String username);

  void saveWeek(int week, int year, List<Log> logs, String username);
}