package com.ambita.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ambita.model.PasswordReset;

@Repository
public interface PasswordResetRepository extends JpaRepository<PasswordReset, Long> {

  Optional<PasswordReset> findByUidAndCreatedBetween(String uid, LocalDateTime from, LocalDateTime to);
}