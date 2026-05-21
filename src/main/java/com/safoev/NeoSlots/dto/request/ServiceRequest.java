package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public record ServiceRequest(
        @NotNull UUID companyId,
        @NotBlank @Size(max = 255) String name,
        String description,
        @NotNull Integer durationMinutes,
        BigDecimal price
) {}
