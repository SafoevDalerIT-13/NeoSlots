package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.MasterService;
import com.safoev.NeoSlots.dto.request.MasterServiceRequest;
import com.safoev.NeoSlots.dto.response.MasterServiceResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.MasterRepository;
import com.safoev.NeoSlots.repository.MasterServiceRepository;
import com.safoev.NeoSlots.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MasterServiceEntityService {

    private final MasterServiceRepository repository;
    private final MasterRepository masterRepository;
    private final ServiceRepository serviceRepository;

    public List<MasterServiceResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public MasterServiceResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Услуга мастера", id)));
    }

    public List<MasterServiceResponse> findByMasterId(UUID masterId) {
        return repository.findByMasterId(masterId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public MasterServiceResponse create(MasterServiceRequest request) {
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        var service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new EntityNotFoundException("Услуга", request.serviceId()));
        var ms = new MasterService();
        ms.setMaster(master);
        ms.setService(service);
        ms.setPrice(request.price());
        ms.setDurationMinutes(request.durationMinutes());
        return toResponse(repository.save(ms));
    }

    @Transactional
    public MasterServiceResponse update(UUID id, MasterServiceRequest request) {
        var ms = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Услуга мастера", id));
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        var service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new EntityNotFoundException("Услуга", request.serviceId()));
        ms.setMaster(master);
        ms.setService(service);
        ms.setPrice(request.price());
        ms.setDurationMinutes(request.durationMinutes());
        return toResponse(repository.save(ms));
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Услуга мастера", id);
        }
        repository.deleteById(id);
    }

    private MasterServiceResponse toResponse(MasterService ms) {
        return new MasterServiceResponse(
                ms.getId(), ms.getMaster().getId(), ms.getService().getId(),
                ms.getPrice(), ms.getDurationMinutes());
    }
}
