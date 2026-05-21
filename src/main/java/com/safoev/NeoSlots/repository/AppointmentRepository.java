package com.safoev.NeoSlots.repository;

import com.safoev.NeoSlots.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByClientId(UUID clientId);
    List<Appointment> findByMasterId(UUID masterId);
    List<Appointment> findByCompanyId(UUID companyId);
    List<Appointment> findByMasterIdAndAppointmentDate(UUID masterId, LocalDate appointmentDate);
    List<Appointment> findByAppointmentDateBetween(LocalDate from, LocalDate to);
}
