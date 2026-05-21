package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.ScheduleTemplate;
import com.safoev.NeoSlots.dto.request.ScheduleTemplateRequest;
import com.safoev.NeoSlots.dto.response.ScheduleTemplateResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.MasterRepository;
import com.safoev.NeoSlots.repository.ScheduleTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleTemplateService {

    private final ScheduleTemplateRepository repository;
    private final MasterRepository masterRepository;

    public List<ScheduleTemplateResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ScheduleTemplateResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Шаблон расписания", id)));
    }

    public List<ScheduleTemplateResponse> findByMasterId(UUID masterId) {
        return repository.findByMasterId(masterId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ScheduleTemplateResponse create(ScheduleTemplateRequest request) {
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        var template = new ScheduleTemplate();
        template.setMaster(master);
        template.setDayOfWeek(request.dayOfWeek());
        template.setStartTime(request.startTime());
        template.setEndTime(request.endTime());
        return toResponse(repository.save(template));
    }

    @Transactional
    public ScheduleTemplateResponse update(UUID id, ScheduleTemplateRequest request) {
        var template = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Шаблон расписания", id));
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        template.setMaster(master);
        template.setDayOfWeek(request.dayOfWeek());
        template.setStartTime(request.startTime());
        template.setEndTime(request.endTime());
        return toResponse(repository.save(template));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Шаблон расписания", id);
        }
        repository.deleteById(id);
    }

    private ScheduleTemplateResponse toResponse(ScheduleTemplate st) {
        return new ScheduleTemplateResponse(
                st.getId(), st.getMaster().getId(),
                st.getDayOfWeek(), st.getStartTime(), st.getEndTime(),
                st.getIsActive());
    }
}
