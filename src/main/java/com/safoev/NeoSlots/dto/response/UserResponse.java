package com.safoev.NeoSlots.dto.response;

import com.safoev.NeoSlots.domain.enumeration.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String phone,
        String fullName,
        String email,
        String telegramId,
        UserRole role,
        Boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
