package com.safoev.NeoSlots.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record MasterServiceResponse(
        UUID id,
        UUID masterId,
        UUID serviceId,
        BigDecimal price,
        Integer durationMinutes
) {}
