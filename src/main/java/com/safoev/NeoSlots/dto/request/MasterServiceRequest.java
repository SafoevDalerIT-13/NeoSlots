package com.safoev.NeoSlots.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record MasterServiceRequest(
        @NotNull UUID masterId,
        @NotNull UUID serviceId,
        BigDecimal price,
        Integer durationMinutes
) {}
