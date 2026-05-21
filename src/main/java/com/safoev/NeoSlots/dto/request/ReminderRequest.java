package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderRequest(
        @NotNull UUID appointmentId,
        @NotNull String type,
        @NotNull LocalDateTime scheduledAt
) {}
