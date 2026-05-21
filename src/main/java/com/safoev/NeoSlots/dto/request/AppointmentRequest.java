package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record AppointmentRequest(
        @NotNull UUID clientId,
        @NotNull UUID masterId,
        @NotNull UUID serviceId,
        @NotNull UUID companyId,
        @NotNull LocalDate appointmentDate,
        @NotNull LocalTime startTime,
        @NotNull LocalTime endTime,
        @NotBlank @Size(max = 20) String clientPhone,
        @Size(max = 255) String clientName,
        BigDecimal price
) {}
