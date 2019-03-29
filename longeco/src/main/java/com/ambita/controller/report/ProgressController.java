package com.ambita.controller.report;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ambita.controller.api.report.ProgressReport;
import com.ambita.mapper.ProgressReportMapper;
import com.ambita.model.report.UserDayProgress;
import com.ambita.service.ReportService;

@RestController
public class ProgressController {

  private ReportService reportService;

  public ProgressController(ReportService reportService) {
    this.reportService = reportService;
  }

  @GetMapping("/reports/progress")
  public ProgressReport get() {
    List<UserDayProgress> progresses = reportService.getUsersProgress();
    return ProgressReportMapper.map(progresses);
  }
}
