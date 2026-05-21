package com.safoev.NeoSlots.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReminderResponse(
        UUID id,
        UUID appointmentId,
        String type,
        String status,
        LocalDateTime scheduledAt,
        LocalDateTime sentAt,
        String errorMessage,
        LocalDateTime createdAt
) {}
