package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.ScheduleTemplateRequest;
import com.safoev.NeoSlots.dto.response.ScheduleTemplateResponse;
import com.safoev.NeoSlots.service.ScheduleTemplateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedule-templates")
@RequiredArgsConstructor
public class ScheduleTemplateController {

    private final ScheduleTemplateService service;

    @GetMapping
    public List<ScheduleTemplateResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ScheduleTemplateResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-master/{masterId}")
    public List<ScheduleTemplateResponse> getByMasterId(@PathVariable UUID masterId) {
        return service.findByMasterId(masterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleTemplateResponse create(@Valid @RequestBody ScheduleTemplateRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ScheduleTemplateResponse update(@PathVariable UUID id, @Valid @RequestBody ScheduleTemplateRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
