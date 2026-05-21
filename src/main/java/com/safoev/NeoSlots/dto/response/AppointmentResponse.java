package com.safoev.NeoSlots.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentResponse(
        UUID id,
        UUID clientId,
        UUID masterId,
        UUID serviceId,
        UUID companyId,
        LocalDate appointmentDate,
        LocalTime startTime,
        LocalTime endTime,
        String clientPhone,
        String clientName,
        String status,
        String cancelReason,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
