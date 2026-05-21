package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.ScheduleException;
import com.safoev.NeoSlots.dto.request.ScheduleExceptionRequest;
import com.safoev.NeoSlots.dto.response.ScheduleExceptionResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.MasterRepository;
import com.safoev.NeoSlots.repository.ScheduleExceptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleExceptionService {

    private final ScheduleExceptionRepository repository;
    private final MasterRepository masterRepository;

    public List<ScheduleExceptionResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ScheduleExceptionResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Исключение расписания", id)));
    }

    public List<ScheduleExceptionResponse> findByMasterId(UUID masterId) {
        return repository.findByMasterId(masterId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ScheduleExceptionResponse create(ScheduleExceptionRequest request) {
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        var exception = new ScheduleException();
        exception.setMaster(master);
        exception.setExceptionDate(request.exceptionDate());
        exception.setIsAvailable(request.isAvailable());
        exception.setStartTime(request.startTime());
        exception.setEndTime(request.endTime());
        exception.setReason(request.reason());
        return toResponse(repository.save(exception));
    }

    @Transactional
    public ScheduleExceptionResponse update(UUID id, ScheduleExceptionRequest request) {
        var exception = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Исключение расписания", id));
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        exception.setMaster(master);
        exception.setExceptionDate(request.exceptionDate());
        exception.setIsAvailable(request.isAvailable());
        exception.setStartTime(request.startTime());
        exception.setEndTime(request.endTime());
        exception.setReason(request.reason());
        return toResponse(repository.save(exception));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Исключение расписания", id);
        }
        repository.deleteById(id);
    }

    private ScheduleExceptionResponse toResponse(ScheduleException se) {
        return new ScheduleExceptionResponse(
                se.getId(), se.getMaster().getId(),
                se.getExceptionDate(), se.getIsAvailable(),
                se.getStartTime(), se.getEndTime(), se.getReason());
    }
}
