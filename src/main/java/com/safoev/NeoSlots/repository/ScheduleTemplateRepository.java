package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.ScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleTemplateRepository extends JpaRepository<ScheduleTemplate, UUID> {
    List<ScheduleTemplate> findByMasterId(UUID masterId);
    List<ScheduleTemplate> findByMasterIdAndDayOfWeek(UUID masterId, Integer dayOfWeek);
}
