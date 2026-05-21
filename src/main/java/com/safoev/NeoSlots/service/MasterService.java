package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.Master;
import com.safoev.NeoSlots.dto.request.MasterRequest;
import com.safoev.NeoSlots.dto.response.MasterResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.CompanyRepository;
import com.safoev.NeoSlots.repository.MasterRepository;
import com.safoev.NeoSlots.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MasterService {

    private final MasterRepository repository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public List<MasterResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public MasterResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Мастер", id)));
    }

    public List<MasterResponse> findByCompanyId(UUID companyId) {
        return repository.findByCompanyId(companyId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public MasterResponse create(MasterRequest request) {
        var user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь", request.userId()));
        var company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Компания", request.companyId()));
        var master = new Master();
        master.setUser(user);
        master.setCompany(company);
        master.setBio(request.bio());
        return toResponse(repository.save(master));
    }

    @Transactional
    public MasterResponse update(UUID id, MasterRequest request) {
        var master = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Мастер", id));
        var user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("Пользователь", request.userId()));
        var company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Компания", request.companyId()));
        master.setUser(user);
        master.setCompany(company);
        master.setBio(request.bio());
        return toResponse(repository.save(master));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Мастер", id);
        }
        repository.deleteById(id);
    }

    private MasterResponse toResponse(Master m) {
        return new MasterResponse(
                m.getId(), m.getUser().getId(), m.getCompany().getId(),
                m.getBio(), m.getIsActive(), m.getCreatedAt());
    }
}
