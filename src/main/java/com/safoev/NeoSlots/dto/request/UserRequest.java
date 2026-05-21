package com.safoev.NeoSlots.dto.request;

import com.safoev.NeoSlots.domain.enumeration.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank @Size(max = 20) String phone,
        @NotBlank String fullName,
        String email,
        @NotBlank String passwordHash,
        String telegramId,
        UserRole role
) {}
