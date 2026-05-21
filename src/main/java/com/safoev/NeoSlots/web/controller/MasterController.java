package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.MasterRequest;
import com.safoev.NeoSlots.dto.response.MasterResponse;
import com.safoev.NeoSlots.service.MasterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/masters")
@RequiredArgsConstructor
public class MasterController {

    private final MasterService service;

    @GetMapping
    public List<MasterResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MasterResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-company/{companyId}")
    public List<MasterResponse> getByCompanyId(@PathVariable UUID companyId) {
        return service.findByCompanyId(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MasterResponse create(@Valid @RequestBody MasterRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public MasterResponse update(@PathVariable UUID id, @Valid @RequestBody MasterRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
