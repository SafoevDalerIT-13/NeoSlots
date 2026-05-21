package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.AppointmentRequest;
import com.safoev.NeoSlots.dto.response.AppointmentResponse;
import com.safoev.NeoSlots.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping
    public List<AppointmentResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AppointmentResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-master/{masterId}")
    public List<AppointmentResponse> getByMasterId(@PathVariable UUID masterId) {
        return service.findByMasterId(masterId);
    }

    @GetMapping("/by-client/{clientId}")
    public List<AppointmentResponse> getByClientId(@PathVariable UUID clientId) {
        return service.findByClientId(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentResponse create(@Valid @RequestBody AppointmentRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public AppointmentResponse update(@PathVariable UUID id, @Valid @RequestBody AppointmentRequest request) {
        return service.update(id, request);
    }

    @PostMapping("/{id}/cancel")
    public AppointmentResponse cancel(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        service.cancel(id, body.get("reason"));
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
