package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.ScheduleExceptionRequest;
import com.safoev.NeoSlots.dto.response.ScheduleExceptionResponse;
import com.safoev.NeoSlots.service.ScheduleExceptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/schedule-exceptions")
@RequiredArgsConstructor
public class ScheduleExceptionController {

    private final ScheduleExceptionService service;

    @GetMapping
    public List<ScheduleExceptionResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ScheduleExceptionResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-master/{masterId}")
    public List<ScheduleExceptionResponse> getByMasterId(@PathVariable UUID masterId) {
        return service.findByMasterId(masterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleExceptionResponse create(@Valid @RequestBody ScheduleExceptionRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ScheduleExceptionResponse update(@PathVariable UUID id, @Valid @RequestBody ScheduleExceptionRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
