package com.ambita.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambita.model.report.UserDayProgress;
import com.ambita.model.report.UserSum;
import com.ambita.repository.ReportRepository;

@Service
public class ReportService {

  private ReportRepository reportQuery;

  @Autowired
  public ReportService(ReportRepository reportQuery) {
    this.reportQuery = reportQuery;
  }

  public String getEventName() {
    return reportQuery.getEventName();
  }

  public String getRangeText() {
    return reportQuery.getRangeText();
  }

  public List<UserSum> getUsersSummary() {
    return reportQuery.getUsersSummary();
  }

  public List<UserDayProgress> getUsersProgress() {
    return reportQuery.getUsersProgress();
  }
}
