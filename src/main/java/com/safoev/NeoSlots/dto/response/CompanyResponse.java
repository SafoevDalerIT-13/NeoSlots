package com.safoev.NeoSlots.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record CompanyResponse(
        UUID id,
        String name,
        String slug,
        String address,
        String phone,
        String email,
        String timezone,
        Map<String, Object> settings,
        LocalDateTime createdAt
) {}
