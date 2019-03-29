package com.ambita.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ambita.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
  Event findByUid(String uid);
  Event findByActive(boolean active);

  @Query(value = "SELECT 1", nativeQuery = true)
  Integer pingDatabase();
}
