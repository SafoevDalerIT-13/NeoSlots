package com.safoev.NeoSlots.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record ScheduleExceptionResponse(
        UUID id,
        UUID masterId,
        LocalDate exceptionDate,
        Boolean isAvailable,
        LocalTime startTime,
        LocalTime endTime,
        String reason
) {}
