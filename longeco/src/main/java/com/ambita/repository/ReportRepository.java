package com.ambita.repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ambita.model.Event;
import com.ambita.model.report.UserDayProgress;
import com.ambita.model.report.UserSum;
import com.ambita.util.DateUtil;

@Service
public class ReportRepository {

  private JdbcTemplate jdbcTemplate;
  private String currentEventUid = "0";
  private Event activeEvent;

  @Autowired
  public ReportRepository(EventRepository eventRepository) {
    activeEvent = eventRepository.findByActive(true);
    if (activeEvent != null) {
      this.currentEventUid = activeEvent.getUid();
    }
  }

  @Autowired
  public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public List<UserSum> getUsersSummary() {
    return getSummary("distance desc");
  }

  private List<UserSum> getSummary(String orderBy) {
    return jdbcTemplate.query("select " +
            "sum(l.distance) as distance, " +
            "count(*) as count_days, " +
            "sum(l.distance)/count(*) as distance_avg_day, " +
            "max(date) as last_date, " +
            "u.name as name " +
            "from log l " +
            "inner join event e on l.event_id = e.id and e.uid = '" + currentEventUid + "' " +
            "inner join \"user\" u on l.user_id  = u.id " +
            "group by u.id order by " + orderBy
        , (rs, rowNum) -> new UserSum(rs.getString("name"), rs.getInt("distance"), rs.getInt("count_days"), rs.getInt("distance_avg_day"), rs.getDate("last_date").toLocalDate())
    );
  }

  public String getEventName() {
    if (activeEvent != null) {
      return activeEvent.getName();
    }
    return "Ingen aktive aksjoner";
  }

  public String getRangeText() {
    if (activeEvent != null) {
      return DateUtil.formatForPresentation(activeEvent.getBegin()) + " - " + DateUtil.formatForPresentation(activeEvent.getEnd());
    }
    return "";
  }

  public List<UserDayProgress> getUsersProgress() {
    if (activeEvent == null) {
      return Collections.emptyList();
    }

    return jdbcTemplate.query("select " +
            "U.id userId, " +
            "U.name userName, " +
            "L.date, " +
            "L.distance distanceDay, " +
            "sum(L.distance) OVER (partition by U.id ORDER BY date)  distianceSoFar " +
            "from public.USER U " +
            "join log L on L.user_id = U.id " +
            "where L.event_id =" + activeEvent.getId() + " " +
            "order by U.name,L.date"

        , (rs, rowNum) -> new UserDayProgress(rs.getInt("userId"), rs.getString("userName"), rs.getDate("date").toLocalDate(), rs.getInt("distanceDay"), rs.getInt("distianceSoFar"))
    );
  }
}