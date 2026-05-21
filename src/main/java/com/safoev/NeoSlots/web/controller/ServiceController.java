package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.ServiceRequest;
import com.safoev.NeoSlots.dto.response.ServiceResponse;
import com.safoev.NeoSlots.service.ServiceEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceEntityService service;

    @GetMapping
    public List<ServiceResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ServiceResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-company/{companyId}")
    public List<ServiceResponse> getByCompanyId(@PathVariable UUID companyId) {
        return service.findByCompanyId(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceResponse create(@Valid @RequestBody ServiceRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ServiceResponse update(@PathVariable UUID id, @Valid @RequestBody ServiceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
