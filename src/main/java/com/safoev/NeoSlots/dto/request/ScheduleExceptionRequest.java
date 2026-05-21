package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ScheduleExceptionRequest(
        @NotNull UUID masterId,
        @NotNull LocalDate exceptionDate,
        @NotNull Boolean isAvailable,
        LocalTime startTime,
        LocalTime endTime,
        String reason
) {}
