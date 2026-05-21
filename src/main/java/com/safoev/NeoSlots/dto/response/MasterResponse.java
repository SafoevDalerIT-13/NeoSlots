package com.safoev.NeoSlots.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record MasterResponse(
        UUID id,
        UUID userId,
        UUID companyId,
        String bio,
        Boolean isActive,
        LocalDateTime createdAt
) {}
