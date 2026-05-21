package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.ReminderRequest;
import com.safoev.NeoSlots.dto.response.ReminderResponse;
import com.safoev.NeoSlots.service.ReminderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService service;

    @GetMapping
    public List<ReminderResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ReminderResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-appointment/{appointmentId}")
    public List<ReminderResponse> getByAppointmentId(@PathVariable UUID appointmentId) {
        return service.findByAppointmentId(appointmentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderResponse create(@Valid @RequestBody ReminderRequest request) {
        return service.create(request);
    }

    @PostMapping("/{id}/sent")
    public ReminderResponse markSent(@PathVariable UUID id) {
        service.markSent(id);
        return service.findById(id);
    }

    @PostMapping("/{id}/failed")
    public ReminderResponse markFailed(@PathVariable UUID id, @RequestBody String error) {
        service.markFailed(id, error);
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
