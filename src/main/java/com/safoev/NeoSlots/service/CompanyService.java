package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.Company;
import com.safoev.NeoSlots.dto.request.CompanyRequest;
import com.safoev.NeoSlots.dto.response.CompanyResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

    private final CompanyRepository repository;

    public List<CompanyResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public CompanyResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Компания", id)));
    }

    @Transactional
    public CompanyResponse create(CompanyRequest request) {
        var company = new Company();
        company.setName(request.name());
        company.setAddress(request.address());
        company.setPhone(request.phone());
        company.setEmail(request.email());
        return toResponse(repository.save(company));
    }

    @Transactional
    public CompanyResponse update(UUID id, CompanyRequest request) {
        var company = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Компания", id));
        company.setName(request.name());
        company.setAddress(request.address());
        company.setPhone(request.phone());
        company.setEmail(request.email());
        return toResponse(repository.save(company));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Компания", id);
        }
        repository.deleteById(id);
    }

    private CompanyResponse toResponse(Company c) {
        return new CompanyResponse(
                c.getId(), c.getName(), c.getAddress(),
                c.getPhone(), c.getEmail(),
                c.getSettings(), c.getCreatedAt());
    }
}
