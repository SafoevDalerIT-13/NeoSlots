package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.Company;
import com.safoev.NeoSlots.domain.entity.Service;
import com.safoev.NeoSlots.dto.request.ServiceRequest;
import com.safoev.NeoSlots.dto.response.ServiceResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.CompanyRepository;
import com.safoev.NeoSlots.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceEntityService {

    private final ServiceRepository repository;
    private final CompanyRepository companyRepository;

    public List<ServiceResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public ServiceResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Услуга", id)));
    }

    public List<ServiceResponse> findByCompanyId(UUID companyId) {
        return repository.findByCompanyId(companyId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public ServiceResponse create(ServiceRequest request) {
        var company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Компания", request.companyId()));
        var service = new Service();
        service.setCompany(company);
        service.setName(request.name());
        service.setDescription(request.description());
        service.setDurationMinutes(request.durationMinutes());
        service.setPrice(request.price());
        return toResponse(repository.save(service));
    }

    @Transactional
    public ServiceResponse update(UUID id, ServiceRequest request) {
        var service = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Услуга", id));
        var company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Компания", request.companyId()));
        service.setCompany(company);
        service.setName(request.name());
        service.setDescription(request.description());
        service.setDurationMinutes(request.durationMinutes());
        service.setPrice(request.price());
        return toResponse(repository.save(service));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Услуга", id);
        }
        repository.deleteById(id);
    }

    private ServiceResponse toResponse(Service s) {
        return new ServiceResponse(
                s.getId(), s.getCompany().getId(), s.getName(),
                s.getDescription(), s.getDurationMinutes(), s.getPrice(),
                s.getIsActive(), s.getCreatedAt());
    }
}
