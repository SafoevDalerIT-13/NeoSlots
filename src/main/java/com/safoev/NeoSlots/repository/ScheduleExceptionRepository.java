package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.ScheduleException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ScheduleExceptionRepository extends JpaRepository<ScheduleException, UUID> {
    List<ScheduleException> findByMasterId(UUID masterId);
    List<ScheduleException> findByMasterIdAndExceptionDate(UUID masterId, LocalDate exceptionDate);
    List<ScheduleException> findByMasterIdAndExceptionDateBetween(UUID masterId, LocalDate from, LocalDate to);
}
