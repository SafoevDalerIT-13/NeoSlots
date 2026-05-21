package com.safoev.NeoSlots.dto.response;

import java.time.LocalTime;
import java.util.UUID;

public record ScheduleTemplateResponse(
        UUID id,
        UUID masterId,
        Integer dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isActive
) {}
