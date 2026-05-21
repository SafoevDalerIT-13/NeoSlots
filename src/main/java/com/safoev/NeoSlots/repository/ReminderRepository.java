package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ReminderRepository extends JpaRepository<Reminder, UUID> {
    List<Reminder> findByAppointmentId(UUID appointmentId);
    List<Reminder> findByStatus(Reminder.Status status);
    List<Reminder> findByStatusAndScheduledAtBefore(Reminder.Status status, LocalDateTime before);
}
