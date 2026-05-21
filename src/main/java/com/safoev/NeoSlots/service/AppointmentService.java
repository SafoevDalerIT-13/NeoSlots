package com.safoev.NeoSlots.service;

import com.safoev.NeoSlots.domain.entity.Appointment;
import com.safoev.NeoSlots.dto.request.AppointmentRequest;
import com.safoev.NeoSlots.dto.response.AppointmentResponse;
import com.safoev.NeoSlots.exception.EntityNotFoundException;
import com.safoev.NeoSlots.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppointmentService {

    private final AppointmentRepository repository;
    private final UserRepository userRepository;
    private final MasterRepository masterRepository;
    private final ServiceRepository serviceRepository;
    private final CompanyRepository companyRepository;

    public List<AppointmentResponse> findAll() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public AppointmentResponse findById(UUID id) {
        return toResponse(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись", id)));
    }

    public List<AppointmentResponse> findByMasterId(UUID masterId) {
        return repository.findByMasterId(masterId).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<AppointmentResponse> findByClientId(UUID clientId) {
        return repository.findByClientId(clientId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public AppointmentResponse create(AppointmentRequest request) {
        var client = userRepository.findById(request.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Клиент", request.clientId()));
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        var service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new EntityNotFoundException("Услуга", request.serviceId()));
        var company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Компания", request.companyId()));
        var appointment = new Appointment();
        appointment.setClient(client);
        appointment.setMaster(master);
        appointment.setService(service);
        appointment.setCompany(company);
        appointment.setAppointmentDate(request.appointmentDate());
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.endTime());
        appointment.setClientPhone(request.clientPhone());
        appointment.setClientName(request.clientName());
        appointment.setPrice(request.price());
        return toResponse(repository.save(appointment));
    }

    @Transactional
    public AppointmentResponse update(UUID id, AppointmentRequest request) {
        var appointment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись", id));
        var client = userRepository.findById(request.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Клиент", request.clientId()));
        var master = masterRepository.findById(request.masterId())
                .orElseThrow(() -> new EntityNotFoundException("Мастер", request.masterId()));
        var service = serviceRepository.findById(request.serviceId())
                .orElseThrow(() -> new EntityNotFoundException("Услуга", request.serviceId()));
        var company = companyRepository.findById(request.companyId())
                .orElseThrow(() -> new EntityNotFoundException("Компания", request.companyId()));
        appointment.setClient(client);
        appointment.setMaster(master);
        appointment.setService(service);
        appointment.setCompany(company);
        appointment.setAppointmentDate(request.appointmentDate());
        appointment.setStartTime(request.startTime());
        appointment.setEndTime(request.endTime());
        appointment.setClientPhone(request.clientPhone());
        appointment.setClientName(request.clientName());
        appointment.setPrice(request.price());
        return toResponse(repository.save(appointment));
    }

    @Transactional
    public void cancel(UUID id, String reason) {
        var appointment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Запись", id));
        appointment.setStatus(Appointment.Status.CANCELLED);
        appointment.setCancelReason(reason);
        repository.save(appointment);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Запись", id);
        }
        repository.deleteById(id);
    }

    private AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
                a.getId(), a.getClient().getId(), a.getMaster().getId(),
                a.getService().getId(), a.getCompany().getId(),
                a.getAppointmentDate(), a.getStartTime(), a.getEndTime(),
                a.getClientPhone(), a.getClientName(),
                a.getStatus().name(), a.getCancelReason(),
                a.getPrice(), a.getCreatedAt(), a.getUpdatedAt());
    }
}
