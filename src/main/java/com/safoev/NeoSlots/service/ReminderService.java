package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.Reminder;
import com.safoev.NeoSlots.dto.request.ReminderRequest;
import com.safoev.NeoSlots.dto.response.ReminderResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.AppointmentRepository;
import com.safoev.NeoSlots.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReminderService {

    private final ReminderRepository repository;
    private final AppointmentRepository appointmentRepository;

    public List<ReminderResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ReminderResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Напоминание", id)));
    }

    public List<ReminderResponse> findByAppointmentId(UUID appointmentId) {
        return repository.findByAppointmentId(appointmentId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ReminderResponse create(ReminderRequest request) {
        var appointment = appointmentRepository.findById(request.appointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Запись", request.appointmentId()));
        var reminder = new Reminder();
        reminder.setAppointment(appointment);
        reminder.setType(Reminder.Type.valueOf(request.type()));
        reminder.setScheduledAt(request.scheduledAt());
        return toResponse(repository.save(reminder));
    }

    @Transactional
    public void markSent(UUID id) {
        var reminder = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Напоминание", id));
        reminder.setStatus(Reminder.Status.SENT);
        reminder.setSentAt(LocalDateTime.now());
        repository.save(reminder);
    }

    @Transactional
    public void markFailed(UUID id, String error) {
        var reminder = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Напоминание", id));
        reminder.setStatus(Reminder.Status.FAILED);
        reminder.setErrorMessage(error);
        repository.save(reminder);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Напоминание", id);
        }
        repository.deleteById(id);
    }

    private ReminderResponse toResponse(Reminder r) {
        return new ReminderResponse(
                r.getId(), r.getAppointment().getId(),
                r.getType().name(), r.getStatus().name(),
                r.getScheduledAt(), r.getSentAt(),
                r.getErrorMessage(), r.getCreatedAt());
    }
}
