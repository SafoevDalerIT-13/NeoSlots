package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.UUID;

public record ScheduleTemplateRequest(
        @NotNull UUID masterId,
        @NotNull Integer dayOfWeek,
        @NotNull LocalTime startTime,
        @NotNull LocalTime endTime
) {}
