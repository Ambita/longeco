package com.ambita.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ambita.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

  List<Log> findByDateBetweenAndUserUsernameAndEventUid(LocalDate begin, LocalDate end, String username, String eventUid);
  Optional<Log> findByDateAndUserUsernameAndEventUid(LocalDate date, String username, String eventUid);
}