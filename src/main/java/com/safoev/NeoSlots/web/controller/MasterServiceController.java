package com.safoev.NeoSlots.web.controller;

import com.safoev.NeoSlots.dto.request.MasterServiceRequest;
import com.safoev.NeoSlots.dto.response.MasterServiceResponse;
import com.safoev.NeoSlots.service.MasterServiceEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/master-services")
@RequiredArgsConstructor
public class MasterServiceController {

    private final MasterServiceEntityService service;

    @GetMapping
    public List<MasterServiceResponse> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public MasterServiceResponse getById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("/by-master/{masterId}")
    public List<MasterServiceResponse> getByMasterId(@PathVariable UUID masterId) {
        return service.findByMasterId(masterId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MasterServiceResponse create(@Valid @RequestBody MasterServiceRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public MasterServiceResponse update(@PathVariable UUID id, @Valid @RequestBody MasterServiceRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
