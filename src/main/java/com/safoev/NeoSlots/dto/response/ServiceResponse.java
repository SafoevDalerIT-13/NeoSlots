package com.safoev.NeoSlots.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ServiceResponse(
        UUID id,
        UUID companyId,
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price,
        Boolean isActive,
        LocalDateTime createdAt
) {}
